package com.wallops.java.gui;

import java.awt.Font;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.wallops.java.event.Game;

/**
 * A renderable Object used to display Strings within certain defined boundaries
 * 
 * @author PureChaose
 *
 */
public class GuiText extends Gui implements IRenderable {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	private String text;
	private Font jfont;
	private TrueTypeFont font;
	private Logger logger;

	/**
	 * Creates a new GuiText with specified ratio between the Display's dimension and its own, and the text to display in it.
	 * @param xScale The ratio between the x coordinate of this GuiText and the Display's width
	 * @param yScale The ratio between the y coordinate of this GuiText and the Display's height
	 * @param widthScale The ratio between the width of this GuiText and the Display's width
	 * @param heightScale The ratio between the height of this GuiText and the Display's height
	 * @param text The text to be displayed in this GuiText
	 */
	public GuiText(float xScale, float yScale, float widthScale, float heightScale, String text) {
		this((int)(xScale*Game.game.displayWidth), (int)(yScale*Game.game.displayHeight), (int)(widthScale*Game.game.displayWidth), (int)(heightScale*Game.game.displayHeight), text);
	}

	/**
	 * Creates a new GuiText with specified x and y coordinates, width and height, and the text to display in it.
	 * @param x The x coordinate of this GuiText
	 * @param y The y coordinate of this GuiText
	 * @param width The width of this GuiText
	 * @param height The height of this GuiText
	 * @param text The text to be displayed in this GuiText
	 */
	public GuiText(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.logger = LogManager.getLogger(this.text.split(" ")[0]+".txt");
		this.visible = true;
		this.makeFontRenderer();
	}

	/**
	 * Resizes the internal font renderer to a size that fits within the GuiText's bounds. 
	 * 
	 * Called automatically when a method in this class updates the text in this GuiText.
	 * @see #setText(String)
	 * @see #append(String)
	 */
	public void makeFontRenderer() {
		this.jfont = new Font(Font.MONOSPACED, Font.PLAIN, 1);
		this.font = new TrueTypeFont(this.jfont, false);
		String[] lines = this.text.split("\n");
		float widthMaxFontSize = Float.MAX_VALUE;
		float heightMaxFontSize = (float)(this.height)/(lines.length*(float)(this.font.getHeight(this.text)));
		for(String line : lines) {
			if(line == null || line.isEmpty())
				continue;
			float tempWidthMaxFontSize = (float)(this.width)/(float)(this.font.getWidth(line));
			widthMaxFontSize = widthMaxFontSize > tempWidthMaxFontSize ? tempWidthMaxFontSize : widthMaxFontSize;
		}
		float maxFontSize = widthMaxFontSize > heightMaxFontSize ? heightMaxFontSize : widthMaxFontSize;
		this.font = new TrueTypeFont(jfont.deriveFont(maxFontSize), false);
	}

	@Override
	public void render() {
		if(this.visible) {
			this.drawRectangle(x, y, x+width, y+height, Integer.MAX_VALUE);
			GL11.glDrawBuffer(GL11.GL_BACK);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			String[] lines = text.split("\n");
			for(int i=0; i<lines.length;i++)
				font.drawString(this.x, this.y+font.getHeight()*i, lines[i], Color.black);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDrawBuffer(GL11.GL_FRONT_AND_BACK);
		}
	}

	/**
	 * Sets the text to be displayed in this GuiText are and resizes the internal font renderer.
	 * @param text The text to be displayed in this GuiText.
	 * @see #makeFontRenderer()
	 * @see #append(String)
	 */
	public void setText(String text) {
		this.text = text;
		this.makeFontRenderer();
	}

	/**
	 * @return The text stored in this GuiText.
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Appends the text stored in this GuiText with a given String and resizes the internal font renderer.
	 * @param text The String to append to the stored text in this GuiText.
	 * @see #makeFontRenderer()
	 * @see #setText(String)
	 */
	public void append(String text) {
		this.text += text;
		this.makeFontRenderer();
	}

}
