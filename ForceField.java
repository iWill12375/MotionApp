package main_graph;

public class ForceField {
	private Ad_vector location;
	private double strength;
	private final double G = 0.06;
	private Ad_vector assist_vector;
	public ForceField(double gx,double gy,double m)
	{
		location = new Ad_vector(gx,gy);
		assist_vector = new Ad_vector(0,0);
		strength = G*m;
	}
	public void accelerationGenerate(Ball ball)
	{
		double r2;
		ball.setAcceleration(location.getX()-ball.getLocation().getX(), location.getY()-ball.getLocation().getY());
		r2 = ball.getAcceleration().getSquare();
		ball.getAcceleration().unitlize();
		ball.getAcceleration().numMul(strength/r2);
	}
	public void updateSource(Ad_vector lo,double M)
	{
		location = lo;
		strength = M*G;
	}
}
