package networking;
import java.util.HashMap;
import java.util.Map;

public class Datos implements java.io.Serializable{
    
    public String ip, frase;
    public String[] libros;
    public int numero;
    public Map<String,Double> result;

    public Datos(String ip, String frase, String[] libros){
        this.ip = ip;
        this.frase = frase;
        this.libros = libros;
        result = new HashMap<String,Double>();
        numero = 0;
    }

    public void setResult(Map<String,Double> result){
        this.result = result;
    }

    public Map<String,Double> geResult(){
        return result;
    }

    public void addResult(Double key, String value){
        result.put(value, key);
    }

    public String getIP(){
        return this.ip;
    }

    public Double[] getKeys(){
        return result.keySet().toArray(new Double[0]);
    }

    @Override
    public String toString() {
        return result.toString();
    }

}
