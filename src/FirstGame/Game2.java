package FirstGame;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game2 extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	int x1=60;
	int y1=40;
	int xSpeed=0, ySpeed=0;
	private Image i;
	private Graphics h;
	
	public Game2() {
		addKeyListener(new kl());
		setSize(1000,600);
		setTitle("Awesome");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public class kl extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if (keyCode == KeyEvent.VK_UP)
				ySpeed=-1;
			if (keyCode == KeyEvent.VK_DOWN)
				ySpeed=1;
			if (keyCode == KeyEvent.VK_LEFT)
				xSpeed=-1;
			if (keyCode == KeyEvent.VK_RIGHT)
				xSpeed=1;
			if (keyCode == KeyEvent.VK_UP && ySpeed==1) {
				ySpeed=0;
			}
			if (keyCode == KeyEvent.VK_LEFT && xSpeed==1) {
				xSpeed=0;
			}
			if (keyCode == KeyEvent.VK_DOWN && ySpeed==-1) {
				ySpeed=0;
			}
			if (keyCode == KeyEvent.VK_RIGHT && xSpeed==-1) {
				xSpeed=0;
			}
		}
		public void keyReleased(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if (keyCode == KeyEvent.VK_UP) {
				ySpeed=0;
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				ySpeed=0;
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				xSpeed=0;
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				xSpeed=0;
			}
		}
	}
	
	public void run() {
		try {
			while (true) {
				x1+=xSpeed;
				y1+=ySpeed;
				if (x1<=50)
					x1=50;
				if (x1>=970)
					x1=970;
				if (y1<=30)
					y1=30;
				if (y1>=570)
					y1=570;
				
				if (x1>=70 && x1<=71 && y1<=400)
					x1=70;
				if ((y1==400||y1==399) && x1>=70 && x1<=130)
					y1=400;
				if (x1>=129 && x1<=130 && y1<=400)
					x1=130;
				
				if (x1>=70 && x1<=71 && y1>=420)
					x1=70;
				if ((y1==420||y1==421) && x1>=70 && x1<=130)
					y1=420;
				if (x1>=129 && x1<=130 && y1>=420)
					x1=130;
				
				repaint();
				Thread.sleep(5);
			}
			
		}
		catch(Exception e) {
			System.out.print("Error");
		}
	}
	
	public void paint(Graphics g) {
		i=createImage(getWidth(),getHeight());
		h=i.getGraphics();
		paintComponent(h);
		g.drawImage(i, 0, 0, this);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0,0,1000,600);
		g.setColor(Color.blue);
		g.fillRect(0,0,50,600);
		g.fillRect(0, 0, 1000, 30);
		g.fillRect(100,30,30,370);
		g.fillRect(100, 450, 30, 150);
		
		g.setColor(Color.red);
		g.fillOval(x1,y1,30,30);
	}
	
	public static void main(String args[]) {
		Game2 g=new Game2();
		Thread t=new Thread(g);
		t.start();
	}
	
}
