package com.wallops.java.gui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;

public class GuiMainMenu extends GuiScreen {

	public GuiMainMenu(Game game) {
		super(game);
		Rectangle rect = new Rectangle(Display.getWidth(),Display.getHeight());
		this.screenButtons.add(new GuiButton((int)(rect.getWidth())/2-250, (int)(rect.getHeight())/2-180, 500, 100, "Play"));
		this.screenButtons.add(new GuiButton((int)(rect.getWidth())/2-250, (int)(rect.getHeight())/2-60, 500, 100, "Options"));
		this.screenButtons.add(new GuiButton((int)(rect.getWidth())/2-250, (int)(rect.getHeight())/2+60, 500, 100, "Quit"));
	}
	
	/**
	 * Does an action based on a given clicked button
	 */
	@Override
	public void buttonClicked(GuiButton clickedButton) {
		if(clickedButton.getName().equalsIgnoreCase("play")) {
			game.logger.debug("Starting game from "+this.getClass().getSimpleName()+"\'s \"Play\" button");
			//start the game!
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

}
