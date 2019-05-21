package model.data_structures;

import model.vo.*;

public class Graph<K extends Comparable<K>,V extends Comparable<V>, I extends Comparable<I>> {

	private int V;
	private int E;
	private LinearHash<K,Vertex<K,V,I>> vertices;
	private ArregloDinamico<Edge<I>> arcos;

	public Graph() 
	{
		this.V = 0;
		this.E = 0;
		vertices = new LinearHash<K,Vertex<K,V,I>>(1500); 
		arcos= new ArregloDinamico<Edge<I>>(170000);
	}

	public LinearHash<K,Vertex<K,V,I>> getVertex()
	{
		return vertices;
	}

	public ArregloDinamico<Edge<I>> getEdges()
	{
		return arcos;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addVertex(K idVertice, V infoVertice)
	{
		Vertex<K,V,I> vertice = new Vertex<K, V, I>(idVertice, infoVertice);
		vertices.put(idVertice, vertice);
		V++;
	}

	public void addEdge(K vertice1 , K vertice2, I infoArco) 
	{	
		if(vertices.get(vertice1) != null && vertices.get( vertice2) != null)
		{
			vertices.get(vertice1).addEdge(new Edge<I>(infoArco,vertices.get(vertice1), vertices.get(vertice2)));
			vertices.get(vertice2).addEdge(new Edge<I>(infoArco,vertices.get(vertice2), vertices.get(vertice1)));
			arcos.agregar(new Edge<I>(infoArco,vertices.get(vertice1), vertices.get(vertice2)));
			E++;
		}  
	}	

	public V getInfoVertex(K idVertex)
	{
		if(vertices.get(idVertex )!= null)
		{
			return (V) vertices.get(idVertex).getInfo();
		}
		return null;
	}

	public I getInfoArc(K vertice1, K vertice2)
	{
		Vertex<K,V,I> SegundoVertice = vertices.get(vertice2);
		ArregloDinamico<Edge<I>> arreglo = vertices.get(vertice1).getAdjEdges();
		for(int i = 0; i < arreglo.darTamano(); i++)
		{
			if(arreglo.darElemento(i).getV1().compareTo(SegundoVertice) == 0 || arreglo.darElemento(i).getV2().compareTo(SegundoVertice) == 0 )
			{
				return arreglo.darElemento(i).getInfo();
			}
		}
		return null;	
	}

	public void setInfoArco(K idVertexin, K idVertexfin, I infoArco)
	{
		Vertex<K,V,I> PrimerVertice = vertices.get(idVertexin);
		Vertex<K,V,I> SegundoVertice = vertices.get(idVertexfin);
		ArregloDinamico<Edge<I>> arreglo = PrimerVertice.getAdjEdges();
		for(int i = 0; i < arreglo.darTamano(); i++)
		{
			if(arreglo.darElemento(i).getV1().compareTo(SegundoVertice) == 0 || arreglo.darElemento(i).getV2().compareTo(SegundoVertice) == 0 )
			{
				arreglo.darElemento(i).update(infoArco);
			}
		}

	}

	public void setInfoVertex(K idVertex, V infoVertex)
	{
		vertices.get(idVertex).update(infoVertex); 
	}

	public IteratorLista<K> adj(K idVertex)
	{
		Vertex<K, V,I> vertice = vertices.get(idVertex); 
		ArregloDinamico<Edge<I>> arcos = vertice.getAdjEdges();
		ArregloDinamico<K> verticesR = new ArregloDinamico<K>(100); 
		for(int i = 0; i < arcos.darTamano(); i++)
		{
			if(arcos.darElemento(i).getV1().equals(vertice))
			{
				verticesR.agregar((K) arcos.darElemento(i).getV2().getId());
			}
			else
			{
				verticesR.agregar((K) arcos.darElemento(i).getV1().getId());
			}
		}
		return verticesR.darIterator();
	}
}