package threeDGame;

public class Controller {
	public double x;
	public double z;
	private double rotation;
	private double xa;
	private double za;
	private double rotationa;
	
	public void tick (boolean forward, boolean back, boolean left, boolean right, boolean turnLeft, boolean turnRight) {
		double rotationSpeed = 1;
		double walkSpeed=1;
		double xMove=0;
		double zMove=0;
		
		if (forward) {
			zMove+=1;
		}
		if (back) {
			zMove-=1;
		}
		if (left) {
			xMove-=1;
		}
		if (right) {
			xMove+=1;
		}
		if (turnLeft) {
			rotationa += rotationSpeed;
		}
		if (turnRight) {
			rotationa -= rotationSpeed;
		}
		
		xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
		za += (zMove * Math.cos(rotation) + xMove * Math.sin(rotation)) * walkSpeed;
		
		x+=xa;
		z+=za;
		xa *= .1;
		za *= .1;
		rotation += rotationa;
		rotationa *= .8;
	}
}
