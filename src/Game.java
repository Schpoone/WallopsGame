import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game {
	private JFrame window;
	private JPanel view;
	private JPanel inputSection;
	private JPanel hud;
	private ImageIcon health;
	private JTextArea commandPane;
	private ScrollPane scroll;
	private JTextField textField;
	private KeyListener commandInput;
	
	public Game() {
		
		initVars();
		formatVars();
		addComps();
		
		window.setVisible(true);
	}
	
	public void formatVars() {
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		window.setSize(new Dimension(rect.width,rect.height));
		
		view.setPreferredSize(new Dimension(2*window.getWidth()/3, window.getHeight()));
		view.setMaximumSize(view.getPreferredSize());
		view.setMinimumSize(view.getPreferredSize());
		view.setSize(view.getPreferredSize());
		
		textField.setPreferredSize(new Dimension(window.getWidth()/3, window.getHeight()/24));
		textField.setMaximumSize(textField.getPreferredSize());
		textField.setSize(textField.getPreferredSize());
		
		scroll.setPreferredSize(new Dimension(window.getWidth()/3, 43*window.getHeight()/96));
		scroll.setMaximumSize(scroll.getPreferredSize());
		scroll.setSize(scroll.getPreferredSize());
		
		inputSection.setPreferredSize(new Dimension(window.getWidth()/3, window.getHeight()/2));
		inputSection.setMaximumSize(inputSection.getPreferredSize());
		inputSection.setMinimumSize(inputSection.getPreferredSize());
		inputSection.setSize(inputSection.getPreferredSize());
		
		hud.setPreferredSize(new Dimension(window.getWidth()/3, window.getHeight()/2));
		hud.setMaximumSize(hud.getPreferredSize());
		hud.setMinimumSize(hud.getPreferredSize());
		hud.setSize(hud.getPreferredSize());
		
		
		window.setLayout(new GridBagLayout());
		hud.setLayout(new GridBagLayout());
		inputSection.setLayout(new BoxLayout(inputSection, BoxLayout.PAGE_AXIS));
		
		view.setBackground(Color.BLUE);
		hud.setBackground(Color.CYAN);
		commandPane.setBackground(Color.WHITE);
		textField.setBackground(Color.PINK);
		
		commandPane.setEditable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addComps() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.gridx = 0;
		c.gridy = 0;
		window.add(view, c);
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		window.add(hud, c);
		c.gridy = 1;
		window.add(inputSection, c);
		
		textField.addKeyListener(commandInput);
		
		scroll.add(commandPane);
		inputSection.add(scroll);
		inputSection.add(textField);
		
		//hud.add(comp, constraints);
	}
	
	public void initVars() {
		window = new JFrame();
		view = new JPanel();
		inputSection = new JPanel();
		hud = new JPanel();
		health = new ImageIcon("health.jpg");
		commandPane = new JTextArea();
		textField = new JTextField();
		scroll = new ScrollPane();
		commandInput = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (!textField.getText().isEmpty() && textField.getText().charAt(0) == ' ')
					textField.setText("");
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().isEmpty())
				{
					addCommand(textField.getText());
					commandPane.setText(commandPane.getText() + "\n>" + textField.getText());
					textField.setText("");
					scroll.setScrollPosition(0, scroll.getVAdjustable().getMaximum()+5);
				}
				
			}
		};
	}
	
	public void addCommand(String command) {
		if(command.equals("clear"))
			commandPane.setText("");
	}
}
