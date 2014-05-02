import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game {
	private JFrame window;
	private ScrollPane scroll;
	private JTextArea text;
	private JPanel top;
	private JTextField textField;
	private KeyListener commandInput;
	private String command;
	
	public Game() {
		window = new JFrame();
		scroll = new ScrollPane();
		top = new JPanel();
		text = new JTextArea();
		textField = new JTextField();
		commandInput = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					command = getCommand();
					textField.setText("");
				}
				
			}
		};
		textField.addKeyListener(commandInput);
		scroll.add(textField);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(new GridLayout(2, 1));
		window.add(top);
		window.add(scroll);
		textField.setBackground(Color.decode("211211211"));
		top.setBackground(Color.BLUE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public String getCommand()
	{
		return textField.getText();
	}
}
