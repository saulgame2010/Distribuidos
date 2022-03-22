import java.io.IOException;

/**
 * Ejemplo
 */
public class Ejemplo {

    public static void main(String[] args) throws IOException {

        Hilo fi_micro1 = new Hilo("34.125.12.105:80/search");
        Hilo fi_micro2 = new Hilo("34.125.12.105:80/search");
        Hilo fi_micro3 = new Hilo("34.125.12.105:80/search");
        Hilo fi_micro4 = new Hilo("34.125.12.105:80/search");

        fi_micro1.start();
        fi_micro2.start();
        fi_micro3.start();
        fi_micro4.start();
    }
}