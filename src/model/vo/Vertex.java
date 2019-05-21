package model.vo;

import java.util.ArrayList;

import model.data_structures.*;

public class Vertex <K extends Comparable<K>,V extends Comparable<V> ,I extends Comparable<I>> implements Comparable<Vertex<K,V,I>>
{
	private K id;
	private V info;
	private I infoEdge;
	private ArregloDinamico<Edge<I>> adjEdges;
	private Vertex<K, V, I> vertexTo; 
	private ArrayList<Edge<I>> adj;
	private boolean marcado;

	public Vertex(K pId,V pInfo)
	{
		adjEdges=new  ArregloDinamico<Edge<I>>(10);
		id=pId;
		info=pInfo;
		adj=new ArrayList<Edge<I>>();

	}	

	public ArrayList<Edge<I>> getAdj()
	{
		return adj;
	}

	public ArregloDinamico<Edge<I>> getAdjEdges()
	{
		return adjEdges;
	}

	public void addEdge(Edge<I> pArco)
	{
		adjEdges.agregar(pArco);
		adj.add(pArco);
	}

	public I getInfoEdge()
	{
		return infoEdge;
	}

	public K getId()
	{
		return id;
	}

	public V getInfo()
	{
		return info;
	}

	public void update( V infoVertex)
	{
		info = infoVertex;
	}
	public void setVertexTo(Vertex<K,V ,I > w)
	{
		vertexTo = w; 
	}
	public Vertex<K, V, I> getVertex()
	{
		return vertexTo; 
	}
	public void setMarca(boolean p)
	{
		marcado = p; 
	}
	public boolean getMark()
	{
		return marcado; 
	}

	@Override
	public int compareTo(Vertex<K,V,I> o) 
	{
		return this.getId().compareTo(o.getId());
	}

}