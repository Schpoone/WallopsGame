package com.wallops.java.overworld;

import org.newdawn.slick.opengl.Texture;

public class Spritesheet {
	
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
	
}