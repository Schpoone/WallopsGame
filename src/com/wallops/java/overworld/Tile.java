package com.wallops.java.overworld;

import org.newdawn.slick.opengl.Texture;

public abstract class Tile {
	
	private Texture tileImg;
	
	public Tile() {
		this.tileImg = null;
	}

	public Texture getTileImg() {
		return tileImg;
	}

	public void setTileImg(Texture tileImg) {
		this.tileImg = tileImg;
	}
	
}