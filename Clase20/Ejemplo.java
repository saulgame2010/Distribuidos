import java.io.IOException;

/**
 * Ejemplo
 */
public class Ejemplo {

    public static void main(String[] args) throws IOException {
        Ejemplo obj = new Ejemplo();
        obj.peticionF1Micro();
        // obj.peticionN1Standard1();
        // obj.peticionN1Standard2();
        // obj.peticionN1Standard4();
        // obj.peticionN1HighCPU2();
        // obj.peticionN1HighCPU2();
        // obj.peticionE2Micro();
        // obj.peticionE2HighCPU2();
        // obj.peticionE2HighCPU4();
    }

    public void peticionF1Micro() {
        long inicio, fin;
        Hilo f1_micro1 = new Hilo("34.125.12.105:80/search");
        Hilo f1_micro2 = new Hilo("34.125.12.105:80/search");
        Hilo f1_micro3 = new Hilo("34.125.12.105:80/search");
        Hilo f1_micro4 = new Hilo("34.125.12.105:80/search");

        inicio = System.nanoTime();
        f1_micro1.start();
        f1_micro2.start();
        f1_micro3.start();
        f1_micro4.start();
        try {
            f1_micro1.join();
            f1_micro2.join();
            f1_micro3.join();
            f1_micro4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a f1-micro tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionN1Standard1() {
        long inicio, fin;
        Hilo n1_standard1_1 = new Hilo("34.125.223.242:80/search");
        Hilo n1_standard1_2 = new Hilo("34.125.223.242:80/search");
        Hilo n1_standard1_3 = new Hilo("34.125.223.242:80/search");
        Hilo n1_standard1_4 = new Hilo("34.125.223.242:80/search");

        inicio = System.nanoTime();
        n1_standard1_1.start();
        n1_standard1_2.start();
        n1_standard1_3.start();
        n1_standard1_4.start();
        try {
            n1_standard1_1.join();
            n1_standard1_2.join();
            n1_standard1_3.join();
            n1_standard1_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a n1-standard-1 tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionN1Standard2() {
        long inicio, fin;
        Hilo n1_standard2_1 = new Hilo("34.125.96.56:80/search");
        Hilo n1_standard2_2 = new Hilo("34.125.96.56:80/search");
        Hilo n1_standard2_3 = new Hilo("34.125.96.56:80/search");
        Hilo n1_standard2_4 = new Hilo("34.125.96.56:80/search");

        inicio = System.nanoTime();
        n1_standard2_1.start();
        n1_standard2_2.start();
        n1_standard2_3.start();
        n1_standard2_4.start();
        try {
            n1_standard2_1.join();
            n1_standard2_2.join();
            n1_standard2_3.join();
            n1_standard2_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a n1-standard-2 tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionN1Standard4() {
        long inicio, fin;
        Hilo n1_standard4_1 = new Hilo("34.125.179.82:80/search");
        Hilo n1_standard4_2 = new Hilo("34.125.179.82:80/search");
        Hilo n1_standard4_3 = new Hilo("34.125.179.82:80/search");
        Hilo n1_standard4_4 = new Hilo("34.125.179.82:80/search");

        inicio = System.nanoTime();
        n1_standard4_1.start();
        n1_standard4_2.start();
        n1_standard4_3.start();
        n1_standard4_4.start();
        try {
            n1_standard4_1.join();
            n1_standard4_2.join();
            n1_standard4_3.join();
            n1_standard4_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a n1-standard-4 tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionN1HighCPU2() {
        long inicio, fin;
        Hilo n1_highcpu2_1 = new Hilo("34.125.48.210:80/search");
        Hilo n1_highcpu2_2 = new Hilo("34.125.48.210:80/search");
        Hilo n1_highcpu2_3 = new Hilo("34.125.48.210:80/search");
        Hilo n1_highcpu2_4 = new Hilo("34.125.48.210:80/search");

        inicio = System.nanoTime();
        n1_highcpu2_1.start();
        n1_highcpu2_2.start();
        n1_highcpu2_3.start();
        n1_highcpu2_4.start();
        try {
            n1_highcpu2_1.join();
            n1_highcpu2_2.join();
            n1_highcpu2_3.join();
            n1_highcpu2_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a n1-highcpu-2 tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionN1HighCPU4() {
        long inicio, fin;
        Hilo n1_highcpu4_1 = new Hilo("34.125.164.47:80/search");
        Hilo n1_highcpu4_2 = new Hilo("34.125.164.47:80/search");
        Hilo n1_highcpu4_3 = new Hilo("34.125.164.47:80/search");
        Hilo n1_highcpu4_4 = new Hilo("34.125.164.47:80/search");

        inicio = System.nanoTime();
        n1_highcpu4_1.start();
        n1_highcpu4_2.start();
        n1_highcpu4_3.start();
        n1_highcpu4_4.start();
        try {
            n1_highcpu4_1.join();
            n1_highcpu4_2.join();
            n1_highcpu4_3.join();
            n1_highcpu4_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a n1-highcpu-4 tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionE2Micro() {
        long inicio, fin;
        Hilo e2_micro_1 = new Hilo("34.125.84.203:80/search");
        Hilo e2_micro_2 = new Hilo("34.125.84.203:80/search");
        Hilo e2_micro_3 = new Hilo("34.125.84.203:80/search");
        Hilo e2_micro_4 = new Hilo("34.125.84.203:80/search");

        inicio = System.nanoTime();
        e2_micro_1.start();
        e2_micro_2.start();
        e2_micro_3.start();
        e2_micro_4.start();
        try {
            e2_micro_1.join();
            e2_micro_2.join();
            e2_micro_3.join();
            e2_micro_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a e2-micro tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionE2HighCPU2() {
        long inicio, fin;
        Hilo e2_highcpu2_1 = new Hilo("34.125.19.92:80/search");
        Hilo e2_highcpu2_2 = new Hilo("34.125.19.92:80/search");
        Hilo e2_highcpu2_3 = new Hilo("34.125.19.92:80/search");
        Hilo e2_highcpu2_4 = new Hilo("34.125.19.92:80/search");

        inicio = System.nanoTime();
        e2_highcpu2_1.start();
        e2_highcpu2_2.start();
        e2_highcpu2_3.start();
        e2_highcpu2_4.start();
        try {
            e2_highcpu2_1.join();
            e2_highcpu2_2.join();
            e2_highcpu2_3.join();
            e2_highcpu2_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a e2-highcpu-2 tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void peticionE2HighCPU4() {
        long inicio, fin;
        Hilo e2_highcpu4_1 = new Hilo("34.106.248.131:80/search");
        Hilo e2_highcpu4_2 = new Hilo("34.106.248.131:80/search");
        Hilo e2_highcpu4_3 = new Hilo("34.106.248.131:80/search");
        Hilo e2_highcpu4_4 = new Hilo("34.106.248.131:80/search");

        inicio = System.nanoTime();
        e2_highcpu4_1.start();
        e2_highcpu4_2.start();
        e2_highcpu4_3.start();
        e2_highcpu4_4.start();
        try {
            e2_highcpu4_1.join();
            e2_highcpu4_2.join();
            e2_highcpu4_3.join();
            e2_highcpu4_4.join();
            fin = System.nanoTime();
            System.out.println("Las 4 peticiones a e2-highcpu-4 tomaron " + (fin - inicio) + " nano segundos en ejecutarse");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}