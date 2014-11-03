package com.wallops.java.overworld;

import java.io.File;

import org.newdawn.slick.opengl.Texture;

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
	
	public abstract void requestMoveIn(Player p);
	//called when the player wants to move to this tile
	
	public abstract void requestInteraction(Player p);
	//called when the player presses the action button
	//(usually A on game boy) while facing this tile
}