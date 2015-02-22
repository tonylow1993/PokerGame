package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LeaderBoard extends JPanel{
	MenuBar menu;
	JTable table;
	DefaultTableModel model;
	
	public MenuBar getMenu() {
		return menu;
	}
	
	public LeaderBoard (){
		this.setPreferredSize(new Dimension(600,350));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		menu = new MenuBar();
		model = new DefaultTableModel();
		table = new JTable(model);
		
		String[] columnNames = {"Rank", "Player", "Games Won", "Games Played", "Avg. Winning Time"};
		model.addColumn(columnNames);
		
		panel.add(menu);
		panel.add(table);
		this.add(panel);
	}
	
	public void addRow (String[] row){
		model.addRow(row);
	}
}
