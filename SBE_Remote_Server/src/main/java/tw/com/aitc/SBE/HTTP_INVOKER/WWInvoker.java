package tw.com.aitc.SBE.HTTP_INVOKER;

import org.springframework.http.MediaType;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WWInvoker implements HttpRequestHandler {
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		PrintWriter writer = response.getWriter();
		writer.print("<table border=\"1\"><tr><td>WW</td></tr></table>");
	}
}
