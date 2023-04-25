package FirstGame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class BMTron extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;
    private static int x1 = 10, y1 = 10, x2 = 10, y2 = 10, x3=10, y3=10, xa=10, ya=10;
    private static int x1Speed = 0, y1Speed= -10, x2Speed= 0, y2Speed= 10, x3Speed=-10, y3Speed=0, xaSpeed=10, yaSpeed=0;
    Image i;
    Graphics h;
    private static int[] [] s=new int[50] [50];
    private static int rScore=0,bScore=0,gScore = 0, aScore=0;
    private static boolean Run1=true, Run2=false, Run3=false, AI=false;
    //private Checkbox players2, players3;
    //private Button button;
    private static boolean ready=true;
    private static int speed=100, goal=10;
    private static boolean r=true;
    private static boolean b=true;
    private static boolean g=true;
    private static boolean a=true;
    
    private static int counter=5;
    
    private static boolean allJump=false;
    private static boolean jump=false;
    private static boolean destroyLines=false;
    private static boolean killAll=false;
    private static boolean teleport=false;
    
    private static int xx=0;
    private static int yy=0;
    
    private static int rj=0, bj=0, gj=0, aj=0;
    
    private void randomize() {
    	xx=(int)(Math.random()*50+1);
    	yy=(int)(Math.random()*50+1);
    	teleport=false;
    	allJump=false;
    	jump=false;
    	destroyLines=false;
    	killAll=false;
    	
    	int random=(int)(Math.random()*101+1);
    	if (random>75)
    		teleport=true;
    	else if (random>50)
    		allJump=true;
    	else if (random>25)
    		jump=true;
    	else if (random>1)
    		destroyLines=true;
    	else
    		killAll=true;
    }
    
    public void CPUDetermineDirection() {
    	boolean up=true, left=true, down=true, right=true;
    	
    	int testX=(xa/10)-1;
    	int testY=(ya/10)-1;
    	try {
    	if ((s[testX-1][testY]>0&&s[testX-1][testY]<4)||s[testX-1][testY]==9)
    		left=false;
    	}
    	catch (Exception e) {
    		left=false;
    	}
    	try {
    	if ((s[testX][testY-1]>0&&s[testX][testY-1]<4)||s[testX][testY-1]==9)
    		up=false;
    	}
    	catch (Exception e) {
    		up=false;
    	}
    	try {
    	if ((s[testX+1][testY]>0&&s[testX+1][testY]<4)||s[testX+1][testY]==9)
    		right=false;
    	}
    	catch (Exception e) {
    		right=false;
    	}
    	try {
    	if ((s[testX][testY+1]>0&&s[testX][testY+1]<4)||s[testX][testY+1]==9)
    		down=false;
    	}
    	catch (Exception e) {
    		down=false;
    	}
    	
    	if (up||left||down||right) {
    		if (!down&&!left&&!right) {
    			xaSpeed=0;
    			yaSpeed=-10;
    		}
    		else if (!up&&!left&&!right) {
    			xaSpeed=0;
    			yaSpeed=10;
    		}
    		else if (!up&&!down&&!right) {
    			xaSpeed=-10;
    			yaSpeed=0;
    		}
    		else if (!up&&!down&&!left) {
    			xaSpeed=10;
    			yaSpeed=0;
    		}
    		
    		
    		
    		else if (!up&&!down) {
    			int l=possibleMoves(2,testX,testY);
    			int r=possibleMoves(4,testX,testY);
    			if (l>=r) {
    				xaSpeed=-10;
    				yaSpeed=0;
    			}
    			else if (l<r) {
    				xaSpeed=10;
    				yaSpeed=0;
    			}
    		}
    		else if (!up&&!left) {
    			int d=possibleMoves(3,testX,testY);
    			int r=possibleMoves(4,testX,testY);
    			if (d>=r) {
    				xaSpeed=0;
    				yaSpeed=10;
    			}
    			else if (d<r) {
    				xaSpeed=10;
    				yaSpeed=0;
    			}
    		}
    		else if (!up&&!right) {
    			int l=possibleMoves(2,testX,testY);
    			int d=possibleMoves(3,testX,testY);
    			if (l>=d) {
    				xaSpeed=-10;
    				yaSpeed=0;
    			}
    			else if (l<d) {
    				xaSpeed=0;
    				yaSpeed=10;
    			}
    		}
    		else if (!left&&!down) {
    			int u=possibleMoves(1,testX,testY);
    			int r=possibleMoves(4,testX,testY);
    			if (u>=r) {
    				xaSpeed=0;
    				yaSpeed=-10;
    			}
    			else if (u<r) {
    				xaSpeed=10;
    				yaSpeed=0;
    			}
    		}
    		else if (!left&&!right) {
    			int u=possibleMoves(1,testX,testY);
    			int d=possibleMoves(3,testX,testY);
    			if (u>=d) {
    				xaSpeed=0;
    				yaSpeed=-10;
    			}
    			else if (u<d) {
    				xaSpeed=0;
    				yaSpeed=10;
    			}
    		}
    		else if (!right&&!down) {
    			int l=possibleMoves(2,testX,testY);
    			int u=possibleMoves(1,testX,testY);
    			if (l>=u) {
    				xaSpeed=-10;
    				yaSpeed=0;
    			}
    			else if (l<u) {
    				xaSpeed=0;
    				yaSpeed=-10;
    			}
    		}
    		
    		else if (!up) {
    			int d=possibleMoves(3,testX,testY);
    			int l=possibleMoves(2,testX,testY);
    			int r=possibleMoves(4,testX,testY);
    			int rand=(int)(Math.random()*6+1);
    			
    			if (d+l+r>30) {
	    			if (d>r&&d>l) {
	    				if (rand==1&&r>5) {
	    					xaSpeed=10;
	    					yaSpeed=0;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=0;
	    					yaSpeed=10;
	    				}
	    			}
	    			else if (r>l&&r>d) {
	    				if (rand==1&&l>5) {
	    					xaSpeed=-10;
	    					yaSpeed=0;
	    					counter=5;
	    				}
	    				else {
		    				xaSpeed=10;
		    				yaSpeed=0;
	    				}
	    			}
	    			else {
	    				if (rand==1&&d>5) {
	    					xaSpeed=0;
	    					yaSpeed=10;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=-10;
	    					yaSpeed=0;
	    				}
	    			}
    			}
    			else if (d<4) {
        			l=possibleMoves(2,testX,testY);
        			r=possibleMoves(4,testX,testY);
        			
        			if (l>=r) {
        				xaSpeed=-10;
        				yaSpeed=0;
        			}
        			else if (l<r) {
        				xaSpeed=10;
        				yaSpeed=0;
        			}
    			}
    			else if (l<4) {
    				d=possibleMoves(3,testX,testY);
        			r=possibleMoves(4,testX,testY);
        			if (d>=r) {
        				xaSpeed=0;
        				yaSpeed=10;
        			}
        			else if (d<r) {
        				xaSpeed=10;
        				yaSpeed=0;
        			}
    			}
    			else if (r<4) {
    				l=possibleMoves(2,testX,testY);
        			d=possibleMoves(3,testX,testY);
        			if (l>=d) {
        				xaSpeed=-10;
        				yaSpeed=0;
        			}
        			else if (l<d) {
        				xaSpeed=0;
        				yaSpeed=10;
        			}
    			}
    			else if (l+r+d<12) {
	    			if (d>r&&d>l) {
	    				xaSpeed=0;
	    				yaSpeed=10;
	    			}
	    			else if (r>l&&r>d) {
	    				xaSpeed=10;
	    				yaSpeed=0;
	    			}
	    			else {
	    				xaSpeed=-10;
	    				yaSpeed=0;
	    			}
    			}
    		}
    		else if (!down) {
    			int u=possibleMoves(1,testX,testY);
    			int l=possibleMoves(2,testX,testY);
    			int r=possibleMoves(4,testX,testY);
    			int rand=(int)(Math.random()*6+1);
    			
    			if (u+l+r>30) {
    				if (u>r&&u>l) {
	    				if (rand==1&&r>5) {
	    					xaSpeed=10;
	    					yaSpeed=0;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    				}
	    			}
	    			else if (r>l&&r>u) {
	    				if (rand==1&&l>5) {
	    					xaSpeed=-10;
	    					yaSpeed=0;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=10;
	    					yaSpeed=0;
	    				}
	    			}
	    			else {
	    				if (rand==1&&u>5) {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=-10;
	    					yaSpeed=0;
	    				}
	    			}
    			}
    			else if (u<4) {
        			l=possibleMoves(2,testX,testY);
        			r=possibleMoves(4,testX,testY);
        			if (l>=r) {
        				xaSpeed=-10;
        				yaSpeed=0;
        			}
        			else if (l<r) {
        				xaSpeed=10;
        				yaSpeed=0;
        			}
    			}
    			else if (l<4) {
    				u=possibleMoves(1,testX,testY);
        			r=possibleMoves(4,testX,testY);
        			if (u>=r) {
        				xaSpeed=0;
        				yaSpeed=-10;
        			}
        			else if (u<r) {
        				xaSpeed=10;
        				yaSpeed=0;
        			}
    			}
    			else if (r<4) {
    				l=possibleMoves(2,testX,testY);
        			u=possibleMoves(1,testX,testY);
        			if (l>=u) {
        				xaSpeed=-10;
        				yaSpeed=0;
        			}
        			else if (l<u) {
        				xaSpeed=0;
        				yaSpeed=-10;
        			}
    			}
    			if (u+r+l<12) {
	    			if (u>r&&u>l) {
	    				xaSpeed=0;
	    				yaSpeed=-10;
	    			}
	    			else if (r>l&&r>u) {
	    				xaSpeed=10;
	    				yaSpeed=0;
	    			}
	    			else {
	    				xaSpeed=-10;
	    				yaSpeed=0;
	    			}
    			}
    		}
    		else if (!left) {
    			int d=possibleMoves(3,testX,testY);
    			int u=possibleMoves(1,testX,testY);
    			int r=possibleMoves(4,testX,testY);
    			int rand=(int)(Math.random()*6+1);
    			
    			if (d+u+r>30) {
    				if (d>r&&d>u) {
	    				if (rand==1&&r>5) {
	    					xaSpeed=10;
	    					yaSpeed=0;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    				}
	    			}
	    			else if (r>u&&r>d) {
	    				if (rand==1&&u>5) {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    					counter=5;
	    				}
	    				else {
		    				xaSpeed=10;
		    				yaSpeed=0;
	    				}
	    			}
	    			else {
	    				if (rand==1&&d>5) {
	    					xaSpeed=0;
	    					yaSpeed=10;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    				}
	    			}
    			}
    			else if (d<4) {
        			u=possibleMoves(1,testX,testY);
        			r=possibleMoves(4,testX,testY);
        			if (u>=r) {
        				xaSpeed=0;
        				yaSpeed=-10;
        			}
        			else if (u<r) {
        				xaSpeed=10;
        				yaSpeed=0;
        			}
    			}
    			else if (u<4) {
    				d=possibleMoves(3,testX,testY);
        			r=possibleMoves(4,testX,testY);
        			if (d>=r) {
        				xaSpeed=0;
        				yaSpeed=10;
        			}
        			else if (d<r) {
        				xaSpeed=10;
        				yaSpeed=0;
        			}
    			}
    			else if (r<4) {
    				u=possibleMoves(1,testX,testY);
        			d=possibleMoves(3,testX,testY);
        			if (u>=d) {
        				xaSpeed=0;
        				yaSpeed=-10;
        			}
        			else if (u<d) {
        				xaSpeed=0;
        				yaSpeed=10;
        			}
    			}
    			if (d+u+r<12) {
	    			if (d>r&&d>u) {
	    				xaSpeed=0;
	    				yaSpeed=-10;
	    			}
	    			else if (r>u&&r>d) {
	    				xaSpeed=10;
	    				yaSpeed=0;
	    			}
	    			else {
	    				xaSpeed=0;
	    				yaSpeed=-10;
	    			}
    			}
    		}
    		else if (!right) {
    			int d=possibleMoves(3,testX,testY);
    			int l=possibleMoves(2,testX,testY);
    			int u=possibleMoves(1,testX,testY);
    			int rand=(int)(Math.random()*6+1);
    			
    			if (d+l+u>30) {
    				if (d>u&&d>l) {
	    				if (rand==1&&u>5) {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    				}
	    			}
	    			else if (u>l&&u>d) {
	    				if (rand==1&&l>5) {
	    					xaSpeed=-10;
	    					yaSpeed=0;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=0;
	    					yaSpeed=-10;
	    				}
	    			}
	    			else {
	    				if (rand==1&&d>5) {
	    					xaSpeed=0;
	    					yaSpeed=10;
	    					counter=5;
	    				}
	    				else {
	    					xaSpeed=-10;
	    					yaSpeed=0;
	    				}
	    			}
    			}
    			else if (d<4) {
        			l=possibleMoves(2,testX,testY);
        			u=possibleMoves(1,testX,testY);
        			if (l>=u) {
        				xaSpeed=-10;
        				yaSpeed=0;
        			}
        			else if (l<u) {
        				xaSpeed=0;
        				yaSpeed=-10;
        			}
    			}
    			else if (l<4) {
    				d=possibleMoves(3,testX,testY);
        			u=possibleMoves(1,testX,testY);
        			if (d>=u) {
        				xaSpeed=0;
        				yaSpeed=10;
        			}
        			else if (d<u) {
        				xaSpeed=0;
        				yaSpeed=-10;
        			}
    			}
    			else if (u<4) {
    				l=possibleMoves(2,testX,testY);
        			d=possibleMoves(3,testX,testY);
        			if (l>=d) {
        				xaSpeed=-10;
        				yaSpeed=0;
        			}
        			else if (l<d) {
        				xaSpeed=0;
        				yaSpeed=10;
        			}
    			}
    			if (d+l+u<12) {
	    			if (d>u&&d>l) {
	    				xaSpeed=0;
	    				yaSpeed=-10;
	    			}
	    			else if (u>l&&u>d) {
	    				xaSpeed=0;
	    				yaSpeed=-10;
	    			}
	    			else {
	    				xaSpeed=-10;
	    				yaSpeed=0;
	    			}
    			}
    		}
    	}
    }
    
    public int possibleMoves(int dir, int testX, int testY) {
    	if (dir==2) {
    		int x=0;
    		int cosa=0;
    		boolean jjj=true;
    		while (jjj) {
    			cosa--;
    			try {
    			if (s[testX+cosa][testY]==0||(s[testX+cosa][testY]>3&&s[testX+cosa][testY]<9))
    				x++;
    			else
    				jjj=false;
    			}
    			catch (Exception e) {
    				jjj=false;
			}
    		}
    		return x;
    	}
    	else if (dir==1) {
    		int x=0;
    		int cosa=0;
    		boolean jjj=true;
    		while (jjj) {
    			cosa--;
    			try {
    			if (s[testX][testY+cosa]==0||(s[testX][testY+cosa]>3&&s[testX][testY+cosa]<9))
    				x++;
    			else
    				jjj=false;
    			}
    			catch (Exception e) {
    				jjj=false;
    			}
    		}
    		return x;
    	}
    	else if (dir==4) {
    		int x=0;
    		int cosa=0;
    		boolean jjj=true;
    		while (jjj) {
    			cosa++;
    			try {
    			if (s[testX+cosa][testY]==0||(s[testX+cosa][testY]>3&&s[testX+cosa][testY]<9))
    				x++;
    			else
    				jjj=false;
    			}
    			catch (Exception e) {
    				jjj=false;
    			}
    		}
    		return x;
    	}
    	else if (dir==3) {
    		int x=0;
    		int cosa=0;
    		boolean jjj=true;
    		while (jjj) {
    			cosa++;
    			try {
    			if (s[testX][testY+cosa]==0||(s[testX][testY+cosa]>3&&s[testX][testY+cosa]<9))
    				x++;
    			else
    				jjj=false;
    			}
    			catch (Exception e) {
    				jjj=false;
    			}
    		}
    		return x;
    	}
    	else
    		return 0;
    }
    
    public void run() {
        try {
            while (true) {
            	
            	if (!Run2)
            		b=false;
                if (!Run3)
                	g=false;
                if (!AI)
                	a=false;
                boolean aliver=false;
                if (counter==0)
                	CPUDetermineDirection();
                else
                	counter--;
                if (counter<0)
                	counter=0;
                x1+=x1Speed;
                y1+=y1Speed;
                x2+=x2Speed;
                y2+=y2Speed;
                x3+=x3Speed;
                y3+=y3Speed;
                xa+=xaSpeed;
                ya+=yaSpeed;
                
                
                try {
                	if (s[(x1/10)-1][(y1/10)-1]==8) {
                        s[(x1/10)-1][(y1/10)-1]=1;
                        randomize();
                        rScore+=2;
                		b=false;
                		g=false;
                		a=false;
                	}
                	else if (s[(x1/10)-1][(y1/10)-1]==7) {
                        s[(x1/10)-1][(y1/10)-1]=1;
                        randomize();
                        boolean vari=true;
                		while (vari) {
                			x1=((int)(Math.random()*50+1))*10;
                			y1=((int)(Math.random()*50+1))*10;
                			try {
                				if ((s[(x1/10)-1][(y1/10)-1]<1 || s[(x1/10)-1][(y1/10)-1]>3)&&s[(x1/10)-1][(y1/10)-1]!=9)
                				vari=false;
                			}
                			catch(Exception e) {}
                		}
                	}
                	else if (s[(x1/10)-1][(y1/10)-1]==6) {
                        s[(x1/10)-1][(y1/10)-1]=1;
                        randomize();
                        for (int i=0; i<50; i++) {
                            for (int j=0; j<50; j++) {
                            	if (s[j][i]>0) {
                            		repaint();
                            		Thread.sleep(5);
                            	}
                                s[j][i] = 0;
                            }
                        }
                        try {
                        	s[(x1/10)-1][(y1/10)-1]=1;
                        } catch(Exception e) {}
                	}
                	else if (s[(x1/10)-1][(y1/10)-1]==5) {
                        s[(x1/10)-1][(y1/10)-1]=0;
                        randomize();
                		rj=6;
                	}
                	else if (s[(x1/10)-1][(y1/10)-1]==4) {
                        s[(x1/10)-1][(y1/10)-1]=1;
                        randomize();
                		rj=6;
                		bj=6;
                		gj=6;
                		aj=6;
                	}
                	else if (s[(x1/10)-1][(y1/10)-1]>0) {
                		if (rj==0) {
                			s[(x1/10)-1][(y1/10)-1]=1;
                			r=false;
                		}
                    }
                    else
                       	if (rj==0)
                    		s[(x1/10)-1][(y1/10)-1]=1;
                }
                catch (Exception e) {
                    r=false;
                }
                
                
                
                if (Run2) {
	                try {
	                	if (s[(x2/10)-1][(y2/10)-1]==8) {
	                        s[(x2/10)-1][(y2/10)-1]=2;
	                        randomize();
	                        bScore+=2;
	                		r=false;
	                		g=false;
	                		a=false;
	                	}
	                	else if (s[(x2/10)-1][(y2/10)-1]==7) {
	                        s[(x2/10)-1][(y2/10)-1]=2;
	                        randomize();
	                        boolean vari=true;
	                		while (vari) {
	                			x2=((int)(Math.random()*50+1))*10;
	                			y2=((int)(Math.random()*50+1))*10;
	                			try {
	                			if ((s[(x2/10)-1][(y2/10)-1]<1 || s[(x2/10)-1][(y2/10)-1]>3) && s[(x2/10)-1][(y2/10)-1]!=9)
	                				vari=false;
	                			}
	                			catch(Exception e) {}
	                		}
	                	}
	                	else if (s[(x2/10)-1][(y2/10)-1]==6) {
	                        s[(x2/10)-1][(y2/10)-1]=2;
	                        randomize();
	                        for (int i=0; i<50; i++) {
	                            for (int j=0; j<50; j++) {
	                            	if (s[j][i]>0) {
	                            		repaint();
	                            		Thread.sleep(5);
	                            	}
	                                s[j][i] = 0;
	                            }
	                        }
	                        try {
	                        	s[(x1/10)-1][(y1/10)-1]=1;
	                        } catch(Exception e) {}
	                        try {
	                        	s[(x2/10)-1][(y2/10)-1]=2;
	                        } catch(Exception e) {}
	                	}
	                	else if (s[(x2/10)-1][(y2/10)-1]==5) {
	                        s[(x2/10)-1][(y2/10)-1]=0;
	                        randomize();
	                		bj=6;
	                	}
	                	else if (s[(x2/10)-1][(y2/10)-1]==4) {
	                        s[(x2/10)-1][(y2/10)-1]=2;
	                        randomize();
	                		rj=6;
	                		bj=6;
	                		gj=6;
	                		aj=6;
	                	}
	                	else if (s[(x2/10)-1][(y2/10)-1]>0) {
	                		if (bj==0) {
	                			s[(x2/10)-1][(y2/10)-1]=2;
	                			b=false;
	                		}
	                    }
	                    else
	                    	if (bj==0)
	                    		s[(x2/10)-1][(y2/10)-1]=2;
	                }
	                catch(Exception e) {
	                    b=false;
	                }
                }
                
                
                
                
                if (Run3) {
	                try {
	                    if (s[(x3/10)-1][(y3/10)-1]>0) {
	                        g=false;
	                    }
	                    else
	                        s[(x3/10)-1][(y3/10)-1]=3;
	                }
	                catch(Exception e) {
	                    g=false;
	                }
                }
                else
                	g=false;
                

                if (AI) {
	                try {
	                	if (s[(xa/10)-1][(ya/10)-1]==8) {
	                        s[(xa/10)-1][(ya/10)-1]=9;
	                        randomize();
	                        aScore+=2;
	                		r=false;
	                		b=false;
	                		g=false;
	                	}
	                	else if (s[(xa/10)-1][(ya/10)-1]==7) {
	                        s[(xa/10)-1][(ya/10)-1]=9;
	                        randomize();
	                        boolean vari=true;
	                		while (vari) {
	                			xa=((int)(Math.random()*50+1))*10;
	                			ya=((int)(Math.random()*50+1))*10;
	                			try {
	                			if (s[(xa/10)-1][(ya/10)-1]<1 || s[(xa/10)-1][(ya/10)-1]>3)
	                				vari=false;
	                			}
	                			catch(Exception e) {}
	                		}
	                	}
	                	else if (s[(xa/10)-1][(ya/10)-1]==6) {
	                        s[(xa/10)-1][(ya/10)-1]=9;
	                        randomize();
	                        for (int i=0; i<50; i++) {
	                            for (int j=0; j<50; j++) {
	                            	if (s[j][i]>0) {
	                            		repaint();
	                            		Thread.sleep(5);
	                            	}
	                                s[j][i] = 0;
	                            }
	                        }
	                        try {
	                        	s[(x1/10)-1][(y1/10)-1]=1;
	                        } catch(Exception e) {}
	                        try {
	                        	s[(x2/10)-1][(y2/10)-1]=2;
	                        } catch(Exception e) {}
	                        try {
	                        	s[(x3/10)-1][(y3/10)-1]=3;
	                        } catch(Exception e) {}
	                        try {
	                        	s[(xa/10)-1][(ya/10)-1]=9;
	                        } catch(Exception e) {}
	                	}
	                	else if (s[(xa/10)-1][(ya/10)-1]==5) {
	                        s[(xa/10)-1][(ya/10)-1]=0;
	                        randomize();
	                		aj=6;
	                	}
	                	else if (s[(xa/10)-1][(ya/10)-1]==4) {
	                        s[(xa/10)-1][(ya/10)-1]=9;
	                        randomize();
	                		rj=6;
	                		bj=6;
	                		gj=6;
	                		aj=6;
	                	}
	                	else if (s[(xa/10)-1][(ya/10)-1]>0) {
	                		if (aj==0) {
	                			s[(xa/10)-1][(ya/10)-1]=9;
	                			a=false;
	                		}
	                    }
	                    else
	                    	if (aj==0)
	                    		s[(xa/10)-1][(ya/10)-1]=9;
	                }
	                catch(Exception e) {
	                    a=false;
	                }
                }
                
                if (a) {
	                if (r) {
	                	if (b) {
	                		if (!g) {
	                			x3Speed=0;
	                			y3Speed=0;
	                			Run3=false;
	                		}
	                	}
	                	else {
	                		if (g) {
	                			x2Speed=0;
	                			y2Speed=0;
	                			Run2=false;
	                		}
	                		else {
	                			x3Speed=0;
	                			y3Speed=0;
	                			Run3=false;
	                			x2Speed=0;
	                			y2Speed=0;
	                			Run2=false;
	                		}
	                	}
	                }
	                else {
	                	if (b) {
	                		if (g) {
	                			x1Speed=0;
	                			y1Speed=0;
	                			Run1=false;
	                		}
	                		else {
	                			x1Speed=0;
	                			y1Speed=0;
	                			Run1=false;
	                			x3Speed=0;
	                			y3Speed=0;
	                			Run3=false;
	                		}
	                	}
	                	else {
	                		if (g) {
	                			x1Speed=0;
	                			y1Speed=0;
	                			Run1=false;
	                			x2Speed=0;
	                			y2Speed=0;
	                			Run2=false;
	                		}
	                		else {
	                			aScore++;
	                			reset();
	                			aliver=true;
	                		}
	                	}
	                }
                }
                else {
	                if (r) {
	                	if (b) {
	                		if (!g) {
	                			x3Speed=0;
	                			y3Speed=0;
	                			Run3=false;
	                		}
	                	}
	                	else {
	                		if (g) {
	                			x2Speed=0;
	                			y2Speed=0;
	                			Run2=false;
	                		}
	                		else {
	                			rScore++;
	                			reset();
	                			aliver=true;
	                		}
	                	}
	                }
	                else {
	                	if (b) {
	                		if (g) {
	                			x1Speed=0;
	                			y1Speed=0;
	                			Run1=false;
	                		}
	                		else {
	                			bScore++;
	                			reset();
	                			aliver=true;
	                		}
	                	}
	                	else {
	                		if (g) {
	                			gScore++;
	                			reset();
	                			aliver=true;
	                		}
	                		else {
	                			reset();
	                			aliver=true;
	                		}
	                	}
	                }
                }
                
                
                
                setTitle("Red: "+rScore+"  Blue: "+bScore+" Green: "+gScore+" CPU: "+aScore+" Total: "+(rScore+bScore+gScore+aScore));
                if (rScore+bScore+gScore+aScore>=goal) {
                	goal+=10;
                	if (speed<=40)
                		speed=50;
                	else
                		speed-=10;
                }
                
                if (rj>0)
                	rj--;
                if (bj>0)
                	bj--;
                if (gj>0)
                	gj--;
                if (aj>0)
                	aj--;
                
                if (aliver)
                	Thread.sleep(1000);
                
                
                
                repaint();
                Thread.sleep(speed);
            }
        }
        catch(Exception e) {
            System.out.print("Error");
        }
    }
    
    public static void reset() {
    	counter=5;
    	
    	rj=0;
    	bj=0;
    	gj=0;
    	xx=(int)(Math.random()*50+1);
    	yy=(int)(Math.random()*50+1);
    	teleport=false;
    	allJump=false;
    	jump=false;
    	destroyLines=false;
    	killAll=false;
    	
    	int random=(int)(Math.random()*101+1);
    	if (random>75)
    		teleport=true;
    	else if (random>50)
    		allJump=true;
    	else if (random>25)
    		jump=true;
    	else if (random>1)
    		destroyLines=true;
    	else
    		killAll=true;
    	
        for (int i=0; i<50; i++) {
            for (int j=0; j<50; j++)
                s[i][j] = 0;
        }
        Run1=true;
        Run2=false;
        Run3=false;
        AI=true;
        
        s[17][24]=1;
        if (Run2)
        	s[31][24]=2;
        if (Run3)
        	s[17][31]=3;
        if (AI)
        	s[31][31]=9;
        
        x1Speed=0;
        y1Speed=-10;
        if (Run2) {
        	x2Speed=0;
        	y2Speed=10;
        }
        if (Run3) {
	        x3Speed=-10;
	        y3Speed=0;
        }
        if (AI) {
        	int rand=(int)(Math.random()*4+1);
        	switch (rand) {
        	case 1:
        		xaSpeed=-10;
        		yaSpeed=0;
        		break;
        	case 2:
        		xaSpeed=10;
        		yaSpeed=0;
        		break;
        	case 3:
        		xaSpeed=0;
        		yaSpeed=-10;
        		break;
        	case 4:
        		xaSpeed=0;
        		yaSpeed=10;
        		break;
        	}
        }
        
        x1=180;
        y1=250;
        x2=320;
        y2=250;
        x3=180;
        y3=320;
        xa=320;
        ya=320;
        
        r=true;
        b=true;
        g=true;
        a=true;
        
    }
    
    public BMTron() {
        addKeyListener(new kl());
        setSize(500,500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        rScore=0;
        bScore=0;
        gScore=0;
        aScore=0;
        setTitle("Red: "+rScore+"  Blue: "+bScore+" Green: "+gScore+" CPU: "+aScore+" Total: "+(rScore+bScore+gScore));
        x1=180;
        y1=250;
        if (Run2) {
	        x2=320;
	        y2=250;
        }
        else {
        	x2=-100;
        	y2=-100;
        	x3Speed=0;
        	y3Speed=0;
        }
        if (Run3) {
	        x3=180;
	        y3=320;
        }
        else {
        	x3=-100;
        	y3=-100;
        	x3Speed=0;
        	y3Speed=0;
        }
        if (AI) {
	        xa=320;
	        ya=320;
        }
        else {
        	xa=-100;
        	ya=-100;
        	xaSpeed=0;
        	yaSpeed=0;
        }
    }
    
    public class kl extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_UP && Run1) {
                x1Speed=0;
                y1Speed=-10;
            }
            if (keyCode == KeyEvent.VK_DOWN && Run1) {
                x1Speed=0;
                y1Speed=10;
            }
            if (keyCode == KeyEvent.VK_LEFT && Run1) {
                x1Speed=-10;
                y1Speed=0;
            }
            if (keyCode == KeyEvent.VK_RIGHT && Run1) {
                x1Speed=10;
                y1Speed=0;
            }
            
            if (keyCode == KeyEvent.VK_W && Run2) {
                x2Speed=0;
                y2Speed=-10;
            }
            if (keyCode == KeyEvent.VK_S && Run2) {
                x2Speed=0;
                y2Speed=10;
            }
            if (keyCode == KeyEvent.VK_A && Run2) {
                x2Speed=-10;
                y2Speed=0;
            }
            if (keyCode == KeyEvent.VK_D && Run2) {
                x2Speed=10;
                y2Speed=0;
            }
            
            if (keyCode == KeyEvent.VK_I && Run3) {
                x3Speed=0;
                y3Speed=-10;
            }
            if (keyCode == KeyEvent.VK_K && Run3) {
                x3Speed=0;
                y3Speed=10;
            }
            if (keyCode == KeyEvent.VK_J && Run3) {
                x3Speed=-10;
                y3Speed=0;
            }
            if (keyCode == KeyEvent.VK_L && Run3) {
                x3Speed=10;
                y3Speed=0;
            }
            repaint();
        }
    }
    
    
    
    public static void main(String args[]) {
        BMTron b=new BMTron();
        Thread t=new Thread(b);
        t.start();
        reset();
    }
    
    public void paint(Graphics g) {
    	if (ready) {
	        i=createImage(getWidth(),getHeight());
	        h=i.getGraphics();
	        paintComponent(h);
	        g.drawImage(i,0,0,this);
    	}
    }
    public void paintComponent(Graphics g) {
    	if (allJump) {
    		g.setColor(Color.orange);
    		s[xx-1][yy-1]=4;
    	}
    	else if (jump) {
    		g.setColor(Color.pink);
    		s[xx-1][yy-1]=5;
    	}
    	else if (destroyLines) {
    		g.setColor(Color.gray);
    		s[xx-1][yy-1]=6;
    	}
    	else if (teleport) {
    		g.setColor(Color.cyan);
    		s[xx-1][yy-1]=7;
    	}
    	else if (killAll) {
    		g.setColor(Color.black);
    		s[xx-1][yy-1]=8;
    	}
    	g.fillRect(xx*10,yy*10,10,10);
    	
    	
    	if (ready) {
	        g.setColor(Color.red);
	        g.fillRect(x1,y1,10,10);
	        if (Run2) {
		        g.setColor(Color.blue);
		        g.fillRect(x2,y2,10,10);
	        }
	        if (Run3) {
		        g.setColor(Color.green);
		        g.fillRect(x3,y3,10,10);
	        }
	        if (AI) {
	        	g.setColor(Color.magenta);
	        	g.fillRect(xa, ya, 10, 10);
	        }
	        
	        
	        g.setColor(Color.gray);
	        for (int x=20; x<getWidth(); x+=10) {
	            g.drawLine(x, 0, x, getHeight());
	        }
	        for (int y=20; y<getHeight(); y+=10) {
	            g.drawLine(0, y, getWidth(), y);
	        }
	        g.setColor(Color.black);
	        g.fillRect(0 , 0 , getWidth() , 10);
	        g.fillRect(0, 0, 10, getHeight());
	        
	        
	        for (int i=0; i<50; i++) {
	            for (int j=0; j<50; j++) {
	                g.setColor(Color.red);
	                if (s[i][j]==1)
	                    g.fillRect((i+1)*10, (j+1)*10, 10, 10);
	                g.setColor(Color.blue);
	                if (s[i][j]==2)
	                    g.fillRect((i+1)*10, (j+1)*10, 10, 10);
	                g.setColor(Color.green);
	                if (s[i][j]==3)
	                    g.fillRect((i+1)*10, (j+1)*10, 10, 10);
                	g.setColor(Color.magenta);
	                if (s[i][j]==9)
	                	g.fillRect((i+1)*10, (j+1)*10, 10, 10);
	            }
	        }
        }
    }
}