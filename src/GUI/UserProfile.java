package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserProfile extends JPanel{
	JPanel content;
	MenuBar menu;
	
	public MenuBar getMenu() {
		return menu;
	}

	public UserProfile (){
		this.setPreferredSize(new Dimension(600,350));
		
		JPanel panel = new JPanel();
		menu = new MenuBar();
		content = new JPanel();
		content.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panel.add(menu);
		panel.add(content);
		this.add(panel);
	}
	
	public void displayProfile (String[] profile){
		for (String data : profile){
			content.add(new JLabel(data));
		}
	}
}
