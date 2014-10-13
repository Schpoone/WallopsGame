package com.wallops.java.gui;

/**
 * An interface to help with any collections of renderable Objects that may be needed for the game.
 * 
 * @author PureChaose
 *
 */
public interface IRenderable {
	/**
	 * Renders this Object as per its implementation, at its x and y coordinates, with its width and height, and Texture, if it has one
	 */
	public void render();
}
