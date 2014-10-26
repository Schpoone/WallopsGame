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
	private Map<String,Texture> textureMap;
	private Logger logger = LogManager.getLogger("TextureManager");

	public TextureManager() {
		textureMap = new HashMap<String,Texture>();
	}

	public void load() {
		File parent = new File("src/com/wallops/resources/img");
		if(parent.isDirectory())
			loadDir(parent);
	}

	private void loadDir(File parent) {
		String childPath, childExtension;
		for(File child : parent.listFiles()) {
			if(child.isFile()) {
				try {
					childPath = child.getCanonicalPath().substring((child.getCanonicalPath().indexOf("src/com/wallops/resources/img")+1));
					childExtension = childPath.substring(childPath.lastIndexOf(".")+1);
					if(childExtension.equalsIgnoreCase("png")||childExtension.equals("jpg")) {
						textureMap.put(childPath, TextureLoader.getTexture(childExtension, ResourceLoader.getResourceAsStream(childPath)));
						logger.info("Successfully loaded "+childPath);
					}
				} catch (IOException e) {
					logger.fatal(e.getMessage());
				}

			} else if(child.isDirectory())
				loadDir(child);
		} 
	}
	
	public Texture getTexture(String path) {
		return textureMap.get(path);
	}

}
