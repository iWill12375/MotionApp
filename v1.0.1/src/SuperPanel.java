package main_graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SuperPanel extends JPanel {
	
	private Ellipse2D.Double ball;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;
	
	public SuperPanel() {
		LineBorder bt = (LineBorder)BorderFactory.createLineBorder(Color.BLACK,1);
		this.setBorder(bt);
		ball = new Ellipse2D.Double(0,0,1,1);
		this.setOpaque(false);
		this.setBackground(Color.pink);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e)
			{
				newX = e.getX();
				newY = e.getY();
				DeliverMan.fram_X = DeliverMan.fram_X - 0.01*(newX-oldX);
				DeliverMan.fram_Y = DeliverMan.fram_Y - 0.01*(newY-oldY);
				/*
				DeliverMan.runtime_b1x = DeliverMan.runtime_b1x + 0.01*(newX-oldX);
				DeliverMan.runtime_b1y = DeliverMan.runtime_b1y + 0.01*(newY-oldY);
				DeliverMan.runtime_b2x = DeliverMan.runtime_b2x + 0.01*(newX-oldX);
				DeliverMan.runtime_b2y = DeliverMan.runtime_b2y + 0.01*(newY-oldY);
				DeliverMan.runtime_b3x = DeliverMan.runtime_b3x + 0.01*(newX-oldX);
				DeliverMan.runtime_b3y = DeliverMan.runtime_b3y + 0.01*(newY-oldY);
				*/
				SailingControl.setFrameLocation(DeliverMan.fram_X , DeliverMan.fram_Y);
				repaint();
			}
		});
		//repaint();
	}
	public void putXY() {
		DeliverMan.Pwidth = this.getWidth();
		DeliverMan.Pheight = this.getHeight();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.BLACK);
		g2.drawLine(-(Integer.MAX_VALUE/2)-(int)DeliverMan.fram_X, -(int)DeliverMan.fram_Y, (Integer.MAX_VALUE/2)-(int)DeliverMan.fram_X, -(int)DeliverMan.fram_Y);
		g2.drawLine(-(int)DeliverMan.fram_X, -(Integer.MAX_VALUE/2)-(int)DeliverMan.fram_Y, -(int)DeliverMan.fram_X, (Integer.MAX_VALUE/2)-(int)DeliverMan.fram_Y);
		g2.setPaint(Color.RED);
		ball.setFrame(DeliverMan.runtime_b1x-DeliverMan.B1R, DeliverMan.runtime_b1y-DeliverMan.B1R, 2*DeliverMan.B1R, 2*DeliverMan.B1R);
		g2.fill(ball);
		g2.setPaint(Color.YELLOW);
		ball.setFrame(DeliverMan.runtime_b2x-DeliverMan.B2R, DeliverMan.runtime_b2y-DeliverMan.B2R, 2*DeliverMan.B2R, 2*DeliverMan.B2R);
		g2.fill(ball);
		g2.setPaint(Color.BLUE);
		ball.setFrame(DeliverMan.runtime_b3x-DeliverMan.B3R, DeliverMan.runtime_b3y-DeliverMan.B3R, 2*DeliverMan.B3R, 2*DeliverMan.B3R);
		g2.fill(ball);
	}
	
	public void flush() {
		repaint();
	}
}
