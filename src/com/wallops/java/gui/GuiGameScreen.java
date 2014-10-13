package com.wallops.java.gui;

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
import com.wallops.java.event.Game;
import com.wallops.java.reference.Move;
import com.wallops.java.wallopsmon.BaldEagle;
import com.wallops.java.wallopsmon.HorseshoeCrab;
import com.wallops.java.wallopsmon.LoblollyPine;
import com.wallops.java.wallopsmon.MantisShrimp;
import com.wallops.java.wallopsmon.MudDogWhelk;
import com.wallops.java.wallopsmon.SeaPork;
import com.wallops.java.wallopsmon.Tick;
import com.wallops.java.wallopsmon.Wallopsmon;

public class GuiGameScreen extends GuiScreen {
	private Wallopsmon[] player;
	private Wallopsmon opponent;
	private int fntOpp;
	private GuiText opponentStats;
	private GuiText playerStats;
	private GuiText battleLog;

	public GuiGameScreen(Game game) {
		super(game);
		update();
	}

	public void initGui() {
		super.initGui();
		if(player == null) {
			fntOpp = 0;
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
			opponent= new MudDogWhelk();
		}
		opponentStats = new GuiText(0, 0, 1F/3F, 0.5F, opponentStats==null ? "Opponent Stats:" : opponentStats.getText());
		playerStats = new GuiText(1F/3F, 0.5F, 1F/3F, 0.25F, playerStats==null ? "Player Stats:" : playerStats.getText());
		battleLog = new GuiText(1F/3F, 0.75F, 1F/3F, 0.25F, battleLog==null ? "Battle Log:" : battleLog.getText());
		this.renderables.add(new MoveButton(player[0].getMoveOne(), 0));
		this.renderables.add(new MoveButton(player[0].getMoveTwo(), 1));
		this.renderables.add(new MoveButton(player[0].getMoveThree(), 2));
		this.renderables.add(new MoveButton(player[0].getMoveFour(), 3));
		this.renderables.add(new GuiButton(0.25F, 0.4F, 0.5F, 0.2F, "You lose. Click here to restart.", false));
		this.renderables.add(opponentStats);
		this.renderables.add(playerStats);
		this.renderables.add(battleLog);
		this.renderables.add(new GuiButton(2F/3F, 0F, 1F/6F, 0.25F, "Description"));
		this.renderables.add(new GuiButton(5F/6F, 0F, 1F/6F, 0.25F, "Switch Wallopsmon"));
		this.renderables.add(new GuiButton(2F/3F, 0.25F, 1F/6F, 0.25F, "Use item"));
		this.renderables.add(new GuiButton(5F/6F, 0.25F, 1F/6F, 0.25F, "Run"));
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
			this.opponentStats.setText("Name: " + opponent.getName() + "\nHP: " + opponent.getCurrentHealth() + "/" + opponent.getMaxHealth()  + "\nLevel: "
					+ opponent.getLevel() + "\nProjected XP gain: " + player[0].calcExpGain(opponent.getLevel()));
		}
		this.playerStats.setText("Name: " + player[0].getName() + "\nHP: " + player[0].getCurrentHealth() + "/" + player[0].getMaxHealth()
				+ "\nEXP to next level: " + player[0].getExpToLevel() + "\nLevel: " + player[0].getLevel());
	}

	public void opponentFaint() {
		fntOpp = fntOpp%7+1;
		player[0].updateExp(opponent.getLevel());
		battleLog.setText(opponent.getName() + " fainted!");
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
		battleLog.append("\nA " + opponent.getName() + " has appeared!");
	}

	public void playerFaint() {
		player[0].setCurrentHealth(0);
		for(int i=0; i<6; i++) {
			if(player[i] != null && player[i].getCurrentHealth() != 0) {
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
		if((this.battleLog.getText().indexOf("damage") != -1 || this.battleLog.getText().equals("")) && ((attacker.getName().equals(player[0].getName()) || this.battleLog.getText().indexOf(player[0].getName()) != 0)))
			this.battleLog.setText(attacker.getName() + " used " + m.getName() +" and dealt " + defender.getName() + " " + damage + " damage!");
		else
			this.battleLog.append("\n" + attacker.getName() + " used " + m.getName() +" and dealt " + defender.getName() + " " + damage + " damage!");
		update();
	}

	public void status(Move m, Wallopsmon attacker, Wallopsmon defender) {
		if((this.battleLog.getText().indexOf("damage") != -1 || this.battleLog.getText().equals("")) && ((attacker.getName().equals(player[0].getName()) || this.battleLog.getText().indexOf(player[0].getName()) != 0)))
			this.battleLog.setText(attacker.getName() + " used " + m.getName() +" on " + defender.getName() + "!");
		else
			this.battleLog.append("\n" + attacker.getName() + " used " + m.getName() +" on " + defender.getName() + "!");
	}

	public void reset() {
		opponent= new MudDogWhelk();
		initGui();
		update();
	}

	@Override
	public void buttonClicked(GuiButton clickedButton) {
		if(clickedButton instanceof MoveButton)
			this.attack(((MoveButton)clickedButton).getMove(), this.player[0], this.opponent);
		else if(clickedButton.getName().toLowerCase().contains("restart"))
			this.reset();
		else if(clickedButton.getName().toLowerCase().contains("description"));
		//open description
		else if(clickedButton.getName().toLowerCase().contains("switch"));
		//open switching menu
		else if(clickedButton.getName().toLowerCase().contains("use item"))
			clickedButton.setName("Nah!");//open item menu
		else if(clickedButton.getName().toLowerCase().contains("nah!"))
			clickedButton.setName("Use item");
		else if(clickedButton.getName().toLowerCase().contains("run"));
		//run away!
	}

	@Override
	public void render() {
		super.render();
		this.drawBoundImage(0, Display.getHeight()/2, Display.getWidth()/3, 2*Display.getHeight()/2, player[0].getImage());
		this.drawBoundImage(Display.getWidth()/3, 0, 2*Display.getWidth()/3, Display.getHeight()/2, opponent.getImage());
	}
}
