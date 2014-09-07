package com.wallops.java.gui;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.wallops.java.event.Game;

public class GuiButton extends Gui {

	private int x;
	private int y;
	private int width;
	private int height;
	private String name;
	private boolean enabled;
	private boolean visible;
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
	}

	/**
	 * renders this button in its stored location with color (depending on where the 
	 * mouse is, and whether it's clicked)
	 */
	public void renderButton() {
		int color1 = 255 << 24 | 0 << 16 | 130 << 8 | 28;
		int color2 = 255 << 24 | 0 << 16 | 255 << 8 | 55;
		if (System.currentTimeMillis() >= cooldown) {
			// red (means mouse isn't in button's bounds)
			color1 = 255 << 24 | 130 << 16 | 28 << 8 | 0;
			color2 = 255 << 24 | 255 << 16 | 55 << 8 | 0;
			if(isMouseInBounds()) {
				// blue (mouse in button's bounds, but not clicked)
				color1 = 255 << 24 | 0 << 16 | 28 << 8 | 130;
				color2 = 255 << 24 | 0 << 16 | 55 << 8 | 255;
				if(Mouse.isButtonDown(0)) {
					// green (mouse in button's bounds and clicked)
					cooldown = System.currentTimeMillis() + 175;
					color1 = 255 << 24 | 0 << 16 | 130 << 8 | 28;
					color2 = 255 << 24 | 0 << 16 | 255 << 8 | 55;
				}
			}
		}
		if(this.visible) {
			this.drawRectangle(this.x, this.y, this.x+this.width, this.y+this.height, color1); // ARGB
			this.drawRectangle(this.x+7, this.y+7, this.x+this.width-7, this.y+this.height-7, color2); //ARGB
		}
	}

	/**
	 * a helper method to figure out when the mouse is in the button's bounds
	 * @return whether the mouse is in this button's bounds
	 */
	public boolean isMouseInBounds() {
		return Mouse.getX() > this.x && Mouse.getX() < this.x+this.width && Mouse.getY() > this.y && Mouse.getY() < this.y+this.height;
	}

}
