package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//���ͼ��� ����
//�α��� �� ���¿��� �α���â�� ȸ������ â���� �� �� ������
//�α����� ���� ������ �Խ��� ����Ʈ�� �� �� ������

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		Object userId = request.getSession().getAttribute("userId");
		
		//�α����� ������ �α���/ȸ������ â���� �� �� ������ �����Ѵ�.
		if(request.getRequestURI().equals("/SpringBoard/login.yy") || request.getRequestURI().equals("/SpringBoard/member/join.yy")) {
			if(userId != null) {
				response.sendRedirect(request.getContextPath() + "/board/list.yy");
				return true;
			}
			else {
				return true;
			}
		}
		
		//�α����� �������� �α���â���� ������ �����Ѵ�
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
