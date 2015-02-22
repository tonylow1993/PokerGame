import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


public interface JPoker extends Remote {
	boolean login(String username, String password) throws RemoteException;
	boolean register(String username, String password) throws RemoteException;
	String[] getProfile (String username) throws RemoteException;
	Vector<String[]> getLeaderBoard () throws RemoteException;
	void logout (String username) throws RemoteException;
}
