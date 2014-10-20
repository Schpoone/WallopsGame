package com.wallops.java;

import java.util.ArrayList;
import javax.swing.JFrame;

public class TableFlipException extends RuntimeException {
	/*
	 * why am i even fixing this? this isn't even how exceptions work...
	 */
	public TableFlipException() {
		ArrayList<JFrame> destroy = new ArrayList<JFrame>();//moar tests
		int i = 0;
		while (true) {
			destroy.add(new JFrame());
			destroy.get(i).setVisible(true);
			i++;
		}
	}
}
