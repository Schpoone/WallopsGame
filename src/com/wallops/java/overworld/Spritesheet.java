package com.wallops.java.overworld;

import org.newdawn.slick.opengl.Texture;

public abstract class Spritesheet {
	
	private Texture sheet;
	
	public Spritesheet() {
		this.sheet = null;
	}

	public Texture getSheet() {
		return sheet;
	}

	public void setSheet(Texture sheet) {
		this.sheet = sheet;
	}
	
	public abstract void animate(int animation, int direction);
	
}