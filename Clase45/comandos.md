ls / : Muestra los nodos que se encuentran en la raíz.
ls -w / : Indica que se va a crear un watcher para el nodo elegido, esto nos ayuda a monitorear a un nodo, el watcher funciona solo para un evento.
create / : Crea un árbol jerárquico de nodos.
create -e / : Crea un nodo efímero, es decir, un nodo que una vez que se deje de ejecutar el cliente de ZooKeeper se borran, a diferencia de los nodos creados con el comando create /, los cuales son persistentes.
create -e -s / : Crea un nodo efímero y secuencial.
get -s / : Obtiene la información del nodo dado.
quit: Sale del cliente de ZooKeeper.
set /zoo : Agrega datos de texto al nodo elegido, en este caso /zoo.
delete: Borra un nodo hoja, se debe indicar la ruta absoluta hacia ese nodo.
deleteall / : Borra todos los nodos de manera recursiva.
