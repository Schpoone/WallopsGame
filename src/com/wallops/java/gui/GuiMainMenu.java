package com.wallops.java.gui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

/**
 * A {@linkplain GuiScreen} used for the main menu of the game. Has 3 {@linkplain GuiButton}s, one to play the game, one to open the options menu (to be implemented) and one to quit the game.
 * 
 * @author PureChaose
 *
 */
public class GuiMainMenu extends GuiScreen {
	
	protected static Texture background;
	
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
		if (GuiMainMenu.background == null) {
			GuiMainMenu.background = Game.textureManager.getTexture(new ResourcePath(ResourcePath.imgDir+"main_background.png"));
		}
		this.renderables.add(new GuiButton(1F/3F, 1F/3F, 1F/3F, 5F/54F, "Play"));
		this.renderables.add(new GuiButton(1F/3F, 4F/9F, 1F/3F, 5F/54F, "Options"));
		this.renderables.add(new GuiButton(1F/3F, 5F/9F, 1F/3F, 5F/54F, "Quit"));
		if(this.visible) {
			this.drawTexture(0, 0, this.game.displayWidth, this.game.displayHeight, GuiMainMenu.background);
			GL11.glDrawBuffer(GL11.GL_BACK);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDrawBuffer(GL11.GL_FRONT_AND_BACK);
		}
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
