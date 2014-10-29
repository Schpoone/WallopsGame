package com.wallops.java.overworld;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

public abstract class Tile {

	private Texture tileImg;

	public Tile() {
		this.tileImg = null;
		loadTileTex();
	}

	public Texture getTileImg() {
		return tileImg;
	}

	public void setTileImg(Texture tileImg) {
		this.tileImg = tileImg;
	}

	public void loadTileTex() {
		String tileName = this.getClass().getName();
		tileName = tileName.substring(tileName.lastIndexOf("." + 1));
		tileImg = Game.textureManager.getTexture(new ResourcePath(ResourcePath.resourceDir+"overworld"+File.separator+tileName.toLowerCase() + ".jpg"));
	}
	
	public abstract void requestMoveIn(Player p) {
		
	}

}