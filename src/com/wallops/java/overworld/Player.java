package com.wallops.java.overworld;

import java.io.File;

import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;
import com.wallops.java.wallopsmon.Wallopsmon;

public class Player {
	
	private Texture sprite;
	private Spritesheet animations;
	private Location loc;
	private Pokedex dex;
	private Wallopsmon[] team;
	private Backpack pack;
	//player card
	
	public Player() {
		sprite = Game.textureManager.getTexture(new ResourcePath(ResourcePath.resourceDir+File.separator+"img"+File.separator+"overworld"+File.separator+"walking_spritesheet.jpg"));
		animations = new PlayerSheet();
		loc = new Location(0, 0);
		dex = new Pokedex();
		team = new Wallopsmon[6];
		pack = new Backpack();
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
