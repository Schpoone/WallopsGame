package com.wallops.java.overworld;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

public class PlayerSheet extends Spritesheet {
	
	public static final int WALK = 0;
	public static final int BIKE = 1;
	public static final int FISH = 2;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public PlayerSheet() {
		super();
	}

	@Override
	public void animate(int anim, int dir) {
		GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 5, 1+17*dir, 1+17*anim, 16, 16, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)Game.textureManager.getTexture(new ResourcePath(ResourcePath.overworldImgDir+"player_spritesheet")));
	}

}
