'''
Universidad del Valle de Guatemala
Algoritmos y Estructura de Datos
Seccion: 10
Diego Morales. Carne: 14012
Julio Gonzalez. Carne: 14096
08/11/2015
Hoja de Trabajo 10

'''
#README: Este programa requiere la instalaciond el modulo networkx

import networkx as nx
import sys

g  = nx.Graph() 

try:
    with open('grafo.txt','r') as file:
        array = file.read().splitlines()
        for line in array:
            data = line.split(' ')
            ciudad1 = data[0]
            ciudad2 = data[1]
            distancia = int(data[2])      
            g.add_edge(ciudad1, ciudad2, distance=distancia)
except:
    print "Error durante la lectura del fichero grafo.txt"
    sys.exit(0)
    
    
print"   _____        _                 _     _   _       "
print"  / ____|      | |               (_)   | | (_)      "
print" | |  __ ______| |     ___   __ _ _ ___| |_ _  ___  "
print" | | |_ |______| |    / _   / _` | / __| __| |/ __| "
print" | |__| |      | |___| (_) | (_| |  __   |_| | (__  "
print"   _____|      |______ ___/  __, |_|___/ __|_| ___| "
print"                             __/ |                  "
print"                            |___/                   "

while True:
    print "\nMenu de opciones\n1. Calcular ruta mas corta entre dos ciudades\n2. Centro del grafo\n3. Modificar grafo\n4. Finalizar programa"
    opcion = raw_input("Opcion: ")
    if opcion=="1":
        print "\nCalculo de ruta mas corta"
        while True:
            origin = raw_input("Ciudad de origen: ")
            if origin in g:
                break
            else:
                print "Ciudad no valida\n"
        while True:
            destiny = raw_input("Ciudad de destino: ")
            if destiny in g:
                break
            else:
                print "Ciudad no valida\n"
        try:
            shortestpath = nx.dijkstra_path(g, origin, destiny, 'distance')
            print "La ruta mas corta entre "+origin+" y "+destiny+" es:"
            print shortestpath
            print "La distancia es de: "+str(nx.dijkstra_path_length(g, origin, destiny, 'distance'))+"Km"
        except:
            print "No existe una ruta disponible\n"
    if opcion=="2":
        print "\nCentro del grafo"
        print nx.center(g)
    if opcion=="3":
        print "\nModificar grafo"
        while True:
            print "1. Interrupcion de trafico entre un par de ciudades\n2. Se establece una conexion entre ciudad1 y ciudad2 con valor de x KM"
            modificacion = raw_input("Opcion: ")
            if modificacion=="1":
                print "\nInterrupcion de trafico"
                while True:
                    origin = raw_input("Ciudad de origen: ")
                    if origin in g:
                        break
                    else:
                        print "Ciudad no valida\n"
                while True:
                    destiny = raw_input("Ciudad de destino: ")
                    if destiny in g:
                        break
                    else:
                        print "Ciudad no valida\n"
                g.remove_edge(origin, destiny)
                print "Interrupcion ingresada exitosamente."
                break
            if modificacion=="2":
                print "\nConexion entre dos ciudades"
                while True:
                    origin = raw_input("Ciudad de origen: ")
                    if origin in g:
                        break
                    else:
                        print "Ciudad no valida\n"
                while True:
                    destiny = raw_input("Ciudad de destino: ")
                    if destiny in g:
                        break
                    else:
                        print "Ciudad no valida\n"
                while True:
                    km = raw_input("Distancia: ")
                    try:
                        int(km)/1
                        break
                    except:
                        print "Ingrese un numero valido\n"
                g.add_edge(origin, destiny, distance=int(km))
                print "Conexion realizada exitosamente."
                break
                
    if opcion=="4":
        print "\nFinalizando programa.\n"
        break

