package networking;

import java.util.*;
import java.util.concurrent.BlockingQueue;

public class Hilos implements Runnable {
    Datos d;
    String ip;
    List<byte[]> result;
    public Map<Double, String> map;

    private final BlockingQueue<Datos> queue;

    public Hilos(Datos d, String ip, List<byte[]> result, BlockingQueue<Datos> queue) {
        this.d = d;
        this.ip = ip;
        this.result = result;
        this.queue = queue;
    }

    public void run() {

        try {

            System.out.println("Se envio la frase al servidor " + d.ip);

            Aggregator aggregator = new Aggregator();
            byte[] serializado = SerializationUtils.serialize(d);
            
            result = aggregator.sendTasksToWorkers(Arrays.asList(ip), Arrays.asList(serializado));

            for(byte[] dato : result){
                d = (Datos)SerializationUtils.deserialize(dato);
                queue.put(d);
            }

        }catch (Exception e) {
            System.out.println("Exception is caught");
            System.out.println(e.getMessage() + "\n" + e.getCause() + "\n" + e.getLocalizedMessage());
        }

    }
}
