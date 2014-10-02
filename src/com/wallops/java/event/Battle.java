package com.wallops.java.event;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.wallops.java.buttons.Act;
import com.wallops.java.buttons.Nope;
import com.wallops.java.buttons.OpenDesc;
import com.wallops.java.buttons.OpenSwitch;
import com.wallops.java.buttons.Reset;
import com.wallops.java.buttons.UseItem;
import com.wallops.java.gui.GuiButton;
import com.wallops.java.gui.GuiScreen;
import com.wallops.java.gui.MoveButton;
import com.wallops.java.reference.Move;
import com.wallops.java.wallopsmon.BaldEagle;
import com.wallops.java.wallopsmon.HorseshoeCrab;
import com.wallops.java.wallopsmon.LoblollyPine;
import com.wallops.java.wallopsmon.MantisShrimp;
import com.wallops.java.wallopsmon.MudDogWhelk;
import com.wallops.java.wallopsmon.SeaPork;
import com.wallops.java.wallopsmon.Tick;
import com.wallops.java.wallopsmon.Wallopsmon;

public class Battle extends GuiScreen {
	//private JFrame lose;
	private JButton reset;
	//private JFrame window;
	private JPanel view;
	private JTextArea oppHealth;
	private JPanel playerStatus;
	private JTextArea status;
	private JTextArea playerHealth;
	private JPanel inputSection;
	private JPanel hud;
	private JButton desc;
	private JButton swtch;
	private JButton item;
	private JButton run;
	private Wallopsmon[] player;
	private Wallopsmon opponent;
	private int fntOpp;

	public Battle(Game game) {
		super(game);
		initVars();
		formatVars();
		addComps();
		update();
	}

	public void formatVars() {

		setSize(view, new Dimension(2*Display.getWidth()/3, Display.getHeight()));
		setSize(inputSection, new Dimension(Display.getWidth()/3, Display.getHeight()/2));
		setSize(hud, new Dimension(Display.getWidth()/3, Display.getHeight()/2));

		playerStatus.setLayout(new GridLayout(2, 1));
		view.setLayout(new GridLayout(2,2));

		status.setEditable(false);
		status.setLineWrap(true);
		status.setWrapStyleWord(true);
		oppHealth.setEditable(false);
		playerHealth.setEditable(false);

		inputSection.setLayout(new GridLayout(2,2));
		hud.setLayout(new GridLayout(2,2));

		view.setBackground(Color.BLUE);
		hud.setBackground(Color.CYAN);
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
		//Display.add(view, c);

		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 2;
		//Display.add(hud, c);
		c.gridy = 1;
		//Display.add(inputSection, c);

		playerStatus.add(playerHealth);
		playerStatus.add(status);

		view.add(oppHealth);
		view.add(playerStatus);

		this.screenButtons.add(new MoveButton(player[0].getMoveOne(), 0));
		this.screenButtons.add(new MoveButton(player[0].getMoveTwo(), 1));
		this.screenButtons.add(new MoveButton(player[0].getMoveThree(), 2));
		this.screenButtons.add(new MoveButton(player[0].getMoveFour(), 3));

		desc.addActionListener(new OpenDesc(desc, this));
		item.addActionListener(new UseItem(item));
		run.addActionListener(new Nope(run));
		swtch.addActionListener(new OpenSwitch(player, this));
		reset.addActionListener(new Reset(this));

		hud.add(desc);
		hud.add(swtch);
		hud.add(item);
		hud.add(run);
	}

	public void initVars() {
		reset = new JButton("You lose. Click here to restart.");
		fntOpp = 0;
		view = new JPanel();
		oppHealth = new JTextArea();
		playerHealth = new JTextArea();
		inputSection = new JPanel();
		playerStatus = new JPanel();
		status = new JTextArea();
		hud = new JPanel();
		player = new Wallopsmon[6];
		player[0] = new BaldEagle();
		player[0].setName("Bill the " + player[0].getName());
		player[1] = new HorseshoeCrab();
		player[1].setName("Charlie the " + player[1].getName());
		player[2] = new LoblollyPine();
		player[2].setName("Fred the " + player[2].getName());
		player[3] = new MudDogWhelk();
		player[3].setName("George the " + player[3].getName());
		player[4] = new Tick();
		player[4].setName("Ron the " + player[4].getName());
		player[5] = new MantisShrimp();
		player[5].setName("Ginny the " + player[5].getName());

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
		return player[0];
	}

