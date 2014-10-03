package com.wallops.java;

import com.wallops.java.event.Game;
import com.wallops.java.gui.GuiGameScreen;
import com.wallops.java.wallopsmon.MudDogWhelk;

public class Init {

	public static void main(String[] args) {
		Game wallops = new Game();
		wallops.startup();
		wallops.startGameLoop();
	}

}
