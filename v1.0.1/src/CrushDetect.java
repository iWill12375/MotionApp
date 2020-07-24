package main_graph;

public class CrushDetect {
	public CrushDetect() {
		System.out.println("detecter initialize...");
	}
	public boolean isCrushed(Ball ball1,Ball ball2) {
		if(ball1.getLocation().distanceFrom(ball2.getLocation()) < ball1.getR()+ball2.getR())
		{
			System.out.println("<r1+r2");
			return true;
		}
		if(ball1.getLocation().distanceFrom(ball2.getLocation()) == ball1.getR()+ball2.getR())
		{
			if(ball1.getSpeed().isInline(ball2.getSpeed()))
			{
				return false;
			}
			System.out.println("=r1+r2");
			return true;
		}
		return false;
	}
	public boolean isCrushed(Ball b1,Ball b2,Ball b3) {
		//System.out.println("b1 : "+b1.getX()+" , "+b1.getY());
		//System.out.println("b2 : "+b2.getX()+" , "+b2.getY());
		//System.out.println("b3 : "+b3.getX()+" , "+b3.getY());
		if(isCrushed(b1,b2))
		{
			return true;
		}
		if(isCrushed(b1,b3))
		{
			return true;
		}
		if(isCrushed(b2,b3))
		{
			return true;
		}
		return false;
	}
	public boolean innerCrush(Ball ball1,Ball ball2) {
		if(ball1.getLocation().distanceFrom(ball2.getLocation())<ball1.getR()+ball2.getR())
		{
			return true;
		}
		else
			return false;
	}
	public int isBound(Ball ball,double X,double Y) {
		//type 1 block: X too low
		int flag = 0;
		if(ball.getLocation().getX() <= ball.getR())
			flag = flag + 1;
		if(ball.getLocation().getX()>=X-ball.getR())
			flag = flag + 2;
		if(ball.getLocation().getY()<=ball.getR())
			flag = flag + 4;
		if(ball.getLocation().getY()>=Y-ball.getR())
			flag = flag + 8;
		return flag;
	}
	public void correctLocation(Ball ball,double X,double Y)
	{
		
	}
	
	public boolean preCrush(Ball b1,Ball b2) {
		double detaX = b1.getX() - b2.getX();
		double detaY = b1.getY() - b2.getY();
		double detaVx = b1.getVx() - b2.getVx();
		double detaVy = b1.getVy() - b2.getVy();
		
		double a = detaVx*detaVx + detaVy*detaVy;
		double b = 2*(detaVx*detaX + detaVy*detaY);
		double c = detaX*detaX + detaY*detaY - Math.pow(b1.getR()+b2.getR(), 2);
		
		if(b*b-4*a*c < 0)
		{
			return false;
		}
		else
		{
			double x1 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
			double x2 = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
			if(x1>0 && x1 <1)
				return true;
			if(x2>0 && x2<1)
				return true;
			return false;
		}
	}
}
