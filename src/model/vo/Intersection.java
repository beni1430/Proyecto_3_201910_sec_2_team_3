package model.vo;


public class Intersection implements Comparable<Intersection>
{
	private Long id;
	private double lat;
	private double lon;
	private Long [] adj;
	private Long [] infractions;

	public Long[] getInfractions()
	{
		return infractions;
	}

	public long getId()
	{
		return id;
	}

	public double getLat()
	{
		return lat;
	}

	public double getLon()
	{
		return lon;
	}

	public Long[] getAdj()
	{
		return adj;
	}

	public int compareTo(Intersection o) 
	{
		return (int) (id-o.getId());
	}
}
