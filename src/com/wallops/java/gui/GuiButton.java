package com.wallops.java.gui;

import org.lwjgl.input.Mouse;

public class GuiButton extends Gui {

	private int x;
	private int y;
	private int width;
	private int height;
	private String name;
	private boolean enabled;
	private boolean visible;

	public GuiButton(int x, int y, int width, int height, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.enabled = true;
		this.visible = true;
	}

	public void renderButton() {
		int color1 = 255 << 24 | 130 << 16 | 28 << 8 | 0;
		int color2 = 255 << 24 | 255 << 16 | 55 << 8 | 0;
		if(isMouseInBounds()) {
			color1 = 255 << 24 | 0 << 16 | 28 << 8 | 130;
			color2 = 255 << 24 | 0 << 16 | 55 << 8 | 255;
			if(Mouse.isButtonDown(0)) {
				color1 = 255 << 24 | 0 << 16 | 130 << 8 | 28;
				color2 = 255 << 24 | 0 << 16 | 255 << 8 | 55;
			}
		}
		if(this.visible) {
			this.drawRectangle(this.x, this.y, this.x+this.width, this.y+this.height, color1); // ARGB
			this.drawRectangle(this.x+7, this.y+7, this.x+this.width-7, this.y+this.height-7, color2); //ARGB
		}
	}
	
	public boolean isMouseInBounds() {
		return Mouse.getX() > this.x && Mouse.getX() < this.x+this.width && Mouse.getY() > this.y && Mouse.getY() < this.y+this.height;
	}

}
