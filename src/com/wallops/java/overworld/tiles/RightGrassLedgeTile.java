package com.wallops.java.overworld.tiles;

import com.wallops.java.overworld.Player;

public class RightGrassLedgeTile extends Tile {

	public RightGrassLedgeTile() {
		super();
	}

	@Override
	public void requestMoveIn(Player p) {
		//allows if the player is moving in from the left
	}

	@Override
	public void requestInteraction(Player p) {
		//does nothing
	}
	
}
