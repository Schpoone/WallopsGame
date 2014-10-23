package com.wallops.java.reference;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public enum Type {
	NONE(-1, Color.WHITE),
	NORMAL(0, new Color(200, 200, 150)),
	FIRE(1, Color.RED),
	WATER(2, Color.BLUE),
	ELECTRIC(3, Color.YELLOW),
	GRASS(4, Color.GREEN),
	ICE(5, Color.CYAN),
	FIGHTING(6, new Color(200, 35, 15)),
	POISON(7, new Color(185, 10, 230)),
	GROUND(8, new Color(240, 200, 115)),
	FLYING(9, new Color(215, 190, 245)),
	PSYCHIC(10, new Color(240, 90, 150)),
	BUG(11, new Color(130, 190, 60)),
	ROCK(12, new Color(180, 155, 20)),
	GHOST(13, new Color(120, 85, 150) ),
	DRAGON(14, new Color(90, 10, 200)),
	DARK(15, Color.BLACK),
	STEEL(16, Color.GRAY.brighter()),
	FAIRY(17, Color.PINK);
	
	private int value;
	private Color color;
	public static Properties typeAdvantages;
	
	private Type(int v, Color c) {
		value = v;
		color = c;
	}
	
	public boolean equals(Enum<Type> o) {
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
		if(Type.typeAdvantages == null)
			try {
				typeAdvantages.load(new FileInputStream(new File("src/com/wallops/resources/TypeEffectiveness.properties")));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return Double.parseDouble(typeAdvantages.getProperty(this.name()+o.name()));
	}
}
