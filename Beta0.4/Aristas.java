//Estructura de Datos
//Julio Gonzalez 14096
//Clase para Arista

public class Aristas {
    
    //Atributos 
    private Vertice origen;
    private Vertice destino;
    private double peso;
    
    //Constructor 
    public Aristas(Vertice origen, Vertice destino, double peso){
        this.origen=origen;
        this.destino=destino;
        this.peso=peso;
    }
    
	public void setOrigen(Vertice origen) {
        this.origen = origen;
    }
	
	  public Vertice getOrigen() {
        return origen;
    }

	
	 public void setDestino(Vertice destino) {
        this.destino = destino;
    }
    
    public Vertice getDestino() {
        return destino;
    }
	
	  public boolean equals(Aristas ar){
        if((origen.getNombre().equals(ar.getOrigen().getNombre()))&&(destino.getNombre().equals(ar.getDestino().getNombre()))){
            return true;
        }
        return false;
    }
	
    public void setPeso(float peso) {
        this.peso = peso;
    }
	
	  public double getPeso() {
        return peso;
    }
   
}
