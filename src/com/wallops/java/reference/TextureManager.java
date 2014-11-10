package com.wallops.java.reference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager {
	private Map<ResourcePath,Texture> textureMap;
	private Logger logger = LogManager.getLogger("TextureManager");

	public TextureManager() {
		textureMap = new HashMap<ResourcePath,Texture>();
	}

	public void load() {
		File parent = new File(ResourcePath.resourceDir);
		if(parent.isDirectory())
			loadDir(parent, true);
	}
	
	private void loadFile(File childFile) {
		try {
			ResourcePath child = new ResourcePath(childFile);
			if(child.getExtension().equalsIgnoreCase("png")||child.getExtension().equals("jpg")) {
				Texture textureToPut = TextureLoader.getTexture(child.getExtension(), ResourceLoader.getResourceAsStream(child.getName()));
				if(textureToPut != null) {
					textureMap.put(child, textureToPut);
					logger.debug("Successfully loaded "+child.getName()); // verbose intentionally. might switch this to a less visible log level if it's too cluttered.
				} else
					logger.warn("Failed to load "+child.getName());
			}
		} catch (IOException e) {
			logger.fatal("Error while loading textures: ",e);
		}
	}

	private void loadDir(File parent, boolean recursive) {
		for(File childFile : parent.listFiles()) {
			if(childFile.isFile()) {
				loadFile(childFile);
			} else if(childFile.isDirectory() && recursive)
				loadDir(childFile, recursive);
		} 
	}

	public Texture getTexture(ResourcePath path) {
		if(!textureMap.containsKey(path))
			loadFile(new File(path.getAbsolutePath()));
		return textureMap.get(path);
	}

}
