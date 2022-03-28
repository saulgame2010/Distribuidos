import networking.WebClient;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Aggregator {
    
    //Instancia de un webCliente que se usara para realizar las peticiones
    private WebClient webClient;
    public Aggregator(){
        //Inicializa la instancia
        this.webClient = new WebClient();
    }
    //Este metodo envia las peticiones a los servidores, requiere las direcciones de los mismos asi como los parametros
    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks){
        //La clase CompletableFuture permite la programacion asincrona
        CompletableFuture<String>[] futures = new CompletableFuture[workersAddresses.size()];

        long ini = System.nanoTime();

        //Por cada servidor que tenemos
        for(int i = 0; i < workersAddresses.size(); i++){
            //Se obtiene su direccion
            String workerAddres = workersAddresses.get(i);
            //Y los parametros 
            String task = tasks.get(i);
            //Se obtienen los bytes de los parametros
            byte[] requestPayload = task.getBytes();
            //Se envia la peticion por medio del webClient
            futures[i]  = webClient.sendTask(workerAddres,requestPayload);
        }

        //Espera que terminen todas las tareas para devolver un arraylist con los resultados obtenidos
        List<String> results = Stream.of(futures).map(CompletableFuture::join).collect(Collectors.toList());

        long fin = System.nanoTime();

        System.out.println("El tiempo total fue de "+(fin-ini)+" nanosegundos");

        return results;
    }
}