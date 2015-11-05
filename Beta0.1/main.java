import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class main{
	public static void main(String[] args) {
		System.out.println("Hello World");


		try{
			BufferedReader file = new BufferedReader(new FileReader("grafo.txt"));
			String line;
			while((line = file.readLine()) != null ){
				String city1="",  city2="", distance= "";
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
					int km = Integer.parseInt(distance);		
				}
				catch(Exception e){
					System.out.println("Error 3: Distancia no valida");
					break;
				}
				
				System.out.println("\nCity 1: "+city1);
				System.out.println("City 2: "+city2);
				System.out.println("Distance: "+distance+"KM");
			}
			file.close();
		}
		catch(FileNotFoundException file){
			System.out.println("Error 1: Archivo diccionario.txt no encontrado.");
		}
		catch(IOException io){
			System.out.println("Erro 2: Error en lectura del fichero.");
		}
	}
}
