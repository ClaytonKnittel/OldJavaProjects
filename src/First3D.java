import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class First3D extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public Graphics h;
	public Image img;
	public int x, y;
	public int time;
	public double angle;
	private boolean end=false;
	private final double rt2=Math.sqrt(2);
	
	public First3D() {
		addKeyListener(new k());
		System.out.println("Constructor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setName("3D Supersquare");
		setVisible(true);
		time=0;
		y=350;
		x=300;
		angle=0;
		repaint();
	}
	
	public class k extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int k=e.getKeyCode();
			if (k==KeyEvent.VK_SPACE) {
				end=true;
			}
		}
	}
	
	public void run() {
		while (!end) {
			//x=250+(int)(200*Math.sin((Math.PI*time)/(50.0)));
			angle++;
			time++;
			if (time==100) time=0;
			if (angle==360) angle=0;
			repaint();
			try {
			Thread.sleep(15);
			} catch(Exception e) {}
		}
	}
	
	public static void main(String arsg[]) {
		First3D f=new First3D();
		Thread t=new Thread(f);
		t.start();
	}
	
	public void paint(Graphics g) {
		img=createImage(getWidth(),getHeight());
		h=img.getGraphics();
		paintComponent(h);
		g.drawImage(img,0,0,this);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.drawLine(x,y,x+(int)(100*Math.cos((angle*Math.PI)/180.0)),y-(int)(100*Math.sin((angle*Math.PI)/180.0)));
		g.drawLine(x,y,x+(int)(100*Math.sin((angle*Math.PI)/180.0)),y+(int)(100*Math.cos((angle*Math.PI)/180.0)));
		g.drawLine(x+(int)(100*Math.cos((angle*Math.PI)/180.0)),y-(int)(100*Math.sin((angle*Math.PI)/180.0)),x+(int)(Math.cos(((45-angle)*Math.PI)/180.0)*(100*rt2)),y+(int)(Math.sin(((45-angle)*Math.PI)/180.0)*(100*rt2)));
		g.drawLine(x+(int)(Math.cos(((45-angle)*Math.PI)/180.0)*(100*rt2)),y+(int)(Math.sin(((45-angle)*Math.PI)/180.0)*(100*rt2)),x+(int)(100*Math.sin((angle*Math.PI)/180.0)),y+(int)(100*Math.cos((angle*Math.PI)/180.0)));
		g.setColor(Color.red);
		//g.drawLine(x,y,x+(int)(Math.cos(((45-angle)*Math.PI)/180.0)*(100*rt2)),y);
		//g.drawLine(x,y,x,y+(int)(Math.sin(((45-angle)*Math.PI)/180.0)*(100*rt2)));
		g.drawString(""+time+"      "+angle, 100, 100);
	}

}
