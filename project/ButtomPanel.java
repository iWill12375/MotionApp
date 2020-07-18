package main_graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class ButtomPanel extends JPanel{
	private Ellipse2D.Double point;
	private Color color;
	public ButtomPanel() {
		point = new Ellipse2D.Double(0,0,2,2);
		color = Color.BLACK;
		this.setBackground(Color.ORANGE);
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(color);
		g2.fill(point);
	}
	public void setP(double x,double y) {
		point.setFrame(x, y, 2, 2);
	}
	public void depictTrack(double x,double y) {
		setP(x,y);
		repaint();
	}
	public void setColor(Color cc) {
		color = cc;
	}
}
