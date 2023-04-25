import java.awt.event.*;
import javax.swing.*;

public class RandomGame extends JFrame {
	private static final long serialVersionUID = 1L;
	int randomNum, guess;
	private JButton button;
	private JTextField textField;
	private JLabel promptLabel;
	private JLabel resultLabel;
	private JLabel randomLabel;
	
	private RandomGame() {
		promptLabel = new JLabel("Enter # 1-10");
		add(promptLabel);
		textField = new JTextField(5);
		add(textField);
		
		resultLabel = new JLabel("");
		add (resultLabel);
		
		randomLabel = new JLabel ("");
		add (randomLabel);
		
		event e=new event();
		button.addActionListener(e);
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
