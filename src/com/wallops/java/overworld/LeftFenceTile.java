package com.wallops.java.overworld;

public class LeftFenceTile extends Tile {

	public LeftFenceTile() {
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
