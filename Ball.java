package main_graph;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends Ellipse2D.Double {
	private Ad_vector location;
	private Ad_vector speed;
	private double m;
	private double radius;
	private Color color;
	private Ad_vector currentP;
	private double currentE;
	//add a attribute acceleration
	private Ad_vector acceleration;
	
	public Ball(double x,double y,double r,double mm,Color cc)
	{
		Random ro = new Random();
		radius = r;
		color = cc;
		m = mm;
		location = new Ad_vector(x,y);
		speed = new Ad_vector(ro.nextInt(3),ro.nextInt(3));
		acceleration = new Ad_vector(0,0);
		currentP = new Ad_vector(m*speed.getX(),m*speed.getY());
		currentE = m*speed.getSquare();
	}
	public double getR() {
		return radius;
	}
	public Ad_vector getLocation() {
		return location;
	}
	public Ad_vector getSpeed() {
		return speed;
	}
	public double getWeight() {
		return m;
	}
	public void setLocation(double xx,double yy)
	{
		location.setX(xx);
		location.setY(yy);
	}
	public void setLocationX(double xx)
	{
		location.setX(xx);
	}
	public void setLocationY(double yy)
	{
		location.setY(yy);
	}
	public void setSpeed(double xx,double yy)
	{
		speed.setX(xx);
		speed.setY(yy);
		currentP.set(m*speed.getX(),m*speed.getY());
		currentE = m*speed.getSquare();
	}
	public void setSpeedX(double xx)
	{
		speed.setX(xx);
		currentP.set(m*speed.getX(),m*speed.getY());
		currentE = m*speed.getSquare();
	}
	public void setSpeedY(double yy)
	{
		speed.setY(yy);
		currentP.set(m*speed.getX(),m*speed.getY());
		currentE = m*speed.getSquare();
	}
	public void setAcceleration(double ax,double ay)
	{
		acceleration.set(ax, ay);
	}
	public void setAccelerationX(double ax)
	{
		acceleration.setX(ax);
	}
	public void setAccelerationY(double ay)
	{
		acceleration.setY(ay);
	}
	public Color getColor() {
		return color;
	}
	public double getE() {
		return currentE;
	}
	public void updateLocation() {
		location.Add(speed);
	}
	public void updateSpeed() {
		speed.Add(acceleration);
	}
	public Ad_vector getAcceleration() {
		return acceleration;
	}
}
