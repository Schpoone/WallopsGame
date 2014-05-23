import java.awt.*;

import javax.swing.*;


public class Game {
	private JFrame window;
	private JPanel view;
	private JTextArea oppHealth;
	private JPanel oppImage;
	private JPanel playerImage;
	private JTextArea playerHealth;
	private JPanel inputSection;
	private JPanel hud;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton desc;
	private JButton swtch;
	private JButton item;
	private JButton run;
	private int activeMon;
	private Wallopsmon[] player;
	private Wallopsmon opponent;
	
	public Game() {
		
		initVars();
		formatVars();
		addComps();
		
		window.setVisible(true);
	}
	
	public void formatVars() {
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		window.setSize(new Dimension(rect.width,rect.height));
		
		setSize(view, new Dimension(2*window.getWidth()/3, window.getHeight()));
		setSize(inputSection, new Dimension(window.getWidth()/3, window.getHeight()/2));
		setSize(hud, new Dimension(window.getWidth()/3, window.getHeight()/2));
		setSize(playerImage, new Dimension(view.getWidth()/2, view.getHeight()/2));
		setSize(oppImage, new Dimension(view.getWidth()/2, view.getHeight()/2));
		
		window.setLayout(new GridBagLayout());

		view.setLayout(new GridLayout(2,2));
		//oppHealth.setText("HP: " + opponent.getCurrentHealth() + "/" + opponent.getMaxHealth());
		playerHealth.setText("HP: " + player[activeMon].getCurrentHealth() + "/" + player[activeMon].getMaxHealth()
				+ "\nEXP: " + player[activeMon].getExpToLevel());
		oppHealth.setEditable(false);
		playerHealth.setEditable(false);
		
		oppHealth.setEditable(false);
		playerHealth.setText("HP: " + player[activeMon].getCurrentHealth() + "/" + player[activeMon].getMaxHealth()
				+ "\nEXP: " + player[activeMon].getExpToLevel());
		playerHealth.setEditable(false);
		
		inputSection.setLayout(new GridLayout(2,2));
		hud.setLayout(new GridLayout(2,2));
		
		view.setBackground(Color.BLUE);
		hud.setBackground(Color.CYAN);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setSize(Component c, Dimension d) {
		c.setPreferredSize(d);
		c.setMaximumSize(d);
		c.setMinimumSize(d);
		c.setSize(d);
		
	}
	
	public void addComps() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.gridx = 0;
		c.gridy = 0;
		window.add(view, c);
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 2;
		window.add(hud, c);
		c.gridy = 1;
		window.add(inputSection, c);

		view.add(oppHealth);
		view.add(oppImage);
		view.add(playerImage);
		
		playerImage.add(player[activeMon].getResizedImage(playerImage.getSize()));

		view.add(playerHealth);
		inputSection.add(button1);
		inputSection.add(button2);
		inputSection.add(button3);
		inputSection.add(button4);
		hud.add(desc);
		hud.add(swtch);
		hud.add(item);
		hud.add(run);
	}
	
	public void initVars() {
		window = new JFrame("Wallopsmon");
		view = new JPanel();
		oppHealth = new JTextArea();
		oppImage = new JPanel();
		playerImage = new JPanel();
		playerHealth = new JTextArea();
		inputSection = new JPanel();
		hud = new JPanel();
		player = new Wallopsmon[6];
		activeMon = 0;
		player[activeMon] = Wallopsmon.MUD_DOG_WHELK;
		button1 = new JButton(player[0].getMoveOne().getName());
		if (!player[activeMon].getMoveOne().equals(Move.NONE))
			button1.setBackground(player[activeMon].getMoveOne().getType().getColor());
		button2 = new JButton(player[0].getMoveTwo().getName());
		if (!player[activeMon].getMoveTwo().equals(Move.NONE))
			button2.setBackground(player[activeMon].getMoveTwo().getType().getColor());
		button3 = new JButton(player[0].getMoveThree().getName());
		if (!player[activeMon].getMoveThree().equals(Move.NONE))
			button3.setBackground(player[activeMon].getMoveThree().getType().getColor());
		button4 = new JButton(player[0].getMoveFour().getName());
		if (!player[activeMon].getMoveFour().equals(Move.NONE))
			button4.setBackground(player[activeMon].getMoveFour().getType().getColor());
		
		desc = new JButton("Description");
		swtch = new JButton("Switch Wallopsmon");
		item = new JButton("Use item");
		run = new JButton("Run");
			
		desc = new JButton("Description");
		desc.setBackground(Color.WHITE);
		swtch = new JButton("Switch Wallopsmon");
		swtch.setBackground(Color.WHITE);
		item = new JButton("Use item");
		item.setBackground(Color.WHITE);
		run = new JButton("Run");
		run.setBackground(Color.WHITE);
	}
}
