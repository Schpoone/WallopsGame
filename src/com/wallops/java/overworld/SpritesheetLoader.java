package com.wallops.java.overworld;

import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

public class SpritesheetLoader {
	
	public static final int SQUARE_SPRITE_DIMEN = 16;
	public static final String WALKING_SPRITES = "walking_spritesheet";
	public static final String BIKING_SPRITES = "biking_spritesheet";
	
	private Texture sheet;//each sheet is a different animation, each row is a different direction
	
	//columns are the frames of each animation
	
	public SpritesheetLoader() {
		this.sheet = null;
	}
	
	public void loadSheet(String sheet) {
		this.sheet = Game.textureManager.getTexture(new ResourcePath(ResourcePath.overworldImgDir+sheet));
	}
	
	public Texture[] getAnimation(int direction) {
		Texture[] anim = new Texture[this.sheet.getTextureWidth()/SpritesheetLoader.SQUARE_SPRITE_DIMEN];
		//get the frames for a certain direction
		//store each frame in the array
		return anim;
	}
	
	public Texture getHoldFrame(int direction) {
		//get the first frame in the direction
		return null;
	}
	
}