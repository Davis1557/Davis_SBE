package tw.com.aitc.SBE.JAVA_RMI;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
	public static void main(String[] args) {
		try {
			Hello helloWorld = new HelloWorld();

			LocateRegistry.createRegistry(9999);

			Naming.bind("rmi://localhost:9999/hello", helloWorld);

			System.out.println("已開放 RMI");
		}
		catch (RemoteException e) {
			System.out.println("遠端物件異常");
			e.printStackTrace();
		}
		catch (AlreadyBoundException e) {
			System.out.println("重複綁定遠端物件");
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			System.out.println("URL異常！");
			e.printStackTrace();
		}
	}
}
