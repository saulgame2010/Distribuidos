import java.util.Arrays;
import java.util.List;
public class Application {
    
    //Se establecen las direcciones de los servidores asi como el endpoint que se va a consultar
    
    public static void main(String[] args){
        
        final String WORKER_ADDRESS_1 = "http://34.125."+args[0]+":80/searchipn";
        final String WORKER_ADDRESS_2 = "http://34.125."+args[1]+":80/searchipn";
        final String WORKER_ADDRESS_3 = "http://34.125."+args[2]+":80/searchipn";
        final String WORKER_ADDRESS_4 = "http://34.125."+args[3]+":80/searchipn";

        //Instancia del objeto aggregator
        Aggregator aggregator = new Aggregator();
        //Se establecen los parametros que se enviaran al servidor
        String task1 = "1757600,IPN";
        String task2 = "1757600,IPN";
        String task3 = "1757600,IPN";
        String task4 = "1757600,IPN";
        
        //Se envian todas las tareas a todos los trabajadores
        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(
            WORKER_ADDRESS_1,
            WORKER_ADDRESS_2,
            WORKER_ADDRESS_3,
            WORKER_ADDRESS_4), Arrays.asList(
            task1,
            task2,
            task3,
            task4)
        );
        //Imprime lo devuelto por aggregator
        for(String result : results){
            System.out.println(result);
        }
    }
}