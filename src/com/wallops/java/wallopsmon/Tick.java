package com.wallops.java.wallopsmon;

import com.wallops.java.reference.Move;
import com.wallops.java.reference.Type;

public class Tick extends Wallopsmon {
	public Tick() {
		super("Tick", Type.BUG, Type.DARK, 1, 60, 55, 50, 40, 55, 45, Move.BUG_BITE, Move.LEECH_LIFE, Move.INFESTATION, Move.POISON_FANG);
		//same stats as venonat
	}
}
