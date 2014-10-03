package com.wallops.java.gui;

import java.awt.Font;

import org.lwjgl.opengl.Display;

public abstract class Resizeable extends Gui implements IRenderable {
	protected float xScale;
	protected float yScale;
	protected float widthScale;
	protected float heightScale;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;

	public Resizeable(float xScale, float yScale, float widthScale, float heightScale) {
		this.xScale = xScale;
		this.yScale = yScale;
		this.widthScale = widthScale;
		this.heightScale = heightScale;
	}

	public void resize() {
		this.x = (int) (xScale*Display.getWidth());
		this.y = (int) (yScale*Display.getHeight());
		this.width = (int) (widthScale*Display.getWidth());
		this.height = (int) (heightScale*Display.getHeight());
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
