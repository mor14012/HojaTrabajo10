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
		

		System.out.println(city.toString());


		/*
			PRINT TEST
		*/
		VectorHeap heap = new VectorHeap();
		System.out.println("\n\n\nEstoy en "+city.get(origin)+"\n Y estos lugares estan:\n");
		//place neighbors of lab in into priority queue
		for(Iterator i=city.neighbors(origin); i.hasNext();){
		   Object nextcity = i.next();
		   Object distance = city.getEdge(origin, nextcity).label();
		   heap.add(new ComparableAssociation((Comparable)distance,nextcity));
		}

		//print out theaters in order of distance
		while(!heap.isEmpty()){
		   ComparableAssociation show = (ComparableAssociation)heap.remove();
		   System.out.println(show.getValue()+" is "+show.getKey()+" miles away.");
		}
		
		
		System.out.println("\nMenu de Opciones");
		while(true){
			try{
				System.out.println("\nIngrese la opcion deseada");
				Scanner input = new Scanner(System.in);
				System.out.print(" 1.Ruta mas corta entre dos ciudades\n 2.Nombre de ciudad central\n 3.Cambios en el grafo\n 4.Salir\n");
				String opcion = input.nextLine();
				if(opcion.equals("4"))
					break;
				
							if(opcion.equals("1")){
								System.out.print("\nIngrese su ciudad de origen: ");
								Scanner hom = new Scanner(System.in);
								String home = hom.nextLine();
								
								System.out.print("\nIngrese su ciudad de destino: ");
								Scanner des = new Scanner(System.in);
								String dest = des.nextLine();
								
								break;
									}
							if(opcion.equals("2")){
								System.out.print("\nIndique el nombre del la ciudad central");
								Scanner cent = new Scanner(System.in);
								String center = cent.nextLine();
								break;
							}
							if(opcion.equals("3")){
								System.out.print("\n1.Existe una interrupcion entre ciudades\n2.Existe una nueva ocnexion entre ciudades\n");
								Scanner op = new Scanner(System.in);
								String option = op.nextLine();
								
								if(option.equals("1")){
									System.out.print("\nIngrese su ciudad de origen: ");
									Scanner hom = new Scanner(System.in);
									String home = hom.nextLine();
								
									System.out.print("\nIngrese su ciudad de destino: ");
									Scanner des = new Scanner(System.in);
									String dest = des.nextLine();
									city.removeEdge(home,dest);
									break;
								}
								if(option.equals("2")){
									System.out.print("\nIngrese su ciudad de origen: ");
									Scanner hom = new Scanner(System.in);
									String home = hom.nextLine();
								
									System.out.print("\nIngrese su ciudad de destino: ");
									Scanner des = new Scanner(System.in);
									String dest = des.nextLine();
									
									System.out.print("\nIngrese la distancia entre ambas ciudades ");
									Scanner dis = new Scanner(System.in);
									String distance = dis.nextLine();
									
									
									city.addEdge(home,dest,distance);
								 break;
								}
							}
					}
				catch(Exception e){

					}
				}
	}
}	
