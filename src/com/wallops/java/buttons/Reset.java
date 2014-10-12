package com.wallops.java.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.wallops.java.gui.GuiGameScreen;


public class Reset implements ActionListener {
	private GuiGameScreen battle;
	
	public Reset(GuiGameScreen g) {
		battle = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		battle.reset();
	}

}
