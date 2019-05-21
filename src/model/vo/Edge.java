package model.vo;


public class Edge <I extends Comparable<I>> implements Comparable<Edge<I>>
{
	private I info;
	private Vertex v1;
	private Vertex v2;

	public Edge(I pInfo, Vertex pV1, Vertex pV2)
	{
		info=pInfo;
		v1=pV1;
		v2=pV2;
	}

	public I getInfo()
	{
		return info;
	}

	public Vertex  getV1()
	{
		return v1;
	}

	public Vertex getV2()
	{
		return v2;
	}

	public void update(I pInfo)
	{
		info=pInfo;
	}

	public int compareTo(Edge<I> arg0) 
	{
		return info.compareTo(arg0.getInfo());
	}
}
