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
		update();

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

		oppHealth.setEditable(false);
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
		player[activeMon] = new MudDogWhelk();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		//		button1.setForeground(Color.WHITE);
		//		button2.setForeground(Color.WHITE);
		//		button3.setForeground(Color.WHITE);
		//		button4.setForeground(Color.WHITE);

		desc = new JButton("Description");
		desc.setBackground(Color.WHITE);
		swtch = new JButton("Switch Wallopsmon");
		swtch.setBackground(Color.WHITE);
		item = new JButton("Use item");
		item.setBackground(Color.WHITE);
		run = new JButton("Run");
		run.setBackground(Color.WHITE);
	}

	public Wallopsmon getOpponent() {
		return opponent;
	}

	public void setOpponent(Wallopsmon opponent) {
		this.opponent = opponent;
	}
	
	public Wallopsmon getActive() {
		return player[activeMon];
	}

	public void update() {
		if(opponent != null)
			oppHealth.setText("HP: " + opponent.getCurrentHealth() + "/" + opponent.getMaxHealth());
		playerHealth.setText("HP: " + player[activeMon].getCurrentHealth() + "/" + player[activeMon].getMaxHealth()
				+ "\nEXP: " + player[activeMon].getExpToLevel());

		if(playerImage.getComponentCount() != 0)
			playerImage.remove(0);
		playerImage.add(player[activeMon].getResizedImage(playerImage.getSize()));
		playerImage.setVisible(false);
		playerImage.setVisible(true);

		if(opponent != null) {
			if(oppImage.getComponentCount() != 0)
				playerImage.remove(0);
			oppImage.add(opponent.getResizedImage(oppImage.getSize()));
			oppImage.setVisible(false);
			oppImage.setVisible(true);
		}

		button1.setText(player[0].getMoveOne().getName());
		button2.setText(player[0].getMoveTwo().getName());
		button3.setText(player[0].getMoveThree().getName());
		button4.setText(player[0].getMoveFour().getName());

		if(button1.getActionListeners().length == 0)
			button1.addActionListener(new Act(player[activeMon].getMoveOne(), this));
		if(button2.getActionListeners().length == 0)
			button2.addActionListener(new Act(player[activeMon].getMoveTwo(), this));
		if(button3.getActionListeners().length == 0)
			button3.addActionListener(new Act(player[activeMon].getMoveThree(), this));
		if(button4.getActionListeners().length == 0)
			button4.addActionListener(new Act(player[activeMon].getMoveFour(), this));
		
		button1.setOpaque(true);
		button2.setOpaque(true);
		button3.setOpaque(true);
		button4.setOpaque(true);

		if (!player[activeMon].getMoveOne().equals(Move.NONE)) 
			button1.setBackground(player[activeMon].getMoveOne().getType().getColor());
		if (!player[activeMon].getMoveTwo().equals(Move.NONE))
			button2.setBackground(player[activeMon].getMoveTwo().getType().getColor());
		if (!player[activeMon].getMoveThree().equals(Move.NONE))
			button3.setBackground(player[activeMon].getMoveThree().getType().getColor());
		if (!player[activeMon].getMoveFour().equals(Move.NONE))
			button4.setBackground(player[activeMon].getMoveFour().getType().getColor());
		inputSection.setVisible(false);
		inputSection.setVisible(true);
	}

	public void attack(Move m, Wallopsmon attacker, Wallopsmon defender) {
		int damage;
		int atk = 0;
		int pwr = m.getPower();
		int def = 0;
		int rand = (int)(Math.random()*17+84);
		int lvl = attacker.getLevel();
		double stab = 1;
		if(attacker.getType1().equals(m.getType()))
			stab = 1.5;
		double typeAdvantage = 1;
		if(m.getCategory() == Move.PHYSICAL) {
			atk = attacker.getAttack();
			def = defender.getDefense();
		}
		if(m.getCategory() == Move.SPECIAL) {
			atk = attacker.getSpecAttack();
			def = defender.getSpecDefense();
		}
		switch(defender.getType1()) {
		case NORMAL:
			switch(m.getType()) {
			case FIGHTING:
				typeAdvantage = 2;
				break;
			}
			break;
		case FIRE:
			switch(m.getType()) {
			case FIRE:
			case GRASS:
			case ICE:
			case BUG:
			case STEEL:
				typeAdvantage = 0.5;
				break;
			case WATER:
			case GROUND:
			case ROCK:
				typeAdvantage = 2;
				break;
			}
			break;
		case WATER:
			switch(m.getType()) {
			case FIRE:
			case WATER:
			case ICE:
			case STEEL:
				typeAdvantage = 0.5;
				break;
			case ELECTRIC:
			case GRASS:
				typeAdvantage = 2;
				break;
			}
			break;
		case ELECTRIC:
			switch(m.getType()) {
			case ELECTRIC:
			case FLYING:
			case STEEL:
				typeAdvantage = 0.5;
				break;
			case GROUND:
				typeAdvantage = 2;
				break;
			}
			break;
		case GRASS:
			switch(m.getType()) {
			case WATER:
			case ELECTRIC:
			case GRASS:
			case GROUND:
				typeAdvantage = 0.5;
				break;
			case FIRE:
			case ICE:
			case POISON:
			case FLYING:
			case BUG:
				typeAdvantage = 2;
				break;
			}
			break;
		case ICE:
			switch(m.getType()) {
			case ICE:
				typeAdvantage = 0.5;
				break;
			case FIRE:
			case FIGHTING:
			case ROCK:
				typeAdvantage = 2;
				break;
			}
		case FIGHTING:
			switch(m.getType()) {
			case BUG:
			case ROCK:
			case DARK:
				typeAdvantage = 0.5;
				break;
			case FLYING:
				typeAdvantage = 2;
				break;
			}
			break;
		case POISON:
			switch(m.getType()) {
			case GRASS:
			case FIGHTING:
			case POISON:
			case BUG:
				typeAdvantage = 0.5;
				break;
			case GROUND:
				typeAdvantage = 2;
				break;
			}
			break;
		case GROUND:
			switch(m.getType()) {
			case ELECTRIC:
				typeAdvantage = 0;
				break;
			case POISON:
			case ROCK:
				typeAdvantage = 0.5;
				break;
			case WATER:
			case GRASS:
			case ICE:
				typeAdvantage = 2;
				break;
			}
			break;
		case FLYING:
			switch(m.getType()) {
			case GROUND:
				typeAdvantage = 0;
				break;
			case FIGHTING:
			case BUG:
				typeAdvantage = 0.5;
				break;
			case ELECTRIC:
			case ICE:
			case ROCK:
				typeAdvantage = 2;
				break;
			}
			break;
		case BUG:
			switch(m.getType()) {
			case GRASS:
			case FIGHTING:
			case GROUND:
				typeAdvantage = 0.5;
				break;
			case FIRE:
			case FLYING:
			case ROCK:
				typeAdvantage = 2;
				break;
			}
			break;
		case ROCK:
			switch(m.getType()) {
			case NORMAL:
			case FIRE:
			case POISON:
			case FLYING:
				typeAdvantage = 0.5;
				break;
			case WATER:
			case GRASS:
			case ICE:
			case GROUND:
			case STEEL:
				typeAdvantage = 2;
				break;
			}
			break;
		case DRAGON:
			switch(m.getType()) {
			case FIRE:
			case WATER:
			case ELECTRIC:
			case GRASS:
				typeAdvantage = 0.5;
				break;
			case ICE:
			case DRAGON:
				typeAdvantage = 2;
				break;
			}
			break;
		case DARK:
			switch(m.getType()) {
			case DARK:
				typeAdvantage = 0.5;
				break;
			case FIGHTING:
			case ROCK:
				typeAdvantage = 2;
				break;
			}
			break;
		case STEEL:
			switch(m.getType()) {
			case POISON:
				typeAdvantage = 0;
				break;
			case NORMAL:
			case GRASS:
			case ICE:
			case FLYING:
			case BUG:
			case ROCK:
			case DRAGON:
			case STEEL:
				typeAdvantage = 0.5;
				break;
			case FIGHTING:
			case GROUND:
				typeAdvantage = 2;
				break;
			}
			break;
		}
		damage = (int)Math.round(((((2.0*lvl/5.0 + 2)*atk*pwr/def) / 50) + 2)*stab*typeAdvantage*rand/100);
		defender.setCurrentHealth(defender.getCurrentHealth() - damage);
		update();
	}
}