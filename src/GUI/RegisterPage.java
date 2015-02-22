package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterPage extends JPanel{
	private JPasswordField pwd;
	private JTextField name;
	private JPasswordField confirm;
	private JButton can;
	private JButton reg;
	
	public String getPassword() {
		return pwd.getText();
	}

	public String getUsername() {
		return name.getText();
	}
	
	public RegisterPage (){
		this.setBorder(new EmptyBorder(10, 10, 10, 10)); 
		JPanel inner = new JPanel();
		inner.setBorder(BorderFactory.createTitledBorder("Register")); 
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
		panel.setPreferredSize(new Dimension(350,300));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel l1 = new JLabel("Login Name");
		JLabel l2 = new JLabel("Password");
		JLabel l3 = new JLabel("Confirm Password");
		name = new JTextField();
		name.setPreferredSize(new Dimension(330,40));
		pwd = new JPasswordField();
		pwd.setPreferredSize(new Dimension(330,40));
		confirm = new JPasswordField();
		confirm.setPreferredSize(new Dimension(330,40));
		
		can = new JButton("Cancel");
		can.setPreferredSize(new Dimension(130,30));
		reg = new JButton("Register");
		reg.setPreferredSize(new Dimension(130,30));
		
		panel.add(l1);
		panel.add(name);
		panel.add(l2);
		panel.add(pwd);
		panel.add(l3);
		panel.add(confirm);
		panel.add(reg);
		panel.add(can);
		this.add(inner);
		inner.add(panel);
	}
	
	public void regListener (ActionListener registerListener, ActionListener cancelListener){
		reg.addActionListener(registerListener);
		can.addActionListener(cancelListener);
	}
	
	public boolean confirmPassword(){
		return pwd.getPassword()==confirm.getPassword();
	}
}
