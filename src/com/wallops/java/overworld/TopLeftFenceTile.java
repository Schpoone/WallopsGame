package com.wallops.java.overworld;

public class TopLeftFenceTile extends Tile {

	public TopLeftFenceTile() {
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
