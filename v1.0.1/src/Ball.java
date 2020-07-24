package main_graph;
/**
 * @author zjc
 * 2020.7.18
 * The abstract ball class which
 * contains several attributes necessary
 * for creating a real ball in the panel
 * actually it draw a ellipse on the panel
 * represents the real ball
 */
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends Ellipse2D.Double {
	
	private Ad_vector location;			//attribute of ball's location that is a vector
	private Ad_vector speed;			//attribute of ball's real-time speed which is also a vector
	private double m;					//the attribute of the ball's weight
	private double radius;				//the radius of the ball that is a Double type
	private Color color;				//color of the ball differs from another
	private Ad_vector currentP;			//the real-time momentum of the ball
	private double currentE;			//the real-time kinetic energy of the ball
	private Ad_vector acceleration;		//the real-time acceleration of the ball which is also, you know, a vector
	private Ad_vector relative_location;//the real-time relative coordination of the ball
	
	public Ball(double x,double y,double r,double mm,Color cc)
	{
		Random ro = new Random();
		radius = r;
		color = cc;
		m = mm;
		location = new Ad_vector(x,y);
		speed = new Ad_vector(Math.pow(-1, ro.nextInt())*ro.nextInt(5),Math.pow(-1, ro.nextInt())*ro.nextInt(5));
		acceleration = new Ad_vector(0,0);
		currentP = new Ad_vector(m*speed.getX(),m*speed.getY());
		relative_location = new Ad_vector(x,y);
		currentE = m*speed.getSquare();
	}
	public double getR() {
		return radius;
	}
	public Ad_vector getLocation() {
		return location;
	}
	public double getX() {
		return location.getX();
	}
	public double getY() {
		return location.getY();
	}
	public Ad_vector getSpeed() {
		return speed;
	}
	public double getVx() {
		return speed.getX();
	}
	public double getVy() {
		return speed.getY();
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
	public void setR(double rr) {
		radius = rr;
	}
	public void setWeight(double ww) {
		m = ww;
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
	public void updateRelativeLocation(Ad_vector fram_location) {
		//this method simply set the relative location value but very important
		relative_location.set(location.getX()-fram_location.getX(), location.getY()-fram_location.getY());
	}
	public Ad_vector getRelativeLocation() {
		return relative_location;
	}
}
