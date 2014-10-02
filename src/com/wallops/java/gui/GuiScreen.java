package com.wallops.java.gui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;

public abstract class GuiScreen extends Gui implements IRenderable{
	/** where the button in this GuiScreen are stored */
	protected ArrayList<GuiButton> screenButtons;
	protected boolean visible;
	protected Game game;

	public GuiScreen(Game game) {
		screenButtons = new ArrayList<GuiButton>();
		this.visible = true;
		this.game = game;
	}

	public void renderScreen() {
		if(this.visible) {
			this.drawRectangle(0, 0, Display.getWidth(), Display.getHeight(), Integer.MAX_VALUE);
			for(GuiButton g : screenButtons) {
				g.renderButton();
			}
		}
	}

	public abstract void buttonClicked(GuiButton clickedButton);

	public void handleMouse() {
		int x = Mouse.getEventX();
		int y = Display.getHeight()-Mouse.getEventY();
		int buttonPressed = Mouse.getEventButton();
		if(buttonPressed == -1)
			return;
		if(buttonPressed == 0)
			for(GuiButton g : screenButtons) {
				if(g.isMouseInBounds(x, y) && Mouse.isButtonDown(buttonPressed))
					this.buttonClicked(g);
			}
	}

	public void resize() {
		for(GuiButton g : this.screenButtons)
			g.resize();
	}
}
