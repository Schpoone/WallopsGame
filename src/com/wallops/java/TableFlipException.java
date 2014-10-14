package com.wallops.java;

import java.util.ArrayList;
import javax.swing.JFrame;

public class TableFlipException extends RuntimeException {
	/*
	 * why am i even fixing this? this isn't even how exceptions work...
	 */
	public TableFlipException() {
		ArrayList<JFrame> destroy = new ArrayList<JFrame>();
		int i = 0;
		while (true) {//TESTSJSKFGSJS
			destroy.add(new JFrame());
			destroy.get(i).setVisible(true);
			i++;
		}
	}
}
