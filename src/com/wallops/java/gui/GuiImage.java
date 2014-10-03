package com.wallops.java.gui;

public class GuiImage extends Resizeable implements IRenderable {
	private int color;

	public GuiImage(float xScale, float yScale, float widthScale, float heightScale, int color) {
		super(xScale, yScale, widthScale, heightScale);
		this.color = color;
	}

	@Override
	public void render() {
		this.drawRectangle(this.x, this.y, this.x+this.width, this.y+this.height, color);
	}

}
