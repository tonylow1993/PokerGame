package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuBar extends JPanel{
	JButton pro;
	JButton ply;
	JButton bor;
	JButton out;
	
	public MenuBar (){
		this.setLayout(new GridLayout(1, 4));
		pro = new JButton("User Profile");
		ply = new JButton("Play Game");
		bor = new JButton("Leader Board");
		out = new JButton("Logout");
		this.add(pro);
		this.add(ply);
		this.add(bor);
		this.add(out);
		this.setPreferredSize(new Dimension(600,30));
	}
	
	public void regListener (ActionListener listener1, ActionListener listener2, ActionListener listener3, ActionListener listener4){
		pro.addActionListener(listener1);
		ply.addActionListener(listener2);
		bor.addActionListener(listener3);
		out.addActionListener(listener4);
	}
}
