package com.wallops.java.gui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;
import com.wallops.java.reference.MouseHandler;

public abstract class GuiScreen extends Gui implements IRenderable{
	/** where the button in this GuiScreen are stored */
	protected ArrayList<IRenderable> renderables;
	protected boolean visible;
	protected Game game;

	public GuiScreen(Game game) {
		this.game = game;
		this.initGui();
	}
	
	public void initGui() {
		renderables = new ArrayList<IRenderable>();
		this.renderables.add(new GuiImage(0F,0F,1F,1F,Integer.MAX_VALUE));
		this.visible = true;
	}

	public void render() {
		if(this.visible) {
			for(IRenderable g : renderables) {
				g.render();
			}
		}
	}

	public abstract void buttonClicked(GuiButton clickedButton);

	public void handleMouse() {
		int x = MouseHandler.getEventX();
		int y = MouseHandler.getEventY();
		int buttonPressed = Mouse.getEventButton();
		if(buttonPressed == -1)
			return;
		if(buttonPressed == 0)
			for(IRenderable r : renderables) {
				if(r instanceof GuiButton) {
					GuiButton g = (GuiButton) r;
					if(g.isEnabled() && g.isMouseInBounds(x, y) && Mouse.isButtonDown(buttonPressed))
						this.buttonClicked(g);
				}
			}
	}

	public void resize() {
		this.renderables.clear();
		this.initGui();
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
