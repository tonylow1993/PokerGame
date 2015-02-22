import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class JPokerGame extends UnicastRemoteObject implements JPoker{
	FileReader in = null;
	FileWriter out = null;
	
	protected JPokerGame() throws RemoteException {}

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
		try {
			in = new FileReader("UserInfo.txt");
			out = new FileWriter("OnlineUser.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(in);
		BufferedWriter bw = new BufferedWriter(out);
		String line;
		try {
			while ((line = br.readLine()) != null) {
			    String temp[] = line.split(" ");
			    if (temp[0] == username && temp[1] == password){
			    	bw.write(temp[0]);
			    	return true;
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getProfile(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getLeaderBoard() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
