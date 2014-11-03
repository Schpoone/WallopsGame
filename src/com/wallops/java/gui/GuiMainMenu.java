package com.wallops.java.gui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;

/**
 * A {@linkplain GuiScreen} used for the main menu of the game. Has 3 {@linkplain GuiButton}s, one to play the game, one to open the options menu (to be implemented) and one to quit the game.
 * 
 * @author PureChaose
 *
 */
public class GuiMainMenu extends GuiScreen {
	
	/**
	 * Creates a new GuiMainMenu GuiScreen with a reference back to the calling Game Object as specified by GuiScreen's constructor.
	 * @param game The calling Game Object
	 * @see GuiBattleScreen#GuiBattleScreen(Game)
	 */
	public GuiMainMenu(Game game) {
		super(game);
	}

	/**
	 * Calls GuiScreen initGui and then adds three GuiButtons to render, one to start the game, one for options, and one to quit the game.
	 */
	@Override
	public void initGui() {
		super.initGui();
		this.renderables.add(new GuiButton(1F/3F, 1F/3F, 1F/3F, 5F/54F, "Play"));
		this.renderables.add(new GuiButton(1F/3F, 4F/9F, 1F/3F, 5F/54F, "Options"));
		this.renderables.add(new GuiButton(1F/3F, 5F/9F, 1F/3F, 5F/54F, "Quit"));
	}
	
	/**
	 * Using the given GuiButton, the GuiMainMenu decides whether to play the game, open the options menu or quit the game.
	 */
	@Override
	public void buttonClicked(GuiButton clickedButton) {
		if(clickedButton.getName().equalsIgnoreCase("play")) {
			game.logger.debug("Starting game from "+this.getClass().getSimpleName()+"\'s \"Play\" button");
			game.setScreen(new GuiBattleScreen(game));
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
