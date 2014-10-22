package com.wallops.java.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * A class used for easily rendering for its subclasses.
 * 
 * @author PureChaose
 *
 */
public class Gui {

	private Logger logger = LogManager.getLogger("Gui");
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
	 * @see Gui#drawTexture(int, int, int, int, Texture)
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
		tess.startDrawingQuads();
		tess.addVertex((double)x1, (double)y2, 0.0D);
		tess.addVertex((double)x2, (double)y2, 0.0D);
		tess.addVertex((double)x2, (double)y1, 0.0D);
		tess.addVertex((double)x1, (double)y1, 0.0D);
		tess.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	//i think you mean bottom-right for @param y2
	/**
	 * Draws a given {@linkplain Texture} in a given location with a given size. 
	 * @param x1 The x coordinate top-left point of where the {@linkplain Texture} should be rendered
	 * @param y1 The y coordinate top-left point of where the {@linkplain Texture} should be rendered
	 * @param x2 The x coordinate bottom-right point of where the {@linkplain Texture} should be rendered
	 * @param y2 The y coordinate bottom-left point of where the {@linkplain Texture} should be rendered
	 * @param t The {@linkplain Texture} to be rendered
	 * @see Gui#drawRectangle(int, int, int, int, int)
	 */
	public void drawTexture(int x1, int y1, int x2, int y2, Texture t) {

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
		float[] tCoords = {0,1};
		int[][] coords = {{x1,x2},{y1,y2}};
		this.drawTexture(coords, tCoords, t);
		
	}
	
	public void drawTexture(int[][] coords, float[] tCoords, Texture t) {
		this.drawTexture(coords, tCoords, t, 255,255,255,255);
	}
	
	public void drawTexture(int[][] coords, float[] tCoords, Texture t, int color) {
		this.drawTexture(coords, tCoords, t, color >> 24 & 255, color >> 16 & 255, color >> 8 & 255, color & 255);
	}

	public void drawTexture(int[][] coords, float[] tCoords, Texture t, int alpha, int red, int green, int blue) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(red/255F, green/255F, blue/255F, alpha/255F);
		t.bind();
		//these two values are used because of how LWJGL stores images. 
		float uMod = (float)(t.getImageWidth())/t.getTextureWidth();
		float vMod = (float)(t.getImageHeight())/t.getTextureHeight();

		double ptX1,ptY1,ptX2,ptY2;
		float u1,v1,u2,v2;
		Tesselator tess = Tesselator.instance;
		tess.startDrawingQuads();
		for(int i=0;i+1<tCoords.length;i++) 
			for(int j=0;j+1<tCoords.length;j++) {
				u1=tCoords[i]*uMod;
				v1=tCoords[j]*vMod;
				u2=tCoords[i+1]*uMod;
				v2=tCoords[j+1]*vMod;
				ptX1=coords[0][i];
				ptY1=coords[1][j];
				ptX2=coords[0][i+1];
				ptY2=coords[1][j+1];
				tess.addVertexTex(ptX1, ptY1, 0.0D, u1, v1);
				tess.addVertexTex(ptX2, ptY1, 0.0D, u2, v1);
				tess.addVertexTex(ptX2, ptY2, 0.0D, u2, v2);
				tess.addVertexTex(ptX1, ptY2, 0.0D, u1, v2);
			}
		tess.draw();
	}
}
