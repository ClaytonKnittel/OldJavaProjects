import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class events extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel label2;
	private JButton button;
	private JButton button2;
	private int x = 0;
	
	public events() {
		setLayout(new FlowLayout());
		
		
		button = new JButton("Click for text");
		add(button);
		label = new JLabel("");
		add(label);
		button2 = new JButton("Click for more text");
		add(button2);
		label2 = new JLabel("");
		add(label2);
		
		event e=new event();
		button.addActionListener(e);
		
		event2 ev=new event2();
		button2.addActionListener(ev);
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (x==0) {
				label.setText("Awesome!");
				x=1;
			}
			else {
				label.setText("");
				x=0;
			}
		}
		
	}
	
	public class event2 implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			label2.setText("Cool!");
		}
	}
	
	public static void main(String args[]) {
		events gui=new events();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setTitle("Thing");
		gui.setSize(300,100);
		gui.setVisible(true);
	}
}
