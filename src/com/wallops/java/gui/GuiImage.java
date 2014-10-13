package com.wallops.java.gui;

import com.wallops.java.event.Game;

/**
 * A {@linkplain Gui} used to render colors, and later images, onto the screen easily in 2D.
 * 
 * @author PureChaose
 *
 */
public class GuiImage extends Gui implements IRenderable {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private boolean visible;
	private int color;

	/**
	 * Creates a new GuiImage with specified ratio between the Display's dimension and its own, the color, packed to an int, to display, and sets it to be visible.
	 * @param xScale The ratio between the x coordinate of this image and the Display's width
	 * @param yScale The ratio between the y coordinate of this image and the Display's height
	 * @param widthScale The ratio between the width of this image and the Display's width
	 * @param heightScale The ratio between the height of this image and the Display's height
	 * @param color The color of the GuiImage, packed into an int in ARGB form
	 * @see GuiImage#GuiImage(int, int, int, int, int)
	 * @see GuiImage#GuiImage(int, int, int, int, int, boolean)
	 */
	public GuiImage(float xScale, float yScale, float widthScale, float heightScale, int color) {
		this((int)(xScale*Game.game.displayWidth), (int)(yScale*Game.game.displayHeight), (int)(widthScale*Game.game.displayWidth), (int)(heightScale*Game.game.displayHeight), color);
	}
	
	/**
	 * Creates a new GuiImage with specified x and y coordinates, width and height, the color, packed to an int, to display, and sets it to be visible.
	 * @param x The x coordinate of this image
	 * @param y The y coordinate of this image
	 * @param width The width of this image
	 * @param height The height of this image
	 * @param color The color of the GuiImage, packed into an int in ARGB form
	 * @see GuiImage#GuiImage(float, float, float, float, int)
	 * @see GuiImage#GuiImage(int, int, int, int, int, boolean)
	 */
	public GuiImage(int x, int y, int width, int height, int color) {
		this(x,y,width,height,color,true);
	}
	
	/**
	 * Creates a new GuiImage with specified x and y coordinates, width and height, the color, packed to an int, to display, and a custom visibility.
	 * @param x The x coordinate of this image
	 * @param y The y coordinate of this image
	 * @param width The width of this image
	 * @param height The height of this image
	 * @param color The color of the GuiImage, packed into an int in ARGB form
	 * @param visible Whether this image is visible or not.
	 * @see GuiImage#GuiImage(float, float, float, float, int)
	 * @see GuiImage#GuiImage(int, int, int, int, int)
	 */
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

	/**
	 * Sets the visibility of the image.
	 * @param visible Whether the image is visible or not.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
