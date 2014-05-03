import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game {
	private JFrame window;
	private JPanel top;
	private JPanel bottom;
	private String[] commandHistory;
	private JTextArea com1;
	private JTextArea com2;
	private JTextArea com3;
	private JTextArea com4;
	private JTextArea com5;
	private JTextArea com6;
	private JTextArea com7;
	private JTextArea com8;
	private JTextArea com9;
	private JTextArea com10;
	private JTextField textField;
	private KeyListener commandInput;
	
	public Game() {
		window = new JFrame();
		top = new JPanel();
		bottom = new JPanel();
		commandHistory = new String[10];
		com1 = new JTextArea();
		com2 = new JTextArea();
		com3 = new JTextArea();
		com4 = new JTextArea();
		com5 = new JTextArea();
		com6 = new JTextArea();
		com7 = new JTextArea();
		com8 = new JTextArea();
		com9 = new JTextArea();
		com10 = new JTextArea();
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
					addCommand(textField.getText());
					textField.setText("");
					com10.setText(commandHistory[9]);
					com9.setText(commandHistory[8]);
					com8.setText(commandHistory[7]);
					com7.setText(commandHistory[6]);
					com6.setText(commandHistory[5]);
					com5.setText(commandHistory[4]);
					com4.setText(commandHistory[3]);
					com3.setText(commandHistory[2]);
					com2.setText(commandHistory[1]);
					com1.setText(commandHistory[0]);
				}
				
			}
		};
		textField.addKeyListener(commandInput);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(new GridLayout(2, 1));
		window.add(top);
		bottom.setLayout(new GridLayout(11, 1));
		bottom.add(com10);
		com10.setEditable(false);
		bottom.add(com9);
		com9.setEditable(false);
		bottom.add(com8);
		com8.setEditable(false);
		bottom.add(com7);
		com7.setEditable(false);
		bottom.add(com6);
		com6.setEditable(false);
		bottom.add(com5);
		com5.setEditable(false);
		bottom.add(com4);
		com4.setEditable(false);
		bottom.add(com3);
		com3.setEditable(false);
		bottom.add(com2);
		com2.setEditable(false);
		bottom.add(com1);
		com1.setEditable(false);
		bottom.add(textField);
		window.add(bottom);
		bottom.setBackground(Color.WHITE);
		textField.setBackground(Color.GREEN);
		top.setBackground(Color.BLUE);
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
