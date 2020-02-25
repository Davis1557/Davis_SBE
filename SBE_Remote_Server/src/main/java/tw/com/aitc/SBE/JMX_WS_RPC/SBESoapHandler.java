package tw.com.aitc.SBE.JMX_WS_RPC;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Set;

public class SBESoapHandler implements SOAPHandler<SOAPMessageContext> {
	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		SOAPMessage message = context.getMessage();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			message.writeTo(baos);

			if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
				System.out.println("--------------  Send  ---------------");
				System.out.println(baos);
				System.out.println("-------------------------------------");
			}
			else {
				System.out.println("------------  Receive  --------------");
				System.out.println(baos);
				System.out.println("-------------------------------------");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("--------------- close ---------------");
	}
}
