import java.awt.*;
import javax.swing.*;

public class Sorting extends JFrame {
	
	Graphics h;
	Image img;
    private int[] array = new int[500];
    
    public Sorting () {
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
    }
    
    public static void main(String a[]){
    }
    
    public void paint(Graphics g) {
		img=createImage(600,600);
		h=img.getGraphics();
		paintComponent(h);
		g.drawImage(img, 0, 0, this);
    }
    
    public void paintComponent(Graphics g) {
    	g.setColor(Color.black);
    	for (int x=0; x<array.length; x++) {
    		g.drawRect(2*x, 1000-(2*array[x]), 2, 2*array[x]);
    	}
    }
}