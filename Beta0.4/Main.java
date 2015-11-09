import java.io.*;
import javax.swing.JOptionPane;

public class Main {

    
    public static void main(String[] args) {
      
        int cont=0;
        
        String[] lista;
        
        Graph grafo= new Graph();
        
        try{
            
            /*Lectura del archivo*/
            		
			BufferedReader file = new BufferedReader(new FileReader("grafo.txt"));
            String nueva_linea;
            
            /*Lectura de las lineas del archivo*/
            while ((nueva_linea = file.readLine()) != null)   {
               
                lista=nueva_linea.split(" ");
                
                /*Para primera linea (nodos = ciudades)*/
                if(cont==0){
                   
                    System.out.println("Los nodos son ("+lista.length+"): ");
                    for (String lista1 : lista) {
                        System.out.print(lista1 + " ");
                        grafo.addVertex(lista1);
                    }
                }
                else if(lista.length==3){
                    grafo.addEdge(lista[0], lista[1], Double.parseDouble(lista[2]));
                    System.out.println("\n Existe una arista con  origen "+lista[0]+", destino "+lista[1]+" y Distancia "+lista[2]);
                }
                cont++;
            }
            
            /*Finalizacion de lectura*/
            file.close();
			
            
        }catch (Exception e){
            System.err.println("Ha ocurrido un error durante la ejecucion del programa: " + e.getMessage());
        }
		
		
		
        int opc = 1;
        
        //Cuando no se desee salir
        while(opc!=4){
            grafo.crearMatriz();
            grafo.algoritmoFloyd();
            
            //Menu de opciones
            JOptionPane.showMessageDialog(null, "1. Conocer la ruta más corta entre dos ciudades\n" +
                                                "2. Centro del grafo\n" + 
                                                "3. Realizar modificaciones en las rutas\n" + 
                                                "4. Salir");
        
          
            opc = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desea:"));
            
            
          
            switch(opc){
                case 1:
                            
                    //Cuidad 1
                    String principio = JOptionPane.showInputDialog("Ingrese la ciudad de origen:");
                    
                    //Ciudad 2
                    String fin = JOptionPane.showInputDialog("Ingrese la ciudad de destino:");
                     
                    //Obtencion del peso
                    double peso = grafo.getPeso(principio,fin);
                    
                    //valor para Ruta que no existe
                    if(peso==6666.6){
                        
                         JOptionPane.showMessageDialog(null,"No existe ruta entre las ciudades o ha ingresado una ciudad no existente");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"La distancia entre las ciudades es de: " + peso);
                    }
                    break;
                    
                    
                case 2:
                    JOptionPane.showMessageDialog(null,grafo.getCenter());
                    break;
                    
                    
                case 3:
                    
                    
                    int opc2 = 0;
                    
                    //Mientras no se desee salir
                    while(opc2!=3){
                       
                        JOptionPane.showMessageDialog(null,"1. Crear interrupcion entre dos ciudades\n"
                                                         + "2. Crear conexión entre dos ciudades\n"
                                                         + "3. Salir");
                       
                        opc2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desea:"));
                        switch(opc2){
                            case 1: //Crear interrupcion entre ciudades
                                String origen = JOptionPane.showInputDialog(null, "Ciudad de origen:");
                                
                                String destino = JOptionPane.showInputDialog(null, "Ciudad de destino:");
                                if(!grafo.removeEdge(origen, destino)){
                                    JOptionPane.showMessageDialog(null,"No se pudo crear la interrupcion exitosamente");
                                }else{
                                    JOptionPane.showMessageDialog(null,"Se creo la interrupcion exitosamente");
                                }
                               
                                break;
                            case 2: //Crear coneccion entre ciudades
                               
                                String origen2 = JOptionPane.showInputDialog(null, "Ciudad de origen: ");
                                
                                String destino2 = JOptionPane.showInputDialog(null, "Ciudad de destino: ");
                                
                                peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Distancia entre ambas ciudades: "));
                                
                                try {
                                    grafo.addEdge(origen2, destino2, peso);
                                    
                                    JOptionPane.showMessageDialog(null,"Se creo la conexion exitosamente");
                                } catch (Exception ex) {
                                    
                                    JOptionPane.showMessageDialog(null,"No se pudo crear la conexion exitosamente");
                                }
                                break;
                            case 3: //Salir
                                break;
                            default:
                                
                                JOptionPane.showMessageDialog(null,"Ha ingresado una opcion incorrecta");
                        }
                    }
                    
                    grafo.imprimirMatriz();
                    
                    break;
               
                   
                   
                case 4:
                    System.out.println("Usted a salido");
                    break;
                default:
                    System.out.println("opcion incorrecta!!");
            }
        }
    }
    
}
