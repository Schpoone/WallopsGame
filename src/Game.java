import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game {
	private JFrame window;
	private JPanel top;
	private JPanel bottom;
	private JPanel inputSection;
	private JPanel hud;
	private String[] commandHistory;
	//private JTextArea[] commandPanes;
	private JTextArea commandPane;
	private JTextField textField;
	private KeyListener commandInput;
	
	public Game() {
		window = new JFrame();
		top = new JPanel();
		bottom = new JPanel();
		inputSection = new JPanel();
		hud = new JPanel();
		commandHistory = new String[20];
		commandPane = new JTextArea();
		commandPane.setBackground(Color.CYAN);
		//commandPanes = new JTextArea[20];
		//for (int i = 0; i < commandPanes.length; i++)
		//	commandPanes[i] = new JTextArea();
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
					//addCommand(">" + textField.getText());
					/*for (int i = 0; i < commandPanes.length; i++)
					{
						commandPanes[i].setText(commandHistory[commandHistory.length - 1 - i]);
					}*/
					commandPane.setText(commandPane.getText() + "\n>" + textField.getText());
					textField.setText("");
				}
				
			}
		};
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		textField.requestFocusInWindow();
		textField.addKeyListener(commandInput);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setSize(new Dimension(xSize,ySize));
		window.setLayout(new GridLayout(2, 1));
		window.add(top);
		top.setBackground(Color.BLUE);
		window.add(bottom);
		bottom.setLayout(new GridLayout(1, 2));
		bottom.add(inputSection);
		bottom.add(hud);
		hud.setBackground(Color.RED);
		inputSection.setLayout(new BoxLayout(inputSection, BoxLayout.PAGE_AXIS));
		/*Color gray = new Color(170, 170, 170);
		for (int i = 0; i < commandPanes.length; i++)
		{
			inputSection.add(commandPanes[i]);
			commandPanes[i].setEditable(false);
			commandPanes[i].setBackground(gray);
			gray = new Color(gray.getRed() + 3, gray.getGreen() +3, gray.getBlue() + 3);
		}*/
		inputSection.add(commandPane);
		inputSection.add(textField);
		textField.setBackground(Color.PINK);
		textField.setPreferredSize(new Dimension(window.getWidth()/2, window.getHeight()/24));
		commandPane.setPreferredSize(new Dimension(window.getWidth()/2, 11 * window.getHeight()/24));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
	
	public String getCommand()
	{
		return textField.getText();
	}
	
	public void addCommand(String command)
	{
		for (int i = commandHistory.length - 1; i > 0; i--)
		{
			commandHistory[i] = commandHistory[i - 1];
		}
		commandHistory[0] = command;
	}
}
