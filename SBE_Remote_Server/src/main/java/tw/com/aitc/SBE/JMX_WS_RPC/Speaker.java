package tw.com.aitc.SBE.JMX_WS_RPC;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Speaker {
	@WebMethod()
	String speak(String word);
}
