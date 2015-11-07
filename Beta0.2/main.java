import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class main{
	public static void main(String[] args) {
		Graph city = new GraphMatrixDirected(10);

		try{
			BufferedReader file = new BufferedReader(new FileReader("grafo.txt"));
			String line;
			int counter=0;
			while((line = file.readLine()) != null ){
				String city1="",  city2="", distance= "";
				int km=0;
				boolean read1=true, read2=false, read3=false;
				for(int i=0; i<line.length(); i++){
					if(line.charAt(i)==' '){
						if(read1==true){
							read1=false;
							read2=true;		
						}
						else{
							read2=false;
							read3=true;		
						}
						i++;
						if(line.length()==i)
							break;
					}
					if(read1==true)
						city1+= line.charAt(i);
					if(read2==true)
						city2+= line.charAt(i);
					if(read3==true)
						distance+= line.charAt(i);
				}
				try{
					km = Integer.parseInt(distance);		
				}
				catch(Exception e){
					System.out.println("Error 3: Distancia no valida");
					break;
				}
				System.out.println("\nCity 1: "+city1);
				city.add(city1);
				System.out.println("City 2: "+city2);
				city.add(city2);
				System.out.println("Distance: "+distance+"KM");
				city.addEdge(city1,city2,km);
				counter ++;
			}
			file.close();
		}
		catch(FileNotFoundException file){
			System.out.println("Error 1: Archivo diccionario.txt no encontrado.");
		}
		catch(IOException io){
			System.out.println("Error 2: Error en lectura del fichero.");
		}


	String inputcity = "Guatemala";
	VectorHeap heap = new VectorHeap();
	System.out.println("\n\n\nEstoy en "+city.get(inputcity)+"\n Y estos lugares estan:\n");
	//place neighbors of lab in into priority queue
	for(Iterator i=city.neighbors(inputcity); i.hasNext();){
	   Object nextcity = i.next();
	   Object distance = city.getEdge(inputcity, nextcity).label();
	   heap.add(new ComparableAssociation((Comparable)distance,nextcity));
	}

	//print out theaters in order of distance
	while(!heap.isEmpty()){
	   ComparableAssociation show = (ComparableAssociation)heap.remove();
	   System.out.println(show.getValue()+" is "+show.getKey()+" miles away.");
	}


	}
}

