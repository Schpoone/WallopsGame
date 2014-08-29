package com.wallops.java.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UseItem implements ActionListener {
	
	private JButton item;
	
	public UseItem(JButton i) {
		item = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(item.getText().equalsIgnoreCase("Use Item")) {
			item.setText("to be implemented later");
			item.setVisible(false);
			item.setVisible(true);
		} else {
			item.setText("Use Item");
			item.setVisible(false);
			item.setVisible(true);
		}
	}

}
