
public class PruebaPoli {

    public static void main (String[] args) {

        PoligonoIrreg poli = new PoligonoIrreg();
        long fin = 0, ini = 0;

        //ini = System.nanoTime();

        for(int i = 0; i < 10; i++){
            poli.anadeVertice(new Coordenada(Math.random()*(200)-100,Math.random()*(200)-100));
        }

        System.out.println(poli.toString());

        poli.ordenar();

        System.out.println(poli.toString());

        /*fin = System.nanoTime();
        System.out.println("Tiempo empleado "+(fin-ini)+" ns");*/

    }

}
