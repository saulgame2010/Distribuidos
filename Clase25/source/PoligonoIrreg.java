import java.io.Serializable;
import java.util.*;

class PoligonoIrreg implements Serializable {
	
	private List<Coordenada> vertices;

	public PoligonoIrreg(){
		vertices = new ArrayList<Coordenada>();
		for(int i = 0; i < 3; i++){
            this.anadeVertice(new Coordenada(Math.random()*(200)-100,Math.random()*(200)-100));
        }
	} 

	public void anadeVertice(Coordenada nva){
		vertices.add(nva);
	}

	public Coordenada getVertice(int i){
		return vertices.get(i);
	}

	@Override
	public String toString(){
		String result = "";
		for(Coordenada c: vertices){
			result = result + c.toString() + ",\n";
		}
		return result;
	}

	public void ordenar(){
		Collections.sort(vertices, new Ordenamiento());

	}

}


class Ordenamiento implements Comparator<Coordenada> {
	@Override
    public int compare(Coordenada a, Coordenada b){
        return Double.compare(a.getDistancia(),b.getDistancia());
    }
}