package main_graph;
/**
 * @author zjc
 * 2020.7.23
 * This class controls the main
 * motion of the balls. It's in charge
 * of the speed and acceleration of the
 * balls and updating the location showing
 * onto the screen. It mainly coordinates 
 * Frame and the balls.
 * 
 */
import java.awt.Color;

public class SailingControl {
	
	protected Ball ball1;			//three bodies
	protected Ball ball2;
	protected Ball ball3;
	private CrushDetect detecter;	//crush detector
	private ForceField FFD;			//gravity generate that means to generate Acceleration
	private static Ad_vector frame_location;	//record the windows frame location
	private int code;				//give the heaviest one and encode it
	
	/*constructor*/
	public SailingControl() {
		
		ball1 = new Ball(DeliverMan.B1X,DeliverMan.B1Y,DeliverMan.B1R,DeliverMan.B1M,Color.red);
		
		ball2 = new Ball(DeliverMan.B2X,DeliverMan.B2Y,DeliverMan.B2R,DeliverMan.B2M,Color.yellow);
		
		ball3 = new Ball(DeliverMan.B3X,DeliverMan.B3Y,DeliverMan.B3R,DeliverMan.B3M,Color.blue);
		detecter = new CrushDetect();
		FFD = new ForceField();
		frame_location = new Ad_vector(0,0);
	}
	
	/*provide a method for changing info of the FrameLocation*/
	public static void setFrameLocation(double xx,double yy) {
		frame_location.set(xx, yy);
	}
	
	/*this the main driver of balls motion*/
	public void runningMotion() throws Exception{
		
		if(detecter.isCrushed(ball1, ball2, ball3))
		{
			throw new CrushException("e1");
		}
		
		if(FFD.isOverEsc(ball1,ball2,ball3))
		{
			throw new OverEscException("e2");
		}
		FFD.accelerationGenerate(ball2, ball3, ball1);
		FFD.accelerationGenerate(ball1, ball3, ball2);
		FFD.accelerationGenerate(ball1, ball2, ball3);
		
		ball1.updateSpeed();
		ball2.updateSpeed();
		ball3.updateSpeed();
		
		if(detecter.preCrush(ball1,ball2) || detecter.preCrush(ball1,ball3) || detecter.preCrush(ball2,ball3))
		{
			throw new CrushException("e3");
		}
		
		ball1.updateLocation();
		ball1.updateRelativeLocation(frame_location);
		DeliverMan.runtime_b1x = ball1.getRelativeLocation().getX();
		DeliverMan.runtime_b1y = ball1.getRelativeLocation().getY();
		ball2.updateLocation();
		ball2.updateRelativeLocation(frame_location);
		DeliverMan.runtime_b2x = ball2.getRelativeLocation().getX();
		DeliverMan.runtime_b2y = ball2.getRelativeLocation().getY();
		ball3.updateLocation();
		ball3.updateRelativeLocation(frame_location);
		DeliverMan.runtime_b3x = ball3.getRelativeLocation().getX();
		DeliverMan.runtime_b3y = ball3.getRelativeLocation().getY();
		
		updateFrameLocation();
		
		Thread.sleep(14);
	}
	
	/*initialize the balls actually load the arguments into the container */
	public void initializeBalls() {
		if(DeliverMan.Switch1) {
			ball1.setLocation(DeliverMan.B1X,DeliverMan.B1Y);
			ball1.setR(DeliverMan.B1R);
			ball1.setWeight(DeliverMan.B1M);
			ball1.setSpeed(DeliverMan.V1X, DeliverMan.V1Y);
			DeliverMan.Switch1 = !DeliverMan.Switch1;
		}
		if(DeliverMan.Switch2) {
			ball2.setLocation(DeliverMan.B2X,DeliverMan.B2Y);
			ball2.setR(DeliverMan.B2R);
			ball2.setWeight(DeliverMan.B2M);
			ball2.setSpeed(DeliverMan.V2X, DeliverMan.V2Y);
			DeliverMan.Switch2 = !DeliverMan.Switch2;
		}
		if(DeliverMan.Switch3) {
			ball3.setLocation(DeliverMan.B3X,DeliverMan.B3Y);
			ball3.setR(DeliverMan.B3R);
			ball3.setWeight(DeliverMan.B3M);
			ball3.setSpeed(DeliverMan.V3X, DeliverMan.V3Y);
			DeliverMan.Switch3 = !DeliverMan.Switch3;
			double S = Math.max(DeliverMan.B3M,Math.max(DeliverMan.B1M,DeliverMan.B2M));
			if(S == DeliverMan.B1M)
			{
				code = 1;
				setFrameLocation(ball1.getX()-DeliverMan.Pwidth/2,ball1.getY()-DeliverMan.Pheight/2);
			}
			else
			{
				if(S == DeliverMan.B2M)
				{
					code = 2;
					setFrameLocation(ball2.getX()-DeliverMan.Pwidth/2,ball2.getY()-DeliverMan.Pheight/2);
				}
				else
				{
					setFrameLocation(ball3.getX()-DeliverMan.Pwidth/2,ball3.getY()-DeliverMan.Pheight/2);
					code = 3;
				}
			}
		}
	}
	
	private void updateFrameLocation() {
		switch(code) {
		case 1:
			doTrack(ball1);
			break;
		case 2 :
			doTrack(ball2);
			break;
		case 3:
			doTrack(ball3);
			break;
		default:break;
		}
	}
	
	//Tracking the balls to make them always showing on the screen
	private void doTrack(Ball b) {
		frame_location.set(b.getX()-DeliverMan.Pwidth/2, b.getY()-DeliverMan.Pheight/2);
		DeliverMan.fram_X = frame_location.getX();
		DeliverMan.fram_Y = frame_location.getY();
	}

}

class CrushException extends Exception {
	public CrushException(String name) {
		super(name);
	}
}

class OverEscException extends Exception {
	public OverEscException(String name) {
		super(name);
	}
}