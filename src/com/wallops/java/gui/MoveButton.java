package com.wallops.java.gui;

import org.lwjgl.opengl.Display;

import com.wallops.java.reference.Move;

/**
 * A specific {@linkplain GuiButton} used to display a Move object of the player's Wallopsmon and make it interactable
 * 
 * @author PureChaose
 *
 */
public class MoveButton extends GuiButton {
	private int loc;
	private Move move;
	
	/**
	 * Creates a new MoveButton with specified ratio between the Display's dimension and its own, and the Move associated with it and the Move's ID with respect to the Move's owner's moveset
	 * @param xScale The ratio between the x coordinate of this button and the Display's width
	 * @param yScale The ratio between the y coordinate of this button and the Display's height
	 * @param widthScale The ratio between the width of this button and the Display's width
	 * @param heightScale The ratio between the height of this button and the Display's height
	 * @param move The Move associated with this MoveButton
	 * @param loc The ID of the Move associated with this MoveButton with respect to the Move's owner's moveset
	 */
	public MoveButton(float xScale, float yScale, float widthScale, float heightScale, Move move, int loc) {
		super(xScale, yScale, widthScale, heightScale, move.getName());
		this.move = move;
		this.loc = loc;
	}
	
	/**
	 * Creates a new MoveButton with specified x and y coordinates, width and height, and the Move associated with it and the Move's ID with respect to the Move's owner's moveset
	 * @param x The x coordinate of this button
	 * @param y The y coordinate of this button
	 * @param width The width of this button
	 * @param height The height of this button
	 * @param move The Move associated with this MoveButton
	 * @param loc The ID of the Move associated with this MoveButton with respect to the Move's owner's moveset
	 */
	public MoveButton(int x, int y, int width, int height,  Move move, int loc) {
		super(x, y, width, height, move.getName());
		this.move = move;
		this.loc = loc;
	}

	/**
	 * Creates a new MoveButton at its default position, and the Move associated with it and the Move's ID with respect to the Move's owner's moveset
	 * @param move The Move associted with this MoveButton
	 * @param loc The Move's ID with respect to the Move's owner's moveset
	 */
	public MoveButton(Move move, int loc) {
		super(((loc%2+4F)/6F), ((loc/2+2F)/4F), (1F/6F), (1F/4F), move.getName());
		this.move = move;
		this.loc = loc;
	}
	
	/**
	 * Returns the Move associated with this MoveButton
	 * @return The Move associated with this MoveButton
	 */
	public Move getMove() {
		return this.move;
	}
}
