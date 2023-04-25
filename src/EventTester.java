import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class EventTester extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static boolean j=false;
	
	public EventTester() {
		setLayout(new FlowLayout());
		
		
		JButton b=new JButton();
		event e=new event();
		add(b);
		b.addActionListener(e);
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			j=true;
			repaint();
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		if (j) {
			g.setColor(Color.black);
		}
		g.fillRect(1, 1, 30, 30);
	}
	
	
	public static void main(String args[]) {
		EventTester E=new EventTester();
		E.setDefaultCloseOperation(EXIT_ON_CLOSE);
		E.setTitle("Cosa");
		E.setSize(300,300);
		E.setVisible(true);
	}
	
}
