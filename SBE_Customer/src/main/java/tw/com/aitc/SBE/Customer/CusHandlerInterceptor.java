package tw.com.aitc.SBE.Customer;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CusHandlerInterceptor extends HandlerInterceptorAdapter {


	// 處理前：初始化、判斷是否進入處理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("=== Cus Pre Handle : " + request.getRequestURI());
		return super.preHandle(request, response, handler);
	}

	// 處理後：渲染前：處理 Response 或 ModelAndView
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("=== Cus Post Handle : " + request.getRequestURI());
		super.postHandle(request, response, handler, modelAndView);
	}

	// 渲染後：資源清理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("=== Cus After Completion : " + request.getRequestURI());
		super.afterCompletion(request, response, handler, ex);
	}
}
