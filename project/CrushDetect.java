package main_graph;

public class CrushDetect {
	public CrushDetect() {
		System.out.println("detecter initialize...");
	}
	public boolean isCrushed(Ball ball1,Ball ball2) {
		if(ball1.getLocation().distanceFrom(ball2.getLocation()) < ball1.getR()+ball2.getR())
		{
			return true;
		}
		if(ball1.getLocation().distanceFrom(ball2.getLocation()) == ball1.getR()+ball2.getR())
		{
			if(ball1.getSpeed().isInline(ball2.getSpeed()))
			{
				return false;
			}
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
}
