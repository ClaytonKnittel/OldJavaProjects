package threeDGame;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
	
	public boolean[] key = new boolean[68836];
	
	@Override
	public void mouseDragged(MouseEvent me) {
		
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
				
	}

	@Override
	public void mousePressed(MouseEvent me) {
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		
	}

	@Override
	public void focusGained(FocusEvent fe) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
				
	}
	
	public class kk extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if (keyCode > 0 && keyCode < key.length) {
				key[keyCode]=true;
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if (keyCode > 0 && keyCode < key.length) {
				key[keyCode]=false;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
