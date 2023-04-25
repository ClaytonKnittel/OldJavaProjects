import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;

public class FirstGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JButton button;
	private JTextField textfield;
	
	public FirstGui() {
		setLayout(new FlowLayout());
		
		label=new JLabel("Hi, I am a label");
		add(label);
		
		
		textfield=new JTextField(15);
		add(textfield);
		
		button = new JButton("Click ME!!");
		add(button);
	}
	
	public static void main(String args[]) {
		FirstGui gui=new FirstGui();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(200, 125);
		gui.setVisible(true);
		gui.setTitle("First GUI");
	}
}