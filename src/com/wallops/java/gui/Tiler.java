package com.wallops.java.gui;

import org.newdawn.slick.opengl.Texture;

import com.wallops.java.event.Game;
import com.wallops.java.reference.ResourcePath;

public class Tiler extends Gui implements IRenderable {
	private ResourcePath texturePath;
	
	public Tiler(ResourcePath path) {
		texturePath=path;
	}

	@Override
	public void render() {
		Texture t = Game.textureManager.getTexture(texturePath);
		for(int x = 0; x<Game.game.displayWidth;x+=t.getImageWidth())
			for(int y = 0; y<Game.game.displayHeight;y+=t.getImageHeight())
				this.drawTexture(x, y, x+t.getImageWidth(), y+t.getImageHeight(), t);
	}

}
