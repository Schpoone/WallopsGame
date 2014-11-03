package com.wallops.java.overworld.tiles;

import com.wallops.java.overworld.Player;

public class LeftGrassLedgeTile extends Tile {

	public LeftGrassLedgeTile() {
		super();
	}

	@Override
	public void requestMoveIn(Player p) {
		//allows if the player is moving in from the right
	}

	@Override
	public void requestInteraction(Player p) {
		//does nothing
	}
	
}
