package kr.ac.kopo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.member.vo.MemberVO;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("prehandle 동작");
		System.out.println("handler : " + handler);
		
		System.out.println("ContextPath : " + request.getContextPath());
		System.out.println("uri : " + request.getRequestURI());
		System.out.println("QueryString : " + request.getQueryString());
		
		String dest = request.getRequestURI();
		dest = dest.substring(request.getContextPath().length());
		
		HttpSession session = request.getSession();
		session.setAttribute("dest", dest);
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		
		if(loginVO == null ) {
			// 로그인이 안되어있는 경우
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}else {
			// 로그인이 되어있는 경우
			return true;
		}
		// Redirect 무한루프라 404 에러
		//response.sendRedirect(request.getContextPath()+"member/login");
		//return false: preHandle 빼고 모든게 동작하지 않는다 
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("handler : "+handler);
		System.out.println("postHandle 동작");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion 동작");
	}
}
