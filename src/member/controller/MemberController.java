package member.controller;

import member.model.MemberModel;
import member.service.MemberService;
import member.service.MemberValidator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private ApplicationContext context;
	
	@RequestMapping("/join.yy")
	public String memberJoin() {
		//return "/board/join";
		return "registMemberForm";
	}
	
	@RequestMapping(value="/join.yy", method = RequestMethod.POST) 
	public ModelAndView addMember(@ModelAttribute("MemberModel") MemberModel memberModel, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		new MemberValidator().validate(memberModel, result);
		
		if(result.hasErrors()) {
			//mav.setViewName("/board/join");
			mav.setViewName("registMemberForm");
			return mav;
		}
		
		context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
		MemberService memberService = (MemberService) context.getBean("memberService");
		MemberModel checkMemberModel = memberService.findByUserId(memberModel.getUserId());
		 
		//입력한 아이디가 이미 있는 아이디라면
		if(checkMemberModel != null) {
			mav.addObject("errCode",1); //("이미 가입된 이메일 주소입니다!");
			//mav.setViewName("/board/join"); //회원가입창으로 
			//return mav;
			mav.setViewName("registMemberForm");
			return mav;
		}
		
		//회원가입이 되면 로그인으로 넘어간다(로그인성공)
		if(memberService.addMember(memberModel)) {
			mav.addObject("errCode",3); //("회원가입 처리가 완료되었습니다! 로그인하실?");
			//mav.setViewName("/board/login"); //로그인화면으로 포워딩
			//return mav;
			mav.setViewName("registMember");
			return mav;
		}
		//회원가입이 잘 안되면 회원가입창으로 간다
		else {
			mav.addObject("errCode",2); //("회원가입 처리가 실패하였습니다. 잠시 후 다시 시도해주세요");
			//mav.setViewName("/board/join"); //회원가입창으로 
			//return mav;
			mav.setViewName("registMemberForm");
			return mav;
		}
	}
}
