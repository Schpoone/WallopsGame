package com.wallops.java;

import com.wallops.java.event.Battle;
import com.wallops.java.wallopsmon.*;

public class Init {

	public static void main(String[] args) {
		Battle wallops = new Battle();
		wallops.setOpponent(new MudDogWhelk());
		wallops.update();
	}

}
