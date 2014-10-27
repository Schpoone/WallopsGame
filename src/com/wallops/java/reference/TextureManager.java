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
	String s = File.separator;
	File parent = new File("src"+s+"com"+s+"wallops"+s+"resources"+s+"img"+s);
	if(parent.isDirectory())
	    loadDir(parent);
    }

    private void loadDir(File parent) {
	String childPath, childExtension;
	String s = System.getProperty("file.separator");
	String imgDirPath = "src"+s+"com"+s+"wallops"+s+"resources"+s+"img"+s;
	for(File child : parent.listFiles()) {
	    if(child.isFile()) {
		try {
		    childPath = child.getCanonicalPath().substring(child.getCanonicalPath().indexOf(imgDirPath));
		    childExtension = childPath.substring(childPath.lastIndexOf(".")+1);
		    if(childExtension.equalsIgnoreCase("png")||childExtension.equals("jpg")) {
			textureMap.put(childPath, TextureLoader.getTexture(childExtension, ResourceLoader.getResourceAsStream(childPath)));
			logger.info("Successfully loaded "+childPath); // verbose intentionally. might switch this to a less visible log level if it's too cluttered.
		    }
		} catch (IOException e) {
		    logger.fatal("Error while loading textures: ",e);
		}

	    } else if(child.isDirectory())
		loadDir(child);
	} 
    }

    public Texture getTexture(String path) {
	return textureMap.get(path);
    }

}
