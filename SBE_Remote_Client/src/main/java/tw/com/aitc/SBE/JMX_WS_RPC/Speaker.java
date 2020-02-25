package tw.com.aitc.SBE.JMX_WS_RPC;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Speaker {

	@WebMethod()
	String speak(String word);
}
