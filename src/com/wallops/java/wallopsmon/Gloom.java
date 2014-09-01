package com.wallops.java.wallopsmon;

import com.wallops.java.reference.Move;
import com.wallops.java.reference.Type;

public class Gloom extends Wallopsmon {
	public Gloom() {
		super("Gloom", Type.GRASS, Type.POISON, 1, 60, 65, 70, 85, 75, 40, Move.ABSORB, Move.SWEET_SCENT, Move.ACID, Move.NONE);
	}
}
