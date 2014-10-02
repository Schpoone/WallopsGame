package com.wallops.java.gui;

import com.wallops.java.event.Game;
import com.wallops.java.reference.Move;

public class GuiGameScreen extends GuiScreen {

	public GuiGameScreen(Game game) {
		super(game);
		this.screenButtons.add(new MoveButton(Move.ABSORB, 0));
		this.screenButtons.add(new MoveButton(Move.BUG_BITE, 1));
		this.screenButtons.add(new MoveButton(Move.CONFUSION, 2));
		this.screenButtons.add(new MoveButton(Move.NONE, 3));
	}

	@Override
	public void buttonClicked(GuiButton clickedButton) {
		
	}

}
