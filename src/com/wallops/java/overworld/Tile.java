package com.wallops.java.overworld;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.wallops.java.event.Game;

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

	public void loadTileTex(String tileName) {
		tileImg = Game.textureManager.getTexture("src/com/wallops/resources/img/overworld/" + tileName.toLowerCase() + ".jpg");
	}

}