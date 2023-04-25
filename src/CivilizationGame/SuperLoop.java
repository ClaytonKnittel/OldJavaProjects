package CivilizationGame;

public class SuperLoop {
	
	public static void main(String args[]) {
		long c=2;
		for (int x=0; x<61; x++) {
			c*=2;
		}
		c--;
		c*=2;
		c++;
		System.out.print("The biggest number a long can be: ");
		System.out.println(c);
		c++;
		System.out.print("The smallest number a long can be: ");
		System.out.println(c);
	}
}
