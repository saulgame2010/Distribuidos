import java.util.Random;

/**
 * EjerciciosSerie3
 */
public class EjerciciosSerie3 {
    public static void main(String[] args) {
        long tiempoI = System.nanoTime();
        int asciiInicio = 65; // Este es el código ASCII de la 'A'
        int asciiFinal = 90; // Este es el código ASCII de la 'Z'
        int n = Integer.parseInt(args[0]);
        int index = 0, apariciones = 0;
        long tiempoF = 0, tiempoRes;
        Random random = new Random();
        StringBuilder cadenota = new StringBuilder(n*4);        
        for (int i = 0; i < n; i++) {            
            cadenota.append((char) (asciiInicio + (int) (random.nextFloat() * (asciiFinal - asciiInicio + 1))));
            cadenota.append((char) (asciiInicio + (int) (random.nextFloat() * (asciiFinal - asciiInicio + 1))));
            cadenota.append((char) (asciiInicio + (int) (random.nextFloat() * (asciiFinal - asciiInicio + 1))));
            //El 32 es el espacio en el código ASCII
            cadenota.append((char)32);
        }
        // Esta es la primer aparición de la cadena IPN por eso la variable apariciones = 1 en este punto        
        index = cadenota.indexOf("IPN");                       
        apariciones = 1;
        while(index >= 0) {
            // System.out.println(index);            
            index = cadenota.indexOf("IPN", index + 1);
            apariciones++;
        }
        tiempoF = System.nanoTime();
        tiempoRes = tiempoF - tiempoI;
        System.out.println("'IPN' se encontro " + apariciones + " veces en " + tiempoRes + " nano segundos");                
        // String generatedString = cadenota.toString();
        // System.out.println(generatedString);
    }
}