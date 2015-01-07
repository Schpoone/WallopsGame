package com.wallops.java.overworld;

import com.wallops.java.gui.GuiImage;
import com.wallops.java.gui.IRenderable;

public class Animation implements IRenderable {
	
	private GuiImage[][] anim;
	
	public Animation(String animName) {
		anim = new GuiImage[4][];
	}
	
	@Override
	public void render() {
		
	}

}
