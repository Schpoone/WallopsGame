import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class OpenSwitch implements ActionListener {
	private Wallopsmon[] wallopsmons;
	private JButton[] buttons;
	private JFrame choose;
	private ActionListener[] actions;
	private Game battle;

	public OpenSwitch(Wallopsmon[] w, Game g) {
		wallopsmons = w;
		buttons = new JButton[6];
		for(int i=0; i<6; i++)
			buttons[i] = new JButton();
		choose = new JFrame("Please pick a Wallopsmon to switch to");
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		Dimension full = Toolkit.getDefaultToolkit().getScreenSize();
		choose.setBounds(rect.width/20, rect.height/20 + ((int)full.getHeight()-rect.height), 9*rect.width/10, 9*rect.height/10);
		choose.setLayout(new GridLayout(3, 2));
		for(int i=0; i<6; i++)
			choose.add(buttons[i]);
		choose.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		actions = new ActionListener[6];
		battle = g;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		open();
	}
	
	public void open() {
		for(int i=0; i<6; i++) {
			if(wallopsmons[i] == null)
				break;
			buttons[i].setText(wallopsmons[i].getName() + " Level: " + wallopsmons[i].getLevel() + " HP: " + 
					wallopsmons[i].getCurrentHealth() + "/" + wallopsmons[i].getMaxHealth());
			buttons[i].removeActionListener(actions[i]);
			actions[i] = new Switch(battle, choose, wallopsmons, i);
			if(wallopsmons[i].getCurrentHealth() < 1)
				buttons[i].setForeground(Color.RED);
			else {
				buttons[i].addActionListener(actions[i]);
				buttons[i].setForeground(Color.BLACK);
			}
		}
		choose.setVisible(true);
	}

}
