package TwoPlayerGames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class BoxyShoot extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	/** Lists 
	 * size() = length of list
	 * add(int) = adds int to list
	 * add(index, int) = adds int to list at position index
	 * clear() = clears the list
	 */
	
	public Graphics h;
	public Image img;
	
	public int gamemode=0;
	
	public static double oneX, oneY, oneAngle, bx, by, twoX, twoY, twoAngle, bbx, bby;
	public static int oYSpeed, oTSpeed, tYSpeed, tTSpeed;
	
	public int weaponTimer=300;
	
	public boolean rAlive=true;
	public int time=0, totalTime=0;
	
	public boolean machineGun, mGShooting, sprayGun, laser;
	public int mGAmmo, sGAmmo;
	
	public List<Double> redX=new ArrayList<Double>();
	public List<Double> redY=new ArrayList<Double>();
	public List<Double> redA=new ArrayList<Double>();
	public List<Integer> redL=new ArrayList<Integer>();
	public List<Double> blueX=new ArrayList<Double>();
	public List<Double> blueY=new ArrayList<Double>();
	public List<Double> blueA=new ArrayList<Double>();
	public List<Integer> blueL=new ArrayList<Integer>();
	
	public List<Double> redPiecesX=new ArrayList<Double>();
	public List<Double> redPiecesY=new ArrayList<Double>();
	public List<Double> redPiecesA=new ArrayList<Double>();
	public List<Double> redPiecesS=new ArrayList<Double>();
	
	public List<Integer> weaponX=new ArrayList<Integer>();
	public List<Integer> weaponY=new ArrayList<Integer>();
	public List<Integer> weaponType=new ArrayList<Integer>();
	
	public List<Double> laserX=new ArrayList<Double>();
	public List<Double> laserY=new ArrayList<Double>();
	
	public int timer=0;
	public int lTime;
	
	public BoxyShoot() {
		addKeyListener(new k());
		setSize(600,600);
		setTitle("BoxyShoot (ShoxyBoot)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		oneX=290;
		oneY=145;
		oneAngle=90;
		twoX=290;
		twoY=445;
		twoAngle=270;
		oYSpeed=0;
		oTSpeed=0;
		tYSpeed=0;
		tTSpeed=0;
		machineGun=false;
		mGShooting=false;
		mGAmmo=0;
		sprayGun=false;
		sGAmmo=0;
		laser=false;
		weaponTimer=300;
		lTime=0;
		repaint();
	}
	
	public void reset() {
		rAlive=true;
		oneX=290;
		oneY=145;
		oneAngle=90;
		twoX=290;
		twoY=445;
		twoAngle=270;
		oYSpeed=0;
		oTSpeed=0;
		tYSpeed=0;
		tTSpeed=0;
		redX.clear();
		redY.clear();
		redA.clear();
		redL.clear();
		blueX.clear();
		blueY.clear();
		blueA.clear();
		blueL.clear();
		redPiecesX.clear();
		redPiecesY.clear();
		redPiecesA.clear();
		redPiecesS.clear();
		weaponX.clear();
		weaponY.clear();
		weaponType.clear();
		laserX.clear();
		laserY.clear();
		totalTime=0;
		time=0;
		machineGun=false;
		mGShooting=false;
		mGAmmo=0;
		sprayGun=false;
		sGAmmo=0;
		laser=false;
		weaponTimer=300;
		lTime=0;
		repaint();
	}
	
	
	public class k extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int k=e.getKeyCode();
			if (k==KeyEvent.VK_UP && rAlive) {
				oYSpeed=4;
			}
			if (k==KeyEvent.VK_DOWN && rAlive) {
				oYSpeed=-3;
			}
			if (k==KeyEvent.VK_RIGHT && rAlive) {
				oTSpeed=5;
			}
			if (k==KeyEvent.VK_LEFT && rAlive) {
				oTSpeed=-5;
			}
			if (k==KeyEvent.VK_W) {
				tYSpeed=-4;
			}
			if (k==KeyEvent.VK_S) {
				tYSpeed=4;
			}
			if (k==KeyEvent.VK_A) {
				tTSpeed=-5;
			}
			if (k==KeyEvent.VK_D) {
				tTSpeed=5;
			}
			if (k==KeyEvent.VK_SLASH && rAlive && gamemode==0) {
				try {
					if (machineGun) {
						mGShooting=true;
					}
					else if (sprayGun) {
						for (int x=0; x<360; x++) {
							redX.add((29*(Math.cos(((oneAngle+x)*Math.PI)/180.0))+oneX));
							redY.add((29*(Math.sin(((oneAngle+x)*Math.PI)/180.0))+oneY));
							redA.add(oneAngle+x);
							redL.add(100);
						}
						sprayGun=false;
					}
					else if (laser) {
						double angle=oneAngle;
						double xx=29*(Math.cos(((angle)*Math.PI)/180.0))+oneX;
						double yy=29*(Math.sin(((angle)*Math.PI)/180.0))+oneY;
						for (int x=0; x<1000; x++) {
							xx+=Math.cos(angle*Math.PI/180.0);
							yy+=Math.sin(angle*Math.PI/180.0);
							laserX.add(xx);
							laserY.add(yy);
							if (xx>600 || xx<0) angle=180-angle;
							if (yy<25 || yy>600) angle=360-angle;
						}
						lTime=30;
						laser=false;
					}
					else if (redX.size()<6) {
					redX.add((29*(Math.cos((oneAngle*Math.PI)/180.0))+oneX));
					redY.add((29*(Math.sin((oneAngle*Math.PI)/180.0))+oneY));
					redA.add(oneAngle);
					redL.add(300);
					}
				} catch(Exception ex) {}
			}
			if (k==KeyEvent.VK_Q) {
				
			}
		}
		public void keyReleased(KeyEvent e) {
			int k=e.getKeyCode();
			if (k==KeyEvent.VK_UP) {
				oYSpeed=0;
			}
			if (k==KeyEvent.VK_DOWN) {
				oYSpeed=0;
			}
			if (k==KeyEvent.VK_RIGHT) {
				oTSpeed=0;
			}
			if (k==KeyEvent.VK_LEFT) {
				oTSpeed=0;
			}
			if (k==KeyEvent.VK_W) {
				tYSpeed=0;
			}
			if (k==KeyEvent.VK_S) {
				tYSpeed=0;
			}
			if (k==KeyEvent.VK_A) {
				tTSpeed=0;
			}
			if (k==KeyEvent.VK_D) {
				tTSpeed=0;
			}
			if (k==KeyEvent.VK_SLASH) {
				mGShooting=false;
			}
		}
	}
	
	
	
	public void run() {
		while (true) {
			time++;
			if (gamemode==1 && time==1 && rAlive) {
				totalTime++;
				time=0;
				int r=(int)(Math.random()*4);
				int ra=(int)(Math.random()*600);
				switch (r) {
				case 0:
					redX.add(ra*1.0);
					redY.add(26.0);
					redA.add(Math.random()*90+45);
					redL.add(-1);
					break;
				case 1:
					redX.add(1.0);
					redY.add(ra*1.0);
					redA.add(Math.random()*90-45);
					break;
				case 2:
					redX.add(ra*1.0);
					redY.add(599.0);
					redA.add(Math.random()*90+135);
					break;
				case 3:
					redX.add(599.0);
					redY.add(ra*1.0);
					redA.add(Math.random()*90-135);
					break;
				}
			}
			if (rAlive) {
				oneX+=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
				oneY+=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
				oneAngle+=oTSpeed;
			}
			twoX+=tYSpeed*Math.cos((twoAngle*Math.PI)/180.0);
			twoY+=tYSpeed*Math.sin((twoAngle*Math.PI)/180.0);
			twoAngle+=tTSpeed;
			
			if (rAlive) {
				if (20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX<0) {
					if (oYSpeed!=0) {
						if (oneAngle>=150 && oneAngle<180) {
							oneAngle+=5;
						}
						if (oneAngle>180 && oneAngle<=210) {
							oneAngle-=5;
						}
						if (oneAngle>210 && oneAngle<270) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<150) {
							oneAngle-=5;
						}
						if (oneAngle==180) {
							oneX=(10*Math.sqrt(3));
						}
						if (20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX<0) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while (20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX<0) {
							oneX+=4;
						}
					}
				}
				if ((20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY)<25) {
					if (oYSpeed!=0) {
						if (oneAngle<270 && oneAngle>=240) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<=310) {
							oneAngle-=5;
						}
						if (oneAngle>180 && oneAngle<240) {
							oneAngle-=5;
						}
						if (oneAngle>310 && oneAngle<360) {
							oneAngle+=5;
						}
						if ((20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY)<25) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY)<25) {
							oneY+=4;
						}
					}
				}
				if ((int)(20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX)<0) {
					if (oYSpeed!=0) {
						if (oneAngle>=150 && oneAngle<180) {
							oneAngle+=5;
						}
						if (oneAngle>180 && oneAngle<=210) {
							oneAngle-=5;
						}
						if (oneAngle>210 && oneAngle<270) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<150) {
							oneAngle-=5;
						}
						if (oneAngle==180) {
							oneX=(10*Math.sqrt(3));
						}
						if ((int)(20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX)<0) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX)<0) {
							oneX+=4;
						}
					}
				}
				if ((int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY)<25) {
					if (oYSpeed!=0) {
						if (oneAngle<270 && oneAngle>=240) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<=310) {
							oneAngle-=5;
						}
						if (oneAngle>180 && oneAngle<240) {
							oneAngle-=5;
						}
						if (oneAngle>310 && oneAngle<360) {
							oneAngle+=5;
						}
						if ((int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY)<25) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY)<25) {
							oneY+=4;
						}
					}
				}
				if ((int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX)<0) {
					if (oYSpeed!=0) {
						if (oneAngle>=330 && oneAngle<360) {
							oneAngle+=5;
						}
						if (oneAngle>0 && oneAngle<=30) {
							oneAngle-=5;
						}
						if (oneAngle>30 && oneAngle<90) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<330) {
							oneAngle-=5;
						}
						if (oneAngle==0) {
							oneX=(10*Math.sqrt(3));
						}
						if ((int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX)<0) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX)<0) {
							oneX+=4;
						}
					}
				}
				if ((int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY)<25) {
					if (oYSpeed!=0) {
						if (oneAngle<90 && oneAngle>=60) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<=120) {
							oneAngle-=5;
						}
						if (oneAngle>0 && oneAngle<60) {
							oneAngle-=5;
						}
						if (oneAngle>120 && oneAngle<180) {
							oneAngle+=5;
						}
						if ((int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY)<25) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY)<25) {
							oneY+=4;
						}
					}
				}
				if ((int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX)<0) {
					if (oYSpeed!=0) {
						if (oneAngle>=330 && oneAngle<360) {
							oneAngle+=5;
						}
						if (oneAngle>0 && oneAngle<=30) {
							oneAngle-=5;
						}
						if (oneAngle>30 && oneAngle<90) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<330) {
							oneAngle-=5;
						}
						if (oneAngle==0) {
							oneX=(10*Math.sqrt(3));
						}
						if ((int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX)<0) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX)<0) {
							oneX+=4;
						}
					}
				}
				if ((int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY)<25) {
					if (oYSpeed!=0) {
						if (oneAngle<90 && oneAngle>=60) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<=120) {
							oneAngle-=5;
						}
						if (oneAngle>0 && oneAngle<60) {
							oneAngle-=5;
						}
						if (oneAngle>120 && oneAngle<180) {
							oneAngle+=5;
						}
						if ((int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY)<25) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY)<25) {
							oneY+=4;
						}
					}
				}
				
				
				
				
				if (20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX>600) {
					if (oYSpeed!=0) {
						if (oneAngle>=330 && oneAngle<360) {
							oneAngle+=5;
						}
						if (oneAngle>0 && oneAngle<=30) {
							oneAngle-=5;
						}
						if (oneAngle>30 && oneAngle<90) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<330) {
							oneAngle-=5;
						}
						if (oneAngle==0) {
							oneX=600-(10*Math.sqrt(3));
						}
						if ((int)(20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while (20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX>600) {
							oneX-=4;
						}
					}
				}
				if ((20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY)>600) {
					if (oYSpeed!=0) {
						if (oneAngle<90 && oneAngle>=60) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<=120) {
							oneAngle-=5;
						}
						if (oneAngle>0 && oneAngle<60) {
							oneAngle-=5;
						}
						if (oneAngle>120 && oneAngle<180) {
							oneAngle+=5;
						}
						if ((int)(20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY)>600) {
							oneY-=4;
						}
					}
				}
				if ((int)(20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX)>600) {
					if (oYSpeed!=0) {
						if (oneAngle>=330 && oneAngle<360) {
							oneAngle+=5;
						}
						if (oneAngle>0 && oneAngle<=30) {
							oneAngle-=5;
						}
						if (oneAngle>30 && oneAngle<90) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<330) {
							oneAngle-=5;
						}
						if (oneAngle==0) {
							oneX=600-(10*Math.sqrt(3));
						}
						if ((int)(20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while (20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX>600) {
							oneX-=4;
						}
					}
				}
				if ((int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY)>600) {
					if (oYSpeed!=0) {
						if (oneAngle<90 && oneAngle>=60) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<=120) {
							oneAngle-=5;
						}
						if (oneAngle>0 && oneAngle<60) {
							oneAngle-=5;
						}
						if (oneAngle>120 && oneAngle<180) {
							oneAngle+=5;
						}
						if ((int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY)>600) {
							oneY-=4;
						}
					}
				}
				if ((int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX)>600) {
					if (oYSpeed!=0) {
						if (oneAngle>=150 && oneAngle<180) {
							oneAngle+=5;
						}
						if (oneAngle>180 && oneAngle<=210) {
							oneAngle-=5;
						}
						if (oneAngle>210 && oneAngle<270) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<150) {
							oneAngle-=5;
						}
						if (oneAngle==180) {
							oneX=600-(10*Math.sqrt(3));
						}
						if ((int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX)>600) {
							oneX-=4;
						}
					}
				}
				if ((int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY)>600) {
					if (oYSpeed!=0) {
						if (oneAngle<270 && oneAngle>=240) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<=310) {
							oneAngle-=5;
						}
						if (oneAngle>180 && oneAngle<240) {
							oneAngle-=5;
						}
						if (oneAngle>310 && oneAngle<360) {
							oneAngle+=5;
						}
						if ((int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY)>600) {
							oneY-=4;
						}
					}
				}
				if ((int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX)>600) {
					if (oYSpeed!=0) {
						if (oneAngle>=150 && oneAngle<180) {
							oneAngle+=5;
						}
						if (oneAngle>180 && oneAngle<=210) {
							oneAngle-=5;
						}
						if (oneAngle>210 && oneAngle<270) {
							oneAngle+=5;
						}
						if (oneAngle>90 && oneAngle<150) {
							oneAngle-=5;
						}
						if (oneAngle==180) {
							oneX=600-(10*Math.sqrt(3));
						}
						if ((int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX)>600) {
							oneX-=4;
						}
					}
				}
				if ((int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY)>600) {
					if (oYSpeed!=0) {
						if (oneAngle<270 && oneAngle>=240) {
							oneAngle+=5;
						}
						if (oneAngle>270 && oneAngle<=310) {
							oneAngle-=5;
						}
						if (oneAngle>180 && oneAngle<240) {
							oneAngle-=5;
						}
						if (oneAngle>310 && oneAngle<360) {
							oneAngle+=5;
						}
						if ((int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY)>600) {
							oneX-=oYSpeed*Math.cos((oneAngle*Math.PI)/180.0);
							oneY-=oYSpeed*Math.sin((oneAngle*Math.PI)/180.0);
						}
					}
					else {
						while ((int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY)>600) {
							oneY-=4;
						}
					}
				}
				
				
				
				for (int x=0; x<redX.size(); x++) {
					redX.set(x, (redX.get(x)+5*Math.cos((redA.get(x)*Math.PI)/180.0)));
				}
				for (int x=0; x<redY.size(); x++) {
					redY.set(x, (redY.get(x)+5*Math.sin((redA.get(x)*Math.PI)/180.0)));
				}
				
				
				
				for (int x=0; x<redX.size(); x++) {
					if (redX.get(x)<0) {
						redA.set(x, 180-redA.get(x));
					}
					if (redY.get(x)<25) {
						redA.set(x, 360-redA.get(x));
					}
					if (redX.get(x)>600) {
						redA.set(x, 180-redA.get(x));
					}
					if (redY.get(x)>600) {
						redA.set(x, 360-redA.get(x));
					}
				}
				
				if (gamemode!=1) {
					for (int x=0; x<redX.size(); x++) {
						if (redL.get(x)<=0) {
							redX.remove(x);
							redY.remove(x);
							redA.remove(x);
							redL.remove(x);
							x--;
						}
					}
				}
				
				for (int x=0; x<redL.size(); x++) {
					redL.set(x, redL.get(x)-1);
				}
				
				
				
				
				
				
				
				if (oneAngle==360) oneAngle=0;
				if (oneAngle==-5) oneAngle=355;
				
				
				
				if (mGShooting && mGAmmo>0) {
					mGAmmo--;
					if (mGAmmo<=0) machineGun=false;
					redX.add((29*(Math.cos((oneAngle*Math.PI)/180.0))+oneX));
					redY.add((29*(Math.sin((oneAngle*Math.PI)/180.0))+oneY));
					redA.add(oneAngle);
					redL.add(300);
					redX.add((29*(Math.cos(((oneAngle-3)*Math.PI)/180.0))+oneX));
					redY.add((29*(Math.sin(((oneAngle-3)*Math.PI)/180.0))+oneY));
					redA.add(oneAngle-3);
					redL.add(300);
					redX.add((29*(Math.cos(((oneAngle+3)*Math.PI)/180.0))+oneX));
					redY.add((29*(Math.sin(((oneAngle+3)*Math.PI)/180.0))+oneY));
					redA.add(oneAngle+3);
					redL.add(300);
				}
				
				
				for (int x=0; x<redX.size(); x++) {
					boolean f=false;
					boolean r=false;
					boolean l=false;
					boolean b=false;
					double x1=20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX;
					double y1=20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY;
					double x2=20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX;
					double y2=20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY;
					double x3=20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX;
					double y3=20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY;
					double x4=20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX;
					double y4=20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY;
					double slopeF=(y1-y2)/(x1-x2);
					double cF=-slopeF*x1+y1;
					double slopeL=(y2-y3)/(x2-x3);
					double cL=-slopeL*x2+y2;
					double slopeB=(y3-y4)/(x3-x4);
					double cB=-slopeB*x3+y3;
					double slopeR=(y4-y1)/(x4-x1);
					double cR=-slopeR*x4+y4;
					
					
					if (oneAngle<180 && oneAngle>0) {
						if (redY.get(x)<=(redX.get(x)*slopeF)+cF)
							f=true;
						if (redY.get(x)>=(redX.get(x)*slopeB)+cB)
							b=true;
						
					}
					else  if (oneAngle>180 && oneAngle<360) {
						if (redY.get(x)>=(redX.get(x)*slopeF)+cF)
							f=true;
						if (redY.get(x)<=(redX.get(x)*slopeB)+cB)
							b=true;
					}
					else if (oneAngle==0) {
						if (redX.get(x)<=x1)
							f=true;
						if (redX.get(x)>=x3)
							b=true;
					}
					else {
						if (redX.get(x)>=x1)
							f=true;
						if (redX.get(x)<=x3)
							b=true;
					}
					
					
					if (oneAngle>90 && oneAngle<270) {
						if (redY.get(x)<=(redX.get(x)*slopeL)+cL)
							l=true;
						if (redY.get(x)>=(redX.get(x)*slopeR)+cR)
							r=true;
					}
					else if ((oneAngle>270 && oneAngle<360) || (oneAngle>=0 && oneAngle<90)) {
						if (redY.get(x)>=(redX.get(x)*slopeL)+cL)
							l=true;
						if (redY.get(x)<=(redX.get(x)*slopeR)+cR)
							r=true;
					}
					else if (oneAngle==90) {
						if (redX.get(x)<=x3)
							l=true;
						if (redX.get(x)>=x4)
							r=true;
					}
					else {
						if (redX.get(x)>=x3)
							l=true;
						if (redX.get(x)<=x4)
							r=true;
					}
					
					
					if (f&&b&&l&&r) {
						rAlive=false;
						for (int xx=0; xx<50; xx++) {
							timer=150;
							redPiecesX.add(20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX-4*(xx/10)*Math.sin((oneAngle*Math.PI)/180.0)-4*(xx%10)*Math.cos((oneAngle*Math.PI)/180.0));
							redPiecesY.add(20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY+4*(xx/10)*Math.cos((oneAngle*Math.PI)/180.0)-4*(xx%10)*Math.sin((oneAngle*Math.PI)/180.0));
							if (oneX==redPiecesX.get(xx)) {
								if (oneY>redPiecesY.get(xx))
									redPiecesA.add(2*Math.PI/3);
								else
									redPiecesA.add(Math.PI/2);
							}
							else
								redPiecesA.add(180.0*Math.atan((redPiecesY.get(xx)-oneY)/(redPiecesX.get(xx)-oneX))/Math.PI);
							redPiecesS.add(Math.sqrt(Math.pow((oneX-redPiecesX.get(xx)), 2)+Math.pow((oneY-redPiecesY.get(xx)) ,2)));
							
						}
					}
				}
			}
			
			if (!rAlive) {
				timer--;
				for (int xx=0; xx<redPiecesX.size(); xx++) {
					redPiecesX.set(xx, redPiecesX.get(xx)+redPiecesS.get(xx)*Math.cos(redPiecesA.get(xx)));
					redPiecesY.set(xx, redPiecesY.get(xx)+redPiecesS.get(xx)*Math.sin(redPiecesA.get(xx)));
					
					if (redPiecesX.get(xx)<0 || redPiecesX.get(xx)>600)
						redPiecesA.set(xx, Math.PI-redPiecesA.get(xx));
					if (redPiecesY.get(xx)<0 || redPiecesY.get(xx)>600)
						redPiecesA.set(xx, 2*Math.PI-redPiecesA.get(xx));
					
					
					if (redPiecesS.get(xx)>0) {
						redPiecesS.set(xx, redPiecesS.get(xx)-redPiecesS.get(xx)/16);
					}
					else
						redPiecesS.set(xx, 0.0);
				}
				if (timer<=0) {
					reset();
				}
			}
			
			if (rAlive) {
				weaponTimer--;
				if (weaponTimer<=0) {
					weaponTimer=300;
					int xVal=(int)(Math.random()*29);
					int yVal=(int)(Math.random()*29);
					xVal*=20;
					yVal*=20;
					boolean fum=true;
					for (int x=0; x<weaponX.size(); x++) {
						if (xVal==weaponX.get(x) && yVal==weaponY.get(x)) {
							fum=false;
						}
					}
					if (fum) {
						weaponX.add(xVal);
						weaponY.add(yVal);
						int ww=(int)(Math.random()*17);
						int www=ww/8;
						weaponType.add(www);
					}
				}
			}
			
			for (int x=0; x<weaponX.size(); x++) {
				if (!machineGun && !sprayGun && !laser) {
					if (oneX>=weaponX.get(x)-10 && oneX<=weaponX.get(x)+30 && oneY>=weaponY.get(x)-10 && oneY<=weaponY.get(x)+30) {
						if (weaponType.get(x)==0) {
							laser=true;
						}
						else if (weaponType.get(x)==1) {
							sprayGun=true;
						}
						else if (weaponType.get(x)==2) {
							machineGun=true;
							mGAmmo=30;
						}
						weaponX.remove(x);
						weaponY.remove(x);
						weaponType.remove(x);
						x--;
					}
				}
			}
			
			lTime--;
			if (lTime<=0) {
				laserX.clear();
				laserY.clear();
			}
			
			repaint();
			try {
				Thread.sleep(20);
			} catch (Exception e) {}
		}
	}
	
	
	public static void main(String args[]) {
		BoxyShoot b=new BoxyShoot();
		Thread t=new Thread(b);
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
		g.drawString(redX.size()+"  ",10,40);
		
			g.drawString(""+totalTime, 297, 40);
		
		g.setColor(Color.black);
		
		/**
		if (rAlive) {
			for (int x=0; x<600; x+=5) {
				for (int y=0; y<600; y+=5) {
					boolean f=false;
					boolean r=false;
					boolean l=false;
					boolean b=false;
					double x1=20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX;
					double y1=20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY;
					double x2=20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX;
					double y2=20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY;
					double x3=20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX;
					double y3=20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY;
					double x4=20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX;
					double y4=20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY;
					double slopeF=(y1-y2)/(x1-x2);
					double cF=-slopeF*x1+y1;
					double slopeL=(y2-y3)/(x2-x3);
					double cL=-slopeL*x2+y2;
					double slopeB=(y3-y4)/(x3-x4);
					double cB=-slopeB*x3+y3;
					double slopeR=(y4-y1)/(x4-x1);
					double cR=-slopeR*x4+y4;
					
					
					if (oneAngle<180 && oneAngle>0) {
						if (y<=(x*slopeF)+cF)
							f=true;
						if (y>=(x*slopeB)+cB)
							b=true;
						
					}
					else  if (oneAngle>180 && oneAngle<360) {
						if (y>=(x*slopeF)+cF)
							f=true;
						if (y<=(x*slopeB)+cB)
							b=true;
					}
					else if (oneAngle==0) {
						if (x<=x1)
							f=true;
						if (x>=x3)
							b=true;
					}
					else {
						if (x>=x1)
							f=true;
						if (x<=x3)
							b=true;
					}
					
					
					if (oneAngle>90 && oneAngle<270) {
						if (y<=(x*slopeL)+cL)
							l=true;
						if (y>=(x*slopeR)+cR)
							r=true;
					}
					else if ((oneAngle>270 && oneAngle<360) || (oneAngle>=0 && oneAngle<90)) {
						if (y>=(x*slopeL)+cL)
							l=true;
						if (y<=(x*slopeR)+cR)
							r=true;
					}
					else if (oneAngle==90) {
						if (x<=x3)
							l=true;
						if (x>=x4)
							r=true;
					}
					else {
						if (x>=x3)
							l=true;
						if (x<=x4)
							r=true;
					}
					
					
					if (f&&b&&l&&r) {
						g.drawLine(x, y, x, y);
					}
					
					
				}
			}
		}
		**/
		
		
		
		
		if (rAlive) {
			g.setColor(Color.red);
			//tank
			g.drawLine((int)(20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY), (int)(20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY));
			g.drawLine((int)(20*(Math.cos(((oneAngle-30)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle-30)*Math.PI)/180.0))+oneY), (int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY));
			g.drawLine((int)(20*(Math.cos(((oneAngle-150)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle-150)*Math.PI)/180.0))+oneY), (int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY));
			g.drawLine((int)(20*(Math.cos(((oneAngle+150)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle+150)*Math.PI)/180.0))+oneY), (int)(20*(Math.cos(((oneAngle+30)*Math.PI)/180.0))+oneX), (int)(20*(Math.sin(((oneAngle+30)*Math.PI)/180.0))+oneY));
			
			//cannon
			g.drawLine((int)(10*(Math.cos(((oneAngle-15)*Math.PI)/180.0))+oneX), (int)(10*(Math.sin(((oneAngle-15)*Math.PI)/180.0))+oneY), (int)(10*(Math.cos(((oneAngle+15)*Math.PI)/180.0))+oneX), (int)(10*(Math.sin(((oneAngle+15)*Math.PI)/180.0))+oneY));
			g.drawLine((int)(30*(Math.cos(((oneAngle-5)*Math.PI)/180.0))+oneX), (int)(30*(Math.sin(((oneAngle-5)*Math.PI)/180.0))+oneY), (int)(30*(Math.cos(((oneAngle+5)*Math.PI)/180.0))+oneX), (int)(30*(Math.sin(((oneAngle+5)*Math.PI)/180.0))+oneY));
			g.drawLine((int)(10*(Math.cos(((oneAngle-15)*Math.PI)/180.0))+oneX), (int)(10*(Math.sin(((oneAngle-15)*Math.PI)/180.0))+oneY), (int)(30*(Math.cos(((oneAngle-5)*Math.PI)/180.0))+oneX), (int)(30*(Math.sin(((oneAngle-5)*Math.PI)/180.0))+oneY));
			g.drawLine((int)(10*(Math.cos(((oneAngle+15)*Math.PI)/180.0))+oneX), (int)(10*(Math.sin(((oneAngle+15)*Math.PI)/180.0))+oneY), (int)(30*(Math.cos(((oneAngle+5)*Math.PI)/180.0))+oneX), (int)(30*(Math.sin(((oneAngle+5)*Math.PI)/180.0))+oneY));
		}
		
		g.setColor(Color.black);
		for (int x=0; x<redX.size(); x++) {
			g.fillOval((int)(redX.get(x)-2), (int)(redY.get(x)-2), 4, 4);
		}
		
		
		
		
		g.setColor(Color.red);
		for (int x=0; x<redPiecesX.size(); x++) {
			g.drawRect((int)(redPiecesX.get(x)-1.5), (int)(redPiecesY.get(x)-1.5), 3, 3);
		}
		
		
		g.setColor(new Color(255, 35, 50));
		for (int x=0; x<laserX.size(); x++) {
			g.fillOval((int)(laserX.get(x)-2), (int)(laserY.get(x)-2), 4, 4);
		}
		
		
		for (int x=0; x<weaponX.size(); x++) {
			g.setColor(Color.black);
			g.drawRect(weaponX.get(x), weaponY.get(x), 20, 20);
			switch (weaponType.get(x)) {
			case 0: g.setColor(Color.blue);
				break;
			case 1: g.setColor(Color.green);
				break;
			case 2: g.setColor(Color.red);
				break;
			}
			g.drawRect(weaponX.get(x)+8, weaponY.get(x)+8, 4, 4);
		}
		
		
		
		g.setColor(Color.blue);
		//g.fillRect((int)twoX,(int)twoY,20,20);
	}
}