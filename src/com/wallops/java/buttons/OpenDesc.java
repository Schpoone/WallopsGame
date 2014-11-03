package com.wallops.java.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.wallops.java.gui.GuiBattleScreen;


public class OpenDesc implements ActionListener {

	private JFrame descWindow;
	private ScrollPane scroll;
	private JTextArea text;
	private GuiBattleScreen battle;
	
	public OpenDesc(JButton d, GuiBattleScreen g) {
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		Dimension full = Toolkit.getDefaultToolkit().getScreenSize();
		descWindow = new JFrame("Description of " + g.getActive().getName());
		descWindow.setBounds(rect.width/20, rect.height/20 + ((int)full.getHeight()-rect.height), 9*rect.width/10, 9*rect.height/10);
		scroll = new ScrollPane();
		text = new JTextArea(100, descWindow.getHeight()/20-2);
		scroll.add(text);
		text.setBackground(Color.WHITE);
		text.setForeground(Color.BLACK);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setFont(new Font("MONOSPACED", 0, 20));
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
