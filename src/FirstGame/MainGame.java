package FirstGame;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class MainGame extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	static int x=50, y=50, xDirection, yDirection;
	static int a=100,b=100, aDirection, bDirection;
	static int c=0,d=0;
	private Image dbImage;
	private Graphics dbg;
	Font font=new Font("Times New Roman", Font.BOLD, 30);
	private static boolean movable=true;
	public int score = 0;
	private static boolean decoy=false;
	private static int lastDirection=0;
	private static int counter=0;
	private static int lastA=0,lastB=0;
	
	public void run() {
		try {
			while (true) {
				move();
				if (x<=a+50 && x>=a-20 && y<=b+50 && y>=b-20) {
					movable=false;
				}
				if (x>=c-20 && x<=c+10 && y>=d-20 && y<=d+10) {
					score++;
					c=(int)(Math.random()*490);
					d=(int)(Math.random()*490);
				}
				if (decoy) {
					counter++;
					if (counter>200)
						decoy=false;
				}
				Thread.sleep(5);
			}
		}
		catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	
	public void move() {
		if (movable) {
			x+=xDirection*2;
			y+=yDirection*2;
			if (x<=0)
				x=0;
			if (x>=450)
				x=450;
			if (y<=20)
				y=20;
			if (y>=450)
				y=20;
			a+=aDirection;
			b+=bDirection;
			if (a<=0)
				a=0;
			if (a>=450)
				a=450;
			if (b<=20)
				b=20;
			if (b>=450)
				b=450;
		}
		
	}
	
	public void setXDir(int xdir) {
		xDirection=xdir;
	}
	
	public void setYDir(int ydir) {
		yDirection=ydir;
	}
	
	public void setADir(int adir) {
		aDirection=adir;
	}
	
	public void setBDir(int bdir) {
		bDirection=bdir;
	}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if (movable) {
				if (keyCode == KeyEvent.VK_LEFT) {
					setXDir(-1);
				}
				if (keyCode == KeyEvent.VK_RIGHT) {
					setXDir(1);
				}
				if (keyCode == KeyEvent.VK_UP) {
					setYDir(-1);
				}
				if (keyCode == KeyEvent.VK_DOWN) {
					setYDir(1);
				}
				
				if (keyCode == KeyEvent.VK_A) {
					setADir(-1);
					if (!decoy)
						lastDirection=1;
				}
				if (keyCode == KeyEvent.VK_D) {
					setADir(1);
					if (!decoy)
						lastDirection=2;
				}
				if (keyCode == KeyEvent.VK_W) {
					setBDir(-1);
					if (!decoy)
						lastDirection=3;
				}
				if (keyCode == KeyEvent.VK_S) {
					setBDir(1);
					if (!decoy)
						lastDirection=4;
				}
				if (keyCode == KeyEvent.VK_E) {
					decoy=true;
					lastA=a;
					lastB=b;
				}
			}
			repaint();
		}
		public void keyReleased(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT) {
				setXDir(0);
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				setXDir(0);
			}
			if (keyCode == KeyEvent.VK_UP) {
				setYDir(0);
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				setYDir(0);
			}
			
			if (keyCode == KeyEvent.VK_A) {
				setADir(0);
			}
			if (keyCode == KeyEvent.VK_D) {
				setADir(0);
			}
			if (keyCode == KeyEvent.VK_W) {
				setBDir(0);
			}
			if (keyCode == KeyEvent.VK_S) {
				setBDir(0);
			}
			repaint();
			
		}
	}
	
	public MainGame() {
		addKeyListener(new AL());
		setTitle("KeyPress");
		setSize(500,500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		a=100;
		b=100;
		c=(int)(Math.random()*490);
		d=(int)(Math.random()*490);
		x=50;
		y=50;
	}
	
	//Double buffering
	public void paint(Graphics g) {
		dbImage=createImage(getWidth(),getHeight());
		dbg=dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage,0,0,this);
	}
	
	public void paintComponent(Graphics g) {
		setFont(font);
		setBackground(Color.blue);
		g.setColor(Color.red);
		g.drawString(("Score: "+score),50,50);
		g.fillRect(x, y, 20, 20);
		g.setColor(Color.green);
		if (decoy) {
			int moveA=0;
			int moveB=0;
			if (lastDirection==1) {
				moveA=-1*counter;
				moveB=0;
			}
			else if (lastDirection==2) {
				moveA=1*counter;
				moveB=0;
			}
			else if (lastDirection==3) {
				moveA=0;
				moveB=-1*counter;
			}
			else {
				moveA=0;
				moveB=1*counter;
			}
			g.fillRect(lastA+moveA, lastB+moveB, 50, 50);
		}
		else
			g.fillRect(a, b, 50, 50);
		g.setColor(Color.white);
		g.fillRect(c,d,10,10);
	}
	
	public static void main(String args[]) {
		MainGame mg=new MainGame();
		
		Thread t1=new Thread(mg);
		t1.start();
	}
}