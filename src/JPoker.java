import java.rmi.Remote;
import java.rmi.RemoteException;


public interface JPoker extends Remote {
	boolean login(String username, String password) throws RemoteException;
	boolean register(String username, String password) throws RemoteException;
	String[] getProfile (String username) throws RemoteException;
	String[][] getLeaderBoard () throws RemoteException;
	void logout () throws RemoteException;
}
