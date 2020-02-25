package tw.com.aitc.SBE.JMX_WS_RPC;

import javax.xml.ws.Endpoint;

// RPC Server
public class ServerMain {
	public static void main(String[] args) {
		System.out.println("Start WebService");
		Endpoint.publish("http://localhost:7777/ws/speaker", new SpeakerImpl());
		System.out.println("Show WSDL on http://localhost:7777/ws/speaker?wsdl");
	}
}
