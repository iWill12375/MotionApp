package main_graph;

public class ForceField {
	
	private double strength;
	public final static double G = 0.6;
	private double r2;
	private Ad_vector assist_vector1;
	private Ad_vector assist_vector2;
	
	public ForceField()
	{
		r2 = 0;
		assist_vector1 = new Ad_vector(0,0);
		assist_vector2 = new Ad_vector(0,0);
	}
	
	public void accelerationGenerate(Ball source1,Ball source2,Ball destin) {
		assist_vector1.set(source1.getX()-destin.getX(), source1.getY()-destin.getY());
		r2 = assist_vector1.getSquare();
		assist_vector1.unitlize();
		strength = G*source1.getWeight();
		assist_vector1.numMul(strength/r2);
		
		assist_vector2.set(source2.getX()-destin.getX(), source2.getY()-destin.getY());
		r2 = assist_vector2.getSquare();
		assist_vector2.unitlize();
		strength = G*source2.getWeight();
		assist_vector2.numMul(strength/r2);
		
		destin.setAcceleration(assist_vector1.getX()+assist_vector2.getX(), assist_vector1.getY()+assist_vector2.getY());
	}
	
	private boolean isEsc(Ball source1,Ball source2,Ball destin) {
		double GE = Math.sqrt(2*G*(source1.getWeight()/destin.getLocation().distanceFrom(source1.getLocation())+source2.getWeight()/destin.getLocation().distanceFrom(source2.getLocation())));
		System.out.println("GE = "+GE);
		System.out.println("destin sp = "+destin.getSpeed().getMod());
		if(destin.getSpeed().getMod()>GE+1) {
			return true;
		}
		return false;
	}
	public boolean isOverEsc(Ball b1,Ball b2,Ball b3) {
		if(isEsc(b1,b2,b3))
		{
			System.out.println("b3 over speed");
			return true;
		}
		if(isEsc(b1,b3,b2))
		{
			System.out.println("b2 over speed");
			return true;
		}
		if(isEsc(b2,b3,b1))
		{
			System.out.println("b1 over speed");
			return true;
		}
		return false;
	}
}
