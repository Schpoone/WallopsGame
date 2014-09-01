package com.wallops.java.event;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.wallops.java.gui.Gui;
import com.wallops.java.gui.GuiMainMenu;
import com.wallops.java.gui.GuiScreen;

/**
 * The game object for Wallopsmon. Here all initialization and registration will take
 * place.
 * 
 * @author PureChaose
 *
 */
public class Game {

	/** used for logging anything that needs to be traced to the Game object */
	public Logger logger = LogManager.getLogger();
	
	private int displayHeight;
	private int displayWidth;
	private GuiScreen activeGui;
	private Game game;
	
	/**
	 * creates a display and initializes game loop.
	 * TODO: have this varied based on command line arguments
	 */
	public Game() {
		// all messages will temporarily be logged under ERROR, until a configuration is added
		logger.error("^Ignore that. I'll fix it later. ~Pure");
		this.game = this;
		this.displayHeight = 480;
		this.displayWidth = 720;
		this.activeGui = new GuiMainMenu(game);
	}
	
	public void startGameLoop() {
		logger.log(Level.ERROR, "Starting main game loop.");
		// Game loop, methinks
		try {
			while(!Display.isCloseRequested()) {
				this.activeGui.renderScreen();
				Display.update();
			}
		} catch (Exception e) {
			
		} finally {
			cleanup();
		}
	}
	
	/**
	 * creates a new instance of LWJGL's Display class, initializes OpenGL
	 */
	public void startup() {
		logger.log(Level.ERROR, "Initializing graphics...");
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		try {
			Display.setDisplayMode(new DisplayMode((int)rect.getWidth(), (int)rect.getHeight()));
			Display.create();
		} catch (LWJGLException e) {
			logger.fatal("Crashed while trying to initialize display: ", e);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION); // setting focused matrix to projection matrix
		GL11.glLoadIdentity(); // clearing the projection matrix
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		logger.log(Level.ERROR, "Graphics initialized: lwjgl 2.9.1, see http://www.lwjgl.org/");
	}
	
	/**
	 * cleans up the game before closing (or caught crashes)
	 */
	private void cleanup() {
		logger.log(Level.ERROR, "Cleaning up game...");
		
		Display.destroy();
		
		logger.log(Level.ERROR, "Game shut down completely.");
	}
}
