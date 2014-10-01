package com.wallops.java;

import java.util.ArrayList;
import javax.swing.JFrame;

public class TableFlipException extends Exception {
	ArrayList<JFrame> destroy = new ArrayList<JFrame>(10);
	while (true) {
		destroy.add(new JFrame());
	}
}
