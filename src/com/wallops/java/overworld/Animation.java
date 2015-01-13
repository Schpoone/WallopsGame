package com.wallops.java.overworld;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.gui.GuiImage;
import com.wallops.java.gui.IRenderable;

/**
 * This class stores the array of images that are displayed in order to create an animation
 * @author Jason
 *
 */
public class Animation {
	
	private Texture[] anim;
	
	public Animation(String animName, int direction) {
		Game.spriteLoader.loadSheet(animName);
		this.anim = Game.spriteLoader.getAnimation(direction);
	}

}
