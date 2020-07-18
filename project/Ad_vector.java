package main_graph;

public class Ad_vector {
	private double x;
	private double y;
	public Ad_vector(double xx,double yy) {
		x = xx;
		y = yy;
	}
	public void set(double xx,double yy) {
		x = xx;
		y = yy;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double xm) {
		x = xm;
	}
	public void setY(double ym) {
		y = ym;
	}
	public double getMod() {
		return Math.sqrt(x*x+y*y);
	}
	public double innerProduct(Ad_vector v2) {
		return x*v2.getX()+y*v2.getY();
	}
	public double projectionTo(Ad_vector v2) {
		return this.innerProduct(v2)/v2.getMod();
	}
	public double distanceFrom(Ad_vector v2) {
		double temp = Math.pow(x-v2.getX(), 2)+Math.pow(y-v2.getY(), 2);
		return Math.sqrt(temp);
	}
	public void unitlize() {
		double buff;
		buff = this.getMod();
		x = x/buff;
		y = y/buff;
	}
	public boolean isInline(Ad_vector v2) {
		if(x*v2.getY()==y*v2.getX())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean isInline(Ad_vector v2,Ad_vector v3) {
		if(this.isInline(v2))
		{
			if(this.isInline(v3))
			{
				return true;
			}
		}
		return false;
	}
	public double getSquare() {
		return x*x + y*y;
	}
	public void numMul(double k)
	{
		x = x*k;
		y = y*k;
	}
	public void Add(Ad_vector v2)
	{
		x = x + v2.getX();
		y = y + v2.getY();
	}
	public void Sub(Ad_vector v2)
	{
		x = x - v2.getX();
		y = y - v2.getY();
	}
}
