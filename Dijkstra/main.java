import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;


public class main{
	public static void main(String[] args) {
		Edge [] edges = new Edge[13];
		City city = new City();
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
				System.out.println("City 2: "+city2);			
				System.out.println("Distance: "+km+"KM");
				edges[counter]=new Edge(city.getIndex(city1), city.getIndex(city2), km);	
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


		String origin, destiny;
		System.out.println("   _____        _                 _     _   _       ");
		System.out.println("  / ____|      | |               (_)   | | (_)      ");
		System.out.println(" | |  __ ______| |     ___   __ _ _ ___| |_ _  ___  ");
		System.out.println(" | | |_ |______| |    / _   / _` | / __| __| |/ __| ");
		System.out.println(" | |__| |      | |___| (_) | (_| |  __   |_| | (__  ");
		System.out.println("   _____|      |______ ___/  __, |_|___/ __|_| ___| ");
		System.out.println("                             __/ |                  ");
		System.out.println("                            |___/                   ");
		System.out.println("Bienvenido a G-Logistic\n");
		while(true){
			System.out.print("\nIngrese su ciudad de origen: ");
			Scanner input = new Scanner(System.in);
			origin = input.nextLine();
			if(city.contains(origin))
				break;
			else
				System.out.println("Ciudad no valida.");
		}
		while(true){
			System.out.print("\nIngrese su ciudad de destino: ");
			Scanner input = new Scanner(System.in);
			destiny = input.nextLine();
			if(city.contains(destiny))
				break;
			else
				System.out.println("Ciudad no valida.");
		}

		Graph graph = new Graph(edges);

 		graph.calculateShortestDistances();

		System.out.println(graph.toString());


	}
}
