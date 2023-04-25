package CivilizationGame;


public class Render3D extends WorldGenerator {
	private static final long serialVersionUID = 1L;
	
	public int[] pixels=new int[360000];
	
	public Render3D() {
		int x=1;
	}
	
	public void draw3D(int[][] s, int[][] type, int [][] clouds, int xPos, int yPos, int scale) {
		for (double i=0;i<200;i++) {
			double top=scale+((i/200.0)*((2*(double)scale)/3));
			double bottom=scale+(((i+1)/200.0)*((2*(double)scale)/3));
			for (double j=0;j<200;j++) {
				System.out.println(top+" "+bottom);
			}
		}
	}
}
