import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.LeaderBoard;
import GUI.LoginPage;
import GUI.MenuBar;
import GUI.RegisterPage;
import GUI.UserProfile;


public class JPoker24Game implements Runnable {
	private JFrame frame;
	private JPanel currentPage;
	private JPoker poker;
	private boolean logged;
	private String username;
	
	public JPoker24Game() {
		frame = new JFrame();
		logged = false;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			poker = (JPoker)registry.lookup("poker");
			//wordCounter = (WordCount)Naming.lookup("WordCounter");
		} catch(Exception e) {
			System.err.println("Failed accessing RMI: "+e);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new JPoker24Game());
	}
	
	public void run() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		changePage("login");
	}
	
	public void login () throws RemoteException{
		if (currentPage instanceof LoginPage){
			LoginPage page = (LoginPage)currentPage;
			logged = poker.login(page.getUsername(), page.getPassword());
			if (logged) {
				username = page.getUsername();
				changePage("userprofile");
			}
		}
	}
	
	public void register ()  throws RemoteException{
		if (currentPage instanceof RegisterPage){
			RegisterPage page = (RegisterPage)currentPage;
			if (page.confirmPassword()){
				poker.register(page.getUsername(), page.getPassword());
			}
		}
	}
	
	public void profile () throws RemoteException{
		if (currentPage instanceof UserProfile){
			UserProfile page = (UserProfile)currentPage;
			page.displayProfile(poker.getProfile(username));
		}
	}
	
	public void leaderBoard () throws RemoteException{
		if (currentPage instanceof LeaderBoard){
			LeaderBoard page = (LeaderBoard)currentPage;
			Vector<String[]>  data = poker.getLeaderBoard();
			for (String[] row : data){
				page.addRow(row);
			}
		}
	}
	
	public void changePage (String name){
		if (name == "login"){
			LoginPage page = new LoginPage();
			currentPage = page;
			frame.setTitle("Login");
			frame.setContentPane(page);
			frame.pack();
			page.regListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						login();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}, new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					changePage ("register");
				}
			});
		}else if (name == "register"){
			RegisterPage page = new RegisterPage();
			currentPage = page;
			frame.setTitle("Register");
			frame.setContentPane(page);
			frame.pack();
			page.regListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						register();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}, new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					changePage ("login");
				}
			});
		}else if (name == "leaderboard"){
			LeaderBoard page = new LeaderBoard();
			currentPage = page;
			regMenuButton(page.getMenu());
			frame.setTitle("Leader Board");
			frame.add(page);
			frame.pack();
			try {
				leaderBoard();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}else if (name == "userprofile"){
			UserProfile page = new UserProfile();
			currentPage = page;
			regMenuButton(page.getMenu());
			frame.setTitle("User Profile");
			frame.pack();
			try {
				profile();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void regMenuButton (MenuBar menu){
		menu.regListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					changePage ("userprofile");
				}
			}, new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					changePage ("playgame");
				}
			},new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					changePage ("leaderboard");
				}
			},new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					changePage ("login");
					logged = false;
					username = null;
				}
			});
	}
}
