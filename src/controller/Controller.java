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
					RutaArchivo = ""; //TODO Dar la ruta del archivo de Downtown
				else
					RutaArchivo = "./data/finalGraph.json"; //TODO Dar la ruta del archivo de la ciudad completa

				startTime = System.currentTimeMillis();
				loadJSON(RutaArchivo);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				// TODO Informar el total de vértices y el total de arcos que definen el grafo cargado
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
				TODO Consola: Mostrar el camino a seguir con sus vértices (Id, Ubicación Geográfica),
				el costo mínimo (menor cantidad de infracciones), y la distancia estimada (en Km).

				TODO Google Maps: Mostrar el camino resultante en Google Maps 
				(incluyendo la ubicación de inicio y la ubicación de destino).
				 */
				break;

			case 2:
				view.printMessage("2A. Consultar los N v�rtices con mayor n�mero de infracciones. Ingrese el valor de N: ");
				int n = sc.nextInt();


				startTime = System.currentTimeMillis();
				mayorNumeroVerticesA2(n);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/* 
				TODO Consola: Mostrar la informacion de los n vertices 
				(su identificador, su ubicación (latitud, longitud), y el total de infracciones) 
				Mostra el número de componentes conectadas (subgrafos) y los  identificadores de sus vertices 

				TODO Google Maps: Marcar la localización de los vértices resultantes en un mapa en
				Google Maps usando un color 1. Destacar la componente conectada más grande (con
				más vértices) usando un color 2. 
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
					el total de vértices, sus vértices (Id, Ubicación Geográfica) y la distancia estimada (en Km).

				   TODO Google Maps: Mostre el camino resultante en Google Maps (incluyendo la
					ubicación de inicio y la ubicación de destino).
				 */
				break;

			case 4:		
				double lonMin;
				double lonMax;
				view.printMessage("Ingrese la longitud minima (Ej. -87,806): ");
				lonMin = sc.nextDouble();
				view.printMessage("Ingrese la longitud m�xima (Ej. -87,806): ");
				lonMax = sc.nextDouble();

				view.printMessage("Ingrese la latitud minima (Ej. 44,806): ");
				double latMin = sc.nextDouble();
				view.printMessage("Ingrese la latitud m�xima (Ej. 44,806): ");
				double latMax = sc.nextDouble();

				view.printMessage("Ingrese el n�mero de columnas");
				int columnas = sc.nextInt();
				view.printMessage("Ingrese el n�mero de filas");
				int filas = sc.nextInt();


				startTime = System.currentTimeMillis();
				definirCuadriculaB2(lonMin,lonMax,latMin,latMax,columnas,filas);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar el número de vértices en el grafo
					resultado de la aproximación. Mostar el identificador y la ubicación geográfica de cada
					uno de estos vértices. 

				   TODO Google Maps: Marcar las ubicaciones de los vértices resultantes de la
					aproximación de la cuadrícula en Google Maps.
				 */
				break;

			case 5:

				startTime = System.currentTimeMillis();
				arbolMSTKruskalC1();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vértices (identificadores), los arcos incluidos (Id vértice inicial e Id vértice
					final), y el costo total (distancia en Km) del árbol.

				   TODO Google Maps: Mostrar el árbol generado resultante en Google Maps: sus vértices y sus arcos.
				 */

				break;

			case 6:

				startTime = System.currentTimeMillis();
				arbolMSTPrimC2();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vértices (identificadores), los arcos incluidos (Id vértice inicial e Id vértice
				 	final), y el costo total (distancia en Km) del árbol.

				   TODO Google Maps: Mostrar el árbol generado resultante en Google Maps: sus vértices y sus arcos.
				 */
				break;

			case 7:

				startTime = System.currentTimeMillis();
				caminoCostoMinimoDijkstraC3();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar de cada camino resultante: su secuencia de vértices (identificadores) y su costo (distancia en Km).

				   TODO Google Maps: Mostrar los caminos de costo mínimo en Google Maps: sus vértices
					y sus arcos. Destaque el camino más largo (en distancia) usando un color diferente
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
				   TODO Consola: Mostrar del camino resultante: su secuencia de vértices (identificadores), 
				   el total de infracciones y la distancia calculada (en Km).

				   TODO Google Maps: Mostrar  el camino resultante en Google Maps: sus vértices y sus arcos.	  */
				break;

			case 9: 	
				fin = true;
				sc.close();
				break;
			}
		}
	}


	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia


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
					Intersection I1=grafo.getInfoVertex(vActual.getId());
					Intersection I2=grafo.getInfoVertex(adyacente);
					grafo.addEdge(vActual.getId(),adyacente,new Way(Haversine.distance(I1.getLat(),I1.getLon(),I2.getLat(),I2.getLon())));
				}
			}
		}
	}
	private LinearHash<Long, Edge<Way>>  edgeTo; 
	private LinearHash<Long, Vertex<Long, Intersection, Way>> verticesPunto; 
	private double[] disTo; 
	private ColaPrioridad<Vertex<Long, Intersection, Way>> cola;


	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 1A: Encontrar el camino de costo m�nimo para un viaje entre dos ubicaciones geogr�ficas.
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoCostoMinimoA1(long idVertice1, long idVertice2) {
		// TODO Auto-generated method stub
		System.out.println(grafo.getInfoVertex(new Long(50251648)).getId());

		edgeTo = new LinearHash<>(grafo.V());
		verticesPunto = grafo.getVertex(); 

		cola = new ColaPrioridad<>(new comparatorVertex());
		disTo = new double[verticesPunto.darCapacidad()]; 
		for(int i = 0; i <disTo.length; i++)
		{
			disTo[i] = Integer.MAX_VALUE; 
		}
		Vertex<Long, Intersection,Way > vertice = verticesPunto.get(idVertice1);
		disTo[verticesPunto.getI(idVertice1)] = 0; 

		cola.agregar(vertice);
		while(!cola.esVacia())
		{
			Vertex<Long, Intersection, Way> v = cola.delMax();
			ArregloDinamico<Edge<Way>> adyacentes = v.getAdjEdges();
			for(int i = 0; i < adyacentes.darTamano(); i++)
			{
				relax(adyacentes.darElemento(i), v);
			}

		}	
		Mapa mapa = new Mapa("Punto A1");
		Vertex< Long, Intersection, Way> actual = verticesPunto.get(idVertice2); 
		while(actual.getId() != vertice.getId())
		{
			Vertex<Long, Intersection, Way> v2; 
			Edge<Way> arco = edgeTo.get(actual.getId()); 
			if(actual.getId() == arco.getV1().getId())
			{
				v2 = arco.getV2();
			}
			else
			{
				v2 = arco.getV1();
			}
			mapa.generateCircle(new LatLng(actual.getInfo().getLat(), actual.getInfo().getLon()));
			mapa.generateCircle(new LatLng(v2.getInfo().getLat(), v2.getInfo().getLon()));
			Intersection uno = actual.getInfo(); 
			Intersection dos = v2.getInfo();
			mapa.generateSimplePath(new LatLng(uno.getLat(), uno.getLon()), new LatLng(dos.getLat(),dos.getLon()), false);

		}


	}

	private void relax(Edge<Way> e, Vertex<Long, Intersection, Way> v)
	{
		Vertex<Long, Intersection, Way> w; 
		if(e.getV1().equals(v))
		{
			w = e.getV2();
		}
		else
		{
			w = e.getV1(); 
		}
		if(disTo[verticesPunto.getI(w.getId())] > disTo[verticesPunto.getI(v.getId())] + v.getInfo().getInfractions().length)
		{
			disTo[verticesPunto.getI(w.getId())] = disTo[verticesPunto.getI(v.getId())] + v.getInfo().getInfractions().length;
			edgeTo.put(w.getId(), e);
			if(!cola.contains(w))
			{
				cola.agregar(w);
			}

		}
	}
	public class comparatorVertex implements Comparator<Vertex<Long, Intersection, Way>>
	{

		public int compare(Vertex<Long, Intersection, Way> o1, Vertex<Long, Intersection, Way> o2) {
			// TODO Auto-generated method stub
			return o1.getInfo().getInfractions().length - o2.getInfo().getAdj().length;
		}

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia 
	/**
	 * Requerimiento 2A: Determinar los n v�rtices con mayor n�mero de infracciones. Adicionalmente identificar las
	 * componentes conectadas (subgrafos) que se definan �nicamente entre estos n v�rtices
	 * @param  int n: numero de vertices con mayor numero de infracciones  
	 */
	public void mayorNumeroVerticesA2(int n) {
		LinearHash<Long, Vertex<Long, Intersection, Way>> tabla =   grafo.getVertex(); 
		ArregloDinamico<Vertex<Long, Intersection, Way>> vertices = tabla.getValues();



		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 1B: Encontrar el camino m�s corto para un viaje entre dos ubicaciones geogr�ficas 
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoLongitudMinimoaB1(int idVertice1, int idVertice2) {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 2B:  Definir una cuadricula regular de N columnas por M filas. que incluya las longitudes y latitudes dadas
	 * @param  lonMin: Longitud minima presente dentro de la cuadricula
	 * @param  lonMax: Longitud maxima presente dentro de la cuadricula
	 * @param  latMin: Latitud minima presente dentro de la cuadricula
	 * @param  latMax: Latitud maxima presente dentro de la cuadricula
	 * @param  columnas: Numero de columnas de la cuadricula
	 * @param  filas: Numero de filas de la cuadricula
	 */
	public void definirCuadriculaB2(double lonMin, double lonMax, double latMin, double latMax, int columnas,
			int filas) {
		// TODO Auto-generated method stub
	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 1C:  Calcular un �rbol de expansi�n m�nima (MST) con criterio distancia, utilizando el algoritmo de Kruskal.
	 */
	public void arbolMSTKruskalC1() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 2C: Calcular un �rbol de expansi�n m�nima (MST) con criterio distancia, utilizando el algoritmo de Prim. (REQ 2C)
	 */
	public void arbolMSTPrimC2() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 3C: Calcular los caminos de costo m�nimo con criterio distancia que conecten los v�rtices resultado
	 * de la aproximaci�n de las ubicaciones de la cuadricula N x M encontrados en el punto 5.
	 */
	public void caminoCostoMinimoDijkstraC3() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 4C:Encontrar el camino m�s corto para un viaje entre dos ubicaciones geogr�ficas escogidas aleatoriamente al interior del grafo.
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoMasCortoC4(int idVertice1, int idVertice2) {
		// TODO Auto-generated method stub

	}

}
