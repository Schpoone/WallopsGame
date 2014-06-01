import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Nope implements ActionListener {
	private JButton run;
	public Nope(JButton j) {
		run = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(run.getText().equals("Run")) {
			run.setText("You can't run because reasons");
			run.setVisible(false);
			run.setVisible(true);
		} else {
			run.setText("Run");
			run.setVisible(false);
			run.setVisible(true);
		}
	}
}
