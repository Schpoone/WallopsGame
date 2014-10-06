package com.wallops.java.gui;

import org.lwjgl.opengl.Display;

import com.wallops.java.reference.Move;

public class MoveButton extends GuiButton {
	private int loc;
	private Move move;
	
	public MoveButton(float xScale, float yScale, float widthScale, float heightScale, Move move, int loc) {
		super(xScale, yScale, widthScale, heightScale, move.getName());
		this.move = move;
		this.loc = loc;
	}
	
	public MoveButton(int x, int y, int width, int height,  Move move, int loc) {
		super(x, y, width, height, move.getName());
		this.move = move;
		this.loc = loc;
	}

	public MoveButton(Move move, int loc) {
		super(((loc%2+4F)/6F)+2F/150F, ((loc/2+2F)/4F)+2F/150F, (1F/6F)-2F/150F, (1F/4F)-2F/150F, move.getName());
		this.move = move;
		this.loc = loc;
	}
	
	public Move getMove() {
		return this.move;
	}
}
