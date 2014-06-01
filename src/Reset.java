import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Reset implements ActionListener {
	private Game battle;
	
	public Reset(Game g) {
		battle = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		battle.reset();
	}

}
