/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.clientezookeepereleccion;

import org.apache.zookeeper.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sadaga
 */
public class ClienteZooKeeperEleccion implements Watcher {

    //Se especifica la dirección y el puerto donde se ubica el servidor
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    //Se especifica el timeout en ms
    //EL servidor verifica la comunicación con el cliente y si excede 3s
    //se asume que el cliente se ha desconectado
    private static final int SESSION_TIMEOUT = 3000;
    //Se debe instanciar un objeto de tipo ZooKeeper para interactuar con el
    //servidor
    private ZooKeeper zooKeeper;
    private static final String ELECTION_NAMESPACE = "/eleccion";
    private String currentZnodeName;

    /*
    -La máquina virtual de java se encarga del manejo de excepciones
    -Interrupted exception nos permite trabajar con eventos sobre los hilos de
    zookeeper, mientras que KeeperException maneja las excepciones del sevidor
     */
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //Se crea una instancia de nuestra clase que tiene la interfaz watcher
        ClienteZooKeeperEleccion clienteBasico = new ClienteZooKeeperEleccion();
        //Se llama al método connectToZookeeper
        clienteBasico.connectToZookeeper();
        clienteBasico.voluntarioParaLiderazgo();
        clienteBasico.liderElecto();
        clienteBasico.run();
        clienteBasico.close();
        System.out.println("Desconectado del servidor Zookeeper. Terminando la aplicación cliente.");
    }

    public void connectToZookeeper() throws IOException {
        /*
        Se instancia un objeto Zookeeper con la ubicación del servidor
        y el timeout. Con esto se crean los hilos de E/S y los hilos de eventos
         */
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
    }

    private void run() throws InterruptedException {
        /*
        Se utiliza synchronized el cual permite solo a un hilo del objeto
        Zookeeper ejecturar la instrucción wait
         */
        synchronized (zooKeeper) {
            /*
            El método wait dejará al hilo en estado de espera hasta que llegue
            una notificación, en este caso hasta que se cierre la conexión
             */
            zooKeeper.wait();
        }
    }

    private void close() throws InterruptedException {
        this.zooKeeper.close();
    }

    @Override
    /*
    Recibe los eventos mediante objetos de tipo WatchedEvent mediante
    el método getType().
     */
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            /*
            Los eventos de desconexión y conexión no tienen un tipo, por lo que
            para estos eventos, el método getType devuelve None.
             */
            case None:
                /*
                Para obtener el estado del evento se usa el método getState()
                y en el caso de que el estado del evento corresponda a
                Event.KeeperState.SyncConnected significa que el cliente está
                conectado.
                 */
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Conectado exitosamente a Zookeeper");
                    //En caso contrario se notifica que está desconectado.
                } else {
                    synchronized (zooKeeper) {
                        System.out.println("Desconectando de Zookeeper...");
                        /*
                        mediante el método notinyAll() se despiertan todos los
                        hilos que están en estado de espera. EN este caso el 
                        hilo que está en estado de espera es el hilo principal.
                         */
                        zooKeeper.notifyAll();
                    }
                }
        }
    }

    //Permite elegir un nodo z para que sea coordinardor
    public void voluntarioParaLiderazgo() throws KeeperException, InterruptedException {
        String znodePrefix = ELECTION_NAMESPACE + "/c_";
        /*
        Crea un nodo z en el path especificado como primer argumento,
        Como segundo parámetro, los datos que queremos incluir como datos del nodo
        El tercer parámetro es una lista de control de acceso
        El cuarto parámetro indica que el nodo será efímero y secuencial
        Finalmente se devuelve la ruta completa incluyendo el número del nodo
        z que se acaba de crear.
         */
        String znodeFullPath = zooKeeper.create(znodePrefix, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Nombre del znode: " + znodeFullPath);
        //Aquí se guarda el nombre del nodo eliminando el path completo.
        this.currentZnodeName = znodeFullPath.replace("/eleccion/", "");
    }

    public void liderElecto() throws KeeperException, InterruptedException {
        /*
        Se obtiene una lista de los nodos hijos del nodo especificado dentro del
        primer parámetro.
        */
        List<String> children = zooKeeper.getChildren(ELECTION_NAMESPACE, false);
        //Se ordena la lista
        Collections.sort(children);
        //Se obtiene el primer elemento de la lista
        String smallestChild = children.get(0);
        /*
        Si el nodo que está ejecutando este código es igual al nodo con el id
        ás pequeño entonces este nodo es el lider
        */
        if (smallestChild.equals(currentZnodeName)) {
            System.out.println("Yo soy el lider");
            return;
        }
        System.out.println("Yo no soy el lider, " + smallestChild + " es el lider");
    }
}
