import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**
 * Class ChessGUI - The most awesomest chess game ever.
 * 
 * @author (Clayton Knittel)
 * @version (0.0.3)
 */
public class ChessGUI extends Applet implements MouseListener
{
    private static final long serialVersionUID = 1L;
    
    static boolean chooseShowPossibleMoves=true;
    
    static int turn = 1;
    static int round = 1;
    static int round1 = 1;
    static boolean nexti=false;
    static int times=0;
    static boolean foo=false;
    static boolean wKingNeverMoved=true;
    static boolean bKingNeverMoved=true;
    static boolean wRook1NeverMoved=true;
    static boolean wRook2NeverMoved=true;
    static boolean bRook1NeverMoved=true;
    static boolean bRook2NeverMoved=true;
    
    public static Label label=new Label("Chess: White player's turn.");
    
    public static int mx = 0;
    public static int my = 0;
    
    public static int lett = 0;
    
    public static int wm = 0;
    public static int bm = 0;
    
    public static String[] s=new String[64];
    public static int[] p=new int[64];
    public static int[] pawn=new int[8];
    public static int[] bawn=new int[8];
    
    public static int lettt = 0;
    
    public static int wCheckmate = 0;
    public static int bCheckmate = 0;
    
    public static int wPawn1 = 9;
    public static int wPawn2 = 10;
    public static int wPawn3 = 11;
    public static int wPawn4 = 12;
    public static int wPawn5 = 13;
    public static int wPawn6 = 14;
    public static int wPawn7 = 15;
    public static int wPawn8 = 16;
        
    public static int bPawn1 = 49;
    public static int bPawn2 = 50;
    public static int bPawn3 = 51;
    public static int bPawn4 = 52;
    public static int bPawn5 = 53;
    public static int bPawn6 = 54;
    public static int bPawn7 = 55;
    public static int bPawn8 = 56;
        
    public static int wRook1 = 1;
    public static int wRook2 = 8;
        
    public static int bRook1 = 57;
    public static int bRook2 = 64;
        
    public static int wKnight1 = 2;
    public static int wKnight2 = 7;
        
    public static int bKnight1 = 58;
    public static int bKnight2 = 63;
        
    public static int wBishop1 = 3;
    public static int wBishop2 = 6;
        
    public static int bBishop1 = 59;
    public static int bBishop2 = 62;
        
    public static int wQueen = 4;
    public static int wKing = 5;
        
    public static int bQueen = 60;
    public static int bKing = 61;
    

    
    public static int wQueen1=0;
    public static int wQueen2=0;
    public static int wQueen3=0;
    public static int wQueen4=0;
    public static int wQueen5=0;
    public static int wQueen6=0;
    public static int wQueen7=0;
    public static int wQueen8=0;
    
    public static int bQueen1=0;
    public static int bQueen2=0;
    public static int bQueen3=0;
    public static int bQueen4=0;
    public static int bQueen5=0;
    public static int bQueen6=0;
    public static int bQueen7=0;
    public static int bQueen8=0;
    
    public  static int g;
    
    public static int h;
    
    public static int i;
    
    public static boolean valid;
    
    public static int lette;
    
