package program;

public class Point {
	private double X;
	private double Y;
	private double D;
	
	public Point(double x, double y, double d)
	{
		this.X = x;
		this.Y = y;
		this.D = d;
	}
	
	public Point(double x, double y)
	{
		this.X = x;
		this.Y = y;
		this.D = 0;
	}
	
	public double getX()
	{
		return X;
	}
	
	public double getY()
	{
		return Y;
	}
	
	public double getD()
	{
		return D;
	}
	
	public void setD(double d)
	{
		D = d;
	}
}
