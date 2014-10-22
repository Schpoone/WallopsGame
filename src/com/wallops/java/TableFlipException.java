package com.wallops.java;

import java.util.ArrayList;
import javax.swing.JFrame;

public class TableFlipException extends RuntimeException {
	
	public TableFlipException() {
		ArrayList<JFrame> destroy = new ArrayList<JFrame>();
		int i = 0;
		while (true) {
			destroy.add(new JFrame());
			destroy.get(i).setVisible(true);
			i++;
		}
	}
}
