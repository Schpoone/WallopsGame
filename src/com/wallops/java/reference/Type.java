package com.wallops.java.reference;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public enum Type {
	NONE(-1, Color.WHITE),
	FIRE(1, Color.RED),
	WATER(2, Color.BLUE),
	GRASS(4, Color.GREEN),
	NORMAL(0, new Color(200, 200, 150)),
	ELECTRIC(3, Color.YELLOW),
	ICE(5, Color.CYAN),
	DARK(15, Color.BLACK),
	FIGHTING(6, new Color(200, 35, 15)),
	FLYING(9, new Color(215, 190, 245)),
	POISON(7, new Color(185, 10, 230)),
	GROUND(8, new Color(240, 200, 115)),
	ROCK(12, new Color(180, 155, 20)),
	BUG(11, new Color(130, 190, 60)),
	STEEL(16, Color.GRAY.brighter()),
	DRAGON(14, new Color(90, 10, 200)),
	PSYCHIC(10, new Color(240, 90, 150)),
	GHOST(13, new Color(120, 85, 150) ),
	FAIRY(17, Color.PINK);
	
	private int value;
	private Color color;
	
	private Type(int v, Color c) {
		value = v;
		color = c;
	}
	
	public boolean equals(Enum o) {
		Type t = (Type)o;
		return t.getValue() == this.getValue();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getEffectivenessAgainst(Type o) {
		double[][] chart = new double[18][18];
		try {
			Scanner in = new Scanner(new FileReader("src/com/wallops/java/reference/TypeEffectivenessChart.txt"));
			for (int r = 0; r < chart.length; r++) {
				for (int c = 0; c < chart[r].length; c++) {
					chart[r][c] = in.nextDouble();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("TypeEffectivenessChart.txt not found");
			System.exit(0);
		}
		int att = this.getValue();
		int def = o.getValue();
		return chart[att][def];
	}
}
