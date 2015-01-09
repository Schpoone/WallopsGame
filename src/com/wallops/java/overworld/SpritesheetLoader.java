package com.wallops.java.overworld;

import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.gui.GuiImage;
import com.wallops.java.reference.ResourcePath;

public class SpritesheetLoader {
	
	public static final int SQUARE_SPRITE_DIMEN = 16;
	public static final String WALKING_SPRITES = "walking_spritesheet";
	public static final String BIKING_SPRITES = "biking_spritesheet";
	
	private Texture sheet;
	
	public SpritesheetLoader() {
		this.sheet = null;
	}
	
	public SpritesheetLoader(String path) {
		this.sheet = Game.textureManager.getTexture(new ResourcePath(path));
	}

	public Texture getSheet() {
		return sheet;
	}

	public void setSheet(Texture sheet) {
		this.sheet = sheet;
	}
	
	public GuiImage getSprite(int r, int c) {
		return null;
	}
	
}