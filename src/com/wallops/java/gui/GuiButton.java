package com.wallops.java.gui;

import java.awt.Font;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.MouseHandler;
import com.wallops.java.reference.ResourcePath;

/**
 * A {@link Gui} used to display buttons in their location and have utility methods for clicking it.
 * 
 * @author PureChaose
 *
 */
public class GuiButton extends Gui implements IRenderable {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected static Texture buttonImage;
	private int[][] coords;
	private float[] tCoords;
	private Logger logger;
	private int mouseX;
	private int mouseY;
	protected String name;
	private boolean enabled;
	private boolean visible;
	private Font jfont;
	private TrueTypeFont font;
	private int trimColor;
	/** time when button should stop being rendered as "pushed" after it is clicked (175 millis after it is clicked, by default) */
	private long cooldown;
	private int centerColor;

	/**
	 * Creates a new button at a specified location, with a certain size and text to display
	 * @param xScale the ratio between the Display width and the button's x coordinate
	 * @param yScale the ratio between the Display height and the button's y coordinate
	 * @param widthScale the ratio between the Display width and the button's width
	 * @param heightScale the ratio between the Display size and the button's height
	 * @param name name of the button (to be displayed)
	 * @see GuiButton#GuiButton(int, int, int, int, String)
	 * @see GuiButton#GuiButton(float, float, float, float, String, boolean)
	 * @see GuiButton#GuiButton(int, int, int, int, String, boolean)
	 */
	public GuiButton(float xScale, float yScale, float widthScale, float heightScale, String name) {
		this((int)(xScale*Game.game.displayWidth), (int)(yScale*Game.game.displayHeight), (int)(widthScale*Game.game.displayWidth), (int)(heightScale*Game.game.displayHeight), name);
	}

	/**
	 * creates a new button at a specified location, with a certain size and text to display
	 * @param xScale the ratio between the Display width and the button's x coordinate
	 * @param yScale the ratio between the Display height and the button's y coordinate
	 * @param widthScale the ratio between the Display width and the button's width
	 * @param heightScale the ratio between the Display size and the button's height
	 * @param name name of the button (to be displayed)
	 * @param visible whether to display this button
	 * @see GuiButton#GuiButton(float, float, float, float, String)
	 * @see GuiButton#GuiButton(float, float, float, float, String, boolean)
	 * @see GuiButton#GuiButton(int, int, int, int, String, boolean)
	 */

	public GuiButton(float xScale, float yScale, float widthScale, float heightScale, String name, boolean visible) {
		this((int)(xScale*Game.game.displayWidth), (int)(yScale*Game.game.displayHeight), (int)(widthScale*Game.game.displayWidth), (int)(heightScale*Game.game.displayHeight), name, visible);
	}

	/**
	 * creates a new button at a specified location, with a certain size and text to display
	 * @param x the button's x coordinate
	 * @param y the button's y coordinate
	 * @param width the button's width
	 * @param height the button's height
	 * @param name name of the button (to be displayed)
	 * @see GuiButton#GuiButton(float, float, float, float, String)
	 * @see GuiButton#GuiButton(int, int, int, int, String)
	 * @see GuiButton#GuiButton(int, int, int, int, String, boolean)
	 */
	public GuiButton(int x, int y, int width, int height, String name) {
		this(x, y, width, height, name, true);
	}

	/**
	 * creates a new button at a specified location, with a certain size and text to display
	 * @param x the button's x coordinate
	 * @param y the button's y coordinate
	 * @param width the button's width
	 * @param height the button's height
	 * @param name name of the button (to be displayed)
	 * @param visible whether to display this button
	 * @see GuiButton#GuiButton(float, float, float, float, String)
	 * @see GuiButton#GuiButton(int, int, int, int, String)
	 * @see GuiButton#GuiButton(float, float, float, float, String, boolean)
	 */
	public GuiButton(int x, int y, int width, int height, String name, boolean visible) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.coords = new int[][]{{x,x+15,x+width-15,x+width},{y,y+15,y+height-15,y+height}};
		this.tCoords = new float[]{0,15/32F,17/32F,1};
		this.name = name;
		this.logger = LogManager.getLogger(this.name);
		this.enabled = true;
		this.visible = visible;
		this.jfont = new Font(Font.MONOSPACED, Font.PLAIN, 1);
		this.font = new TrueTypeFont(this.jfont, false);
		float widthMaxFontSize = (float)(this.width)/(float)(this.font.getWidth(this.name));
		float heightMaxFontSize = (float)(this.height)/(float)(this.font.getHeight(this.name));
		float maxFontSize = widthMaxFontSize > heightMaxFontSize ? heightMaxFontSize : widthMaxFontSize;
		this.jfont = jfont.deriveFont(jfont.getSize2D()*maxFontSize);
		this.font = new TrueTypeFont(this.jfont, false);
		if(GuiButton.buttonImage == null)
			GuiButton.buttonImage = Game.textureManager.getTexture(new ResourcePath(ResourcePath.imgDir+"ButtonTemplate32X.png"));
	}

	/**
	 * renders this button in its stored location with color (depending on where the 
	 * mouse is, and whether it's clicked)
	 */
	@Override
	public void render() {
		this.mouseX = MouseHandler.getX();
		this.mouseY = MouseHandler.getY();
		if (System.currentTimeMillis() >= cooldown) {
			// red (means mouse isn't in button's bounds)
			this.trimColor = 255 << 24 | 130 << 16 | 28 << 8 | 0;
			this.centerColor = 255 << 24 | 255 << 16 | 55 << 8 | 0;
			if(isMouseInBounds()) {
				// blue (mouse in button's bounds, but not clicked)
				this.trimColor = 255 << 24 | 0 << 16 | 28 << 8 | 130;
				this.centerColor = 255 << 24 | 0 << 16 | 55 << 8 | 255;
				if(Mouse.isButtonDown(0)) {
					// green (mouse in button's bounds and clicked)
					cooldown = System.currentTimeMillis() + 175;
					this.trimColor = 255 << 24 | 0 << 16 | 130 << 8 | 28;
					this.centerColor = 255 << 24 | 0 << 16 | 255 << 8 | 55;
				}
			}
		}
		if(this.visible) {
			this.drawTexture(this.coords, this.tCoords, GuiButton.buttonImage, this.centerColor);
			GL11.glDrawBuffer(GL11.GL_BACK);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			font.drawString(this.x+this.width/2-font.getWidth(this.name)/2, this.y+this.height/2-font.getHeight()/2, this.name, Color.black);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDrawBuffer(GL11.GL_FRONT_AND_BACK);
		}
	}

	/**
	 * a helper method to figure out when the mouse is in the button's bounds
	 * @return whether the mouse is in this button's bounds
	 */
	public boolean isMouseInBounds() {
		return this.isMouseInBounds(this.mouseX, this.mouseY);
	}

	/**
	 * a helper method to figure out when the mouse is in the button's bounds
	 * @return whether the mouse is in this button's bounds
	 */
	public boolean isMouseInBounds(int mouseX, int mouseY) {
		return (mouseX > this.x && mouseX < this.x+this.width && mouseY > this.y && mouseY < this.y+this.height) && this.enabled && this.visible;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enbled) {
		this.enabled = enbled;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
