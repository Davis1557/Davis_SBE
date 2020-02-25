package tw.com.aitc.SBE.JMX_WS_RPC;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(endpointInterface = "tw.com.aitc.SBE.JMX_WS_RPC.Speaker")
@HandlerChain(file = "handlers.xml")
public class SpeakerImpl implements Speaker {
	@Override
	public String speak(String word) {
		return "RPC Speak : " + word;
	}
}
