import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Switch implements ActionListener {
	private Game battle;
	private JFrame choose;
	private Wallopsmon[] wallopsmons;
	private int loc;
	
	public Switch(Game g, JFrame jf, Wallopsmon[] w, int i) {
		battle = g;
		choose = jf;
		wallopsmons = w;
		loc = i;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Wallopsmon temp = wallopsmons[loc];
		wallopsmons[loc] = wallopsmons[0];
		wallopsmons[0] = temp;
		choose.setVisible(false);
		battle.update();
	}

}
