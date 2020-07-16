package main_graph;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.MenuEvent;
public class graph_engine {
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.println(1.0/2);
		System.out.println(1);
		
		JFrame mywin = new JFrame("my_windows");
		JButton b1 = new JButton("B1");
		b1.setSize(30, 20);
		SuperPanel mp = new SuperPanel();
		//TestPanel mp = new TestPanel(100,100,20);
		mywin.add(mp);
		//mp.setBackground(Color.BLACK);
		mywin.setLocation(300, 200);
		mywin.setSize(800, 600);
		mywin.setResizable(false);
		mywin.setLocationRelativeTo(null);
		mywin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mywin.setVisible(true);
		try {
			while(true)
			{
				mp.RunningTime();
			}
		}
		catch(AWTException ex)
		{
			JDialog jd = new JDialog(mywin,"system unstable",true);
			jd.setSize(200, 100);
			jd.setLocationByPlatform(true);
			jd.add(new JLabel("engine over flow!"),BorderLayout.CENTER);
			jd.setVisible(true);
		}
	}
}

class TestPanel extends JPanel{
	private Ellipse2D.Double ee;
	private double r;
	public TestPanel(double x,double y,double rr)
	{
		r = rr;
		ee = new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				ee.setFrame(e.getX()-r, e.getY()-r, 2*r, 2*r);
				repaint();
			}
		});
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(ee);
	}
}

class SuperPanel extends JPanel {
	private double X;
	private double Y;
	private double E_total;
	private Ball ball1;
	private Ball ball2;
	private CrushDetect detecter;
	private Ellipse2D.Double ball;
	private ForceField G;
	
	
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
		//System.out.println("ball1 location = "+ball1.getLocation().getX()+" , "+ball1.getLocation().getY());
		//System.out.println("ball2 location = "+ball2.getLocation().getX()+" , "+ball2.getLocation().getY());
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(ball1.getColor());
		ball.setFrame(ball1.getLocation().getX()-ball1.getR(),ball1.getLocation().getY()-ball1.getR(),2*ball1.getR(),2*ball1.getR());
		g2.fill(ball);
		g2.setPaint(ball2.getColor());
		ball.setFrame(ball2.getLocation().getX()-ball2.getR(),ball2.getLocation().getY()-ball2.getR(),2*ball2.getR(),2*ball2.getR());
		g2.fill(ball);
	}
	//control the move of two balls
	public void RunningTime() throws AWTException {
		
		if(ball1.getE()+ball2.getE()>E_total+1)
		{
			//JDialog reminder = new JDialog(this,"System is unstable",true);
			//throw new AWTException("unnamed");
		}
		if(detecter.isCrushed(ball1, ball2))
		{
			CorrectV();
		}
		
		//System.out.println("ball1 location = "+ball1.getLocation().getX()+" , "+ball1.getLocation().getY());
		//System.out.println("ball2 location = "+ball2.getLocation().getX()+" , "+ball2.getLocation().getY());
		Correct();
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
		//System.out.println("current Engine = "+(ball1.getE()+ball2.getE())+" total Engine = "+E_total);
		G.updateSource(ball2.getLocation(), ball2.getWeight());
		G.accelerationGenerate(ball1);
		G.updateSource(ball1.getLocation(), ball1.getWeight());
		G.accelerationGenerate(ball2);
		ball1.updateSpeed();
		ball2.updateSpeed();
		repaint();
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	//initial the info
	public SuperPanel() {
		ball = new Ellipse2D.Double(0,0,1,1);
		X = this.getSize().width;
		Y = this.getSize().height;
		ball1 = new Ball(500,400,10,10000,Color.RED);
		ball2 = new Ball(250,300,5,10,Color.green);
		//ball1.setSpeed(0, 0);
		//ball2.setSpeed(0, 4.5);
		E_total = ball1.getE()+ball2.getE();
		detecter = new CrushDetect();
		G = new ForceField(250,250,10000);
		//repaint();
	}
}
/*
class Ball extends Ellipse2D.Double {
	private Ellipse2D.Double ball;
	public Ball(double x,double y,double r) {
		ball = new Ellipse2D.Double(x-r,y-r,2*r,2*r);
	}
	public Ellipse2D.Double getBall() {
		return ball;
	}
}
*/