	public void update() {
		if(opponent != null && opponent.getCurrentHealth() <= 0)
			opponentFaint();
		if(player[0].getCurrentHealth() <= 1) {
			playerFaint();
		}
		if(opponent != null) {
			if(opponent.getName().indexOf("Wild ") != 0)
					opponent.setName("Wild " + opponent.getName());
			oppHealth.setText("Name: " + opponent.getName() + "\nHP: " + opponent.getCurrentHealth() + "/" + opponent.getMaxHealth()  + "\nLevel: "
					+ opponent.getLevel() + "\nProjected XP gain: " + player[0].calcExpGain(opponent.getLevel()));
		}
		playerHealth.setText("Name: " + player[0].getName() + "\nHP: " + player[0].getCurrentHealth() + "/" + player[0].getMaxHealth()
				+ "\nEXP to next level: " + player[0].getExpToLevel() + "\nLevel: " + player[0].getLevel());

		/*if (player[0].getMoveOne().getCategory() != Move.NO) 
			button1.setBackground(player[0].getMoveOne().getType().getColor());
		if (player[0].getMoveTwo().getCategory() != Move.NO)
			button2.setBackground(player[0].getMoveTwo().getType().getColor());
		if (player[0].getMoveThree().getCategory() != Move.NO)
			button3.setBackground(player[0].getMoveThree().getType().getColor());
		if (player[0].getMoveFour().getCategory() != Move.NO)
			button4.setBackground(player[0].getMoveFour().getType().getColor());*/
		inputSection.setVisible(false);
		inputSection.setVisible(true);
	}

	public void opponentFaint() {
		fntOpp = fntOpp%7+1;
		player[0].updateExp(opponent.getLevel());
		status.setText(opponent.getName() + " fainted!");
		if(fntOpp == 7)		
			opponent = new MudDogWhelk();
		if(fntOpp == 1)		
			opponent = new HorseshoeCrab();
		if(fntOpp == 2)
			opponent = new SeaPork();
		if(fntOpp == 3)
			opponent = new Tick();
		if(fntOpp == 4)
			opponent = new MantisShrimp();
		if(fntOpp == 5)
			opponent = new BaldEagle();
		if(fntOpp == 6)
			opponent = new LoblollyPine();
		opponent.setName("Wild " + opponent.getName());
		status.append("\nA " + opponent.getName() + " has appeared!");
	}

	public void playerFaint() {
		player[0].setCurrentHealth(0);
		for(int i=0; i<6; i++) {
			if(player[i] != null && player[i].getCurrentHealth() != 0) {
				((OpenSwitch) swtch.getActionListeners()[0]).open();
				return;
			}
		}
		/*lose = new JFrame("You Lose!");
		lose.add(reset);
		lose.setSize(window.getSize());
		lose.setVisible(true);*/
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
		if((status.getText().indexOf("damage") != -1 || status.getText().equals("")) && (attacker.getName().equals(player[0].getName()) || status.getText().indexOf(player[0].getName()) != 0))
			status.setText(attacker.getName() + " used " + m.getName() +" and dealt " + defender.getName() + " " + damage + " damage!");
		else
			status.append("\n" + attacker.getName() + " used " + m.getName() +" and dealt " + defender.getName() + " " + damage + " damage!");
		update();
	}
	
	public void status(Move m, Wallopsmon attacker, Wallopsmon defender) {
		if((status.getText().indexOf("damage") != -1 || status.getText().equals("")) && (attacker.getName().equals(player[0].getName()) || status.getText().indexOf(player[0].getName()) != 0))
			status.setText(attacker.getName() + " used " + m.getName() +" on " + defender.getName() + "!");
		else
			status.append("\n" + attacker.getName() + " used " + m.getName() +" on " + defender.getName() + "!");
	}
	
	public void reset() {
		opponent= new MudDogWhelk();
		initVars();
		formatVars();
		addComps();
		update();
		//lose.setVisible(false);
	}

	@Override
	public void buttonClicked(GuiButton clickedButton) {
		if(clickedButton instanceof MoveButton)
			this.attack(((MoveButton)clickedButton).getMove(), this.player[0], this.opponent);
	}
	
	@Override
	public void renderScreen() {
		super.renderScreen();
		player[0].getImage().bind();
		drawRectangle(Display.getWidth()/3, Display.getHeight()/2, 2*Display.getWidth()/3, 2*Display.getHeight()/2, Integer.MAX_VALUE);
		player[0].getImage().release();
		opponent.getImage().bind();
		drawRectangle(0, 0, Display.getWidth()/3, Display.getHeight()/2, Integer.MAX_VALUE);
		opponent.getImage().release();
	}
}
