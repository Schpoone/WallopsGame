package com.wallops.java.gui;

import com.wallops.java.event.Game;

public class GuiImage extends Gui implements IRenderable {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private boolean visible;
	private int color;

	public GuiImage(float xScale, float yScale, float widthScale, float heightScale, int color) {
		this((int)(xScale*Game.game.displayWidth), (int)(yScale*Game.game.displayHeight), (int)(widthScale*Game.game.displayWidth), (int)(heightScale*Game.game.displayHeight), color);
	}
	
	public GuiImage(int x, int y, int width, int height, int color) {
		this(x,y,width,height,color,true);
	}
	
	public GuiImage(int x, int y, int width, int height, int color, boolean visible) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.visible = visible;
	}

	@Override
	public void render() {
		if(this.visible)
			this.drawRectangle(this.x, this.y, this.x+this.width, this.y+this.height, color);
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
