package com.wallops.java.overworld;

import java.io.File;

import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

public class Player {
	
	private Texture sprite;
	
	public Player() {
		sprite = Game.textureManager.getTexture(new ResourcePath(ResourcePath.resourceDir+File.separator+"img"+File.separator+"overworld"+File.separator+"walking_spritesheet.jpg"));
	}

}
