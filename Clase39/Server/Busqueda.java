import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Busqueda {

    public List<DatosLibro> librosDatos;
    public String frase;
    public String[] fraseSeparada;
    public String[] libros;
    public int tam = 0;
    public int numeroDeArchivos;
    public int[] palabraEnLibro = {0};
    public double[] idf = {0.0};

    public Busqueda(String frase, String[] libros, int numeroDeArchivos){
        this.frase = frase;
        this.libros = libros;
        this.fraseSeparada = this.frase.split(" ");
        this.tam = fraseSeparada.length;
        this.numeroDeArchivos = numeroDeArchivos;
        librosDatos = new ArrayList<DatosLibro>();
        palabraEnLibro =  new int[tam];
        idf =  new double[tam];
    }

    public void metodoUno(){

        int libroSeleccionado = 0;
        for(libroSeleccionado = 0; libroSeleccionado<libros.length; libroSeleccionado++){
            try{

                File file = new File(libros[libroSeleccionado]);
                FileInputStream fis = new FileInputStream(file);
                byte[] bytesArray = new byte[(int)file.length()];
                fis.read(bytesArray);
                String s = new String(bytesArray, StandardCharsets.UTF_8);
                String [] data = s.split("[\\s\\p{Punct}\\p{Digit}]+");
                DatosLibro datos = new DatosLibro(libros[libroSeleccionado], fraseSeparada.length);

                for(int i = 0; i<fraseSeparada.length; i++){
                    for(int j = 0; j<data.length; j++){
                        if(data[j].equalsIgnoreCase(fraseSeparada[i])){
                            datos.setCuentaPalabra(i, datos.getCuentaPalabra(i)+1);
                        }
                    }
                }
                
                datos.setPalabrasTotales(data.length);
                
                fis.close();
                
                int cuentaTotal = 0;

                for(int i = 0; i < fraseSeparada.length; i++){
                    cuentaTotal += datos.getCuentaPalabra(i);
                }
                
                datos.setSumaTotalMetodoUno(cuentaTotal);

                librosDatos.add(datos);

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

        

    }

    public void metodoDos(){

        for(DatosLibro d : librosDatos){
            for(int i = 0; i<tam; i++){
                d.setFrecuenciaPalabra(i, ((float)d.getCuentaPalabra(i)/(float)d.getPalabrasTotales()));
            }
            for(int i = 0; i<tam; i++){
                d.setSumaTotalMetodoDos(d.getSumaTotalMetodoDos() + d.getFrecuenciaPalabra(i));
            }
        }

    }

    public void metodoTres(){

        for(DatosLibro d : librosDatos){
            for(int i = 0; i<tam; i++){
                if(d.getCuentaPalabra(i) != 0){
                    //d.setPalabraEnLibro(i, d.getPalabraEnLibro(i) + 1);
                    palabraEnLibro[i] += 1;
                }
            }
        }

        for(int i = 0; i<tam; i++){
            if(palabraEnLibro[i] != 0){
                idf[i] = Math.log10((double)numeroDeArchivos/(double)palabraEnLibro[i]);
            }else{
                idf[i] = 0;
            }
        }

        for(DatosLibro d: librosDatos){
            for(int i = 0; i<tam; i++){
                d.setSumaTotalMetodoTres(d.getSumaTotalMetodoTres() + (d.getFrecuenciaPalabra(i)*idf[i]));
            }
        }

    }

    public Map<String,Double> haceBusqueda(){
        metodoUno();
        metodoDos();
        metodoTres();

        Map<String,Double> map = new HashMap<String,Double>();
        for(DatosLibro d : librosDatos){
            map.put(d.getLibro(), d.getSumaTotalMetodoTres());
        }

        return map;
    }
    
}