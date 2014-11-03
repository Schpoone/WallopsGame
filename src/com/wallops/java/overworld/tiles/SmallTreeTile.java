package com.wallops.java.overworld.tiles;

import com.wallops.java.overworld.Player;

public class SmallTreeTile extends Tile {

	public SmallTreeTile() {
		super();
	}
	
	@Override
	public void requestMoveIn(Player p) {
		//cannot move in
	}

	@Override
	public void requestInteraction(Player p) {
		//headbutt
	}

}
