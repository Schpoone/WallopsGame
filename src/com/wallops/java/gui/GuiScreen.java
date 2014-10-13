package com.wallops.java.gui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;
import com.wallops.java.reference.MouseHandler;

/**
 * An abstract class used for all screens that must be displayed and called generically within the game.
 * Usually only stored in {@linkplain Game}'s activeGuiScreen for rendering
 * 
 * @author PureChaose
 *
 */
public abstract class GuiScreen extends Gui implements IRenderable{
	/** where the button in this GuiScreen are stored */
	protected ArrayList<IRenderable> renderables;
	protected boolean visible;
	protected Game game;

	/**
	 * Creates a new GuiScreen with a reference back to the calling Game Object, and initializes any IRenderalbes relted to it.
	 * @param game The calling Game Object.
	 */
	public GuiScreen(Game game) {
		this.game = game;
		this.initGui();
	}
	
	/**
	 * Creates a new IRendeable ArrayList to render whenever this GuiScreen is rendered, sets its background and sets it to be visible.
	 */
	public void initGui() {
		renderables = new ArrayList<IRenderable>();
		this.renderables.add(new GuiImage(0F,0F,1F,1F,Integer.MAX_VALUE));
		this.visible = true;
	}

	/**
	 * Renders all the IRenderables stored in this GuiScreen, but only if this GuiScreen is visible.
	 */
	public void render() {
		if(this.visible) {
			for(IRenderable g : renderables) {
				g.render();
			}
		}
	}

	/**
	 * Gives the GuiScreen a GuiButton that was clicked, and the GuiScreen then checks which one, and acts depending on which GuiButton it is.
	 * @param clickedButton The GuiButton that was clicked
	 */
	public abstract void buttonClicked(GuiButton clickedButton);

	/**
	 * Handles the mouse input to the Game, usually not overridden.
	 */
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

	/**
	 * Clears the IRenderable ArrayList and remakes all the components that were in it. Called when the Display is resized to make sure the internal IRenderables are the correct size.
	 */
	public void resize() {
		this.renderables.clear();
		this.initGui();
	}
	
	/**
	 * Sets this GuiScreen's visibility
	 * @param visible Whether this GuiScreen is visible or not
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
