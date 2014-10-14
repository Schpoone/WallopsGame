package com.wallops.java;

import com.wallops.java.event.Game;

public class Init {

	public static void main(String[] args) {
		Game wallops = new Game();
		wallops.startup();
		wallops.startGameLoop();
	}

}