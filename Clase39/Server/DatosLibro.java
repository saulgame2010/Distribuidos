public class DatosLibro {

    public int[] cuentasPalabra;
    public int palabrasTotales;
    public float[] tf;
    public String libro;
    public int sumaTotalMetodoUno;
    public float sumaTotalMetodoDos;
    public double sumaTotalMetodoTres;
    
    public DatosLibro(String libro, int fraseLenght){
        this.libro = libro;
        cuentasPalabra =  new int[fraseLenght];
        tf =  new float[fraseLenght];
        inicializaArreglos(fraseLenght);
        this.sumaTotalMetodoUno = 0;
        this.sumaTotalMetodoDos = 0;
        this.sumaTotalMetodoTres = 0;
    }

    public void inicializaArreglos(int tam){
        for(int i = 0; i<tam; i++){
            cuentasPalabra[i] = 0;
            tf[i] = 0;
        }
    }

    public String getLibro(){
        return this.libro;
    }

    public void setCuentaPalabra(int posicion, int total){
        cuentasPalabra[posicion] = total;
    }

    public int getCuentaPalabra(int posicion){
        return cuentasPalabra[posicion];
    }

    public void setPalabrasTotales(int total){
        this.palabrasTotales = total;
    }

    public int getPalabrasTotales(){
        return this.palabrasTotales;
    }

    public void setFrecuenciaPalabra(int posicion, float total){
        tf[posicion] = total;
    }

    public float getFrecuenciaPalabra(int posicion){
        return tf[posicion];
    }

    public void setLibro(String libro){
        this.libro = libro;
    }

    public void setSumaTotalMetodoUno(int sumaTotalMetodoUno){
        this.sumaTotalMetodoUno = sumaTotalMetodoUno;
    }

    public int getSumaTotalMetodoUno(){
        return this.sumaTotalMetodoUno;
    }

    public void setSumaTotalMetodoDos(float sumaTotalMetodoDos){
        this.sumaTotalMetodoDos = sumaTotalMetodoDos;
    }

    public float getSumaTotalMetodoDos(){
        return this.sumaTotalMetodoDos;
    }

    public void setSumaTotalMetodoTres(double sumaTotalMetodoTres){
        this.sumaTotalMetodoTres = sumaTotalMetodoTres;
    }

    public double getSumaTotalMetodoTres(){
        return this.sumaTotalMetodoTres;
    }

}
