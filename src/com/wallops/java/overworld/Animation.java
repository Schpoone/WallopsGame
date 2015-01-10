package com.wallops.java.overworld;

import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;
import com.wallops.java.gui.GuiImage;
import com.wallops.java.gui.IRenderable;

/**
 * This class stores the array of images that are displayed in order to create an animation
 * @author Jason
 *
 */
public class Animation implements IRenderable {
	
	private GuiImage[] anim;
	private boolean isVisible;
	private boolean isAnimating;
	
	public Animation(String animName, int direction) {
		Game.spriteLoader.loadSheet(animName);
		this.anim = Game.spriteLoader.getAnimation(direction);
		this.isVisible = false;
		this.isAnimating = false;
	}
	
	@Override
	public void render() {
		if (!isVisible) return;
		while (isAnimating) {
			//cycle through images to create the animation
		}
		anim[0].drawTexture(x1, y1, x2, y2, t);
	}

}
