package com.wallops.java.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;

public class Gui {
	
	/**
	 * draws a horizontal line, one pixel high from the specified starting point to a specified 
	 * ending point with a designated color
	 * @param x1 starting point's x
	 * @param y1 starting and ending points' y
	 * @param x2 ending point's x
	 * @param color the line's color packed into an int
	 */
	protected void drawHorizontalLine(int x1, int y1, int x2, int color) {
		
		if(x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		
		drawRectangle(x1, y1, x2+1, y1+1, color);
	}
	
	/**
	 * draws a vertical line, one pixel wide from the specified starting point to a specified 
	 * ending point with a designated color
	 * @param x1 starting and ending points' x
	 * @param y1 starting point's y
	 * @param y2 ending point's y
	 * @param color the line's color packed into an int
	 */
	protected void drawVerticalLine(int x1, int y1, int y2, int color) {
		
		// assigns the starting point's y to be the 
		if(y1 > y2) {
			int temp = y1;
			y2 = y1;
			y2 = temp;
		}
		
		drawRectangle(x1, y1, x1+1, y2+1, color);
	}
	
	/**
	 * draws a rectangle by designating its opposite corners' coordinates and its color
	 * @param x1 starting point's x
	 * @param y1 starting point's y
	 * @param x2 ending point's x
	 * @param y2 ending point's y
	 * @param color the rectangle's color packed into an int
	 */
	public void drawRectangle(int x1, int y1, int x2, int y2, int color) {
		
		// 
		if(x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		
		if(y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		// unpacking the color int RGBA
		float alpha = (float)(color >> 24 & 255) / 255.0F;
		float red = (float)(color >> 16 & 255) / 255.0F;
		float green = (float)(color >> 8 & 255) / 255.0F;
		float blue = (float)(color & 255) / 255.0F;
		Tesselator tess = Tesselator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(red, green, blue, alpha);
		tess.addVertex((double)x1, (double)y2, 0.0D);
		tess.addVertex((double)x2, (double)y2, 0.0D);
		tess.addVertex((double)x2, (double)y1, 0.0D);
		tess.addVertex((double)x1, (double)y1, 0.0D);
		tess.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
