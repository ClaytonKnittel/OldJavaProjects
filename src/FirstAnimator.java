import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class FirstAnimator extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	Timer t=new Timer(5,this);
	int x=0;
	int velX=2;
	int y=0;
	int velY=0;
	double l=0;
	double velL=.2;
	public void paint(Graphics g) {
		g.fillRect(x,y,(int)l,100);
		g.setColor(Color.red);
		t.start();
		
	}
	
	public FirstAnimator() {
		setSize(1000,1000);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (x<0||x>970) {
			velX*=-1;
		}
		if (velX>0)
			l+=velL;
		else
			l-=velL;
		y=0;
		velY=(int)Math.sqrt(x*25);
		x+=velX;
		y+=velY;
		repaint();
	}
	
	public static void main(String args[]) {
		FirstAnimator f=new FirstAnimator();
		JFrame jf=new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Animation");
		jf.setSize(1000,1000);
		jf.setVisible(true);
		jf.add(f);
	}
}
