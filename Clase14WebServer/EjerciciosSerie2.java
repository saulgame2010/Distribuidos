import java.util.Random;

public class EjerciciosSerie2 {
    public static void main(String[] args) {
        long tiempoI = System.nanoTime();
        int asciiInicio = 65; // Este es el código ASCII de la 'A'
        int asciiFinal = 90; // Este es el código ASCII de la 'Z'
        int n = Integer.parseInt(args[0]);
        int index = 0, apariciones = 0;
        long tiempoF = 0, tiempoRes;
        Random random = new Random();
        byte[] cadenota = new byte[n * 4];
        cadenota[index] = (byte) ((char) asciiInicio + (int) (random.nextFloat() * (asciiFinal - asciiInicio + 1)));
        for (int i = 0; i < n; i++) {
            cadenota[index++] = (byte) ((char) asciiInicio + (int) (random.nextFloat() * (asciiFinal - asciiInicio + 1)));
            cadenota[index++] = (byte) ((char) asciiInicio + (int) (random.nextFloat() * (asciiFinal - asciiInicio + 1)));
            cadenota[index++] = (byte) ((char) asciiInicio + (int) (random.nextFloat() * (asciiFinal - asciiInicio + 1)));
            cadenota[index++] = (byte) ((char) 32);
        }
        //ESTE ES EL CICLO QUE CUENTA LAS APARICIONES Y DONDE HAY PROBLEMAS PORQUE NO LAS CUENTA BIEN
        for (int i = 0; i < n; i++) {
            // if(Byte.compare(cadenota[i], (byte) ' ') == 0) {
            //     i++;
            // } else {
                if((Byte.compare(cadenota[i], (byte) 'I') == 0) && (Byte.compare(cadenota[i+1], (byte) 'P') == 0) && (Byte.compare(cadenota[i+2], (byte) 'N') == 0)) {
                    apariciones++;
                }
            // }
        }
        tiempoF = System.nanoTime();
        tiempoRes = tiempoF - tiempoI;
        System.out.println("'IPN' se encontro " + apariciones + " veces en " + tiempoRes + " nano segundos");        
        String cadena = new String(cadenota);
        System.out.println(cadena);
    }
}