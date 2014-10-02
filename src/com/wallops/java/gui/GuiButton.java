package com.wallops.java.gui;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class GuiButton extends Gui {

	private int mouseX;
	private int mouseY;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected String name;
	private boolean enabled;
	private boolean visible;
	private TrueTypeFont font;
	private int trimColor;
	private int centerColor;
	/** time when button should stop being rendered as "pushed" after it is clicked (175 milis after it is clicked, by default) */
	private long cooldown;

	/**
	 * creates a new button at a specified location, with a certain size and text to display
	 * @param x left bound of button
	 * @param y lower bound of button
	 * @param width width of the button
	 * @param height height of the button
	 * @param name name of the button (to be displayed)
	 */
	public GuiButton(int x, int y, int width, int height, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.enabled = true;
		this.visible = true;
		this.font = new TrueTypeFont(new Font(Font.MONOSPACED, Font.PLAIN, this.height/4), false);
	}

	/**
	 * renders this button in its stored location with color (depending on where the 
	 * mouse is, and whether it's clicked)
	 */
	public void renderButton() {
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

	public void resize() {}
	
	public void setSize(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = new TrueTypeFont(new Font(Font.MONOSPACED, Font.PLAIN, this.height/4), false);
	}

}
