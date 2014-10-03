package com.wallops.java.gui;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class GuiText extends Resizeable implements IRenderable {
	private String text;
	private Font jfont;
	private TrueTypeFont font;

	public GuiText(float xScale, float yScale, float widthScale, float heightScale, String text) {
		super(xScale, yScale, widthScale, heightScale);
		this.jfont = new Font(Font.MONOSPACED, Font.PLAIN, 1);
		this.text=text;
		this.resize();
	}

	@Override
	public void render() {
		this.drawRectangle(x, y, x+width, y+height, Integer.MAX_VALUE);
		GL11.glDrawBuffer(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		font.drawString(this.x+this.width/2-font.getWidth(this.text)/2, this.y+this.height/2-font.getHeight()/2, this.text, Color.black);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDrawBuffer(GL11.GL_FRONT_AND_BACK);
	}

	@Override
	public void resize() {
		super.resize();
		this.font = new TrueTypeFont(jfont.deriveFont((float)this.height/4), false);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void append(String text) {
		this.text += text;
	}

}
