package com.wallops.java.gui;

import org.lwjgl.opengl.Display;

import com.wallops.java.reference.Move;

public class MoveButton extends GuiButton {
	private int loc;
	private Move move;
	
	public MoveButton(int x, int y, int width, int height, Move move, int loc) {
		super(x, y, width, height, move.getName());
		this.move = move;
		this.loc = loc;
	}

	public MoveButton(Move move, int loc) {
		super((2*Display.getWidth()/3)+(loc%2*Display.getWidth()/6)+3, Display.getHeight()/2+(loc/2*Display.getHeight()/4)+3, Display.getWidth()/6-3, Display.getHeight()/4-3, move.getName());
		this.move = move;
		this.loc = loc;
	}

	@Override
	public void renderButton() {
		calcLoc();
		super.renderButton();
	}
	
	private void calcLoc() {
		this.x = (2*Display.getWidth()/3)+(loc%2*Display.getWidth()/6)+3;
		this.y = Display.getHeight()/2+(loc/2*Display.getHeight()/4)+3;
		this.width = Display.getWidth()/6-3;
		this.height = Display.getHeight()/4-3;
	}
	
	public Move getMove() {
		return this.move;
	}
}
