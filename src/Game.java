import java.awt.*;
import javax.swing.*;


public class Game {
	private JFrame window;
	private ScrollPane scroll;
	private JTextArea text;
	private JPanel top;
	private JPanel bottom;
	
	public Game() {
		window = new JFrame();
		scroll = new ScrollPane();
		top = new JPanel();
		bottom = new JPanel();
		text = new JTextArea();
		scroll.add(text);
		bottom.add(scroll);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(new GridLayout(2, 1));
		window.add(top);
		window.add(bottom);
		System.out.println(window.getHeight());
		System.out.println(bottom.getHeight());
		System.out.println(scroll.getHeight());
		scroll.setBackground(Color.RED);
		text.setBackground(Color.RED);
		top.setBackground(Color.BLUE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		//edited from the website
	}
}
