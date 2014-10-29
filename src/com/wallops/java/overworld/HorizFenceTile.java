package com.wallops.java.overworld;

public class HorizFenceTile extends Tile {

	public HorizFenceTile() {
		super();
	}

	@Override
	public void requestMoveIn(Player p) {
		//cannot move in
	}

	@Override
	public void requestInteraction(Player p) {
		//does nothing
	}

}
