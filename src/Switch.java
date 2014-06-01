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
		if(battle.getOpponent().getCurrentHealth() > 0) {
			Move m2 = Move.NONE;
			while(m2.getCategory() != Move.PHYSICAL && m2.getCategory() != Move.SPECIAL) {
				int n = (int) (Math.random()*3+1);
				if(n == 1)
					m2 = battle.getOpponent().getMoveOne();
				if(n == 2)
					m2 = battle.getOpponent().getMoveTwo();
				if(n == 3)
					m2 = battle.getOpponent().getMoveThree();
				if(n == 4)
					m2 = battle.getOpponent().getMoveFour();
			}
			battle.attack(m2, battle.getOpponent(), battle.getActive());
		}
		battle.update();
	}

}
