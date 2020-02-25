package tw.com.aitc.SBE.JAVA_RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorld extends UnicastRemoteObject implements Hello {
	public HelloWorld() throws RemoteException {
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Hello World";
	}
}
