package com.wallops.java.gui;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;

public class GuiMainMenu extends GuiScreen {

	private GuiButton play;
	private GuiButton options;
	private GuiButton quit;
	private boolean visible;
	private Game game;

	public GuiMainMenu(Game game) {
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.play = new GuiButton((int)(rect.getWidth())/2-250, (int)(rect.getHeight())/2-60, 500, 100, "Play");
		this.options = new GuiButton((int)(rect.getWidth())/2-250, (int)(rect.getHeight())/2+60, 500, 100, "Options");
		this.quit = new GuiButton((int)(rect.getWidth())/2-250, (int)(rect.getHeight())/2-180, 500, 100, "Quit");
		this.visible = true;
		this.game = game;
	}

	@Override
	public void renderScreen() {
		if(this.visible) {
			super.renderScreen();
			this.play.renderButton();
			this.options.renderButton();
			this.quit.renderButton();
		}
	}

}
