
public class PruebaPoli {
    public static void main (String[] args) {
        Coordenada coordenadas = new Coordenada(0, 0);
        PoligonoIrreg poligono = new PoligonoIrreg(10000000);
        long tiempoI = 0, tiempoF = 0, tiempoR;
        tiempoI = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            poligono.anadeVertice(new Coordenada(Math.random(), Math.random()));
        }        
        tiempoF = System.nanoTime();
        tiempoR = tiempoF - tiempoI;
        System.out.println("El poligono tardo en hacerse: " + tiempoR + " nanosegundos");
    }
}