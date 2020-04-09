package tw.com.aitc.SBE;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CusZuulFilter extends ZuulFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CusZuulFilter.class);

	@Value("${zuul.filter.cus.enabled}")
	private boolean enabled = true;

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	@Override
	public boolean shouldFilter() {
		return enabled;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String url = request.getRequestURL().toString();
		LOGGER.info(String.format("send %s request to %s", request.getMethod(), url));
		if (url.endsWith("Rex")) {
			ctx.setSendZuulResponse(false);
			throw new ZuulException("We don't want Rex", 404, "Because you set Rex");
		}
		return null;
	}
}