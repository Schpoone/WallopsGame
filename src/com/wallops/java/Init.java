package com.wallops.java;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.lwjgl.opengl.*;

import com.wallops.java.event.Battle;
import com.wallops.java.event.Game;
import com.wallops.java.wallopsmon.*;

public class Init {

	public static void main(String[] args) {
		Game wallops = new Game();
		wallops.startup();
		wallops.startGameLoop();
	}

}
