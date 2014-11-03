package com.wallops.java.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.wallops.java.gui.GuiBattleScreen;


public class Reset implements ActionListener {
	private GuiBattleScreen battle;
	
	public Reset(GuiBattleScreen g) {
		battle = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		battle.reset();
	}

}
