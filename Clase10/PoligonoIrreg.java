import java.util.*;

public class PoligonoIrreg {
	
	private Coordenada[] vertices;
	private int i;

	public PoligonoIrreg(int nvertices){
		vertices = new Coordenada[nvertices];
	} 

	public void anadeVertice(Coordenada nvertice){
		vertices[i] = nvertice;
		i++;
		return;
	}

	@Override
	public String toString(){
		String result = "";
		for(int i = 0; i < vertices.length; i++){
			result = result + vertices[i].toString() + ",";
		}
		return result;
	}

}