/*
Universidad del Valle de Guatemala
Algoritmos y Estructura de Datos
Seccion: 10
Diego Morales. Carne: 14012
08/11/2015
Hoja de Trabajo 10
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;


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
				city.add(city1);
				city.add(city2);
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

		System.out.println(city.toString());

		String origin="", destiny="";
		Scanner input;
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
			input = new Scanner(System.in);
			System.out.println("\nMenu de opciones:\n1. Calcular ruta mas corta entre dos ciudades (fuera de funcionamiento)\n2. Centro del grafo (fuera de funcionamiento)\n3. Modificar grafo\n4. Finalizar programa");
			String opcion = input.nextLine();
			if(opcion.equals("1")){
				System.out.println("\nCalculo de ruta mas corta entre dos ciudades");
				while(true){
					System.out.print("Ingrese su ciudad de origen: ");
					input = new Scanner(System.in);
					origin = input.nextLine();
					if(city.contains(origin))
						break;
					else
						System.out.println("Ciudad no valida.");
				}
				while(true){
					System.out.print("Ingrese su ciudad de destino: ");
					input = new Scanner(System.in);
					destiny = input.nextLine();
					if(city.contains(destiny))
						break;
					else
						System.out.println("Ciudad no valida.");
				}
				Edge path = city.getEdge(origin, destiny);
				if(path!=null)
					System.out.println("La distancia mas corta es de "+(int)path.label()+"Km");
				else
					System.out.println("No existe una ruta disponible");
			}
			if(opcion.equals("2")){
				System.out.println("El centro del grafo no se encuentra disponible");
			}
			if(opcion.equals("3")){
				while(true){
					System.out.println("	1. Interrupcion de trafico entre un par de ciudades\n	2. Se establece una conexion entre ciudad1 y ciudad2 con valor de x KM\nSeleccione una opcion:");
					input = new Scanner(System.in);
					opcion = input.nextLine();
					if(opcion.equals("1")){
						System.out.println("Interrupcion de trafico");
						while(true){
							System.out.print("Ingrese la ciudad de origen: ");
							input = new Scanner(System.in);
							origin = input.nextLine();
							if(city.contains(origin))
								break;
							else
								System.out.println("Ciudad no valida.");
						}
						while(true){
							System.out.print("Ingrese la ciudad de destino: ");
							input = new Scanner(System.in);
							destiny = input.nextLine();
							if(city.contains(destiny))
								break;
							else
								System.out.println("Ciudad no valida.");
						}
						city.removeEdge(origin, destiny);
						System.out.println("Interrupcion ingresada con exito.");
						break;
					}
					if(opcion.equals("2")){
						System.out.println("Conexion entre dos ciudades");
						while(true){
							System.out.print("Ingrese la ciudad de origen: ");
							input = new Scanner(System.in);
							origin = input.nextLine();
							if(city.contains(origin))
								break;
							else
								System.out.println("Ciudad no valida.");
						}
						while(true){
							System.out.print("Ingrese la ciudad de destino: ");
							input = new Scanner(System.in);
							destiny = input.nextLine();
							if(city.contains(destiny))
								break;
							else
								System.out.println("Ciudad no valida.");
						}
						int distance;
						while(true){
							System.out.println("Ingrese la distancia entre las dos ciudades: ");
							input = new Scanner(System.in);
							try{
								distance = input.nextInt();
								break;
							}
							catch(Exception e){
								System.out.println("Distancia no valida");
							}
						}
						city.addEdge(origin,destiny,distance);
						System.out.println("Conexion realizada exitosamente");
						break;
					}					
				}
			}
			if(opcion.equals("4")){
				System.out.println("Finalizando programa.\n");
				break;
			}
		}





		
		/*
		Imprimir grafo
		*/
		VectorHeap heap = new VectorHeap();
		System.out.println("\n\n\nEstoy en "+city.get(origin));
		//place neighbors of lab in into priority queue
		for(Iterator i=city.neighbors(origin); i.hasNext();){
		   Object nextcity = i.next();
		   Object distance = city.getEdge(origin, nextcity).label();
		   heap.add(new ComparableAssociation((Comparable)distance,nextcity));
		}

		//print out theaters in order of distance
		while(!heap.isEmpty()){
		   ComparableAssociation show = (ComparableAssociation)heap.remove();
		   System.out.println(show.getValue()+" a "+show.getKey()+" Km de distancia.");
		}
		
		
	}
}	
