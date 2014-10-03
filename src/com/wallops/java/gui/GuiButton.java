package com.wallops.java.gui;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class GuiButton extends Resizeable implements IRenderable {

	private int mouseX;
	private int mouseY;
	protected String name;
	private boolean enabled;
	private Font jfont;
	private TrueTypeFont font;
	private int trimColor;
	private int centerColor;
	/** time when button should stop being rendered as "pushed" after it is clicked (175 milis after it is clicked, by default) */
	private long cooldown;

	/**
	 * creates a new button at a specified location, with a certain size and text to display
	 * @param xScale the ratio between the Display width and the button's x coordinate
	 * @param yScale the ratio between the Display height and the button's y coordinate
	 * @param widthScale the ratio between the Display width and the button's width
	 * @param heightScale the ratio between the Display size and the button's height
	 * @param name name of the button (to be displayed)
	 */
	public GuiButton(float xScale, float yScale, float widthScale, float heightScale, String name) {
		super(xScale, yScale, widthScale, heightScale);
		this.name = name;
		this.enabled = true;
		this.visible = true;
		this.jfont = new Font(Font.MONOSPACED, Font.PLAIN, 1);
		this.resize();
	}

	/**
	 * renders this button in its stored location with color (depending on where the 
	 * mouse is, and whether it's clicked)
	 */
	@Override
	public void render() {
		this.mouseX = Mouse.getX();
		this.mouseY = Display.getHeight() - Mouse.getY();
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
			this.drawRectangle(this.x, this.y, this.x+this.width, this.y+this.height, this.trimColor); // ARGB
			this.drawRectangle(this.x+7, this.y+7, this.x+this.width-7, this.y+this.height-7, this.centerColor); //ARGB
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
		return mouseX > this.x && mouseX < this.x+this.width && mouseY > this.y && mouseY < this.y+this.height;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	@Override
	public void resize() {
		super.resize();
		this.font = new TrueTypeFont(jfont.deriveFont((float)this.height/4), false);
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enbled) {
		this.enabled = enbled;
	}

}
