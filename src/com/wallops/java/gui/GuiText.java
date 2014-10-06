package com.wallops.java.gui;

import java.awt.Font;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.wallops.java.event.Game;

public class GuiText extends Gui implements IRenderable {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	private String text;
	private Font jfont;
	private TrueTypeFont font;
	private Logger logger;

	public GuiText(float xScale, float yScale, float widthScale, float heightScale, String text) {
		this((int)(xScale*Game.game.displayWidth), (int)(yScale*Game.game.displayHeight), (int)(widthScale*Game.game.displayWidth), (int)(heightScale*Game.game.displayHeight), text);
	}

	public GuiText(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.logger = LogManager.getLogger(this.text.split(" ")[0]+".txt");
		this.visible = true;
		this.makeFontRenderer();
	}

	public void makeFontRenderer() {
		this.jfont = new Font(Font.MONOSPACED, Font.PLAIN, 1);
		this.font = new TrueTypeFont(this.jfont, false);
		String[] lines = this.text.split("\n");
		float widthMaxFontSize = Float.MAX_VALUE;
		float heightMaxFontSize = (float)(this.height)/(lines.length*(float)(this.font.getHeight(this.text)));
		for(String line : lines) {
			if(line == null || line.isEmpty())
				continue;
			float tempWidthMaxFontSize = (float)(this.width)/(float)(this.font.getWidth(line));
			widthMaxFontSize = widthMaxFontSize > tempWidthMaxFontSize ? tempWidthMaxFontSize : widthMaxFontSize;
			logger.debug(line+" "+widthMaxFontSize);
		}
		float maxFontSize = widthMaxFontSize > heightMaxFontSize ? heightMaxFontSize : widthMaxFontSize;
		this.font = new TrueTypeFont(jfont.deriveFont(maxFontSize), false);
	}

	@Override
	public void render() {
		if(this.visible) {
			this.drawRectangle(x, y, x+width, y+height, Integer.MAX_VALUE);
			GL11.glDrawBuffer(GL11.GL_BACK);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			String[] lines = text.split("\n");
			for(int i=0; i<lines.length;i++)
				font.drawString(this.x, this.y+font.getHeight()*i, lines[i], Color.black);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDrawBuffer(GL11.GL_FRONT_AND_BACK);
		}
	}

	public void setText(String text) {
		this.text = text;
		this.makeFontRenderer();
	}

	public String getText() {
		return this.text;
	}

	public void append(String text) {
		this.text += text;
		this.makeFontRenderer();
	}

}
