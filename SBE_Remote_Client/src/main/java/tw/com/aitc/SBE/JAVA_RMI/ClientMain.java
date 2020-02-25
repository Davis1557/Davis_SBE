package tw.com.aitc.SBE.JAVA_RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMain {
	public static void main(String[] args) {
		try {
			Hello hello = (Hello) Naming.lookup("rmi://localhost:9999/hello");
			System.out.println(hello.sayHello());
		}
		catch (RemoteException e) {
			System.out.println("遠端物件異常");
			e.printStackTrace();
		}
		catch (NotBoundException e) {
			System.out.println("沒有綁定的物件");
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			System.out.println("URL異常！");
			e.printStackTrace();
		}
	}
}
