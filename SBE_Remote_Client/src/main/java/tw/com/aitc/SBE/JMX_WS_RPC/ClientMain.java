package tw.com.aitc.SBE.JMX_WS_RPC;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

// RPC Client
public class ClientMain {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://localhost:7777/ws/speaker?wsdl");

		QName qname = new QName("http://JMX_WS_RPC.SBE.aitc.com.tw/", "SpeakerImplService");
		Service service = Service.create(url, qname);

		Speaker speaker = service.getPort(Speaker.class);

		Scanner scanner = new Scanner(System.in);
		String next;
		do {
			next = scanner.next();
			System.out.println(speaker.speak(next));
		}
		while (next != null && !next.equalsIgnoreCase("Q"));
	}
}
