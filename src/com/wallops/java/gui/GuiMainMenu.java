package com.wallops.java.gui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;

public class GuiMainMenu extends GuiScreen {

	public GuiMainMenu(Game game) {
		super(game);
		this.screenButtons.add(new GuiButton((Display.getWidth())/3, (Display.getHeight())/3, Display.getWidth()/3, 5*Display.getHeight()/54, "Play"));
		this.screenButtons.add(new GuiButton((Display.getWidth())/3, (4*Display.getHeight())/9, Display.getWidth()/3, 5*Display.getHeight()/54, "Options"));
		this.screenButtons.add(new GuiButton((Display.getWidth())/3, (5*Display.getHeight())/9, Display.getWidth()/3, 5*Display.getHeight()/54, "Quit"));
	}
	
	/**
	 * Does an action based on a given clicked button
	 */
	@Override
	public void buttonClicked(GuiButton clickedButton) {
		if(clickedButton.getName().equalsIgnoreCase("play")) {
			game.logger.debug("Starting game from "+this.getClass().getSimpleName()+"\'s \"Play\" button");
			game.setScreen(new GuiGameScreen(game));
		}
		if(clickedButton.getName().equalsIgnoreCase("options")) {
			game.logger.debug("Opening options menu from "+this.getClass().getSimpleName()+"\'s \"Options\" button.");
			//open the option gui screen!
		}
		if(clickedButton.getName().equalsIgnoreCase("quit")) {
			game.logger.debug("Close requested from "+this.getClass().getSimpleName()+"\'s \"Quit\" button.");
			this.game.shutdown();
		}
	}
	
	@Override
	public void resize() {
		super.resize();
		this.screenButtons.get(0).setSize((Display.getWidth())/3, (Display.getHeight())/3, Display.getWidth()/3, 5*Display.getHeight()/54);
		this.screenButtons.get(1).setSize((Display.getWidth())/3, (4*Display.getHeight())/9, Display.getWidth()/3, 5*Display.getHeight()/54);
		this.screenButtons.get(2).setSize((Display.getWidth())/3, (5*Display.getHeight())/9, Display.getWidth()/3, 5*Display.getHeight()/54);
	}

}
