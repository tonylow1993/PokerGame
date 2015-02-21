import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class JPoker24Game implements Runnable, DocumentListener {
	private JPasswordField pwd;
	private JTextField ac;
	
	public JPoker24Game() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new JPoker24Game());
	}
	
	public void run() {
		JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,2));
		panel.setBorder(BorderFactory.createTitledBorder("Login")); 
		panel.setPreferredSize(new Dimension(400,300));
		panel.setLayout(new GridLayout(5,1));
		
		JLabel l1 = new JLabel("Login Name");
		JLabel l2 = new JLabel("Password");
		ac = new JTextField();
		pwd = new JPasswordField();
		
		JButton log = new JButton("Login");
		JButton reg = new JButton("Register");
		buttons.add(log);
		buttons.add(reg);
		
		panel.add(l1);
		panel.add(ac);
		panel.add(l2);
		panel.add(pwd);
		panel.add(buttons);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
