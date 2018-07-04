package login.controller;

import javax.servlet.http.HttpSession;

import login.model.LoginSessionModel;
import login.service.LoginService;
import login.service.LoginValidator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private ApplicationContext context; //수동으로 가져다 쓰기 위해서 생성한다
	
	@RequestMapping("/login.yy")
	public String login() {
		return "/board/login";
	}
	
	@RequestMapping(value="/login.yy", method=RequestMethod.POST) 
	public ModelAndView loginProc(@ModelAttribute("LoginModel") LoginSessionModel loginModel,
									BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		//폼의 검증
		new LoginValidator().validate(loginModel, result);
		if(result.hasErrors()) {
			mav.setViewName("/board/login");
			return mav;
		}
		
		String userId = loginModel.getUserId();
		String userPw = loginModel.getUserPw();
		
		context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
		LoginService loginService = (LoginService) context.getBean("loginService");
		LoginSessionModel loginCheckResult = loginService.checkUserId(userId); //서비스의 LoginCheck이라는 select쿼리가 실행됨.
		
		//없는 이메일주소로 로그인을 시도하면
		if(loginCheckResult == null) {
			mav.addObject("userId", userId);
			mav.addObject("errCode",1); //("가입된 이메일 주소가 아닙니다!");
			mav.setViewName("/board/login");
			return mav;
		}
		
		//로그인이 성공적으로 되면 
		if(loginCheckResult.getUserPw().equals(userPw)) {
			session.setAttribute("userId", userId);
			session.setAttribute("userName", loginCheckResult.getUserName());
			mav.setViewName("redirect:/board/list.yy"); //게시판 리스트로 리다이렉트
			return mav;
		}
		//비밀번호가 맞지 않으면
		else {
			mav.addObject("userId", userId);
			mav.addObject("errCode",2); //("비밀번호가 일치하지 않습니다!");
			mav.setViewName("/board/login"); //로그인창으로 포워딩?
			return mav;
		}
		
	}
	
	@RequestMapping("/logout.yy")
	public String logout(HttpSession session) {
		session.invalidate(); //세션 무효화
		return "redirect:login.yy"; //로그인창으로 리다이렉트
	}
	

}
