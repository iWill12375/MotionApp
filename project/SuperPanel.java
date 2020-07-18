package main_graph;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SuperPanel extends JPanel {
	private double X;
	private double Y;
	private double E_total;
	protected Ball ball1;
	protected Ball ball2;
	private CrushDetect detecter;
	private Ellipse2D.Double ball;
	private ForceField G;
	private Ad_vector frame_location;
	public final double lamda = 90;
	public final static double follow_rate = 0.84;
	private Ad_vector speed;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;
	private int shift;
	//private final double motionrate = 0.4;
	
	public SuperPanel() {
		LineBorder bt = (LineBorder)BorderFactory.createLineBorder(Color.BLACK,1);
		this.setBorder(bt);
		ball = new Ellipse2D.Double(0,0,1,1);
		X = this.getSize().width;
		Y = this.getSize().height;
		ball1 = new Ball(500,400,15,10000,Color.RED);
		ball2 = new Ball(350,240,5,10,Color.blue);
		frame_location = new Ad_vector(ball1.getLocation().getX()-graph_engine.Width/2,ball1.getLocation().getY()-graph_engine.Height/2);
		//ball1.setSpeed(0, 0);
		//ball2.setSpeed(-2, 1);
		E_total = ball1.getE()+ball2.getE();
		detecter = new CrushDetect();
		G = new ForceField(250,250,10000);
		speed = new Ad_vector(0,0);
		//this.setOpaque(false);
		this.setBackground(Color.pink);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				oldX = e.getX();
				oldY = e.getY();
			}
			//@Override
			//public void mouseReleased(MouseEvent e) {
				//frame_location.set(frame_location.getX()-newX+oldX, frame_location.getY()-newY+oldY);
			//}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e)
			{
				newX = e.getX();
				newY = e.getY();
				if(Math.abs(newX-oldX)>20||Math.abs(newY-oldY)>20)
					frame_location.set(frame_location.getX()-0.01*(newX-oldX), frame_location.getY()-0.01*(newY-oldY));
			}
		});
		//repaint();
	}
	public Ball getBall1() {
		return ball1;
	}
	public Ball getBall2() {
		return ball2;
	}
	private boolean isEsc(Ball b1,Ball b2)
	{
		if(b1.getSpeed().getMod()>Math.sqrt(2*ForceField.G*b2.getWeight()/b1.getLocation().distanceFrom(b2.getLocation()))+1)
		{
			return true;
		}
		return false;
	}
	
	public void fillXY()
	{
		X = this.getSize().getWidth();
		Y = this.getSize().getHeight();
		frame_location.set(ball1.getLocation().getX()-this.getSize().getWidth()/2, ball1.getLocation().getY()-this.getSize().getHeight()/2);
		System.out.println("("+this.getSize().getWidth()+" , "+this.getSize().getHeight()+")");
	}
	public void updateFrameLocation(Ball bn)
	{
		if(isIncomfort(bn))
		{
			//deal this
			//System.out.println("comfortable");
			frame_location.set(frame_location.getX()+follow_rate*bn.getSpeed().getX(), frame_location.getY()+follow_rate*bn.getSpeed().getY());
			repaint();
		}
		if(isIncritical(bn))
		{
			//deal this
			//System.out.println("critical");
			do{
				frame_moving(bn);
			}while(isIncritical(bn));
			do {
				frame_moving(bn);
			}while(isIncomfort(bn));
			frame_location.set(frame_location.getX()-bn.getSpeed().getX(), frame_location.getY()-bn.getSpeed().getY());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			repaint();
		}
		if(isInoverflow(bn))
		{
			//deal this
			//System.out.println("overflow");
			while(!isIncomfort(bn)) {
				frame_moving(bn);
			}
			do {
				frame_moving(bn);
			}while(isIncomfort(bn));
			frame_location.set(frame_location.getX()-bn.getSpeed().getX(), frame_location.getY()-bn.getSpeed().getY());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			repaint();
		}
	}
	public void frame_moving(Ball bm) {
		frame_location.set(frame_location.getX()+bm.getSpeed().getX(), frame_location.getY()+bm.getSpeed().getY());
	}
	public boolean isIncomfort(Ball b)
	{
		double RX = this.getRelativeLocation(b).getX();
		double RY = this.getRelativeLocation(b).getY();
		if(RX>b.getR()+lamda && RX<X-b.getR()-lamda)
		{
			if(RY>b.getR()+lamda && RY<Y-b.getR()-lamda)
			{
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean isIncritical(Ball b)
	{
		double RX = this.getRelativeLocation(b).getX();
		double RY = this.getRelativeLocation(b).getY();
		if((RX>0 && RX<b.getR()+lamda) || (RX>X-b.getR()-lamda && RX<X))
		{
			if((RY>0 && RY<b.getR()+lamda) || (RY>Y-b.getR()-lamda && RY<Y))
			{
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean isInoverflow(Ball b)
	{
		double RX = this.getRelativeLocation(b).getX();
		double RY = this.getRelativeLocation(b).getY();
		if(RX<0 || RX>X)
		{
			if(RY<0 || RY>Y)
			{
				return true;
			}
			return false;
		}
		return false;
	}
	
	public Ad_vector getRelativeLocation(Ball ball)
	{
		return new Ad_vector(ball.getLocation().getX()-frame_location.getX(),ball.getLocation().getY()-frame_location.getY());
	}
	public void Correct()
	{
		X = this.getSize().getWidth();
		Y = this.getSize().getHeight();
		//System.out.println("X = "+X);
		//System.out.println("Y = "+Y);
		switch(detecter.isBound(ball1, X, Y))
		{
		case 0 :
			break;
		case 1 :
			if(ball1.getSpeed().getX()<0)
				ball1.setSpeedX(-ball1.getSpeed().getX());
			//System.out.println("ball1 speed = "+ball1.getSpeed().getX()+" , "+ball1.getSpeed().getY());
			break;
		case 2 :
			if(ball1.getSpeed().getX()>0)
				ball1.setSpeedX(-ball1.getSpeed().getX());
			break;
		case 4 :
			if(ball1.getSpeed().getY()<0)
				ball1.setSpeedY(-ball1.getSpeed().getY());
			break;
		case 8 :
			if(ball1.getSpeed().getY()>0)
				ball1.setSpeedY(-ball1.getSpeed().getY());
			//System.out.println("ball1 speed = "+ball1.getSpeed().getX()+" , "+ball1.getSpeed().getY());
			break;
		case 5 :
			if(ball1.getSpeed().getX()<0)
				ball1.setSpeedX(-ball1.getSpeed().getX());
			if(ball1.getSpeed().getY()<0)
				ball1.setSpeedY(-ball1.getSpeed().getY());
			break;
		case 9 :
			if(ball1.getSpeed().getX()<0)
				ball1.setSpeedX(-ball1.getSpeed().getX());
			if(ball1.getSpeed().getY()>0)
				ball1.setSpeedY(-ball1.getSpeed().getY());
			break;
		case 6 :
			if(ball1.getSpeed().getX()>0)
				ball1.setSpeedX(-ball1.getSpeed().getX());
			if(ball1.getSpeed().getY()<0)
				ball1.setSpeedY(-ball1.getSpeed().getY());
			break;
		case 10 :
			if(ball1.getSpeed().getX()>0)
				ball1.setSpeedX(-ball1.getSpeed().getX());
			if(ball1.getSpeed().getY()>0)
				ball1.setSpeedY(-ball1.getSpeed().getY());
			break;
		default : break;
		}
		//System.out.println("Ball2 is being detected...");
		switch(detecter.isBound(ball2, X, Y))
		{
		case 0 :
			break;
		case 1 :
			if(ball2.getSpeed().getX()<0)
				ball2.setSpeedX(-ball2.getSpeed().getX());
			//System.out.println("ball2 speed = "+ball2.getSpeed().getX()+" , "+ball2.getSpeed().getY());
			break;
		case 2 :
			if(ball2.getSpeed().getX()>0)
				ball2.setSpeedX(-ball2.getSpeed().getX());
			//System.out.println("ball2 speed = "+ball2.getSpeed().getX()+" , "+ball2.getSpeed().getY());
			break;
		case 4 :
			if(ball2.getSpeed().getY()<0)
				ball2.setSpeedY(-ball2.getSpeed().getY());
			//System.out.println("ball2 speed = "+ball2.getSpeed().getX()+" , "+ball2.getSpeed().getY());
			break;
		case 8 :
			if(ball2.getSpeed().getY()>0)
				ball2.setSpeedY(-ball2.getSpeed().getY());
			//System.out.println("ball2 speed = "+ball2.getSpeed().getX()+" , "+ball2.getSpeed().getY());
			break;
		case 5 :
			if(ball2.getSpeed().getX()<0)
				ball2.setSpeedX(-ball2.getSpeed().getX());
			if(ball2.getSpeed().getY()<0)
				ball2.setSpeedY(-ball2.getSpeed().getY());
			break;
		case 9 :
			if(ball2.getSpeed().getX()<0)
				ball2.setSpeedX(-ball2.getSpeed().getX());
			if(ball2.getSpeed().getY()>0)
				ball2.setSpeedY(-ball2.getSpeed().getY());
			break;
		case 6 :
			if(ball2.getSpeed().getX()>0)
				ball2.setSpeedX(-ball2.getSpeed().getX());
			if(ball2.getSpeed().getY()<0)
				ball2.setSpeedY(-ball2.getSpeed().getY());
			break;
		case 10 :
			if(ball2.getSpeed().getX()>0)
				ball2.setSpeedX(-ball2.getSpeed().getX());
			if(ball2.getSpeed().getY()>0)
				ball2.setSpeedY(-ball2.getSpeed().getY());
			break;
		default : break;
		}
	}
	public void CorrectV()
	{
		//pre handle avoid bug
		double lamda = 0.005;
		double deta = 0.001;
		//pre variable
		Ad_vector v1 = new Ad_vector(ball1.getSpeed().getX(),ball1.getSpeed().getY());
		Ad_vector v2 = new Ad_vector(ball2.getSpeed().getX(),ball2.getSpeed().getY());
		double e = (ball1.getWeight()-ball2.getWeight())/(ball1.getWeight()+ball2.getWeight());
		double e1 = 2*ball2.getWeight()/(ball1.getWeight()+ball2.getWeight());
		double e2 = 2*ball1.getWeight()/(ball1.getWeight()+ball2.getWeight());
		
		while(ball1.getLocation().distanceFrom(ball2.getLocation())<ball1.getR()+ball2.getR())
		{
			ball1.setLocation(ball1.getLocation().getX()-ball1.getSpeed().getX()*lamda, ball1.getLocation().getY()-ball1.getSpeed().getY()*lamda);
			ball2.setLocation(ball2.getLocation().getX()-ball2.getSpeed().getX()*lamda, ball2.getLocation().getY()-ball2.getSpeed().getY()*lamda);
			lamda = lamda + deta;
		}
		//
		if(ball1.getSpeed().isInline(ball2.getSpeed()))
		{
			ball1.setSpeedX(e*v1.getX()+e1*v2.getX());
			ball1.setSpeedY(e*v1.getY()+e1*v2.getY());
			ball2.setSpeedX(-e*v2.getX()+e2*v1.getX());
			ball2.setSpeedY(-e*v2.getY()+e2*v1.getY());
		}
		else
		{
			Ad_vector T = new Ad_vector(ball2.getLocation().getX()-ball1.getLocation().getX(),ball2.getLocation().getY()-ball1.getLocation().getY());
			Ad_vector H = new Ad_vector(-T.getY(),T.getX());
			T.unitlize();
			H.unitlize();
			Ad_vector ball1_T;
			Ad_vector ball1_H;
			Ad_vector ball2_T;
			Ad_vector ball2_H;
			double vt1 = v1.innerProduct(T);//cast to T direction
			double vt2 = v2.innerProduct(T);
			double v1p;
			double v2p;
			ball1_T = new Ad_vector(0,0);
			ball1_H = new Ad_vector(v1.innerProduct(H)*H.getX(),v1.innerProduct(H)*H.getY());
			ball2_T = new Ad_vector(0,0);
			ball2_H = new Ad_vector(v2.innerProduct(H)*H.getX(),v2.innerProduct(H)*H.getY());
			v1p = e*vt1+e1*vt2;
			v2p = -e*vt2+e2*vt1;
			ball1_T.set(v1p*T.getX(), v1p*T.getY());
			ball2_T.set(v2p*T.getX(), v2p*T.getY());
			ball1.setSpeed(ball1_T.getX()+ball1_H.getX(), ball1_T.getY()+ball1_H.getY());
			ball2.setSpeed(ball2_T.getX()+ball2_H.getX(), ball2_T.getY()+ball2_H.getY());
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(ball1.getColor());
		ball.setFrame(this.getRelativeLocation(ball1).getX()-ball1.getR(),this.getRelativeLocation(ball1).getY()-ball1.getR(),2*ball1.getR(),2*ball1.getR());
		g2.fill(ball);
		//g2.fillOval((int)(ball1.getLocation().getX()-ball1.getR()), (int)(ball1.getLocation().getY()-ball1.getR()), 1, 1);
		g2.setPaint(ball2.getColor());
		ball.setFrame(this.getRelativeLocation(ball2).getX()-ball2.getR(),this.getRelativeLocation(ball2).getY()-ball2.getR(),2*ball2.getR(),2*ball2.getR());
		g2.fill(ball);
		//g2.fillOval((int)(ball2.getLocation().getX()-ball2.getR()), (int)(ball2.getLocation().getY()-ball2.getR()), 1, 1);
	}
	//main control the motion of two balls
	public void RunningTime() throws AWTException,RuntimeException {
		//X = this.getSize().getWidth();
		//Y = this.getSize().getHeight();
		//updateLocation();
		if(ball1.getE()+ball2.getE()>E_total+1)
		{
			throw new AWTException("unnamed");
		}
		
		if(isEsc(ball2,ball1))
		{
			throw new RuntimeException("nammed");
		}
		
		if(detecter.isCrushed(ball1, ball2))
		{
			CorrectV();
		}
		
		//System.out.println("("+X+" , "+Y);
		//System.out.println("ball2 location = "+ball2.getLocation().getX()+" , "+ball2.getLocation().getY());
		//Correct();
		ball1.updateLocation();
		ball2.updateLocation();
		double ratio = 0.01;
		if(ball1.getLocation().distanceFrom(ball2.getLocation())<ball1.getR()+ball2.getR())
		{
			double formal_X1 = ball1.getLocation().getX();
			double formal_Y1 = ball1.getLocation().getY();
			double formal_X2 = ball2.getLocation().getX();
			double formal_Y2 = ball2.getLocation().getY();
			while(ball1.getLocation().distanceFrom(ball2.getLocation())<ball1.getR()+ball2.getR())
			{
				ball1.setLocation(formal_X1-ball1.getSpeed().getX()*ratio, formal_Y1-ball1.getSpeed().getY()*ratio);
				ball2.setLocation(formal_X2-ball2.getSpeed().getX()*ratio, formal_Y2-ball2.getSpeed().getY()*ratio);
				ratio = ratio + 0.01;
			}
			ratio = ratio - 0.02;
			ball1.setLocation(formal_X1-ball1.getSpeed().getX()*ratio, formal_Y1-ball1.getSpeed().getY()*ratio);
			ball2.setLocation(formal_X2-ball2.getSpeed().getX()*ratio, formal_Y2-ball2.getSpeed().getY()*ratio);
		}
		//here is the location for ...
		updateFrameLocation(ball1);
		//System.out.println("current Engine = "+(ball1.getE()+ball2.getE())+" total Engine = "+E_total);
		G.updateSource(ball2.getLocation(), ball2.getWeight());
		G.accelerationGenerate(ball1);
		G.updateSource(ball1.getLocation(), ball1.getWeight());
		G.accelerationGenerate(ball2);
		ball1.updateSpeed();
		ball2.updateSpeed();
		//updateLocation();
		repaint();
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	//initial the info
}
