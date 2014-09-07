package com.wallops.java.gui;

import org.lwjgl.opengl.Display;

public class GuiScreen extends Gui {
	
	public void renderScreen() {
		this.drawRectangle(0, 0, Display.getWidth(), Display.getHeight(), Integer.MAX_VALUE);
	}
}
