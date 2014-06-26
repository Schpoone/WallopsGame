package com.wallops.java.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.wallops.java.event.Battle;


public class Reset implements ActionListener {
	private Battle battle;
	
	public Reset(Battle g) {
		battle = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		battle.reset();
	}

}
