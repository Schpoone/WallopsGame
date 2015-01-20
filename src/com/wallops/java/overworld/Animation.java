package com.wallops.java.overworld;

import org.newdawn.slick.opengl.Texture;
import com.wallops.java.event.Game;

/**
 * This class stores the array of images that are displayed in order to create an animation
 * @author Jason
 *
 */
public class Animation {
	
	private Texture[] anim;
	
	public Animation() {
		anim = null;
	}
	
	public Animation(Texture[] anim) {
		this.anim = anim;
	}
	
	public Texture[] getAnimation() {
		return anim;
	}
	
	public void setAnimation(Texture[] anim) {
		this.anim = anim;
	}
	
	public Texture getHoldFrame(int direction) {
		//get the first frame in the direction
		return null;
	}
	
	//right now, this class is pretty useless
	
}
