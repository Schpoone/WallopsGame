package com.wallops.java.overworld;

public class PlayerSheet extends Spritesheet {
	
	public static final int WALK = 1;
	public static final int BIKE = 2;
	public static final int FISH = 3;
	
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;
	
	public PlayerSheet() {
		super();
	}

	@Override
	public void animate(int animation, int direction) {
		
	}

}
