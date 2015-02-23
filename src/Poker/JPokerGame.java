package Poker;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Vector;


public class JPokerGame extends UnicastRemoteObject implements JPoker{
	File userInfo;
	File onlineUser;
	
	protected JPokerGame() throws RemoteException {
		userInfo = new File("UserInfo.txt");
		onlineUser = new File("OnlineUser.txt");
	}

	public static void main(String[] args) {
		try {
			JPokerGame app = new JPokerGame();
			System.setSecurityManager(new SecurityManager());
			Naming.rebind("poker", app);
			System.out.println("Service registered");
		} catch(Exception e) {
			System.err.println("Exception thrown: "+e);
		}		
	}
	
	@Override
	public boolean login(String username, String password) throws RemoteException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(userInfo));
			bw = new BufferedWriter(new FileWriter(onlineUser));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String line;
		try {
			while ((line = br.readLine()) != null) {
			    String[] row = line.split(" ");
			    if (row[0] == username && row[1] == password){
			    	//check online users
			    	bw.write(row[0]);
			    	return true;
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean register(String username, String password) throws RemoteException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(userInfo));
		}catch (IOException e) {
			e.printStackTrace();
		}
		String row = username + " " + password + " 0" + " 0" + " 0" + " 0";
		try {
			bw.write(row);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String[] getProfile(String username) throws RemoteException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(userInfo));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
			    String[] row = line.split(" ");
			    if (row[0] == username){
			    	return row;
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Vector<String[]>  getLeaderBoard() throws RemoteException {
		Vector<String[]> data = new Vector<String[]>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(userInfo));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
			    String[] row = line.split(" ");
			    Vector<String> list = new Vector<String>(Arrays.asList(row));
			    list.remove(1);
			    row = list.toArray(row);
			    data.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//sorting
		return data;
	}

	@Override
	public void logout(String username) throws RemoteException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(userInfo));
			bw = new BufferedWriter(new FileWriter(onlineUser));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				if (line == username){
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
