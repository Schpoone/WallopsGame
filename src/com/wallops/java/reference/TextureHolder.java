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

import com.wallops.java.event.Game;

public class TextureHolder {
	private Map<String,Texture> textureMap;
	private Logger logger = LogManager.getLogger("TextureHolder");

	public TextureHolder() {
		textureMap = new HashMap<String,Texture>();
	}

	public void load() {
		try {
			File f = File.createTempFile("temp", ".tmp");
			String path = f.getAbsolutePath();
			File parent = new File("src/com/wallops/resources/img");
			loadDir(parent);
		} catch (IOException e) {
			logger.fatal(e.getMessage());
		}

	}

	private void loadDir(File parent) {
		for(File child : parent.listFiles()) {
			if(child.isFile()) {
				try {
					String childPath;
					childPath = child.getCanonicalPath();
					String childExtension = childPath.substring(childPath.lastIndexOf(".")+1);
					if(childExtension.equals("jpg") || childExtension.equals("png")) {
						textureMap.put(childPath, TextureLoader.getTexture(childExtension, ResourceLoader.getResourceAsStream(childPath)));
						logger.info("Loaded "+childPath+" successfully.");
					}
				} catch (IOException e) {
					logger.fatal(e.getMessage());
				}
			} else if(child.isDirectory())
				loadDir(child);

		}
	}
	
	public Texture get(String path) {
		return textureMap.get(path);
	}
}