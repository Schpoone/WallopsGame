package com.wallops.java.reference;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.wallops.java.event.Game;

public class MouseHandler {
	
	public MouseHandler() {}
	
	public static int getX() {
		return (int)(Mouse.getX() * (Display.getWidth()/(float)(Game.game.displayWidth)));
	}
	
	public static int getY() {
		return (int)(Display.getHeight()-(Mouse.getY() * (Display.getHeight()/(float)(Game.game.displayHeight))));
	}
	
	public static int getEventX() {
		return (int)(Mouse.getEventX() * (Display.getWidth()/(float)(Game.game.displayWidth)));
	}
	
	public static int getEventY() {
		return (int)(Display.getHeight()-(Mouse.getEventY() * (Display.getHeight()/(float)(Game.game.displayHeight))));
	}
	

}
