package com.wallops.java.overworld;

public class RightFenceTile extends Tile {

	public RightFenceTile() {
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
