import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Act implements ActionListener {
	private Move btnMv;
	private Game battle;
	
	public Act(Move m, Game g) {
		btnMv = m;
		battle = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(btnMv.getCategory() != Move.STATUS)
			battle.attack(btnMv, battle.getActive(), battle.getOpponent());
	}

}
