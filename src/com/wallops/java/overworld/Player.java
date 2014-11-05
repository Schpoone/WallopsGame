package com.wallops.java.overworld;

import java.io.File;

import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

public class Player {
	
	private Texture sprite;
	private Spritesheet animations;
	private Location loc;
	
	public Player() {
		sprite = Game.textureManager.getTexture(new ResourcePath(ResourcePath.resourceDir+File.separator+"img"+File.separator+"overworld"+File.separator+"walking_spritesheet.jpg"));
	}

	public Texture getSprite() {
		return sprite;
	}

	public Spritesheet getAnimations() {
		return animations;
	}

	public Location getLoc() {
		return loc;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public void setAnimations(Spritesheet animations) {
		this.animations = animations;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

}
