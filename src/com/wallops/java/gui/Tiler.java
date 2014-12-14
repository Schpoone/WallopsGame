package com.wallops.java.gui;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

public class Tiler extends Gui implements IRenderable {
	private ResourcePath texturePath;
	private float scaleFactor;
	
	public Tiler(ResourcePath path) {
		this(path,1F);
	}
	
	public Tiler(ResourcePath path, float scaleFactor) {
		texturePath=path;
		this.scaleFactor=scaleFactor;
	}

	@Override
	public void render() {
		Texture t = Game.textureManager.getTexture(texturePath);
		for(int x = 0; x<Game.game.displayWidth;x+=t.getImageWidth()*scaleFactor)
			for(int y = 0; y<Game.game.displayHeight;y+=t.getImageHeight()*scaleFactor)
				this.drawTexture(x, y, (int)(x+t.getImageWidth()*scaleFactor), (int)(y+t.getImageHeight()*scaleFactor), t);
	}

}
