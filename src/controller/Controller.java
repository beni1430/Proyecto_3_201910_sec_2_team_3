package controller;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;

import com.google.gson.Gson;
import com.teamdev.jxmaps.LatLng;

import model.utils.*;
import model.data_structures.*;
import view.MovingViolationsManagerView;
import model.vo.*;

public class Controller {

	// Componente vista (consola)
	private MovingViolationsManagerView view;

	//TODO Definir los atributos de estructuras de datos del modelo del mundo del proyecto
	private Graph<Long,Intersection,Way> grafo;
	private ArregloDinamico<Long> vertices;

	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		view = new MovingViolationsManagerView();
		grafo = new Graph<Long,Intersection,Way>();
		vertices = new ArregloDinamico<Long>(746000);
	}

	/**
	 * Metodo encargado de ejecutar los  requerimientos segun la opcion indicada por el usuario
	 */
	public void run()
	{

		long startTime;
		long endTime;
		long duration;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;


		while(!fin){
			view.printMenu();

			int option = sc.nextInt();
			int idVertice1 = 0;
			int idVertice2 = 0;


			switch(option){

			case 0:
				String RutaArchivo = "";
				view.printMessage("Escoger el grafo a cargar: (1) Downtown  o (2)Ciudad Completa.");
				int ruta = sc.nextInt();
				if(ruta == 1)
					RutaArchivo = "./data/archivoJSONDowntown.json"; //TODO Dar la ruta del archivo de Downtown
				else
					RutaArchivo = "./data/finalGraph.json"; //TODO Dar la ruta del archivo de la ciudad completa

				startTime = System.currentTimeMillis();
				loadJSON(RutaArchivo);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				// TODO Informar el total de vÃ©rtices y el total de arcos que definen el grafo cargado
				break;



			case 1:

				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();


				startTime = System.currentTimeMillis();
				caminoCostoMinimoA1(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/* 
				TODO Consola: Mostrar el camino a seguir con sus vÃ©rtices (Id, UbicaciÃ³n GeogrÃ¡fica),
				el costo mÃ­nimo (menor cantidad de infracciones), y la distancia estimada (en Km).

				TODO Google Maps: Mostrar el camino resultante en Google Maps 
				(incluyendo la ubicaciÃ³n de inicio y la ubicaciÃ³n de destino).
				 */
				break;

			case 2:
				view.printMessage("2A. Consultar los N vï¿½rtices con mayor nï¿½mero de infracciones. Ingrese el valor de N: ");
				int n = sc.nextInt();


				startTime = System.currentTimeMillis();
				mayorNumeroVerticesA2(n);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/* 
				TODO Consola: Mostrar la informacion de los n vertices 
				(su identificador, su ubicaciÃ³n (latitud, longitud), y el total de infracciones) 
				Mostra el nÃºmero de componentes conectadas (subgrafos) y los  identificadores de sus vertices 

				TODO Google Maps: Marcar la localizaciÃ³n de los vÃ©rtices resultantes en un mapa en
				Google Maps usando un color 1. Destacar la componente conectada mÃ¡s grande (con
				mÃ¡s vÃ©rtices) usando un color 2. 
				 */
				break;

			case 3:			

				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();


				startTime = System.currentTimeMillis();
				caminoLongitudMinimoaB1(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");

				/*
				   TODO Consola: Mostrar  el camino a seguir, informando
					el total de vÃ©rtices, sus vÃ©rtices (Id, UbicaciÃ³n GeogrÃ¡fica) y la distancia estimada (en Km).

				   TODO Google Maps: Mostre el camino resultante en Google Maps (incluyendo la
					ubicaciÃ³n de inicio y la ubicaciÃ³n de destino).
				 */
				break;

			case 4:		
				
				double lonMin;
				double lonMax;
				view.printMessage("Ingrese la longitud minima (Ej. -87,806): ");
				lonMin = sc.nextDouble();
				view.printMessage("Ingrese la longitud mï¿½xima (Ej. -87,806): ");
				lonMax = sc.nextDouble();

				view.printMessage("Ingrese la latitud minima (Ej. 44,806): ");
				double latMin = sc.nextDouble();
				view.printMessage("Ingrese la latitud mï¿½xima (Ej. 44,806): ");
				double latMax = sc.nextDouble();

				view.printMessage("Ingrese el nï¿½mero de columnas");
				int columnas = sc.nextInt();
				view.printMessage("Ingrese el nï¿½mero de filas");
				int filas = sc.nextInt();


				startTime = System.currentTimeMillis();
				ArregloDinamico<Intersection> minimos = definirCuadriculaB2(lonMin,lonMax,latMin,latMax,columnas,filas);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar el nÃºmero de vÃ©rtices en el grafo
					resultado de la aproximaciÃ³n. Mostar el identificador y la ubicaciÃ³n geogrÃ¡fica de cada
					uno de estos vÃ©rtices. 

				   TODO Google Maps: Marcar las ubicaciones de los vÃ©rtices resultantes de la
					aproximaciÃ³n de la cuadrÃ­cula en Google Maps.
				 */
				final Mapa example = new Mapa("Washington");

				for(int k=0;k < minimos.darTamano();k++)
				{
					example.generateMarker(new LatLng(minimos.darElemento(k).getLat(),minimos.darElemento(k).getLon()));
				}
				
				break;

			case 5:

				startTime = System.currentTimeMillis();
				arbolMSTKruskalC1();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vÃ©rtices (identificadores), los arcos incluidos (Id vÃ©rtice inicial e Id vÃ©rtice
					final), y el costo total (distancia en Km) del Ã¡rbol.

				   TODO Google Maps: Mostrar el Ã¡rbol generado resultante en Google Maps: sus vÃ©rtices y sus arcos.
				 */

				break;

			case 6:

				startTime = System.currentTimeMillis();
				arbolMSTPrimC2();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vÃ©rtices (identificadores), los arcos incluidos (Id vÃ©rtice inicial e Id vÃ©rtice
				 	final), y el costo total (distancia en Km) del Ã¡rbol.

				   TODO Google Maps: Mostrar el Ã¡rbol generado resultante en Google Maps: sus vÃ©rtices y sus arcos.
				 */
				break;

			case 7:

				startTime = System.currentTimeMillis();
				caminoCostoMinimoDijkstraC3();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar de cada camino resultante: su secuencia de vÃ©rtices (identificadores) y su costo (distancia en Km).

				   TODO Google Maps: Mostrar los caminos de costo mÃ­nimo en Google Maps: sus vÃ©rtices
					y sus arcos. Destaque el camino mÃ¡s largo (en distancia) usando un color diferente
				 */
				break;

			case 8:
				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();

				startTime = System.currentTimeMillis();
				caminoMasCortoC4(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar del camino resultante: su secuencia de vÃ©rtices (identificadores), 
				   el total de infracciones y la distancia calculada (en Km).

				   TODO Google Maps: Mostrar  el camino resultante en Google Maps: sus vÃ©rtices y sus arcos.	  */
				break;

			case 9: 	
				fin = true;
				sc.close();
				break;
			}
		}
	}


	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia


	/**
	 * Cargar el Grafo No Dirigido de la malla vial: Downtown o Ciudad Completa
	 * @param rutaArchivo 
	 */

	public void loadJSON(String rutaArchivo) 
	{
		try
		{
			Gson gson=new Gson();
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			Intersection[] actual =  gson.fromJson(br, Intersection[].class);
			for (int i = 0; i < actual.length; i++) 
			{
				grafo.addVertex(actual[i].getId(),actual[i]);
				vertices.agregar(actual[i].getId());
			}
			crearArcos();
			view.printMessage("Se cargaron: "+grafo.E()+" arcos y "+grafo.V()+" vertices" );	
		}

		catch(Exception e)
		{ 
			e.printStackTrace(); 
		}

	}

	public void crearArcos()
	{
		for (int i =0;i<vertices.darTamano();i++)
		{
			Intersection vActual=grafo.getInfoVertex(vertices.darElemento(i));
			for (Long adyacente : vActual.getAdj()) 
			{
				if(grafo.getInfoArc(vActual.getId(), adyacente)==null)
				{
					Intersection I1 = grafo.getInfoVertex(vActual.getId());
					Intersection I2 = grafo.getInfoVertex(adyacente);
					grafo.addEdge(vActual.getId(),adyacente,new Way(Haversine.distance(I1.getLat(),I1.getLon(),I2.getLat(),I2.getLon())));
				}
			}
		}
	}
	private LinearHash<Long, Edge<Way>>  edgeTo; 
	private LinearHash<Long, Vertex<Long, Intersection, Way>> verticesPunto; 
	private double[] disTo; 
	private ColaPrioridad<Vertex<Long, Intersection, Way>> cola;


	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia
	/**
	 * Requerimiento 1A: Encontrar el camino de costo mï¿½nimo para un viaje entre dos ubicaciones geogrï¿½ficas.
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoCostoMinimoA1(long idVertice1, long idVertice2)
	{
		// TODO Auto-generated method stub
	}

	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia 
	/**
	 * Requerimiento 2A: Determinar los n vï¿½rtices con mayor nï¿½mero de infracciones. Adicionalmente identificar las
	 * componentes conectadas (subgrafos) que se definan ï¿½nicamente entre estos n vï¿½rtices
	 * @param  int n: numero de vertices con mayor numero de infracciones  
	 */
	public void mayorNumeroVerticesA2(int n)
	{

		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia
	/**
	 * Requerimiento 1B: Encontrar el camino mï¿½s corto para un viaje entre dos ubicaciones geogrï¿½ficas 
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoLongitudMinimoaB1(int idVertice1, int idVertice2)
	{
		// TODO Auto-generated method stub
		BFS<Long> bfs=new BFS<Long>(grafo,(long)idVertice1);
		ArregloDinamico <Long> arreglo = bfs.pathTo((long)idVertice2);
		view.printMessage("Se obtuvieron: "+arreglo.darTamano()+" v�rtices");

		double distEst=0.0;
		final Mapa example = new Mapa("Washington");
		for(int i=0;i<arreglo.darTamano()-1;i++)
		{
			Intersection actual = grafo.getInfoVertex(arreglo.darElemento(i));
			Intersection siguiente=grafo.getInfoVertex(arreglo.darElemento(i+1));
			view.printMessage("Id: "+actual.getId()+" Ubicaci�n geogr�fica ("+actual.getLon()+","+actual.getLat()+")");
			distEst+=Haversine.distance(actual.getLat(), actual.getLon(), siguiente.getLat(), siguiente.getLon());
			example.generateSimplePath(new LatLng(actual.getLat(),actual.getLon()), new LatLng(siguiente.getLat(),siguiente.getLon()), false);

		}
		view.printMessage("Distancia: "+distEst);
	}

	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia
	/**
	 * Requerimiento 2B:  Definir una cuadricula regular de N columnas por M filas. que incluya las longitudes y latitudes dadas
	 * @param  lonMin: Longitud minima presente dentro de la cuadricula
	 * @param  lonMax: Longitud maxima presente dentro de la cuadricula
	 * @param  latMin: Latitud minima presente dentro de la cuadricula
	 * @param  latMax: Latitud maxima presente dentro de la cuadricula
	 * @param  columnas: Numero de columnas de la cuadricula
	 * @param  filas: Numero de filas de la cuadricula
	 */
	public ArregloDinamico<Intersection> definirCuadriculaB2(double lonMin, double lonMax, double latMin, double latMax, int columnas,
			int filas)
	{
		// TODO Auto-generated method stub
		ArregloDinamico<Intersection> minimos = new ArregloDinamico<Intersection>(filas*columnas);
		for(double i= lonMin;i<lonMax;i+=(lonMax-lonMin)/filas)
		{
			for(double j=latMin;j<latMax;j+=(latMax-latMin)/columnas)
			{
				double minDist=Long.MAX_VALUE;
				Intersection verMin=new Intersection();

				for (int k =0;k<vertices.darTamano();k++)
				{
					Intersection vActual=grafo.getInfoVertex(vertices.darElemento(k));
					if(Haversine.distance(j, i, vActual.getLat(), vActual.getLon())<minDist)
					{
						minDist=Haversine.distance(j, i, vActual.getLat(), vActual.getLon());
						verMin=vActual;
					}
				}
				minimos.agregar(verMin);
			}
		}
		return minimos;
	}

	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia
	/**
	 * Requerimiento 1C:  Calcular un ï¿½rbol de expansiï¿½n mï¿½nima (MST) con criterio distancia, utilizando el algoritmo de Kruskal.
	 */
	public void arbolMSTKruskalC1() 
	{
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia
	/**
	 * Requerimiento 2C: Calcular un ï¿½rbol de expansiï¿½n mï¿½nima (MST) con criterio distancia, utilizando el algoritmo de Prim. (REQ 2C)
	 */
	public void arbolMSTPrimC2() 
	{
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia
	/**
	 * Requerimiento 3C: Calcular los caminos de costo mï¿½nimo con criterio distancia que conecten los vï¿½rtices resultado
	 * de la aproximaciï¿½n de las ubicaciones de la cuadricula N x M encontrados en el punto 5.
	 */
	public void caminoCostoMinimoDijkstraC3()
	{
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los mï¿½todos puede ajustarse segï¿½n la conveniencia
	/**
	 * Requerimiento 4C:Encontrar el camino mï¿½s corto para un viaje entre dos ubicaciones geogrï¿½ficas escogidas aleatoriamente al interior del grafo.
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoMasCortoC4(int idVertice1, int idVertice2) 
	{
		// TODO Auto-generated method stub

	}

}
