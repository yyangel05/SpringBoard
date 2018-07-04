package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 설정
//로그인 한 상태에서 로그인창과 회원가입 창으로 갈 수 없도록
//로그인을 안한 유저가 게시판 리스트로 갈 수 없도록

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		Object userId = request.getSession().getAttribute("userId");
		
		//로그인을 했으면 로그인/회원가입 창으로 갈 수 없도록 설정한다.
		if(request.getRequestURI().equals("/SpringBoard/login.yy") || request.getRequestURI().equals("/SpringBoard/member/join.yy")) {
			if(userId != null) {
				response.sendRedirect(request.getContextPath() + "/board/list.yy");
				return true;
			}
			else {
				return true;
			}
		}
		
		//로그인을 안했으면 로그인창으로 가도록 설정한다
		if(userId == null) {
			response.sendRedirect(request.getContextPath()+"/login.yy");
			return true;
		}
		else {
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
							Object handler, ModelAndView modelAndView) throws Exception{
		
	}

}
