package com.wallops.java.gui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;

public class GuiMainMenu extends GuiScreen {

	public GuiMainMenu(Game game) {
		super(game);
		this.renderables.add(new GuiButton(1F/3F, 1F/3F, 1F/3F, 5F/54F, "Play"));
		this.renderables.add(new GuiButton(1F/3F, 4F/9F, 1F/3F, 5F/54F, "Options"));
		this.renderables.add(new GuiButton(1F/3F, 5F/9F, 1F/3F, 5F/54F, "Quit"));
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

}
