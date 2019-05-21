package model.vo;


public class Way implements Comparable<Way>
{

	private double distance;
	public Way( double pDistance )
	{
		distance=pDistance;
	}

	public double getDistance()
	{
		return distance;
	}

	@Override
	public int compareTo(Way arg0) {
		return (int) (this.getDistance()-arg0.getDistance());
	}
}

