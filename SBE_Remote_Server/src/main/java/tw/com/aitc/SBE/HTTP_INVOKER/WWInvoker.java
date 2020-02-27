package tw.com.aitc.SBE.HTTP_INVOKER;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class WWInvoker implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return true;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		PrintWriter writer = response.getWriter();
		writer.print("<table border=\"1\"><tr><td>WWW</td></tr></table>");
		return null;
	}

	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		return 0;
	}
}
