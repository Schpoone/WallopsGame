import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class OpenDesc implements ActionListener {

	private JFrame descWindow;
	private ScrollPane scroll;
	private JTextArea text;
	private Game battle;
	
	public OpenDesc(JButton d, Game g) {
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		descWindow = new JFrame("Description of " + g.getActive().getName());
		descWindow.setSize(new Dimension(rect.width, rect.height));
		scroll = new ScrollPane();
		text = new JTextArea();
		scroll.add(text);
		text.setBackground(Color.WHITE);
		text.setForeground(Color.BLACK);
		text.setEditable(false);
		text.setFont(new Font("Calibri", 0, 20));
		descWindow.add(scroll);
		battle = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		text.setText(battle.getActive().getDescription());
		descWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		descWindow.setVisible(true);
		
	}

}