    public static int mousex;
    public static int mousey;
    public static int pice=0;
    public static int wt=0;
    
    
    
    
    public void init()
    {
        setSize(650,650);
        addMouseListener(this);
        setArrays();
        setVisible(true);
        label.setVisible(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setLocation(200,0);
        this.add(label);
        repaint();
    }
    
    public ChessGUI () {
        setLayout(new FlowLayout());
        repaint();
    }
    
    
    
    
    
    public void mouseClicked (MouseEvent me) {
        nexti=false;
        boolean execute=false;
        mx=me.getX();
        my=me.getY();
        if (calcSpace(mx,my)>0) {
	        if (turn==1) {
	            if (checkWCheck(wKing))
	                label.setText("White's King is in check!");
	            if (checkWCheckmate()) {
	            	label.setText("Checkmate! Black wins!");
	            }
	            if (round==1) {
	                g=0;
	                g=calcSpace(mx,my);
	                if (g>0)
	                	wCheck(g);
	                if (retWMove()>0) {
	                    nexti=true;
	                }
	            }
	            else {
	                h=0;
	                h=calcSpace(mx,my);
	                check(h);
	                if (retCheck()==1) {
	                    execute=false;
	                    round=1;
	                    nexti=false;
	                }
	                else {
	                    wValidCheck(g,h,i,((g-1)%8+1),false);
	                    if (valid()) {
	                    	int it=0;
	                        if (wm==1)
	                            wPawn1=h;
	                        if (wm==2)
	                            wPawn2=h;
	                        if (wm==3)
	                            wPawn3=h;
	                        if (wm==4)
	                            wPawn4=h;
	                        if (wm==5)
	                            wPawn5=h;
	                        if (wm==6)
	                            wPawn6=h;
	                        if (wm==7)
	                            wPawn7=h;
	                        if (wm==8)
	                            wPawn8=h;
	                        if (wm==9)
	                            wRook1=h;
	                        if (wm==10)
	                            wRook2=h;
	                        if (wm==11)
	                            wKnight1=h;
	                        if (wm==12)
	                            wKnight2=h;
	                        if (wm==13)
	                            wBishop1=h;
	                        if (wm==14)
	                            wBishop2=h;
	                        if (wm==15)
	                            wQueen=h;
	                        if (wm==16)
	                            wKing=h;
	                        if (wm==17)
	                            wQueen1=h;
	                        if (wm==18)
	                            wQueen2=h;
	                        if (wm==19)
	                            wQueen3=h;
	                        if (wm==20)
	                            wQueen4=h;
	                        if (wm==21)
	                            wQueen5=h;
	                        if (wm==22)
	                            wQueen6=h;
	                        if (wm==23)
	                            wQueen7=h;
	                        if (wm==24)
	                            wQueen8=h;
	                        
	    					if (bPawn1==h) {
	    						bPawn1=0;
	    						it=1;
	    					}
	    					if (bPawn2==h) {
	    						bPawn2=0;
	    						it=2;
	    					}
	    					if (bPawn3==h) {
	    						bPawn3=0;
	    						it=3;
	    					}
	    					if (bPawn4==h) {
	    						bPawn4=0;
	    						it=4;
	    					}
	    					if (bPawn5==h) {
	    						bPawn5=0;
	    						it=5;
	    					}
	    					if (bPawn6==h) {
	    						bPawn6=0;
	    						it=6;
	    					}
	    					if (bPawn7==h) {
	    						bPawn7=0;
	    						it=7;
	    					}
	    					if (bPawn8==h) {
	    						bPawn8=0;
	    						it=8;
	    					}
	    					if (bRook1==h) {
	    						bRook1=0;
	    						it=9;
	    					}
	    					if (bRook2==h) {
	    						bRook2=0;
	    						it=10;
	    					}
	    					if (bKnight1==h) {
	    						bKnight1=0;
	    						it=11;
	    					}
	    					if (bKnight2==h) {
	    						bKnight2=0;
	    						it=12;
	    					}
	    					if (bBishop1==h) {
	    						bBishop1=0;
	    						it=13;
	    					}
	    					if (bBishop2==h) {
	    						bBishop2=0;
	    						it=14;
	    					}
	    					if (bQueen==h) {
	    						bQueen=0;
	    						it=15;
	    					}
	    					if (bKing==h) {
	    						bKing=0;
	    						it=16;
	    					}
	    					if (bQueen1==h) {
	    						bQueen1=0;
	    						it=17;
	    					}
	    					if (bQueen2==h) {
	    						bQueen2=0;
	    						it=18;
	    					}
	    					if (bQueen3==h) {
	    						bQueen3=0;
	    						it=19;
	    					}
	    					if (bQueen4==h) {
	    						bQueen4=0;
	    						it=20;
	    					}
	    					if (bQueen5==h) {
	    						bQueen5=0;
	    						it=21;
	    					}
	    					if (bQueen6==h) {
	    						bQueen6=0;
	    						it=22;
	    					}
	    					if (bQueen7==h) {
	    						bQueen7=0;
	    						it=23;
	    					}
	    					if (bQueen8==h) {
	    						bQueen8=0;
	    						it=24;
	    					}
	                        clear();
	                        if (!checkWCheck(wKing)) {
	                            execute=true;
	                        }
	                        else {
	                            round=1;
	                            nexti=false;
	                        }
	                            if (wm==1)
	                                wPawn1=g;
	                            if (wm==2)
	                                wPawn2=g;
	                            if (wm==3)
	                                wPawn3=g;
	                            if (wm==4)
	                                wPawn4=g;
	                            if (wm==5)
	                                wPawn5=g;
	                            if (wm==6)
	                                wPawn6=g;
	                            if (wm==7)
	                                wPawn7=g;
	                            if (wm==8)
	                                wPawn8=g;
	                            if (wm==9)
	                                wRook1=g;
	                            if (wm==10)
	                                wRook2=g;
	                            if (wm==11)
	                                wKnight1=g;
	                            if (wm==12)
	                                wKnight2=g;
	                            if (wm==13)
	                                wBishop1=g;
	                            if (wm==14)
	                                wBishop2=g;
	                            if (wm==15)
	                                wQueen=g;
	                            if (wm==16)
	                                wKing=g;
	                            if (wm==17)
	                                wQueen1=g;
	                            if (wm==18)
	                                wQueen2=g;
	                            if (wm==19)
	                                wQueen3=g;
	                            if (wm==20)
	                                wQueen4=g;
	                            if (wm==21)
	                                wQueen5=g;
	                            if (wm==22)
	                                wQueen6=g;
	                            if (wm==23)
	                                wQueen7=g;
	                            if (wm==24)
	                                wQueen8=g;
	                            
		    					switch (it) {
		    					case 1: bPawn1=h;
		    						break;
		    					case 2: bPawn2=h;
			    					break;
		    					case 3: bPawn3=h;
			    					break;
		    					case 4: bPawn4=h;
			    					break;
		    					case 5: bPawn5=h;
			    					break;
		    					case 6: bPawn6=h;
			    					break;
		    					case 7: bPawn7=h;
			    					break;
		    					case 8: bPawn8=h;
			    					break;
		    					case 9: bRook1=h;
			    					break;
		    					case 10: bRook2=h;
			    					break;
		    					case 11: bKnight1=h;
		    						break;
		    					case 12: bKnight2=h;
		    						break;
		    					case 13: bBishop1=h;
		    						break;
		    					case 14: bBishop2=h;
		    						break;
		    					case 15: bQueen=h;
		    						break;
		    					case 16: bKing=h;
		    						break;
		    					case 17: bQueen1=h;
		    						break;
		    					case 18: bQueen2=h;
		    						break;
		    					case 19: bQueen3=h;
		    						break;
		    					case 20: bQueen4=h;
		    						break;
		    					case 21: bQueen5=h;
		    						break;
		    					case 22: bQueen6=h;
		    						break;
		    					case 23: bQueen7=h;
		    						break;
		    					case 24: bQueen8=h;
		    						break;
		    					default:
		    						break;
		    					}
	                            clear();
	                        
	                    }
	                    else {
	                        round=1;
	                        nexti=false;
	                    }
	                }
	            }
	        }
	        else {
	            if (checkBCheck(bKing))
	                label.setText("Black's King is in check!");
	            if (checkBCheckmate())
	            	label.setText("Checkmate! White wins!");
	            if (round==1) {
	                g=0;
	                g=calcSpace(mx,my);
	                bCheck(g);
	                if (retBMove()>0) {
	                    nexti=true;
					}
				}
				else {
					h=0;
					h=calcSpace(mx,my);
					check(h);
					if (retCheck()==2) {
						execute=false;
						round=1;
						nexti=false;
					}
					else {
						bValidCheck(g,h,i,((g-1)%8+1),false);
						if (valid()) {
							int it = 0;
	    					if (bm==1)
	    						bPawn1=h;
	    					if (bm==2)
	    						bPawn2=h;
	    					if (bm==3)
	    						bPawn3=h;
	    					if (bm==4)
	    						bPawn4=h;
	    					if (bm==5)
	    						bPawn5=h;
	    					if (bm==6)
	    						bPawn6=h;
	    					if (bm==7)
	    						bPawn7=h;
	    					if (bm==8)
	    						bPawn8=h;
	    					if (bm==9)
	    						bRook1=h;
	    					if (bm==10)
	    						bRook2=h;
	    					if (bm==11)
	    						bKnight1=h;
	    					if (bm==12)
	    						bKnight2=h;
	    					if (bm==13)
	    						bBishop1=h;
	    					if (bm==14)
	    						bBishop2=h;
	    					if (bm==15)
	    						bQueen=h;
	    					if (bm==16)
	    						bKing=h;
	    					if (bm==17)
	    						bQueen1=h;
	    					if (bm==18)
	    						bQueen2=h;
	    					if (bm==19)
	    						bQueen3=h;
	    					if (bm==20)
	    						bQueen4=h;
	    					if (bm==21)
	    						bQueen5=h;
	    					if (bm==22)
	    						bQueen6=h;
	    					if (bm==23)
	    						bQueen7=h;
	    					if (bm==24)
	    						bQueen8=h;
	    					
	    					if (wPawn1==h) {
	    						wPawn1=0;
	    						it=1;
	    					}
	    					if (wPawn2==h) {
	    						wPawn2=0;
	    						it=2;
	    					}
	    					if (wPawn3==h) {
	    						wPawn3=0;
	    						it=3;
	    					}
	    					if (wPawn4==h) {
	    						wPawn4=0;
	    						it=4;
	    					}
	    					if (wPawn5==h) {
	    						wPawn5=0;
	    						it=5;
	    					}
	    					if (wPawn6==h) {
	    						wPawn6=0;
	    						it=6;
	    					}
	    					if (wPawn7==h) {
	    						wPawn7=0;
	    						it=7;
	    					}
	    					if (wPawn8==h) {
	    						wPawn8=0;
	    						it=8;
	    					}
	    					if (wRook1==h) {
	    						wRook1=0;
	    						it=9;
	    					}
	    					if (wRook2==h) {
	    						wRook2=0;
	    						it=10;
	    					}
	    					if (wKnight1==h) {
	    						wKnight1=0;
	    						it=11;
	    					}
	    					if (wKnight2==h) {
	    						wKnight2=0;
	    						it=12;
	    					}
	    					if (wBishop1==h) {
	    						wBishop1=0;
	    						it=13;
	    					}
	    					if (wBishop2==h) {
	    						wBishop2=0;
	    						it=14;
	    					}
	    					if (wQueen==h) {
	    						wQueen=0;
	    						it=15;
	    					}
	    					if (wKing==h) {
	    						wKing=0;
	    						it=16;
	    					}
	    					if (wQueen1==h) {
	    						wQueen1=0;
	    						it=17;
	    					}
	    					if (wQueen2==h) {
	    						wQueen2=0;
	    						it=18;
	    					}
	    					if (wQueen3==h) {
	    						wQueen3=0;
	    						it=19;
	    					}
	    					if (wQueen4==h) {
	    						wQueen4=0;
	    						it=20;
	    					}
	    					if (wQueen5==h) {
	    						wQueen5=0;
	    						it=21;
	    					}
	    					if (wQueen6==h) {
	    						wQueen6=0;
	    						it=22;
	    					}
	    					if (wQueen7==h) {
	    						wQueen7=0;
	    						it=23;
	    					}
	    					if (wQueen8==h) {
	    						wQueen8=0;
	    						it=24;
	    					}
	    					clear();
							if (!checkBCheck(bKing)) {
								execute=true;
							}
							else {
								round=1;
								nexti=false;
							}
		    					if (bm==1)
		    						bPawn1=g;
		    					if (bm==2)
		    						bPawn2=g;
		    					if (bm==3)
		    						bPawn3=g;
		    					if (bm==4)
		    						bPawn4=g;
		    					if (bm==5)
		    						bPawn5=g;
		    					if (bm==6)
		    						bPawn6=g;
		    					if (bm==7)
		    						bPawn7=g;
		    					if (bm==8)
		    						bPawn8=g;
		    					if (bm==9)
		    						bRook1=g;
		    					if (bm==10)
		    						bRook2=g;
		    					if (bm==11)
		    						bKnight1=g;
		    					if (bm==12)
		    						bKnight2=g;
		    					if (bm==13)
		    						bBishop1=g;
		    					if (bm==14)
		    						bBishop2=g;
		    					if (bm==15)
		    						bQueen=g;
		    					if (bm==16)
		    						bKing=g;
		    					if (bm==17)
		    						bQueen1=g;
		    					if (bm==18)
		    						bQueen2=g;
		    					if (bm==19)
		    						bQueen3=g;
		    					if (bm==20)
		    						bQueen4=g;
		    					if (bm==21)
		    						bQueen5=g;
		    					if (bm==22)
		    						bQueen6=g;
		    					if (bm==23)
		    						bQueen7=g;
		    					if (bm==24)
		    						bQueen8=g;
		    					
		    					switch (it) {
		    					case 1: wPawn1=h;
		    						break;
		    					case 2: wPawn2=h;
			    					break;
		    					case 3: wPawn3=h;
			    					break;
		    					case 4: wPawn4=h;
			    					break;
		    					case 5: wPawn5=h;
			    					break;
		    					case 6: wPawn6=h;
			    					break;
		    					case 7: wPawn7=h;
			    					break;
		    					case 8: wPawn8=h;
			    					break;
		    					case 9: wRook1=h;
			    					break;
		    					case 10: wRook2=h;
			    					break;
		    					case 11: wKnight1=h;
		    						break;
		    					case 12: wKnight2=h;
		    						break;
		    					case 13: wBishop1=h;
		    						break;
		    					case 14: wBishop2=h;
		    						break;
		    					case 15: wQueen=h;
		    						break;
		    					case 16: wKing=h;
		    						break;
		    					case 17: wQueen1=h;
		    						break;
		    					case 18: wQueen2=h;
		    						break;
		    					case 19: wQueen3=h;
		    						break;
		    					case 20: wQueen4=h;
		    						break;
		    					case 21: wQueen5=h;
		    						break;
		    					case 22: wQueen6=h;
		    						break;
		    					case 23: wQueen7=h;
		    						break;
		    					case 24: wQueen8=h;
		    						break;
		    					default:
		    						break;
		    					}
		    					clear();
							}
						else {
						    round=1;
						    nexti=false;
						}
					}
				}
			}
			if (nexti)
				round++;
			if (execute) {
				round=1;
				times=1;
				if (turn==1) {
					check(h);
					wValidCheck(g,h,i,((g-1)%8+1),true);
					turn=2;
					label.setText("Black player's turn");
				}
				else {
					check(h);
					bValidCheck(g,h,i,((g-1)%8+1),true);
					turn=1;
					label.setText("White player's turn");
				}
			}
			if (checkWCheck(wKing)) {
				label.setText("White's King is in check!");
			}
			if (checkBCheck(bKing)) {
				label.setText("Black's King is in check!");
			}
			if (checkWCheckmate()) {
				label.setText("Checkmate! Black wins!");
			}
			if (checkBCheckmate()) {
				label.setText("Checkmate! White wins!");
			}
			repaint();
        }
	}
	public void mouseEntered (MouseEvent me) {}
	public void mousePressed (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
	public void mouseExited (MouseEvent me) {}
	
	public static int calcSpace(int mx, int my) {
		int ccc = 0;
		if (mx>=25&&mx<100) {
			if (my>=25&&my<100)
				ccc=1;
			else if (my<175)
				ccc=9;
			else if (my<250)
				ccc=17;
			else if (my<325)
				ccc=25;
			else if (my<400)
				ccc=33;
			else if (my<475)
				ccc=41;
			else if (my<550)
				ccc=49;
			else if (my<=625)
				ccc=57;
			else
				ccc=0;
		}
		else if (mx<175) {
			if (my>=25&&my<100)
				ccc=2;
			else if (my<175)
				ccc=10;
			else if (my<250)
				ccc=18;
			else if (my<325)
				ccc=26;
			else if (my<400)
				ccc=34;
			else if (my<475)
				ccc=42;
			else if (my<550)
				ccc=50;
			else if (my<=625)
				ccc=58;
			else
				ccc=0;
		}
		else if (mx<250) {
			if (my>=25&&my<100)
				ccc=3;
			else if (my<175)
				ccc=11;
			else if (my<250)
				ccc=19;
			else if (my<325)
				ccc=27;
			else if (my<400)
				ccc=35;
			else if (my<475)
				ccc=43;
			else if (my<550)
				ccc=51;
			else if (my<=625)
				ccc=59;
			else
				ccc=0;
		}
		else if (mx<325) {
			if (my>=25&&my<100)
				ccc=4;
			else if (my<175)
				ccc=12;
			else if (my<250)
				ccc=20;
			else if (my<325)
				ccc=28;
			else if (my<400)
				ccc=36;
			else if (my<475)
				ccc=44;
			else if (my<550)
				ccc=52;
			else if (my<=625)
				ccc=60;
			else
				ccc=0;
		}
		else if (mx<400) {
			if (my>=25&&my<100)
				ccc=5;
			else if (my<175)
				ccc=13;
			else if (my<250)
				ccc=21;
			else if (my<325)
				ccc=29;
			else if (my<400)
				ccc=37;
			else if (my<475)
				ccc=45;
			else if (my<550)
				ccc=53;
			else if (my<=625)
				ccc=61;
			else
				ccc=0;
		}
		else if (mx<475) {
			if (my>=25&&my<100)
				ccc=6;
			else if (my<175)
				ccc=14;
			else if (my<250)
				ccc=22;
			else if (my<325)
				ccc=30;
			else if (my<400)
				ccc=38;
			else if (my<475)
				ccc=46;
			else if (my<550)
				ccc=54;
			else if (my<=625)
				ccc=62;
			else
				ccc=0;
		}
		else if (mx<550) {
			if (my>=25&&my<100)
				ccc=7;
			else if (my<175)
				ccc=15;
			else if (my<250)
				ccc=23;
			else if (my<325)
				ccc=31;
			else if (my<400)
				ccc=39;
			else if (my<475)
				ccc=47;
			else if (my<550)
				ccc=55;
			else if (my<=625)
				ccc=63;
			else
				ccc=0;
		}
		else if (mx<=625) {
			if (my>=25&&my<100)
				ccc=8;
			else if (my<175)
				ccc=16;
			else if (my<250)
				ccc=24;
			else if (my<325)
				ccc=32;
			else if (my<400)
				ccc=40;
			else if (my<475)
				ccc=48;
			else if (my<550)
				ccc=56;
			else if (my<=625)
				ccc=64;
			else
				ccc=0;
		}
		else
			ccc=0;
		return ccc;
	}
	
	
    public void paint(Graphics gr)
    {
        Font f=new Font("TimesRoman",Font.BOLD, 45);
        gr.setFont(f);
        int x=0;
        int y=0;
        int z=0;
        int a=0;
        gr.setColor(Color.LIGHT_GRAY);
        gr.fillRect(0,0,650,650);
        gr.setColor(Color.white);
        gr.fillRect(25,25,600,600);
        gr.setColor(Color.gray);
        while (y<=525)
        {
            while (x<=525)
            {
                gr.fillRect(25+x,25+y,75,75);
                x+=150;
            }
            y+=75;
            switch(z)
            {
                case 0:
                x=75;
                z=1;
                break;
                case 1:
                x=0;
                z=0;
            }
        }
        gr.drawRect(25,25,600,600);
        /**
           "\u2654 " + // white king
           "\u2655 " + // white queen
           "\u2656 " + // white rook
           "\u2657 " + // white bishop
           "\u2658 " + // white knight
           "\u2659 " + // white pawn
           "\n" +
           "\u265A " + // black queen
           "\u265B " + // black queen
           "\u265C " + // black rook
           "\u265D " + // black bishop
           "\u265E " + // black knight
           "\u265F " + // black pawn
         */
        gr.setColor(Color.black);
        a=1;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        a=2;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        a=3;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        a=4;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        a=5;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        a=6;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        a=7;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        a=8;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265F",x,y);
        
        a=9;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265D",x,y);
        a=10;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265D",x,y);
        
        a=11;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265E",x,y);
        a=12;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265E",x,y);
        
        a=13;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265C",x,y);
        a=14;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265C",x,y);
        
        a=15;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        
        a=16;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265A",x,y);
        
        a=17;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        a=18;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        a=19;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        a=20;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        a=21;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        a=22;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        a=23;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        a=24;
        x=retBX(a);
        y=retBY(a);
        gr.drawString("\u265B",x,y);
        
        
        a=1;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        a=2;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        a=3;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        a=4;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        a=5;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        a=6;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        a=7;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        a=8;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2659",x,y);
        
        a=9;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2657",x,y);
        a=10;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2657",x,y);
        
        a=11;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2658",x,y);
        a=12;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2658",x,y);
        
        a=13;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2656",x,y);
        a=14;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2656",x,y);
        
        a=15;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        
        a=16;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2654",x,y);
        
        a=17;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        a=18;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        a=19;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        a=20;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        a=21;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        a=22;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        a=23;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        a=24;
        x=retWX(a);
        y=retWY(a);
        gr.drawString("\u2655",x,y);
        

    	gr.setColor(Color.blue);
    	
    	if (nexti && chooseShowPossibleMoves) {
    		for (int te=1;te<=64;te++) {
    			if (turn==1) {
    				check(te);
    				wValidCheck(g,te,retCheck(),((te-1)%8+1),false);
    				if (valid()) {
    					int it=0;
    					if (wm==1)
    						wPawn1=te;
    					if (wm==2)
    						wPawn2=te;
    					if (wm==3)
    						wPawn3=te;
    					if (wm==4)
    						wPawn4=te;
    					if (wm==5)
    						wPawn5=te;
    					if (wm==6)
    						wPawn6=te;
    					if (wm==7)
    						wPawn7=te;
    					if (wm==8)
    						wPawn8=te;
    					if (wm==9)
    						wRook1=te;
    					if (wm==10)
    						wRook2=te;
    					if (wm==11)
    						wKnight1=te;
    					if (wm==12)
    						wKnight2=te;
    					if (wm==13)
    						wBishop1=te;
    					if (wm==14)
    						wBishop2=te;
    					if (wm==15)
    						wQueen=te;
    					if (wm==16)
    						wKing=te;
    					if (wm==17)
    						wQueen1=te;
    					if (wm==18)
    						wQueen2=te;
    					if (wm==19)
    						wQueen3=te;
    					if (wm==20)
    						wQueen4=te;
    					if (wm==21)
    						wQueen5=te;
    					if (wm==22)
    						wQueen6=te;
    					if (wm==23)
    						wQueen7=te;
    					if (wm==24)
    						wQueen8=te;
    					
    					if (bPawn1==te) {
    						bPawn1=0;
    						it=1;
    					}
    					if (bPawn2==te) {
    						bPawn2=0;
    						it=2;
    					}
    					if (bPawn3==te) {
    						bPawn3=0;
    						it=3;
    					}
    					if (bPawn4==te) {
    						bPawn4=0;
    						it=4;
    					}
    					if (bPawn5==te) {
    						bPawn5=0;
    						it=5;
    					}
    					if (bPawn6==te) {
    						bPawn6=0;
    						it=6;
    					}
    					if (bPawn7==te) {
    						bPawn7=0;
    						it=7;
    					}
    					if (bPawn8==te) {
    						bPawn8=0;
    						it=8;
    					}
    					if (bRook1==te) {
    						bRook1=0;
    						it=9;
    					}
    					if (bRook2==te) {
    						bRook2=0;
    						it=10;
    					}
    					if (bKnight1==te) {
    						bKnight1=0;
    						it=11;
    					}
    					if (bKnight2==te) {
    						bKnight2=0;
    						it=12;
    					}
    					if (bBishop1==te) {
    						bBishop1=0;
    						it=13;
    					}
    					if (bBishop2==te) {
    						bBishop2=0;
    						it=14;
    					}
    					if (bQueen==te) {
    						bQueen=0;
    						it=15;
    					}
    					if (bKing==te) {
    						bKing=0;
    						it=16;
    					}
    					if (bQueen1==te) {
    						bQueen1=0;
    						it=17;
    					}
    					if (bQueen2==te) {
    						bQueen2=0;
    						it=18;
    					}
    					if (bQueen3==te) {
    						bQueen3=0;
    						it=19;
    					}
    					if (bQueen4==te) {
    						bQueen4=0;
    						it=20;
    					}
    					if (bQueen5==te) {
    						bQueen5=0;
    						it=21;
    					}
    					if (bQueen6==te) {
    						bQueen6=0;
    						it=22;
    					}
    					if (bQueen7==te) {
    						bQueen7=0;
    						it=23;
    					}
    					if (bQueen8==te) {
    						bQueen8=0;
    						it=24;
    					}
    					clear();
    					if (!checkWCheck(wKing)) {
	    		    		x=((te-1)%8)*75+25;
	    		    		y=((te-1)/8)*75+25;
	    					gr.drawRect(x,y,75,75);
	    					gr.drawRect(x+1,y+1,73,73);
	    		    		gr.drawRect(x+2,y+2,71,71);
	    		    		gr.setColor(Color.blue);
    					}
    					if (wm==1)
    						wPawn1=g;
    					if (wm==2)
    						wPawn2=g;
    					if (wm==3)
    						wPawn3=g;
    					if (wm==4)
    						wPawn4=g;
    					if (wm==5)
    						wPawn5=g;
    					if (wm==6)
    						wPawn6=g;
    					if (wm==7)
    						wPawn7=g;
    					if (wm==8)
    						wPawn8=g;
    					if (wm==9)
    						wRook1=g;
    					if (wm==10)
    						wRook2=g;
    					if (wm==11)
    						wKnight1=g;
    					if (wm==12)
    						wKnight2=g;
    					if (wm==13)
    						wBishop1=g;
    					if (wm==14)
    						wBishop2=g;
    					if (wm==15)
    						wQueen=g;
    					if (wm==16)
    						wKing=g;
    					if (wm==17)
    						wQueen1=g;
    					if (wm==18)
    						wQueen2=g;
    					if (wm==19)
    						wQueen3=g;
    					if (wm==20)
    						wQueen4=g;
    					if (wm==21)
    						wQueen5=g;
    					if (wm==22)
    						wQueen6=g;
    					if (wm==23)
    						wQueen7=g;
    					if (wm==24)
    						wQueen8=g;
    					
    					switch (it) {
    					case 1: bPawn1=te;
    						break;
    					case 2: bPawn2=te;
	    					break;
    					case 3: bPawn3=te;
	    					break;
    					case 4: bPawn4=te;
	    					break;
    					case 5: bPawn5=te;
	    					break;
    					case 6: bPawn6=te;
	    					break;
    					case 7: bPawn7=te;
	    					break;
    					case 8: bPawn8=te;
	    					break;
    					case 9: bRook1=te;
	    					break;
    					case 10: bRook2=te;
	    					break;
    					case 11: bKnight1=te;
    						break;
    					case 12: bKnight2=te;
    						break;
    					case 13: bBishop1=te;
    						break;
    					case 14: bBishop2=te;
    						break;
    					case 15: bQueen=te;
    						break;
    					case 16: bKing=te;
    						break;
    					case 17: bQueen1=te;
    						break;
    					case 18: bQueen2=te;
    						break;
    					case 19: bQueen3=te;
    						break;
    					case 20: bQueen4=te;
    						break;
    					case 21: bQueen5=te;
    						break;
    					case 22: bQueen6=te;
    						break;
    					case 23: bQueen7=te;
    						break;
    					case 24: bQueen8=te;
    						break;
    					default:
    						break;
    					}
    					clear();
    				}
    				if (times!=0) {
    				check(h);
    				wValidCheck(g,h,retCheck(),((g-1)%8+1),false);
    				}
    			}
    			else {
    				check(te);
    				bValidCheck(g,te,retCheck(),((te-1)%8+1), false);
    				if (valid()) {
    					int it = 0;
    					if (bm==1)
    						bPawn1=te;
    					if (bm==2)
    						bPawn2=te;
    					if (bm==3)
    						bPawn3=te;
    					if (bm==4)
    						bPawn4=te;
    					if (bm==5)
    						bPawn5=te;
    					if (bm==6)
    						bPawn6=te;
    					if (bm==7)
    						bPawn7=te;
    					if (bm==8)
    						bPawn8=te;
    					if (bm==9)
    						bRook1=te;
    					if (bm==10)
    						bRook2=te;
    					if (bm==11)
    						bKnight1=te;
    					if (bm==12)
    						bKnight2=te;
    					if (bm==13)
    						bBishop1=te;
    					if (bm==14)
    						bBishop2=te;
    					if (bm==15)
    						bQueen=te;
    					if (bm==16)
    						bKing=te;
    					if (bm==17)
    						bQueen1=te;
    					if (bm==18)
    						bQueen2=te;
    					if (bm==19)
    						bQueen3=te;
    					if (bm==20)
    						bQueen4=te;
    					if (bm==21)
    						bQueen5=te;
    					if (bm==22)
    						bQueen6=te;
    					if (bm==23)
    						bQueen7=te;
    					if (bm==24)
    						bQueen8=te;
    					
    					if (wPawn1==te) {
    						wPawn1=0;
    						it=1;
    					}
    					if (wPawn2==te) {
    						wPawn2=0;
    						it=2;
    					}
    					if (wPawn3==te) {
    						wPawn3=0;
    						it=3;
    					}
    					if (wPawn4==te) {
    						wPawn4=0;
    						it=4;
    					}
    					if (wPawn5==te) {
    						wPawn5=0;
    						it=5;
    					}
    					if (wPawn6==te) {
    						wPawn6=0;
    						it=6;
    					}
    					if (wPawn7==te) {
    						wPawn7=0;
    						it=7;
    					}
    					if (wPawn8==te) {
    						wPawn8=0;
    						it=8;
    					}
    					if (wRook1==te) {
    						wRook1=0;
    						it=9;
    					}
    					if (wRook2==te) {
    						wRook2=0;
    						it=10;
    					}
    					if (wKnight1==te) {
    						wKnight1=0;
    						it=11;
    					}
    					if (wKnight2==te) {
    						wKnight2=0;
    						it=12;
    					}
    					if (wBishop1==te) {
    						wBishop1=0;
    						it=13;
    					}
    					if (wBishop2==te) {
    						wBishop2=0;
    						it=14;
    					}
    					if (wQueen==te) {
    						wQueen=0;
    						it=15;
    					}
    					if (wKing==te) {
    						wKing=0;
    						it=16;
    					}
    					if (wQueen1==te) {
    						wQueen1=0;
    						it=17;
    					}
    					if (wQueen2==te) {
    						wQueen2=0;
    						it=18;
    					}
    					if (wQueen3==te) {
    						wQueen3=0;
    						it=19;
    					}
    					if (wQueen4==te) {
    						wQueen4=0;
    						it=20;
    					}
    					if (wQueen5==te) {
    						wQueen5=0;
    						it=21;
    					}
    					if (wQueen6==te) {
    						wQueen6=0;
    						it=22;
    					}
    					if (wQueen7==te) {
    						wQueen7=0;
    						it=23;
    					}
    					if (wQueen8==te) {
    						wQueen8=0;
    						it=24;
    					}
    					clear();
    					if (!checkBCheck(bKing)) {
	    		    		x=((te-1)%8)*75+25;
	    		    		y=((te-1)/8)*75+25;
	    					gr.drawRect(x,y,75,75);
	    					gr.drawRect(x+1,y+1,73,73);
	    		    		gr.drawRect(x+2,y+2,71,71);
	    		    		gr.setColor(Color.blue);
    					}
    					if (bm==1)
    						bPawn1=g;
    					if (bm==2)
    						bPawn2=g;
    					if (bm==3)
    						bPawn3=g;
    					if (bm==4)
    						bPawn4=g;
    					if (bm==5)
    						bPawn5=g;
    					if (bm==6)
    						bPawn6=g;
    					if (bm==7)
    						bPawn7=g;
    					if (bm==8)
    						bPawn8=g;
    					if (bm==9)
    						bRook1=g;
    					if (bm==10)
    						bRook2=g;
    					if (bm==11)
    						bKnight1=g;
    					if (bm==12)
    						bKnight2=g;
    					if (bm==13)
    						bBishop1=g;
    					if (bm==14)
    						bBishop2=g;
    					if (bm==15)
    						bQueen=g;
    					if (bm==16)
    						bKing=g;
    					if (bm==17)
    						bQueen1=g;
    					if (bm==18)
    						bQueen2=g;
    					if (bm==19)
    						bQueen3=g;
    					if (bm==20)
    						bQueen4=g;
    					if (bm==21)
    						bQueen5=g;
    					if (bm==22)
    						bQueen6=g;
    					if (bm==23)
    						bQueen7=g;
    					if (bm==24)
    						bQueen8=g;
    					
    					switch (it) {
    					case 1: wPawn1=te;
    						break;
    					case 2: wPawn2=te;
	    					break;
    					case 3: wPawn3=te;
	    					break;
    					case 4: wPawn4=te;
	    					break;
    					case 5: wPawn5=te;
	    					break;
    					case 6: wPawn6=te;
	    					break;
    					case 7: wPawn7=te;
	    					break;
    					case 8: wPawn8=te;
	    					break;
    					case 9: wRook1=te;
	    					break;
    					case 10: wRook2=te;
	    					break;
    					case 11: wKnight1=te;
    						break;
    					case 12: wKnight2=te;
    						break;
    					case 13: wBishop1=te;
    						break;
    					case 14: wBishop2=te;
    						break;
    					case 15: wQueen=te;
    						break;
    					case 16: wKing=te;
    						break;
    					case 17: wQueen1=te;
    						break;
    					case 18: wQueen2=te;
    						break;
    					case 19: wQueen3=te;
    						break;
    					case 20: wQueen4=te;
    						break;
    					case 21: wQueen5=te;
    						break;
    					case 22: wQueen6=te;
    						break;
    					case 23: wQueen7=te;
    						break;
    					case 24: wQueen8=te;
    						break;
    					default:
    						break;
    					}
    					clear();
    				}
    				if (times!=0) {
    				check(h);
    				bValidCheck(g,h,retCheck(),((g-1)%8+1),false);
    				}
    			}
    		}
    		gr.setColor(Color.red);
    		x=((g-1)%8)*75+25;
    		y=((g-1)/8)*75+25;
    		gr.drawRect(x, y, 75, 75);
    		gr.drawRect(x+1,y+1,73,73);
    		gr.drawRect(x+2,y+2,71,71);
    	}
    }
    
    
    
    public static int retBX(int piece)
    {
        switch(piece)
        {
            case 1:
            	if (bPawn1>0)
            		return ((bPawn1-1)%8)*75+40;
            	else
            		return -500;
            case 2:
            	if (bPawn2>0)
                	return ((bPawn2-1)%8)*75+40;
                else
                	return -500;
            case 3:
            	if (bPawn3>0)
            		return ((bPawn3-1)%8)*75+40;
            	else
            		return -500;
            case 4:
            	if (bPawn4>0)
            		return ((bPawn4-1)%8)*75+40;
            	else
            		return -500;
            case 5:
            	if (bPawn5>0)
            		return ((bPawn5-1)%8)*75+40;
            	else
            		return -500;
            case 6:
            	if (bPawn6>0)
            		return ((bPawn6-1)%8)*75+40;
            	else
            		return -500;
            case 7:
            	if (bPawn7>0)
            		return ((bPawn7-1)%8)*75+40;
            	else
            		return -500;
            case 8:
            	if (bPawn8>0)
            		return ((bPawn8-1)%8)*75+40;
            	else
            		return -500;
            case 9:
            	if (bBishop1>0)
            		return ((bBishop1-1)%8)*75+40;
            	else
            		return -500;
            case 10:
            	if (bBishop2>0)
            		return ((bBishop2-1)%8)*75+40;
            	else
            		return -500;
            case 11:
            	if (bKnight1>0)
            		return ((bKnight1-1)%8)*75+40;
            	else
            		return -500;
            case 12:
            	if (bKnight2>0)
            		return ((bKnight2-1)%8)*75+40;
            	else
            		return -500;
            case 13:
            	if (bRook1>0)
            		return ((bRook1-1)%8)*75+40;
            	else
            		return -500;
            case 14:
            	if (bRook2>0)
            		return ((bRook2-1)%8)*75+40;
            	else
            		return -500;
            case 15:
            	if (bQueen>0)
            		return ((bQueen-1)%8)*75+40;
            	else
            		return -500;
            case 16:
            	if (bKing>0)
            		return ((bKing-1)%8)*75+40;
            	else
            		return -500;
            case 17:
            	if (bQueen1>0)
            		return ((bQueen1-1)%8)*75+40;
            	else
            		return -500;
            case 18:
            	if (bQueen2>0)
            		return ((bQueen2-1)%8)*75+40;
            	else
            		return -500;
            case 19:
            	if (bQueen3>0)
            		return ((bQueen3-1)%8)*75+40;
            	else
            		return -500;
            case 20:
            	if (bQueen4>0)
            		return ((bQueen4-1)%8)*75+40;
            	else
            		return -500;
            case 21:
            	if (bQueen5>0)
            		return ((bQueen5-1)%8)*75+40;
            	else
            		return -500;
            case 22:
            	if (bQueen6>0)
            		return ((bQueen6-1)%8)*75+40;
            	else
            		return -500;
            case 23:
            	if (bQueen7>0)
            		return ((bQueen7-1)%8)*75+40;
            	else
            		return -500;
            case 24:
            	if (bQueen8>0)
            		return ((bQueen8-1)%8)*75+40;
            	else
            		return -500;
            default:
                return 0;
        }
    }
    public static int retBY(int piece)
    {
        switch(piece)
        {
        case 1:
        	if (bPawn1>0)
        		return ((bPawn1-1)/8+1)*75;
        	else
        		return -500;
        case 2:
        	if (bPawn2>0)
        		return ((bPawn2-1)/8+1)*75;
        	else
        		return -500;
        case 3:
        	if (bPawn3>0)
        		return ((bPawn3-1)/8+1)*75;
        	else
        		return -500;
        case 4:
        	if (bPawn4>0)
        		return ((bPawn4-1)/8+1)*75;
        	else
        		return -500;
        case 5:
        	if (bPawn5>0)
        		return ((bPawn5-1)/8+1)*75;
        	else
        		return -500;
        case 6:
        	if (bPawn6>0)
        		return ((bPawn6-1)/8+1)*75;
        	else
        		return -500;
        case 7:
        	if (bPawn7>0)
        		return ((bPawn7-1)/8+1)*75;
        	else
        		return -500;
        case 8:
        	if (bPawn8>0)
        		return ((bPawn8-1)/8+1)*75;
        	else
        		return -500;
        case 9:
        	if (bBishop1>0)
        		return ((bBishop1-1)/8+1)*75;
        	else
        		return -500;
        case 10:
        	if (bBishop2>0)
        		return ((bBishop2-1)/8+1)*75;
        	else
        		return -500;
        case 11:
        	if (bKnight1>0)
        		return ((bKnight1-1)/8+1)*75;
        	else
        		return -500;
        case 12:
        	if (bKnight2>0)
        		return ((bKnight2-1)/8+1)*75;
        	else
        		return -500;
        case 13:
        	if (bRook1>0)
        		return ((bRook1-1)/8+1)*75;
        	else
        		return -500;
        case 14:
        	if (bRook2>0)
        		return ((bRook2-1)/8+1)*75;
        	else
        		return -500;
        case 15:
        	if (bQueen>0)
        		return ((bQueen-1)/8+1)*75;
        	else
        		return -500;
        case 16:
        	if (bKing>0)
        		return ((bKing-1)/8+1)*75;
        	else
        		return -500;
        case 17:
        	if (bQueen1>0)
        		return ((bQueen1-1)/8+1)*75;
        	else
        		return -500;
        case 18:
        	if (bQueen2>0)
        		return ((bQueen2-1)/8+1)*75;
        	else
        		return -500;
        case 19:
        	if (bQueen3>0)
        		return ((bQueen3-1)/8+1)*75;
        	else
        		return -500;
        case 20:
        	if (bQueen4>0)
        		return ((bQueen4-1)/8+1)*75;
        	else
        		return -500;
        case 21:
        	if (bQueen5>0)
        		return ((bQueen5-1)/8+1)*75;
        	else
        		return -500;
        case 22:
        	if (bQueen6>0)
        		return ((bQueen6-1)/8+1)*75;
        	else
        		return -500;
        case 23:
        	if (bQueen7>0)
        		return ((bQueen7-1)/8+1)*75;
        	else
        		return -500;
        case 24:
        	if (bQueen8>0)
        		return ((bQueen8-1)/8+1)*75;
        	else
        		return -500;
        default:
            return 0;
        }
    }
    public static int retWX(int piece)
    {
        switch(piece)
        {
        case 1:
        	if (wPawn1>0)
        		return ((wPawn1-1)%8)*75+40;
        	else
        		return -500;
        case 2:
        	if (wPawn2>0)
            	return ((wPawn2-1)%8)*75+40;
            else
            	return -500;
        case 3:
        	if (wPawn3>0)
        		return ((wPawn3-1)%8)*75+40;
        	else
        		return -500;
        case 4:
        	if (wPawn4>0)
        		return ((wPawn4-1)%8)*75+40;
        	else
        		return -500;
        case 5:
        	if (wPawn5>0)
        		return ((wPawn5-1)%8)*75+40;
        	else
        		return -500;
        case 6:
        	if (wPawn6>0)
        		return ((wPawn6-1)%8)*75+40;
        	else
        		return -500;
        case 7:
        	if (wPawn7>0)
        		return ((wPawn7-1)%8)*75+40;
        	else
        		return -500;
        case 8:
        	if (wPawn8>0)
        		return ((wPawn8-1)%8)*75+40;
        	else
        		return -500;
        case 9:
        	if (wBishop1>0)
        		return ((wBishop1-1)%8)*75+40;
        	else
        		return -500;
        case 10:
        	if (wBishop2>0)
        		return ((wBishop2-1)%8)*75+40;
        	else
        		return -500;
        case 11:
        	if (wKnight1>0)
        		return ((wKnight1-1)%8)*75+40;
        	else
        		return -500;
        case 12:
        	if (wKnight2>0)
        		return ((wKnight2-1)%8)*75+40;
        	else
        		return -500;
        case 13:
        	if (wRook1>0)
        		return ((wRook1-1)%8)*75+40;
        	else
        		return -500;
        case 14:
        	if (wRook2>0)
        		return ((wRook2-1)%8)*75+40;
        	else
        		return -500;
        case 15:
        	if (wQueen>0)
        		return ((wQueen-1)%8)*75+40;
        	else
        		return -500;
        case 16:
        	if (wKing>0)
        		return ((wKing-1)%8)*75+40;
        	else
        		return -500;
        case 17:
        	if (wQueen1>0)
        		return ((wQueen1-1)%8)*75+40;
        	else
        		return -500;
        case 18:
        	if (wQueen2>0)
        		return ((wQueen2-1)%8)*75+40;
        	else
        		return -500;
        case 19:
        	if (wQueen3>0)
        		return ((wQueen3-1)%8)*75+40;
        	else
        		return -500;
        case 20:
        	if (wQueen4>0)
        		return ((wQueen4-1)%8)*75+40;
        	else
        		return -500;
        case 21:
        	if (wQueen5>0)
        		return ((wQueen5-1)%8)*75+40;
        	else
        		return -500;
        case 22:
        	if (wQueen6>0)
        		return ((wQueen6-1)%8)*75+40;
        	else
        		return -500;
        case 23:
        	if (wQueen7>0)
        		return ((wQueen7-1)%8)*75+40;
        	else
        		return -500;
        case 24:
        	if (wQueen8>0)
        		return ((wQueen8-1)%8)*75+40;
        	else
        		return -500;
        default:
            return 0;
        }
    }
    public static int retWY(int piece)
    {
        switch(piece)
        {
            case 1:
            	if (wPawn1>0)
            		return ((wPawn1-1)/8+1)*75;
            	else
            		return -500;
            case 2:
            	if (wPawn2>0)
            		return ((wPawn2-1)/8+1)*75;
            	else
            		return -500;
            case 3:
            	if (wPawn3>0)
            		return ((wPawn3-1)/8+1)*75;
            	else
            		return -500;
            case 4:
            	if (wPawn4>0)
            		return ((wPawn4-1)/8+1)*75;
            	else
            		return -500;
            case 5:
            	if (wPawn5>0)
            		return ((wPawn5-1)/8+1)*75;
            	else
            		return -500;
            case 6:
            	if (wPawn6>0)
            		return ((wPawn6-1)/8+1)*75;
            	else
            		return -500;
            case 7:
            	if (wPawn7>0)
            		return ((wPawn7-1)/8+1)*75;
            	else
            		return -500;
            case 8:
            	if (wPawn8>0)
            		return ((wPawn8-1)/8+1)*75;
            	else
            		return -500;
            case 9:
            	if (wBishop1>0)
            		return ((wBishop1-1)/8+1)*75;
            	else
            		return -500;
            case 10:
            	if (wBishop2>0)
            		return ((wBishop2-1)/8+1)*75;
            	else
            		return -500;
            case 11:
            	if (wKnight1>0)
            		return ((wKnight1-1)/8+1)*75;
            	else
            		return -500;
            case 12:
            	if (wKnight2>0)
            		return ((wKnight2-1)/8+1)*75;
            	else
            		return -500;
            case 13:
            	if (wRook1>0)
            		return ((wRook1-1)/8+1)*75;
            	else
            		return -500;
            case 14:
            	if (wRook2>0)
            		return ((wRook2-1)/8+1)*75;
            	else
            		return -500;
            case 15:
            	if (wQueen>0)
            		return ((wQueen-1)/8+1)*75;
            	else
            		return -500;
            case 16:
            	if (wKing>0)
            		return ((wKing-1)/8+1)*75;
            	else
            		return -500;
            case 17:
            	if (wQueen1>0)
            		return ((wQueen1-1)/8+1)*75;
            	else
            		return -500;
            case 18:
            	if (wQueen2>0)
            		return ((wQueen2-1)/8+1)*75;
            	else
            		return -500;
            case 19:
            	if (wQueen3>0)
            		return ((wQueen3-1)/8+1)*75;
            	else
            		return -500;
            case 20:
            	if (wQueen4>0)
            		return ((wQueen4-1)/8+1)*75;
            	else
            		return -500;
            case 21:
            	if (wQueen5>0)
            		return ((wQueen5-1)/8+1)*75;
            	else
            		return -500;
            case 22:
            	if (wQueen6>0)
            		return ((wQueen6-1)/8+1)*75;
            	else
            		return -500;
            case 23:
            	if (wQueen7>0)
            		return ((wQueen7-1)/8+1)*75;
            	else
            		return -500;
            case 24:
            	if (wQueen8>0)
            		return ((wQueen8-1)/8+1)*75;
            	else
            		return -500;
            default:
                return 0;
        }
    }
    public static void setArrays()
    {
        int t=63;
        while (t>=0)
        {
            s[t]="      ";
            t--;
        }
        t=63;
        while (t>=0)
        {
            p[t]=0;
            t--;
        }
        if (wPawn1!=0)
        	p[wPawn1-1]=1;
        if (wPawn2!=0)
        	p[wPawn2-1]=1;
        if (wPawn3!=0)
        	p[wPawn3-1]=1;
        if (wPawn4!=0)
        	p[wPawn4-1]=1;
        if (wPawn5!=0)
        	p[wPawn5-1]=1;
        if (wPawn6!=0)
        	p[wPawn6-1]=1;
        if (wPawn7!=0)
        	p[wPawn7-1]=1;
        if (wPawn8!=0)
        	p[wPawn8-1]=1;
        if (wBishop1!=0)
        	p[wBishop1-1]=1;
        if (wBishop2!=0)
        	p[wBishop2-1]=1;
        if (wKnight1!=0)
        	p[wKnight1-1]=1;
        if (wKnight2!=0)
        	p[wKnight2-1]=1;
        if (wRook1!=0)
        	p[wRook1-1]=1;
        if (wRook2!=0)
        	p[wRook2-1]=1;
        if (wQueen!=0)
        	p[wQueen-1]=1;
        if (wKing!=0)
        	p[wKing-1]=1;
        
        if (bPawn1!=0)
        	p[bPawn1-1]=2;
        if (bPawn2!=0)
        	p[bPawn2-1]=2;
        if (bPawn3!=0)
        	p[bPawn3-1]=2;
        if (bPawn4!=0)
        	p[bPawn4-1]=2;
        if (bPawn5!=0)
        	p[bPawn5-1]=2;
        if (bPawn6!=0)
        	p[bPawn6-1]=2;
        if (bPawn7!=0)
        	p[bPawn7-1]=2;
        if (bPawn8!=0)
        	p[bPawn8-1]=2;
        if (bBishop1!=0)
        	p[bBishop1-1]=2;
        if (bBishop2!=0)
        	p[bBishop2-1]=2;
        if (bKnight1!=0)
        	p[bKnight1-1]=2;
        if (bKnight2!=0)
        	p[bKnight2-1]=2;
        if (bRook1!=0)
        	p[bRook1-1]=2;
        if (bRook2!=0)
        	p[bRook2-1]=2;
        if (bQueen!=0)
        	p[bQueen-1]=2;
        if (bKing!=0)
        	p[bKing-1]=2;
        
        t=7;
        while (t>=0)
        {
            pawn[t]=0;
            t--;
        }
        t=7;
        while (t>=0) {
        	bawn[t]=0;
        	t--;
        }
    }
    public static void bCheck(int g)
    {
        bm=0;
        
        if (g==bPawn1)
        {
            bm = 1;
        }
        if (g==bPawn2)
        {
            bm = 2;
        }
        if (g==bPawn3)
        {
            bm = 3;
        }
        if (g==bPawn4)
        {
            bm = 4;
        }
        if (g==bPawn5)
        {
            bm = 5;
        }
        if (g==bPawn6)
        {
            bm = 6;
        }
        if (g==bPawn7)
        {
            bm = 7;
        }
        if (g==bPawn8)
        {
            bm = 8;
        }
        
        if (g==bRook1)
        {
            bm = 9;
        }
        if (g==bRook2)
        {
            bm = 10;
        }
        
        if (g==bKnight1)
        {
            bm = 11;
        }
        if (g==bKnight2)
        {
            bm = 12;
        }
        
        if (g==bBishop1)
        {
            bm = 13;
        }
        if (g==bBishop2)
        {
            bm = 14;
        }
        
        if (g==bQueen)
        {
            bm = 15;
        }
        
        if (g==bKing)
        {
            bm = 16;
        }
        
        if (g==bQueen1)
        {
            bm = 17;
        }
        if (g==bQueen2)
        {
            bm = 18;
        }
        if (g==bQueen3)
        {
            bm = 19;
        }
        if (g==bQueen4)
        {
            bm = 20;
        }
        if (g==bQueen5)
        {
            bm = 21;
        }
        if (g==bQueen6)
        {
            bm = 22;
        }
        if (g==bQueen7)
        {
            bm = 23;
        }
        if (g==bQueen8)
        {
            bm = 24;
        }
        
    }
    public static int retBMove()
    {
        return bm;
    }
    public static void wCheck(int g)
    {
        wm=0;
        
        if (g==wPawn1)
        {
            wm = 1;
        }
        if (g==wPawn2)
        {
            wm = 2;
        }
        if (g==wPawn3)
        {
            wm = 3;
        }
        if (g==wPawn4)
        {
            wm = 4;
        }
        if (g==wPawn5)
        {
            wm = 5;
        }
        if (g==wPawn6)
        {
            wm = 6;
        }
        if (g==wPawn7)
        {
            wm = 7;
        }
        if (g==wPawn8)
        {
            wm = 8;
        }
        
        if (g==wRook1)
        {
            wm = 9;
        }
        if (g==wRook2)
        {
            wm = 10;
        }
        
        if (g==wKnight1)
        {
            wm = 11;
        }
        if (g==wKnight2)
        {
            wm = 12;
        }
        
        if (g==wBishop1)
        {
            wm = 13;
        }
        if (g==wBishop2)
        {
            wm = 14;
        }
        
        if (g==wQueen)
        {
            wm = 15;
        }
        
        if (g==wKing)
        {
            wm = 16;
        }
        
        if (g==wQueen1)
        {
            wm = 17;
        }
        if (g==wQueen2)
        {
            wm = 18;
        }
        if (g==wQueen3)
        {
            wm = 19;
        }
        if (g==wQueen4)
        {
            wm = 20;
        }
        if (g==wQueen5)
        {
            wm = 21;
        }
        if (g==wQueen6)
        {
            wm = 22;
        }
        if (g==wQueen7)
        {
            wm = 23;
        }
        if (g==wQueen8)
        {
            wm = 24;
        }
        
        
    }
    public static int retWMove()
    {
        return wm;
    }
    public String retArray()
    {
        String thi="";
        for (int x=0; x<=63; x++)
        {
            thi+=" "+p[x];
        }
        return thi;
    }
    public static void wValidCheck(int g,int h, int i, int lett, boolean tt)
    {
        int test=g;    //h=chosen space,i=availability, lett=letter, ex. A=1, B=2
        int testi=0;
        int ch=0;
        valid=false;
        
        //pawns
        if (wm<=8 && wm>=1)
        {
            int sww = 0;
            if ((test%8)==1)
            {
                sww=1;
            }
            if ((test%8)==0)
            {
                sww=2;
            }
            test+=8;
            if (test==h)
            {
                if (i==0)
                {
                    valid=true;
                    testi=test;
                    if (tt)
                    	pawn[wm-1]=1;
                    if (tt && wm==1)
                    {
                        wPawn1=test;
                    }
                    if (tt && wm==2)
                    {
                        wPawn2=test;
                    }
                    if (tt && wm==3)
                    {
                        wPawn3=test;
                    }
                    if (tt && wm==4)
                    {
                        wPawn4=test;
                    }
                    if (tt && wm==5)
                    {
                        wPawn5=test;
                    }
                    if (tt && wm==6)
                    {
                        wPawn6=test;
                    }
                    if (tt && wm==7)
                    {
                        wPawn7=test;
                    }
                    if (tt && wm==8)
                    {
                        wPawn8=test;
                    }
                }
            }
            test--;
            if (test==h && sww!=1)
            {
                if (i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt)
                    	pawn[wm-1]=1;
                    if (tt && wm==1)
                    {
                        wPawn1=test;
                    }
                    if (tt && wm==2)
                    {
                        wPawn2=test;
                    }
                    if (tt && wm==3)
                    {
                        wPawn3=test;
                    }
                    if (tt && wm==4)
                    {
                        wPawn4=test;
                    }
                    if (tt && wm==5)
                    {
                        wPawn5=test;
                    }
                    if (tt && wm==6)
                    {
                        wPawn6=test;
                    }
                    if (tt && wm==7)
                    {
                        wPawn7=test;
                    }
                    if (tt && wm==8)
                    {
                        wPawn8=test;
                    }
                }
            }
            test+=2;
            if (test==h && sww!=2)
            {
                if (i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt)
                    	pawn[wm-1]=1;
                    if (tt && wm==1)
                    {
                        wPawn1=test;
                    }
                    if (tt && wm==2)
                    {
                        wPawn2=test;
                    }
                    if (tt && wm==3)
                    {
                        wPawn3=test;
                    }
                    if (tt && wm==4)
                    {
                        wPawn4=test;
                    }
                    if (tt && wm==5)
                    {
                        wPawn5=test;
                    }
                    if (tt && wm==6)
                    {
                        wPawn6=test;
                    }
                    if (tt && wm==7)
                    {
                        wPawn7=test;
                    }
                    if (tt && wm==8)
                    {
                        wPawn8=test;
                    }
                }
            }
            test=g;
            test+=16;
            if (pawn[wm-1]==0)
            {
                if (test==h)
                {
                    if (i==0)
                    {
                    	check(test-8);
                    	if (retCheck()==0) {
	                        valid=true;
	                        testi=test;
	                        if (tt)
	                        	pawn[wm-1]=1;
	                        if (tt && wm==1)
	                        {
	                            wPawn1=test;
	                        }
	                        if (tt && wm==2)
	                        {
	                            wPawn2=test;
	                        }
	                        if (tt && wm==3)
	                        {
	                            wPawn3=test;
	                        }
	                        if (tt && wm==4)
	                        {
	                            wPawn4=test;
	                        }
	                        if (tt && wm==5)
	                        {
	                            wPawn5=test;
	                        }
	                        if (tt && wm==6)
	                        {
	                            wPawn6=test;
	                        }
	                        if (tt && wm==7)
	                        {
	                            wPawn7=test;
	                        }
	                        if (tt && wm==8)
	                        {
	                            wPawn8=test;
	                        }
                    	}
                    	else
                    		valid=false;
                    }
                }
            }
            check(h);
            if (tt && valid)
            {
                p[testi-1]=1;
                p[g-1]=0;
            }
            
            if (tt && valid)
            {
                if (wPawn1>=57&&wPawn1<=64)
                {
                    wQueen1=wPawn1;
                    wPawn1=0;
                }
                if (wPawn2>=57&&wPawn2<=64)
                {
                    wQueen2=wPawn2;
                    wPawn2=0;
                }
                if (wPawn3>=57&&wPawn3<=64)
                {
                    wQueen3=wPawn3;
                    wPawn3=0;
                }
                if (wPawn4>=57&&wPawn4<=64)
                {
                    wQueen4=wPawn4;
                    wPawn4=0;
                }
                if (wPawn5>=57&&wPawn5<=64)
                {
                    wQueen5=wPawn5;
                    wPawn5=0;
                }
                if (wPawn6>=57&&wPawn6<=64)
                {
                    wQueen6=wPawn6;
                    wPawn6=0;
                }
                if (wPawn7>=57&&wPawn7<=64)
                {
                    wQueen7=wPawn7;
                    wPawn7=0;
                }
                if (wPawn8>=57&&wPawn8<=64)
                {
                    wQueen8=wPawn8;
                    wPawn8=0;
                }
            }
            
            if (tt && valid)
            {
                if (bPawn1==h)
                {
                    bPawn1=0;
                }
                if (bPawn2==h)
                {
                    bPawn2=0;
                }
                if (bPawn3==h)
                {
                    bPawn3=0;
                }
                if (bPawn4==h)
                {
                    bPawn4=0;
                }
                if (bPawn5==h)
                {
                    bPawn5=0;
                }
                if (bPawn6==h)
                {
                    bPawn6=0;
                }
                if (bPawn7==h)
                {
                    bPawn7=0;
                }
                if (bPawn8==h)
                {
                    bPawn8=0;
                }
                if (bBishop1==h)
                {
                    bBishop1=0;
                }
                if (bBishop2==h)
                {
                    bBishop2=0;
                }
                if (bRook1==h)
                {
                    bRook1=0;
                }
                if (bRook2==h)
                {
                    bRook2=0;
                }
                if (bKnight1==h)
                {
                    bKnight1=0;
                }
                if (bKnight2==h)
                {
                    bKnight2=0;
                }
                if (bQueen==h)
                {
                    bQueen=0;
                }
                if (bKing==h)
                {
                    bKing=0;
                }
                if (bQueen1==h)
                {
                    bQueen1=0;
                }
                if (bQueen2==h)
                {
                    bQueen2=0;
                }
                if (bQueen3==h)
                {
                    bQueen3=0;
                }
                if (bQueen4==h)
                {
                    bQueen4=0;
                }
                if (bQueen5==h)
                {
                    bQueen5=0;
                }
                if (bQueen6==h)
                {
                    bQueen6=0;
                }
                if (bQueen7==h)
                {
                    bQueen7=0;
                }
                if (bQueen8==h)
                {
                    bQueen8=0;
                }
            }
        }
        
        //rooks
        if (wm==9 || wm==10)
        {
        	int tester=0;
            test+=8;
            ch=0;
            while (test<=64 && ch==0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                }
                try {
	                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
	                {
	                    ch=1;
	                }
                }
	            catch(Exception e) {
	            	ch=1;
	            }
                test+=8;
            }
            
            ch=0;
            test=g;
            test++;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (ch==0 && tester<8)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                }
                try {
	                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
	                {
	                    ch=1;
	                }
                }
	            catch (Exception e) {
	            	ch=1;
	            }
                test++;
                tester++;
            }
            
            ch=0;
            test=g;
            test--;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (test>0 && ch==0 && tester>1)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                }
                try {
	                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
	                {
	                    ch=1;
	                }
                }
	            catch(Exception e) {
	            	ch=1;
	            }
                test--;
                tester--;
            }
            
            ch=0;
            test=g;
            test-=8;
            while (test>=1 && ch==0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==9)
                        {
                            wRook1=test;
                            wRook1NeverMoved=false;
                        }
                        if (tt && wm==10)
                        {
                            wRook2=test;
                            wRook2NeverMoved=false;
                        }
                    }
                }
                try {
		            if ((p[test-1]==1||p[test-1]==2)&&valid==false)
		            {
		                ch=1;
		            }
                }
	            catch(Exception e) {
	            	ch=1;
	            }
                test-=8;
            }
            
            if (tt && valid)
            {
                p[testi-1]=1;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (bPawn1==h)
                {
                    bPawn1=0;
                }
                if (bPawn2==h)
                {
                    bPawn2=0;
                }
                if (bPawn3==h)
                {
                    bPawn3=0;
                }
                if (bPawn4==h)
                {
                    bPawn4=0;
                }
                if (bPawn5==h)
                {
                    bPawn5=0;
                }
                if (bPawn6==h)
                {
                    bPawn6=0;
                }
                if (bPawn7==h)
                {
                    bPawn7=0;
                }
                if (bPawn8==h)
                {
                    bPawn8=0;
                }
                if (bBishop1==h)
                {
                    bBishop1=0;
                }
                if (bBishop2==h)
                {
                    bBishop2=0;
                }
                if (bRook1==h)
                {
                    bRook1=0;
                }
                if (bRook2==h)
                {
                    bRook2=0;
                }
                if (bKnight1==h)
                {
                    bKnight1=0;
                }
                if (bKnight2==h)
                {
                    bKnight2=0;
                }
                if (bQueen==h)
                {
                    bQueen=0;
                }
                if (bKing==h)
                {
                    bKing=0;
                }
                if (bQueen1==h)
                {
                    bQueen1=0;
                }
                if (bQueen2==h)
                {
                    bQueen2=0;
                }
                if (bQueen3==h)
                {
                    bQueen3=0;
                }
                if (bQueen4==h)
                {
                    bQueen4=0;
                }
                if (bQueen5==h)
                {
                    bQueen5=0;
                }
                if (bQueen6==h)
                {
                    bQueen6=0;
                }
                if (bQueen7==h)
                {
                    bQueen7=0;
                }
                if (bQueen8==h)
                {
                    bQueen8=0;
                }
            }
        }
        
        //knight
        if (wm==11 || wm==12)
        {
            int k=0;
            if ((test%8)==1)
            {
                k=1;
            }
            if ((test%8)==2)
            {
                k=2;
            }
            if ((test%8)>2&&(test%8)<7)
            {
                k=0;
            }
            if ((test%8)==7)
            {
                k=3;
            }
            if ((test%8)==0)
            {
                k=4;
            }
            test+=6;
            if (test==h && k!=1 && k!=2)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            test+=4;
            if (test==h && k!=3 && k!=4)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            test+=5;
            if (test==h && k!=1)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            test+=2;
            if (test==h && k!=4)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            test=g;
            test-=6;
            if (test==h && k!=3 && k!=4)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            test-=4;
            if (test==h && k!=1 && k!=2)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            test-=5;
            if (test==h && k!=4)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            test-=2;
            if (test==h && k!=1)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt && wm==11)
                    {
                        wKnight1=test;
                    }
                    if (tt && wm==12)
                    {
                        wKnight2=test;
                    }
                }
            }
            
            
            
            if (tt && valid)
            {
                p[testi-1]=1;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (bPawn1==h)
                {
                    bPawn1=0;
                }
                if (bPawn2==h)
                {
                    bPawn2=0;
                }
                if (bPawn3==h)
                {
                    bPawn3=0;
                }
                if (bPawn4==h)
                {
                    bPawn4=0;
                }
                if (bPawn5==h)
                {
                    bPawn5=0;
                }
                if (bPawn6==h)
                {
                    bPawn6=0;
                }
                if (bPawn7==h)
                {
                    bPawn7=0;
                }
                if (bPawn8==h)
                {
                    bPawn8=0;
                }
                if (bBishop1==h)
                {
                    bBishop1=0;
                }
                if (bBishop2==h)
                {
                    bBishop2=0;
                }
                if (bRook1==h)
                {
                    bRook1=0;
                }
                if (bRook2==h)
                {
                    bRook2=0;
                }
                if (bKnight1==h)
                {
                    bKnight1=0;
                }
                if (bKnight2==h)
                {
                    bKnight2=0;
                }
                if (bQueen==h)
                {
                    bQueen=0;
                }
                if (bKing==h)
                {
                    bKing=0;
                }
                if (bQueen1==h)
                {
                    bQueen1=0;
                }
                if (bQueen2==h)
                {
                    bQueen2=0;
                }
                if (bQueen3==h)
                {
                    bQueen3=0;
                }
                if (bQueen4==h)
                {
                    bQueen4=0;
                }
                if (bQueen5==h)
                {
                    bQueen5=0;
                }
                if (bQueen6==h)
                {
                    bQueen6=0;
                }
                if (bQueen7==h)
                {
                    bQueen7=0;
                }
                if (bQueen8==h)
                {
                    bQueen8=0;
                }
            }
        }
        
        //Bishop
        if (wm==13 || wm==14)
        {
            int tes=test%8;
            int count=0;
            
            
            test+=7;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=7;
                count--;
            }
            
            ch=0;
            test=g;
            test+=9;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=9;
                count--;
            }
            
            ch=0;
            test=g;
            test-=9;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=9;
                count--;
            }
            
            ch=0;
            test=g;
            test-=7;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && wm==13)
                        {
                            wBishop1=test;
                        }
                        if (tt && wm==14)
                        {
                            wBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=7;
                count--;
            }
            
            
            
            if (tt && valid)
            {
                p[testi-1]=1;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (bPawn1==h)
                {
                    bPawn1=0;
                }
                if (bPawn2==h)
                {
                    bPawn2=0;
                }
                if (bPawn3==h)
                {
                    bPawn3=0;
                }
                if (bPawn4==h)
                {
                    bPawn4=0;
                }
                if (bPawn5==h)
                {
                    bPawn5=0;
                }
                if (bPawn6==h)
                {
                    bPawn6=0;
                }
                if (bPawn7==h)
                {
                    bPawn7=0;
                }
                if (bPawn8==h)
                {
                    bPawn8=0;
                }
                if (bBishop1==h)
                {
                    bBishop1=0;
                }
                if (bBishop2==h)
                {
                    bBishop2=0;
                }
                if (bRook1==h)
                {
                    bRook1=0;
                }
                if (bRook2==h)
                {
                    bRook2=0;
                }
                if (bKnight1==h)
                {
                    bKnight1=0;
                }
                if (bKnight2==h)
                {
                    bKnight2=0;
                }
                if (bQueen==h)
                {
                    bQueen=0;
                }
                if (bKing==h)
                {
                    bKing=0;
                }
                if (bQueen1==h)
                {
                    bQueen1=0;
                }
                if (bQueen2==h)
                {
                    bQueen2=0;
                }
                if (bQueen3==h)
                {
                    bQueen3=0;
                }
                if (bQueen4==h)
                {
                    bQueen4=0;
                }
                if (bQueen5==h)
                {
                    bQueen5=0;
                }
                if (bQueen6==h)
                {
                    bQueen6=0;
                }
                if (bQueen7==h)
                {
                    bQueen7=0;
                }
                if (bQueen8==h)
                {
                    bQueen8=0;
                }
            }
        }
        
        //queen
        if (wm==15||(wm>=17&&wm<=24))
        {
        	int tester=0;
            test+=8;
            ch=0;
            while (test<=64 && ch==0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=8;
            }
            
            ch=0;
            test=g;
            test++;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (ch==0 && tester<8)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test++;
                tester++;
            }
            
            ch=0;
            test=g;
            test--;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (ch==0 && test>0 && tester>1)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test--;
                tester--;
            }
            
            ch=0;
            test=g;
            test-=8;
            while (test>=1 && ch==0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=8;
            }
            test=g;
            
            
            int tes=test%8;
            int count=0;
            
            ch=0;
            test+=7;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=7;
                count--;
            }
            
            ch=0;
            test=g;
            test+=9;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=9;
                count--;
            }
            
            ch=0;
            test=g;
            test-=9;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=9;
                count--;
            }
            
            ch=0;
            test=g;
            test-=7;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==2)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (wm)
	                        {
	                            case 15:
	                            wQueen=test;
	                            break;
	                            case 17:
	                            wQueen1=test;
	                            break;
	                            case 18:
	                            wQueen2=test;
	                            break;
	                            case 19:
	                            wQueen3=test;
	                            break;
	                            case 20:
	                            wQueen4=test;
	                            break;
	                            case 21:
	                            wQueen5=test;
	                            break;
	                            case 22:
	                            wQueen6=test;
	                            break;
	                            case 23:
	                            wQueen7=test;
	                            break;
	                            case 24:
	                            wQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=7;
                count--;
            }
            
            
            
            if (tt && valid)
            {
                p[testi-1]=1;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (bPawn1==h)
                {
                    bPawn1=0;
                }
                if (bPawn2==h)
                {
                    bPawn2=0;
                }
                if (bPawn3==h)
                {
                    bPawn3=0;
                }
                if (bPawn4==h)
                {
                    bPawn4=0;
                }
                if (bPawn5==h)
                {
                    bPawn5=0;
                }
                if (bPawn6==h)
                {
                    bPawn6=0;
                }
                if (bPawn7==h)
                {
                    bPawn7=0;
                }
                if (bPawn8==h)
                {
                    bPawn8=0;
                }
                if (bBishop1==h)
                {
                    bBishop1=0;
                }
                if (bBishop2==h)
                {
                    bBishop2=0;
                }
                if (bRook1==h)
                {
                    bRook1=0;
                }
                if (bRook2==h)
                {
                    bRook2=0;
                }
                if (bKnight1==h)
                {
                    bKnight1=0;
                }
                if (bKnight2==h)
                {
                    bKnight2=0;
                }
                if (bQueen==h)
                {
                    bQueen=0;
                }
                if (bKing==h)
                {
                    bKing=0;
                }
                if (bQueen1==h)
                {
                    bQueen1=0;
                }
                if (bQueen2==h)
                {
                    bQueen2=0;
                }
                if (bQueen3==h)
                {
                    bQueen3=0;
                }
                if (bQueen4==h)
                {
                    bQueen4=0;
                }
                if (bQueen5==h)
                {
                    bQueen5=0;
                }
                if (bQueen6==h)
                {
                    bQueen6=0;
                }
                if (bQueen7==h)
                {
                    bQueen7=0;
                }
                if (bQueen8==h)
                {
                    bQueen8=0;
                }
            }
        }
        
        //king
        if (wm==16)
        {
            int sw=0;
            if ((test%8)==1)
            {
                sw=1;
            }
            if ((test%8)==0)
            {
                sw=2;
            }
            test++;
            if (test==h && sw!=2)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test+=6;
            if (test==h && sw!=1)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test++;
            if (test==h)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test++;
            if (test==h && sw!=2)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test=g;
            test--;
            if (test==h && sw!=1)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test-=6;
            if (test==h && sw!=2)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test--;
            if (test==h)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test--;
            if (test==h && sw!=1)
            {
                if (i==0 || i==2)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	wKing=test;
                    	wKingNeverMoved=false;
                    }
                }
            }
            test=g;
            test-=2;
            if (test==h && wKingNeverMoved && wRook1NeverMoved && wRook1==1) {
            	if (i==0) {
            		check(test+1);
            		if (retCheck()==0) {
            			valid=true;
            			testi=test;
            			check(h);
            			if (tt) {
            				wKing=test;
            				wRook1=test+1;
            				p[0]=0;
            				p[test]=1;
            				wKingNeverMoved=false;
            				wRook1NeverMoved=false;
            			}
            		}
            		else
            			check(h);
            	}
            	
            }
            test+=4;
            if (test==h && wKingNeverMoved && wRook2NeverMoved && wRook2==8) {
            	if (i==0) {
            		check(test-1);
            		if (retCheck()==0) {
            			valid=true;
            			testi=test;
            			check(h);
            			if (tt) {
            				wKing=test;
            				wRook2=test-1;
            				p[7]=0;
            				p[test-2]=1;
            				wKingNeverMoved=false;
            				wRook2NeverMoved=false;
            			}
            		}
            		else
            			check(h);
            	}
            }
            
            if (tt && valid)
            {
                p[testi-1]=1;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (bPawn1==h)
                {
                    bPawn1=0;
                }
                if (bPawn2==h)
                {
                    bPawn2=0;
                }
                if (bPawn3==h)
                {
                    bPawn3=0;
                }
                if (bPawn4==h)
                {
                    bPawn4=0;
                }
                if (bPawn5==h)
                {
                    bPawn5=0;
                }
                if (bPawn6==h)
                {
                    bPawn6=0;
                }
                if (bPawn7==h)
                {
                    bPawn7=0;
                }
                if (bPawn8==h)
                {
                    bPawn8=0;
                }
                if (bBishop1==h)
                {
                    bBishop1=0;
                }
                if (bBishop2==h)
                {
                    bBishop2=0;
                }
                if (bRook1==h)
                {
                    bRook1=0;
                }
                if (bRook2==h)
                {
                    bRook2=0;
                }
                if (bKnight1==h)
                {
                    bKnight1=0;
                }
                if (bKnight2==h)
                {
                    bKnight2=0;
                }
                if (bQueen==h)
                {
                    bQueen=0;
                }
                if (bKing==h)
                {
                    bKing=0;
                }
                if (bQueen1==h)
                {
                    bQueen1=0;
                }
                if (bQueen2==h)
                {
                    bQueen2=0;
                }
                if (bQueen3==h)
                {
                    bQueen3=0;
                }
                if (bQueen4==h)
                {
                    bQueen4=0;
                }
                if (bQueen5==h)
                {
                    bQueen5=0;
                }
                if (bQueen6==h)
                {
                    bQueen6=0;
                }
                if (bQueen7==h)
                {
                    bQueen7=0;
                }
                if (bQueen8==h)
                {
                    bQueen8=0;
                }
            }
        }
    }
    public static int retg()
    {
        return g;
    }
    public static void bValidCheck(int g,int h, int i, int lett, boolean tt)
    {
        int test=g;    //h=chosen space,i=availability, lett is for row #
        int testi=0;
        int ch=0;
        valid=false;
        
        
        //pawns
        if (bm<=8 && bm>=1)
        {
            int sww = 0;
            if ((test%8)==1)
            {
                sww=1;
            }
            if ((test%8)==0)
            {
                sww=2;
            }
            test-=8;
            if (test==h)
            {
                if (i==0)
                {
                    valid=true;
                    testi=test;
                    if (tt)
                    	bawn[bm-1]=1;
                    if (tt && bm==1)
                    {
                        bPawn1=test;
                    }
                    if (tt && bm==2)
                    {
                        bPawn2=test;
                    }
                    if (tt && bm==3)
                    {
                        bPawn3=test;
                    }
                    if (tt && bm==4)
                    {
                        bPawn4=test;
                    }
                    if (tt && bm==5)
                    {
                        bPawn5=test;
                    }
                    if (tt && bm==6)
                    {
                        bPawn6=test;
                    }
                    if (tt && bm==7)
                    {
                        bPawn7=test;
                    }
                    if (tt && bm==8)
                    {
                        bPawn8=test;
                    }
                }
            }
            test--;
            if (test==h && sww!=1)
            {
                if (i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt)
                    	bawn[bm-1]=1;
                    if (tt && bm==1)
                    {
                        bPawn1=test;
                    }
                    if (tt && bm==2)
                    {
                        bPawn2=test;
                    }
                    if (tt && bm==3)
                    {
                        bPawn3=test;
                    }
                    if (tt && bm==4)
                    {
                        bPawn4=test;
                    }
                    if (tt && bm==5)
                    {
                        bPawn5=test;
                    }
                    if (tt && bm==6)
                    {
                        bPawn6=test;
                    }
                    if (tt && bm==7)
                    {
                        bPawn7=test;
                    }
                    if (tt && bm==8)
                    {
                        bPawn8=test;
                    }
                }
            }
            test+=2;
            if (test==h && sww!=2)
            {
                if (i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt)
                    	bawn[bm-1]=1;
                    if (tt && bm==1)
                    {
                        bPawn1=test;
                    }
                    if (tt && bm==2)
                    {
                        bPawn2=test;
                    }
                    if (tt && bm==3)
                    {
                        bPawn3=test;
                    }
                    if (tt && bm==4)
                    {
                        bPawn4=test;
                    }
                    if (tt && bm==5)
                    {
                        bPawn5=test;
                    }
                    if (tt && bm==6)
                    {
                        bPawn6=test;
                    }
                    if (tt && bm==7)
                    {
                        bPawn7=test;
                    }
                    if (tt && bm==8)
                    {
                        bPawn8=test;
                    }
                }
            }
            test=g;
            test-=16;
            if (bawn[bm-1]==0)
            {
                if (test==h)
                {
                    if (i==0)
                    {
                    	check(test+8);
                    	if (retCheck()==0) {
	                        valid=true;
	                        testi=test;
	                        if (tt)
	                        	bawn[bm-1]=1;
	                        if (tt && bm==1)
	                        {
	                            bPawn1=test;
	                        }
	                        if (tt && bm==2)
	                        {
	                            bPawn2=test;
	                        }
	                        if (tt && bm==3)
	                        {
	                            bPawn3=test;
	                        }
	                        if (tt && bm==4)
	                        {
	                            bPawn4=test;
	                        }
	                        if (tt && bm==5)
	                        {
	                            bPawn5=test;
	                        }
	                        if (tt && bm==6)
	                        {
	                            bPawn6=test;
	                        }
	                        if (tt && bm==7)
	                        {
	                            bPawn7=test;
	                        }
	                        if (tt && bm==8)
	                        {
	                            bPawn8=test;
	                        }
                    	}
                    	else
                    		valid=false;
                    }
                }
            }
            
            if (tt && valid)
            {
                p[testi-1]=2;
                p[g-1]=0;
            }
            
            if (tt && valid)
            {
                if (bPawn1>=1&&bPawn1<=8)
                {
                    bQueen1=bPawn1;
                    bPawn1=0;
                }
                if (bPawn2>=1&&bPawn2<=8)
                {
                    bQueen2=bPawn2;
                    bPawn2=0;
                }
                if (bPawn3>=1&&bPawn3<=8)
                {
                    bQueen3=bPawn3;
                    bPawn3=0;
                }
                if (bPawn4>=1&&bPawn4<=8)
                {
                    bQueen4=bPawn4;
                    bPawn4=0;
                }
                if (bPawn5>=1&&bPawn5<=8)
                {
                    bQueen5=bPawn5;
                    bPawn5=0;
                }
                if (bPawn6>=1&&bPawn6<=8)
                {
                    bQueen6=bPawn6;
                    bPawn6=0;
                }
                if (bPawn7>=1&&bPawn7<=8)
                {
                    bQueen7=bPawn7;
                    bPawn7=0;
                }
                if (bPawn8>=1&&bPawn8<=8)
                {
                    bQueen8=bPawn8;
                    bPawn8=0;
                }
            }
            
            if (tt && valid)
            {
                if (wPawn1==h)
                {
                    wPawn1=0;
                }
                if (wPawn2==h)
                {
                    wPawn2=0;
                }
                if (wPawn3==h)
                {
                    wPawn3=0;
                }
                if (wPawn4==h)
                {
                    wPawn4=0;
                }
                if (wPawn5==h)
                {
                    wPawn5=0;
                }
                if (wPawn6==h)
                {
                    wPawn6=0;
                }
                if (wPawn7==h)
                {
                    wPawn7=0;
                }
                if (wPawn8==h)
                {
                    wPawn8=0;
                }
                if (wBishop1==h)
                {
                    wBishop1=0;
                }
                if (wBishop2==h)
                {
                    wBishop2=0;
                }
                if (wRook1==h)
                {
                    wRook1=0;
                }
                if (wRook2==h)
                {
                    wRook2=0;
                }
                if (wKnight1==h)
                {
                    wKnight1=0;
                }
                if (wKnight2==h)
                {
                    wKnight2=0;
                }
                if (wQueen==h)
                {
                    wQueen=0;
                }
                if (wKing==h)
                {
                    wKing=0;
                }
                if (wQueen1==h)
                {
                    wQueen1=0;
                }
                if (wQueen2==h)
                {
                    wQueen2=0;
                }
                if (wQueen3==h)
                {
                    wQueen3=0;
                }
                if (wQueen4==h)
                {
                    wQueen4=0;
                }
                if (wQueen5==h)
                {
                    wQueen5=0;
                }
                if (wQueen6==h)
                {
                    wQueen6=0;
                }
                if (wQueen7==h)
                {
                    wQueen7=0;
                }
                if (wQueen8==h)
                {
                    wQueen8=0;
                }
            }
        }
        
        //rooks
        if (bm==9 || bm==10)
        {
        	int tester=0;
            test+=8;
            while (test<=64 && ch==0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (tt && bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                    if (tt && i==0)
                    {
                        valid=true;
                        testi=test;
                        if (bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                }
                if (test>0) {
	                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
	                {
	                    ch=1;
	                }
                }
	            else
	            	ch=1;
                test+=8;
            }
            ch=0;
            test=g;
            test++;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (ch==0 && tester<8)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (tt && bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (tt && bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                }
                if (test>0) {
	                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
	                {
	                    ch=1;
	                }
                }
	            else
	            	ch=1;
                test++;
                tester++;
            }
            ch=0;
            test=g;
            test--;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (ch==0 && tester>1)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (tt && bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (tt && bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                }
                if (test>0) {
	                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
	                {
	                    ch=1;
	                }
                }
	            else
	            	ch=1;
                test--;
                tester--;
            }
            ch=0;
            test=g;
            test-=8;
            while (test>=1 && ch==0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (tt && bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==9)
                        {
                            bRook1=test;
                            bRook1NeverMoved=false;
                        }
                        if (tt && bm==10)
                        {
                            bRook2=test;
                            bRook2NeverMoved=false;
                        }
                    }
                }
                if (test>0) {
	                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
	                {
	                    ch=1;
	                }
                }
	            else
	            	ch=1;
                test-=8;
            }
            
            if (tt && valid)
            {
                p[testi-1]=2;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (wPawn1==h)
                {
                    wPawn1=0;
                }
                if (wPawn2==h)
                {
                    wPawn2=0;
                }
                if (wPawn3==h)
                {
                    wPawn3=0;
                }
                if (wPawn4==h)
                {
                    wPawn4=0;
                }
                if (wPawn5==h)
                {
                    wPawn5=0;
                }
                if (wPawn6==h)
                {
                    wPawn6=0;
                }
                if (wPawn7==h)
                {
                    wPawn7=0;
                }
                if (wPawn8==h)
                {
                    wPawn8=0;
                }
                if (wBishop1==h)
                {
                    wBishop1=0;
                }
                if (wBishop2==h)
                {
                    wBishop2=0;
                }
                if (wRook1==h)
                {
                    wRook1=0;
                }
                if (wRook2==h)
                {
                    wRook2=0;
                }
                if (wKnight1==h)
                {
                    wKnight1=0;
                }
                if (wKnight2==h)
                {
                    wKnight2=0;
                }
                if (wQueen==h)
                {
                    wQueen=0;
                }
                if (wKing==h)
                {
                    wKing=0;
                }
                if (wQueen1==h)
                {
                    wQueen1=0;
                }
                if (wQueen2==h)
                {
                    wQueen2=0;
                }
                if (wQueen3==h)
                {
                    wQueen3=0;
                }
                if (wQueen4==h)
                {
                    wQueen4=0;
                }
                if (wQueen5==h)
                {
                    wQueen5=0;
                }
                if (wQueen6==h)
                {
                    wQueen6=0;
                }
                if (wQueen7==h)
                {
                    wQueen7=0;
                }
                if (wQueen8==h)
                {
                    wQueen8=0;
                }
            }
        }
        
        //knight
        if (bm==11 || bm==12)
        {
            int k=0;
            if ((test%8)==1)
            {
                k=1;
            }
            if ((test%8)==2)
            {
                k=2;
            }
            if ((test%8)>2&&(test%8)<7)
            {
                k=0;
            }
            if ((test%8)==7)
            {
                k=3;
            }
            if ((test%8)==0)
            {
                k=4;
            }
            test+=6;
            if (test==h && k!=1 && k!=2)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            test+=4;
            if (test==h && k!=3 && k!=4)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            test+=5;
            if (test==h && k!=1)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            test+=2;
            if (test==h && k!=4)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            test=g;
            test-=6;
            if (test==h && k!=3 && k!=4)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            test-=4;
            if (test==h && k!=1 && k!=2)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            test-=5;
            if (test==h && k!=4)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            test-=2;
            if (test==h && k!=1)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt && bm==11)
                    {
                        bKnight1=test;
                    }
                    if (tt && bm==12)
                    {
                        bKnight2=test;
                    }
                }
            }
            
            
            if (tt && valid)
            {
                p[testi-1]=2;
                p[g-1]=0;
            }
            
            if (tt && valid)
            {
                if (wPawn1==h)
                {
                    wPawn1=0;
                }
                if (wPawn2==h)
                {
                    wPawn2=0;
                }
                if (wPawn3==h)
                {
                    wPawn3=0;
                }
                if (wPawn4==h)
                {
                    wPawn4=0;
                }
                if (wPawn5==h)
                {
                    wPawn5=0;
                }
                if (wPawn6==h)
                {
                    wPawn6=0;
                }
                if (wPawn7==h)
                {
                    wPawn7=0;
                }
                if (wPawn8==h)
                {
                    wPawn8=0;
                }
                if (wBishop1==h)
                {
                    wBishop1=0;
                }
                if (wBishop2==h)
                {
                    wBishop2=0;
                }
                if (wRook1==h)
                {
                    wRook1=0;
                }
                if (wRook2==h)
                {
                    wRook2=0;
                }
                if (wKnight1==h)
                {
                    wKnight1=0;
                }
                if (wKnight2==h)
                {
                    wKnight2=0;
                }
                if (wQueen==h)
                {
                    wQueen=0;
                }
                if (wKing==h)
                {
                    wKing=0;
                }
                if (wQueen1==h)
                {
                    wQueen1=0;
                }
                if (wQueen2==h)
                {
                    wQueen2=0;
                }
                if (wQueen3==h)
                {
                    wQueen3=0;
                }
                if (wQueen4==h)
                {
                    wQueen4=0;
                }
                if (wQueen5==h)
                {
                    wQueen5=0;
                }
                if (wQueen6==h)
                {
                    wQueen6=0;
                }
                if (wQueen7==h)
                {
                    wQueen7=0;
                }
                if (wQueen8==h)
                {
                    wQueen8=0;
                }
            }
        }
        
        //Bishop
        if (bm==13 || bm==14)
        {
            int tes=test%8;
            int count=0;
            
            
            test+=7;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=7;
                count--;
            }
            ch=0;
            test=g;
            test+=9;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=9;
                count--;
            }
            ch=0;
            test=g;
            test-=9;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=9;
                count--;
            }
            ch=0;
            test=g;
            test-=7;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt && bm==13)
                        {
                            bBishop1=test;
                        }
                        if (tt && bm==14)
                        {
                            bBishop2=test;
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=7;
                count--;
            }
            
            
            
            if (tt && valid)
            {
                p[testi-1]=2;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (wPawn1==h)
                {
                    wPawn1=0;
                }
                if (wPawn2==h)
                {
                    wPawn2=0;
                }
                if (wPawn3==h)
                {
                    wPawn3=0;
                }
                if (wPawn4==h)
                {
                    wPawn4=0;
                }
                if (wPawn5==h)
                {
                    wPawn5=0;
                }
                if (wPawn6==h)
                {
                    wPawn6=0;
                }
                if (wPawn7==h)
                {
                    wPawn7=0;
                }
                if (wPawn8==h)
                {
                    wPawn8=0;
                }
                if (wBishop1==h)
                {
                    wBishop1=0;
                }
                if (wBishop2==h)
                {
                    wBishop2=0;
                }
                if (wRook1==h)
                {
                    wRook1=0;
                }
                if (wRook2==h)
                {
                    wRook2=0;
                }
                if (wKnight1==h)
                {
                    wKnight1=0;
                }
                if (wKnight2==h)
                {
                    wKnight2=0;
                }
                if (wQueen==h)
                {
                    wQueen=0;
                }
                if (wKing==h)
                {
                    wKing=0;
                }
                if (wQueen1==h)
                {
                    wQueen1=0;
                }
                if (wQueen2==h)
                {
                    wQueen2=0;
                }
                if (wQueen3==h)
                {
                    wQueen3=0;
                }
                if (wQueen4==h)
                {
                    wQueen4=0;
                }
                if (wQueen5==h)
                {
                    wQueen5=0;
                }
                if (wQueen6==h)
                {
                    wQueen6=0;
                }
                if (wQueen7==h)
                {
                    wQueen7=0;
                }
                if (wQueen8==h)
                {
                    wQueen8=0;
                }
            }
        }
        
        //queen
        if (bm==15||(bm>=17&&bm<=24))
        {
        	int tester=0;
            test+=8;
            while (test<=64 && ch==0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=8;
            }
            ch=0;
            test=g;
            test++;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (ch==0 && tester<8)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test++;
                tester++;
            }
            ch=0;
            test=g;
            test--;
            if ((g-1)%8+1==1)
            	tester=1;
            if ((g-1)%8+1==2)
            	tester=2;
            if ((g-1)%8+1==3)
            	tester=3;
            if ((g-1)%8+1==4)
            	tester=4;
            if ((g-1)%8+1==5)
            	tester=5;
            if ((g-1)%8+1==6)
            	tester=6;
            if ((g-1)%8+1==7)
            	tester=7;
            if ((g-1)%8+1==8)
            	tester=8;
            while (ch==0 && test>0 && tester>1)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test--;
                tester--;
            }
            ch=0;
            test=g;
            test-=8;
            while (test>=1 && ch==0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=8;
            }
            ch=0;
            test=g;
            
            
            int tes=test%8;
            int count=0;
            
            
            test+=7;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=7;
                count--;
            }
            ch=0;
            test=g;
            test+=9;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test+=9;
                count--;
            }
            ch=0;
            test=g;
            test-=9;
            switch (tes)
            {
                case 1: count=0;
                break;
                case 2: count=1;
                break;
                case 3: count=2;
                break;
                case 4: count=3;
                break;
                case 5: count=4;
                break;
                case 6: count=5;
                break;
                case 7: count=6;
                break;
                case 0: count=7;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=9;
                count--;
            }
            ch=0;
            test=g;
            test-=7;
            switch (tes)
            {
                case 1: count=7;
                break;
                case 2: count=6;
                break;
                case 3: count=5;
                break;
                case 4: count=4;
                break;
                case 5: count=3;
                break;
                case 6: count=2;
                break;
                case 7: count=1;
                break;
                case 0: count=0;
                break;
            }
            while (test<=64 && test>0 && ch==0 && count>0)
            {
                if (test==h)
                {
                    if (i==1)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                    if (i==0)
                    {
                        valid=true;
                        testi=test;
                        if (tt) {
	                        switch (bm)
	                        {
	                            case 15:
	                            bQueen=test;
	                            break;
	                            case 17:
	                            bQueen1=test;
	                            break;
	                            case 18:
	                            bQueen2=test;
	                            break;
	                            case 19:
	                            bQueen3=test;
	                            break;
	                            case 20:
	                            bQueen4=test;
	                            break;
	                            case 21:
	                            bQueen5=test;
	                            break;
	                            case 22:
	                            bQueen6=test;
	                            break;
	                            case 23:
	                            bQueen7=test;
	                            break;
	                            case 24:
	                            bQueen8=test;
	                            break;
	                        }
                        }
                    }
                }
                if ((p[test-1]==1||p[test-1]==2)&&valid==false)
                {
                    ch=1;
                }
                test-=7;
                count--;
            }
            
            
            
            if (tt && valid)
            {
                p[testi-1]=2;
                p[g-1]=0;
            }
            
            
            if (tt && valid)
            {
                if (wPawn1==h)
                {
                    wPawn1=0;
                }
                if (wPawn2==h)
                {
                    wPawn2=0;
                }
                if (wPawn3==h)
                {
                    wPawn3=0;
                }
                if (wPawn4==h)
                {
                    wPawn4=0;
                }
                if (wPawn5==h)
                {
                    wPawn5=0;
                }
                if (wPawn6==h)
                {
                    wPawn6=0;
                }
                if (wPawn7==h)
                {
                    wPawn7=0;
                }
                if (wPawn8==h)
                {
                    wPawn8=0;
                }
                if (wBishop1==h)
                {
                    wBishop1=0;
                }
                if (wBishop2==h)
                {
                    wBishop2=0;
                }
                if (wRook1==h)
                {
                    wRook1=0;
                }
                if (wRook2==h)
                {
                    wRook2=0;
                }
                if (wKnight1==h)
                {
                    wKnight1=0;
                }
                if (wKnight2==h)
                {
                    wKnight2=0;
                }
                if (wQueen==h)
                {
                    wQueen=0;
                }
                if (wKing==h)
                {
                    wKing=0;
                }
                if (wQueen1==h)
                {
                    wQueen1=0;
                }
                if (wQueen2==h)
                {
                    wQueen2=0;
                }
                if (wQueen3==h)
                {
                    wQueen3=0;
                }
                if (wQueen4==h)
                {
                    wQueen4=0;
                }
                if (wQueen5==h)
                {
                    wQueen5=0;
                }
                if (wQueen6==h)
                {
                    wQueen6=0;
                }
                if (wQueen7==h)
                {
                    wQueen7=0;
                }
                if (wQueen8==h)
                {
                    wQueen8=0;
                }
            }
        }
        
        //king
        if (bm==16)
        {
            int sw=0;
            if ((test%8)==1)
            {
                sw=1;
            }
            if ((test%8)==0)
            {
                sw=2;
            }
            test++;
            if (test==h && sw!=2)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test+=6;
            if (test==h && sw!=1)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test++;
            if (test==h)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test++;
            if (test==h && sw!=2)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test=g;
            test--;
            if (test==h && sw!=1)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test-=6;
            if (test==h && sw!=2)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test--;
            if (test==h)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test--;
            if (test==h && sw!=1)
            {
                if (i==0 || i==1)
                {
                    valid=true;
                    testi=test;
                    if (tt) {
                    	bKing=test;
                        bKingNeverMoved=false;
                    }
                }
            }
            test=g;
            test-=2;
            if (test==h && bKingNeverMoved && bRook1NeverMoved && bRook1==57) {
            	if (i==0) {
            		check(test+1);
            		if (retCheck()==0) {
            			valid=true;
            			testi=test;
            			check(h);
            			if (tt) {
            				bKing=test;
            				bRook1=test+1;
            				p[56]=0;
            				p[test]=2;
            				bKingNeverMoved=false;
            				bRook1NeverMoved=false;
            			}
            		}
            		else
            			check(h);
            	}
            	
            }
            test+=4;
            if (test==h && bKingNeverMoved && bRook2NeverMoved && bRook2==64) {
            	if (i==0) {
            		check(test-1);
            		if (retCheck()==0) {
            			valid=true;
            			testi=test;
            			check(h);
            			if (tt) {
            				bKing=test;
            				bRook2=test-1;
            				p[63]=0;
            				p[test-2]=2;
            				bKingNeverMoved=false;
            				bRook2NeverMoved=false;
            			}
            		}
            		else
            			check(h);
            	}
            }
            
            
            if (tt && valid)
            {
                p[testi-1]=2;
                p[g-1]=0;
            }
            
            if (tt && valid)
            {
                if (wPawn1==h)
                {
                    wPawn1=0;
                }
                if (wPawn2==h)
                {
                    wPawn2=0;
                }
                if (wPawn3==h)
                {
                    wPawn3=0;
                }
                if (wPawn4==h)
                {
                    wPawn4=0;
                }
                if (wPawn5==h)
                {
                    wPawn5=0;
                }
                if (wPawn6==h)
                {
                    wPawn6=0;
                }
                if (wPawn7==h)
                {
                    wPawn7=0;
                }
                if (wPawn8==h)
                {
                    wPawn8=0;
                }
                if (wBishop1==h)
                {
                    wBishop1=0;
                }
                if (wBishop2==h)
                {
                    wBishop2=0;
                }
                if (wRook1==h)
                {
                    wRook1=0;
                }
                if (wRook2==h)
                {
                    wRook2=0;
                }
                if (wKnight1==h)
                {
                    wKnight1=0;
                }
                if (wKnight2==h)
                {
                    wKnight2=0;
                }
                if (wQueen==h)
                {
                    wQueen=0;
                }
                if (wKing==h)
                {
                    wKing=0;
                }
                if (wQueen1==h)
                {
                    wQueen1=0;
                }
                if (wQueen2==h)
                {
                    wQueen2=0;
                }
                if (wQueen3==h)
                {
                    wQueen3=0;
                }
                if (wQueen4==h)
                {
                    wQueen4=0;
                }
                if (wQueen5==h)
                {
                    wQueen5=0;
                }
                if (wQueen6==h)
                {
                    wQueen6=0;
                }
                if (wQueen7==h)
                {
                    wQueen7=0;
                }
                if (wQueen8==h)
                {
                    wQueen8=0;
                }
            }
        }
    }
    public static boolean valid()
    {
        return valid;
    }
    public static void clear()
    {
    	int t=63;
	    while (t>=0)
	    {
	        p[t]=0;
	        t--;
	    }
        if (wPawn1>0)
            p[wPawn1-1]=1;
        if (wPawn2>0)
            p[wPawn2-1]=1;
        if (wPawn3>0)
            p[wPawn3-1]=1;
        if (wPawn4>0)
            p[wPawn4-1]=1;
        if (wPawn5>0)
            p[wPawn5-1]=1;
        if (wPawn6>0)
            p[wPawn6-1]=1;
        if (wPawn7>0)
            p[wPawn7-1]=1;
        if (wPawn8>0)
            p[wPawn8-1]=1;
        if (wBishop1>0)
            p[wBishop1-1]=1;
        if (wBishop2>0)
            p[wBishop2-1]=1;
        if (wKnight1>0)
            p[wKnight1-1]=1;
        if (wKnight2>0)
            p[wKnight2-1]=1;
        if (wRook1>0)
            p[wRook1-1]=1;
        if (wRook2>0)
            p[wRook2-1]=1;
        if (wQueen>0)
            p[wQueen-1]=1;
        if (wKing>0)
            p[wKing-1]=1;
        if (wQueen1>0)
            p[wQueen1-1]=1;
        if (wQueen2>0)
            p[wQueen2-1]=1;
        if (wQueen3>0)
            p[wQueen3-1]=1;
        if (wQueen4>0)
            p[wQueen4-1]=1;
        if (wQueen5>0)
            p[wQueen5-1]=1;
        if (wQueen6>0)
            p[wQueen6-1]=1;
        if (wQueen7>0)
            p[wQueen7-1]=1;
        if (wQueen8>0)
            p[wQueen8-1]=1;
        
        
        
        if (bPawn1>0)
            p[bPawn1-1]=2;
        if (bPawn2>0)
            p[bPawn2-1]=2;
        if (bPawn3>0)
            p[bPawn3-1]=2;
        if (bPawn4>0)
            p[bPawn4-1]=2;
        if (bPawn5>0)
            p[bPawn5-1]=2;
        if (bPawn6>0)
            p[bPawn6-1]=2;
        if (bPawn7>0)
            p[bPawn7-1]=2;
        if (bPawn8>0)
            p[bPawn8-1]=2;
        if (bBishop1>0)
            p[bBishop1-1]=2;
        if (bBishop2>0)
            p[bBishop2-1]=2;
        if (bKnight1>0)
            p[bKnight1-1]=2;
        if (bKnight2>0)
            p[bKnight2-1]=2;
        if (bRook1>0)
            p[bRook1-1]=2;
        if (bRook2>0)
            p[bRook2-1]=2;
        if (bQueen>0)
            p[bQueen-1]=2;
        if (bKing>0)
            p[bKing-1]=2;
        if (bQueen1>0)
            p[bQueen1-1]=2;
        if (bQueen2>0)
            p[bQueen2-1]=2;
        if (bQueen3>0)
            p[bQueen3-1]=2;
        if (bQueen4>0)
            p[bQueen4-1]=2;
        if (bQueen5>0)
            p[bQueen5-1]=2;
        if (bQueen6>0)
            p[bQueen6-1]=2;
        if (bQueen7>0)
            p[bQueen7-1]=2;
        if (bQueen8>0)
            p[bQueen8-1]=2;
    }
    public static void check(int h)
    {
        i=0;
        try {
        if (p[h-1]==1)
            i=1;
        if (p[h-1]==2)
            i=2;
        if (p[h-1]==0)
            i=0;
        }
        catch(Exception e) {
        	i=0;
        }
        
    }
    public static int retCheck()
    {
        return i;
    }
    public static int retH()
    {
        return h;
    }
    public int retlette()
    {
        return lette;
    }
    public boolean checkWCheck(int wKing) {
    	int saver=bm;
    	bm=1;
    	check(wKing);
		bValidCheck(bPawn1,wKing,1,lett,false);
		if (valid()&&bPawn1!=0) {
			bm=saver;
			return true;
		}
		bm=2;
		bValidCheck(bPawn2,wKing,1,lett,false);
		if (valid()&&bPawn2!=0) {
			bm=saver;
			return true;
		}
		bm=3;
		bValidCheck(bPawn3,wKing,1,lett,false);
		if (valid()&&bPawn3!=0) {
			bm=saver;
			return true;
		}
		bm=4;
		bValidCheck(bPawn4,wKing,1,lett,false);
		if (valid()&&bPawn4!=0) {
			bm=saver;
			return true;
		}
		bm=5;
		bValidCheck(bPawn5,wKing,1,lett,false);
		if (valid()&&bPawn5!=0) {
			bm=saver;
			return true;
		}
		bm=6;
		bValidCheck(bPawn6,wKing,1,lett,false);
		if (valid()&&bPawn6!=0) {
			bm=saver;
			return true;
		}
		bm=7;
		bValidCheck(bPawn7,wKing,1,lett,false);
		if (valid()&&bPawn7!=0) {
			bm=saver;
			return true;
		}
		bm=8;
		bValidCheck(bPawn8,wKing,1,lett,false);
		if (valid()&&bPawn8!=0) {
			bm=saver;
			return true;
		}
		bm=13;
		bValidCheck(bBishop1,wKing,1,lett,false);
		if (valid()&&bBishop1!=0) {
			bm=saver;
			return true;
		}
		bm=14;
		bValidCheck(bBishop2,wKing,1,lett,false);
		if (valid()&&bBishop2!=0) {
			bm=saver;
			return true;
		}
		bm=11;
		bValidCheck(bKnight1,wKing,1,lett,false);
		if (valid()&&bKnight1!=0) {
			bm=saver;
			return true;
		}
		bm=12;
		bValidCheck(bKnight2,wKing,1,lett,false);
		if (valid()&&bKnight2!=0) {
			bm=saver;
			return true;
		}
		bm=9;
		bValidCheck(bRook1,wKing,1,lett,false);
		if (valid()&&bRook1!=0) {
			bm=saver;
			return true;
		}
		bm=10;
		bValidCheck(bRook2,wKing,1,lett,false);
		if (valid()&&bRook2!=0) {
			bm=saver;
			return true;
		}
		bm=15;
		bValidCheck(bQueen,wKing,1,lett,false);
		if (valid()&&bQueen!=0) {
			bm=saver;
			return true;
		}
		bm=16;
		bValidCheck(bKing,wKing,1,lett,false);
		if (valid()) {
			bm=saver;
			return true;
		}
		bm=17;
		bValidCheck(bQueen1,wKing,1,lett,false);
		if (valid()&&bQueen1!=0) {
			bm=saver;
			return true;
		}
		bm=18;
		bValidCheck(bQueen2,wKing,1,lett,false);
		if (valid()&&bQueen2!=0) {
			bm=saver;
			return true;
		}
		bm=19;
		bValidCheck(bQueen3,wKing,1,lett,false);
		if (valid()&&bQueen3!=0) {
			bm=saver;
			return true;
		}
		bm=20;
		bValidCheck(bQueen4,wKing,1,lett,false);
		if (valid()&&bQueen4!=0) {
			bm=saver;
			return true;
		}
		bm=21;
		bValidCheck(bQueen5,wKing,1,lett,false);
		if (valid()&&bQueen5!=0) {
			bm=saver;
			return true;
		}
		bm=22;
		bValidCheck(bQueen6,wKing,1,lett,false);
		if (valid()&&bQueen6!=0) {
			bm=saver;
			return true;
		}
		bm=23;
		bValidCheck(bQueen7,wKing,1,lett,false);
		if (valid()&&bQueen7!=0) {
			bm=saver;
			return true;
		}
		bm=24;
		bValidCheck(bQueen8,wKing,1,lett,false);
		if (valid()&&bQueen8!=0) {
			bm=saver;
			return true;
		}
		bm=saver;
	return false;
    }
    public boolean checkBCheck(int bKing) {
    	int saver=wm;
    	wm=1;
    	check(bKing);
		wValidCheck(wPawn1,bKing,2,lett,false);
		if (valid()&&wPawn1!=0) {
			wm=saver;
			return true;
		}
		wm=2;
		wValidCheck(wPawn2,bKing,2,lett,false);
		if (valid()&&wPawn2!=0) {
			wm=saver;
			return true;
		}
		wm=3;
		wValidCheck(wPawn3,bKing,2,lett,false);
		if (valid()&&wPawn3!=0) {
			wm=saver;
			return true;
		}
		wm=4;
		wValidCheck(wPawn4,bKing,2,lett,false);
		if (valid()&&wPawn4!=0) {
			wm=saver;
			return true;
		}
		wm=5;
		wValidCheck(wPawn5,bKing,2,lett,false);
		if (valid()&&wPawn5!=0) {
			wm=saver;
			return true;
		}
		wm=6;
		wValidCheck(wPawn6,bKing,2,lett,false);
		if (valid()&&wPawn6!=0) {
			wm=saver;
			return true;
		}
		wm=7;
		wValidCheck(wPawn7,bKing,2,lett,false);
		if (valid()&&wPawn7!=0) {
			wm=saver;
			return true;
		}
		wm=8;
		wValidCheck(wPawn8,bKing,2,lett,false);
		if (valid()&&wPawn8!=0) {
			wm=saver;
			return true;
		}
		wm=13;
		wValidCheck(wBishop1,bKing,2,lett,false);
		if (valid()&&wBishop1!=0) {
			wm=saver;
			return true;
		}
		wm=14;
		wValidCheck(wBishop2,bKing,2,lett,false);
		if (valid()&&wBishop2!=0) {
			wm=saver;
			return true;
		}
		wm=11;
		wValidCheck(wKnight1,bKing,2,lett,false);
		if (valid()&&wKnight1!=0) {
			wm=saver;
			return true;
		}
		wm=12;
		wValidCheck(wKnight2,bKing,2,lett,false);
		if (valid()&&wKnight2!=0) {
			wm=saver;
			return true;
		}
		wm=9;
		wValidCheck(wRook1,bKing,2,lett,false);
		if (valid()&&wRook1!=0) {
			wm=saver;
			return true;
		}
		wm=10;
		wValidCheck(wRook2,bKing,2,lett,false);
		if (valid()&&wRook2!=0) {
			wm=saver;
			return true;
		}
		wm=15;
		wValidCheck(wQueen,bKing,2,lett,false);
		if (valid()&&wQueen!=0) {
			wm=saver;
			return true;
		}
		wm=16;
		wValidCheck(wKing,bKing,2,lett,false);
		if (valid()&&wKing!=0) {
			wm=saver;
			return true;
		}
		wm=17;
		wValidCheck(wQueen1,bKing,2,lett,false);
		if (valid()&&wQueen1!=0) {
			wm=saver;
			return true;
		}
		wm=18;
		wValidCheck(wQueen2,bKing,2,lett,false);
		if (valid()&&wQueen2!=0) {
			wm=saver;
			return true;
		}
		wm=19;
		wValidCheck(wQueen3,bKing,2,lett,false);
		if (valid()&&wQueen3!=0) {
			wm=saver;
			return true;
		}
		wm=20;
		wValidCheck(wQueen4,bKing,2,lett,false);
		if (valid()&&wQueen4!=0) {
			wm=saver;
			return true;
		}
		wm=21;
		wValidCheck(wQueen5,bKing,2,lett,false);
		if (valid()&&wQueen5!=0) {
			wm=saver;
			return true;
		}
		wm=22;
		wValidCheck(wQueen6,bKing,2,lett,false);
		if (valid()&&wQueen6!=0) {
			wm=saver;
			return true;
		}
		wm=23;
		wValidCheck(wQueen7,bKing,2,lett,false);
		if (valid()&&wQueen7!=0) {
			wm=saver;
			return true;
		}
		wm=24;
		wValidCheck(wQueen8,bKing,2,lett,false);
		if (valid()&&wQueen8!=0) {
			wm=saver;
			return true;
		}
		wm=saver;
	return false;
    }
    public boolean checkWCheckmate() {
    	int saver=wm;
    	int saverg=g;
    	int returner = 0;
    	for (wm=1;wm<=24;wm++) {
			if (wm==1)
				g=wPawn1;
			if (wm==2)
				g=wPawn2;
			if (wm==3)
				g=wPawn3;
			if (wm==4)
				g=wPawn4;
			if (wm==5)
				g=wPawn5;
			if (wm==6)
				g=wPawn6;
			if (wm==7)
				g=wPawn7;
			if (wm==8)
				g=wPawn8;
			if (wm==9)
				g=wRook1;
			if (wm==10)
				g=wRook2;
			if (wm==11)
				g=wKnight1;
			if (wm==12)
				g=wKnight2;
			if (wm==13)
				g=wBishop1;
			if (wm==14)
				g=wBishop2;
			if (wm==15)
				g=wQueen;
			if (wm==16)
				g=wKing;
			if (wm==17)
				g=wQueen1;
			if (wm==18)
				g=wQueen2;
			if (wm==19)
				g=wQueen3;
			if (wm==20)
				g=wQueen4;
			if (wm==21)
				g=wQueen5;
			if (wm==22)
				g=wQueen6;
			if (wm==23)
				g=wQueen7;
			if (wm==24)
				g=wQueen8;
    		for (int te=1;te<=64;te++) {
				check(te);
				wValidCheck(g,te,retCheck(),((te-1)%8+1),false);
				if (valid()) {
					int it=0;
					if (wm==1)
						wPawn1=te;
					if (wm==2)
						wPawn2=te;
					if (wm==3)
						wPawn3=te;
					if (wm==4)
						wPawn4=te;
					if (wm==5)
						wPawn5=te;
					if (wm==6)
						wPawn6=te;
					if (wm==7)
						wPawn7=te;
					if (wm==8)
						wPawn8=te;
					if (wm==9)
						wRook1=te;
					if (wm==10)
						wRook2=te;
					if (wm==11)
						wKnight1=te;
					if (wm==12)
						wKnight2=te;
					if (wm==13)
						wBishop1=te;
					if (wm==14)
						wBishop2=te;
					if (wm==15)
						wQueen=te;
					if (wm==16)
						wKing=te;
					if (wm==17)
						wQueen1=te;
					if (wm==18)
						wQueen2=te;
					if (wm==19)
						wQueen3=te;
					if (wm==20)
						wQueen4=te;
					if (wm==21)
						wQueen5=te;
					if (wm==22)
						wQueen6=te;
					if (wm==23)
						wQueen7=te;
					if (wm==24)
						wQueen8=te;
					
					if (bPawn1==te) {
						bPawn1=0;
						it=1;
					}
					if (bPawn2==te) {
						bPawn2=0;
						it=2;
					}
					if (bPawn3==te) {
						bPawn3=0;
						it=3;
					}
					if (bPawn4==te) {
						bPawn4=0;
						it=4;
					}
					if (bPawn5==te) {
						bPawn5=0;
						it=5;
					}
					if (bPawn6==te) {
						bPawn6=0;
						it=6;
					}
					if (bPawn7==te) {
						bPawn7=0;
						it=7;
					}
					if (bPawn8==te) {
						bPawn8=0;
						it=8;
					}
					if (bRook1==te) {
						bRook1=0;
						it=9;
					}
					if (bRook2==te) {
						bRook2=0;
						it=10;
					}
					if (bKnight1==te) {
						bKnight1=0;
						it=11;
					}
					if (bKnight2==te) {
						bKnight2=0;
						it=12;
					}
					if (bBishop1==te) {
						bBishop1=0;
						it=13;
					}
					if (bBishop2==te) {
						bBishop2=0;
						it=14;
					}
					if (bQueen==te) {
						bQueen=0;
						it=15;
					}
					if (bKing==te) {
						bKing=0;
						it=16;
					}
					if (bQueen1==te) {
						bQueen1=0;
						it=17;
					}
					if (bQueen2==te) {
						bQueen2=0;
						it=18;
					}
					if (bQueen3==te) {
						bQueen3=0;
						it=19;
					}
					if (bQueen4==te) {
						bQueen4=0;
						it=20;
					}
					if (bQueen5==te) {
						bQueen5=0;
						it=21;
					}
					if (bQueen6==te) {
						bQueen6=0;
						it=22;
					}
					if (bQueen7==te) {
						bQueen7=0;
						it=23;
					}
					if (bQueen8==te) {
						bQueen8=0;
						it=24;
					}
					clear();
					if (!checkWCheck(wKing)) {
    		    		returner=1;
					}
					if (wm==1)
						wPawn1=g;
					if (wm==2)
						wPawn2=g;
					if (wm==3)
						wPawn3=g;
					if (wm==4)
						wPawn4=g;
					if (wm==5)
						wPawn5=g;
					if (wm==6)
						wPawn6=g;
					if (wm==7)
						wPawn7=g;
					if (wm==8)
						wPawn8=g;
					if (wm==9)
						wRook1=g;
					if (wm==10)
						wRook2=g;
					if (wm==11)
						wKnight1=g;
					if (wm==12)
						wKnight2=g;
					if (wm==13)
						wBishop1=g;
					if (wm==14)
						wBishop2=g;
					if (wm==15)
						wQueen=g;
					if (wm==16)
						wKing=g;
					if (wm==17)
						wQueen1=g;
					if (wm==18)
						wQueen2=g;
					if (wm==19)
						wQueen3=g;
					if (wm==20)
						wQueen4=g;
					if (wm==21)
						wQueen5=g;
					if (wm==22)
						wQueen6=g;
					if (wm==23)
						wQueen7=g;
					if (wm==24)
						wQueen8=g;
					
					switch (it) {
					case 1: bPawn1=te;
						break;
					case 2: bPawn2=te;
    					break;
					case 3: bPawn3=te;
    					break;
					case 4: bPawn4=te;
    					break;
					case 5: bPawn5=te;
    					break;
					case 6: bPawn6=te;
    					break;
					case 7: bPawn7=te;
    					break;
					case 8: bPawn8=te;
    					break;
					case 9: bRook1=te;
    					break;
					case 10: bRook2=te;
    					break;
					case 11: bKnight1=te;
						break;
					case 12: bKnight2=te;
						break;
					case 13: bBishop1=te;
						break;
					case 14: bBishop2=te;
						break;
					case 15: bQueen=te;
						break;
					case 16: bKing=te;
						break;
					case 17: bQueen1=te;
						break;
					case 18: bQueen2=te;
						break;
					case 19: bQueen3=te;
						break;
					case 20: bQueen4=te;
						break;
					case 21: bQueen5=te;
						break;
					case 22: bQueen6=te;
						break;
					case 23: bQueen7=te;
						break;
					case 24: bQueen8=te;
						break;
					default:
						break;
					}
					clear();
				}
			}
		}
    	g=saverg;
    	wm=saver;
    	if (returner==0)
    		return true;
    	else
    		return false;
    }
    public boolean checkBCheckmate() {
    	int saver=bm;
    	int saverg=g;
    	int returner = 0;
    	for (bm=1;bm<=24;bm++) {
			if (bm==1)
				g=bPawn1;
			if (bm==2)
				g=bPawn2;
			if (bm==3)
				g=bPawn3;
			if (bm==4)
				g=bPawn4;
			if (bm==5)
				g=bPawn5;
			if (bm==6)
				g=bPawn6;
			if (bm==7)
				g=bPawn7;
			if (bm==8)
				g=bPawn8;
			if (bm==9)
				g=bRook1;
			if (bm==10)
				g=bRook2;
			if (bm==11)
				g=bKnight1;
			if (bm==12)
				g=bKnight2;
			if (bm==13)
				g=bBishop1;
			if (bm==14)
				g=bBishop2;
			if (bm==15)
				g=bQueen;
			if (bm==16)
				g=bKing;
			if (bm==17)
				g=bQueen1;
			if (bm==18)
				g=bQueen2;
			if (bm==19)
				g=bQueen3;
			if (bm==20)
				g=bQueen4;
			if (bm==21)
				g=bQueen5;
			if (bm==22)
				g=bQueen6;
			if (bm==23)
				g=bQueen7;
			if (bm==24)
				g=bQueen8;
    		for (int te=1;te<=64;te++) {
				check(te);
				bValidCheck(g,te,retCheck(),((te-1)%8+1),false);
				if (valid()) {
					int it=0;
					if (bm==1)
						bPawn1=te;
					if (bm==2)
						bPawn2=te;
					if (bm==3)
						bPawn3=te;
					if (bm==4)
						bPawn4=te;
					if (bm==5)
						bPawn5=te;
					if (bm==6)
						bPawn6=te;
					if (bm==7)
						bPawn7=te;
					if (bm==8)
						bPawn8=te;
					if (bm==9)
						bRook1=te;
					if (bm==10)
						bRook2=te;
					if (bm==11)
						bKnight1=te;
					if (bm==12)
						bKnight2=te;
					if (bm==13)
						bBishop1=te;
					if (bm==14)
						bBishop2=te;
					if (bm==15)
						bQueen=te;
					if (bm==16)
						bKing=te;
					if (bm==17)
						bQueen1=te;
					if (bm==18)
						bQueen2=te;
					if (bm==19)
						bQueen3=te;
					if (bm==20)
						bQueen4=te;
					if (bm==21)
						bQueen5=te;
					if (bm==22)
						bQueen6=te;
					if (bm==23)
						bQueen7=te;
					if (bm==24)
						bQueen8=te;
					
					if (wPawn1==te) {
						wPawn1=0;
						it=1;
					}
					if (wPawn2==te) {
						wPawn2=0;
						it=2;
					}
					if (wPawn3==te) {
						wPawn3=0;
						it=3;
					}
					if (wPawn4==te) {
						wPawn4=0;
						it=4;
					}
					if (wPawn5==te) {
						wPawn5=0;
						it=5;
					}
					if (wPawn6==te) {
						wPawn6=0;
						it=6;
					}
					if (wPawn7==te) {
						wPawn7=0;
						it=7;
					}
					if (wPawn8==te) {
						wPawn8=0;
						it=8;
					}
					if (wRook1==te) {
						wRook1=0;
						it=9;
					}
					if (wRook2==te) {
						wRook2=0;
						it=10;
					}
					if (wKnight1==te) {
						wKnight1=0;
						it=11;
					}
					if (wKnight2==te) {
						wKnight2=0;
						it=12;
					}
					if (wBishop1==te) {
						wBishop1=0;
						it=13;
					}
					if (wBishop2==te) {
						wBishop2=0;
						it=14;
					}
					if (wQueen==te) {
						wQueen=0;
						it=15;
					}
					if (wKing==te) {
						wKing=0;
						it=16;
					}
					if (wQueen1==te) {
						wQueen1=0;
						it=17;
					}
					if (wQueen2==te) {
						wQueen2=0;
						it=18;
					}
					if (wQueen3==te) {
						wQueen3=0;
						it=19;
					}
					if (wQueen4==te) {
						wQueen4=0;
						it=20;
					}
					if (wQueen5==te) {
						wQueen5=0;
						it=21;
					}
					if (wQueen6==te) {
						wQueen6=0;
						it=22;
					}
					if (wQueen7==te) {
						wQueen7=0;
						it=23;
					}
					if (wQueen8==te) {
						wQueen8=0;
						it=24;
					}
					clear();
					if (!checkBCheck(bKing)) {
    		    		returner=1;
					}
					if (bm==1)
						bPawn1=g;
					if (bm==2)
						bPawn2=g;
					if (bm==3)
						bPawn3=g;
					if (bm==4)
						bPawn4=g;
					if (bm==5)
						bPawn5=g;
					if (bm==6)
						bPawn6=g;
					if (bm==7)
						bPawn7=g;
					if (bm==8)
						bPawn8=g;
					if (bm==9)
						bRook1=g;
					if (bm==10)
						bRook2=g;
					if (bm==11)
						bKnight1=g;
					if (bm==12)
						bKnight2=g;
					if (bm==13)
						bBishop1=g;
					if (bm==14)
						bBishop2=g;
					if (bm==15)
						bQueen=g;
					if (bm==16)
						bKing=g;
					if (bm==17)
						bQueen1=g;
					if (bm==18)
						bQueen2=g;
					if (bm==19)
						bQueen3=g;
					if (bm==20)
						bQueen4=g;
					if (bm==21)
						bQueen5=g;
					if (bm==22)
						bQueen6=g;
					if (bm==23)
						bQueen7=g;
					if (bm==24)
						bQueen8=g;
					
					switch (it) {
					case 1: wPawn1=te;
						break;
					case 2: wPawn2=te;
    					break;
					case 3: wPawn3=te;
    					break;
					case 4: wPawn4=te;
    					break;
					case 5: wPawn5=te;
    					break;
					case 6: wPawn6=te;
    					break;
					case 7:  wPawn7=te;
    					break;
					case 8: wPawn8=te;
    					break;
					case 9: wRook1=te;
    					break;
					case 10: wRook2=te;
    					break;
					case 11: wKnight1=te;
						break;
					case 12: wKnight2=te;
						break;
					case 13: wBishop1=te;
						break;
					case 14: wBishop2=te;
						break;
					case 15: wQueen=te;
						break;
					case 16: wKing=te;
						break;
					case 17: wQueen1=te;
						break;
					case 18: wQueen2=te;
						break;
					case 19: wQueen3=te;
						break;
					case 20: wQueen4=te;
						break;
					case 21: wQueen5=te;
						break;
					case 22: wQueen6=te;
						break;
					case 23: wQueen7=te;
						break;
					case 24: wQueen8=te;
						break;
					default:
						break;
					}
					clear();
				}
			}
		}
    	g=saverg;
    	bm=saver;
    	if (returner==0)
    		return true;
    	else
    		return false;
    }
}