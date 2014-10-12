package com.wallops.java.event;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import com.wallops.java.gui.Gui;
import com.wallops.java.gui.GuiGameScreen;
import com.wallops.java.gui.GuiMainMenu;
import com.wallops.java.gui.GuiScreen;
import com.wallops.java.reference.MouseHandler;

/**
 * The game object for Wallopsmon. Here all initialization and registration will take
 * place.
 * 
 * @author PureChaose
 *
 */
public class Game {

	/** used for logging anything that needs to be traced to the Game object */
	public static Logger logger = LogManager.getLogger("Game");
	
	private boolean running;
	public int displayHeight;
	public int displayWidth;
	private GuiScreen activeGui;
	public static Game game;
	public static MouseHandler mouseHandler = new MouseHandler();
	
	/** for funky mac related weirdness that'd have to be dealt with */
	public static final boolean isRunningOnMac = System.getProperty("os.name").toLowerCase().contains("mac");
	
	/**
	 * creates a display and initializes game loop.
	 * TODO: have this varied based on command line arguments from launcher
	 */
	public Game() {
		game = this;
		this.displayHeight = 480;
		this.displayWidth = 720;
	}
	
	public void startGameLoop() {
		this.logger.log(Level.INFO, "Starting main game loop.");
		this.running = true;
		this.activeGui = new GuiGameScreen(game);
		this.displayHeight = Display.getHeight();
		this.displayWidth = Display.getWidth();
		// Game loop, methinks
		try {
			while(!Display.isCloseRequested() && this.running) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				this.activeGui.render();
				if(Mouse.isCreated()&&Mouse.next())
					this.activeGui.handleMouse();
				Display.update();
				if(Display.getHeight() != this.displayHeight || Display.getWidth() != this.displayWidth) {
					GL11.glMatrixMode(GL11.GL_PROJECTION);
					GL11.glLoadIdentity();
					
					this.displayHeight = Display.getHeight();
					this.displayWidth = Display.getWidth();
					GL11.glViewport(0, 0, displayWidth, displayHeight);
					GL11.glScissor(0, 0, displayWidth, displayHeight);
					GL11.glOrtho(0,displayWidth,displayHeight,0,-1,1);
					this.activeGui.resize();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.cleanup();
		}
	}
	
	/**
	 * creates a new instance of LWJGL's Display class, initializes OpenGL
	 */
	public void startup() {
		logger.log(Level.INFO, "Initializing graphics...");
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		try {
			Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
			Display.create();
			Display.setResizable(true);
			Display.setVSyncEnabled(true);
			Display.setTitle("Wallopsmon");
		} catch (LWJGLException e) {
			logger.fatal("Crashed while trying to initialize display: ", e);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION); // setting focused matrix to projection matrix
		GL11.glLoadIdentity(); // clearing the projection matrix
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		logger.log(Level.INFO, "Graphics initialized: lwjgl 2.9.1, see http://www.lwjgl.org/");
	}
	
	
	/**
	 * shuts down the game
	 */
	public void shutdown() {
		this.running = false;
		this.displayHeight = Display.getHeight();
		this.displayWidth = Display.getWidth();
	}
	
	/**
	 * cleans up the game before closing (or caught crashes)
	 */
	private void cleanup() {
		logger.log(Level.INFO, "Cleaning up game...");
		
		Display.destroy();
		
		logger.log(Level.INFO, "Game shut down completely.");
	}
	
	public void setScreen(GuiScreen screen) {
		this.activeGui = screen;
	}
}
