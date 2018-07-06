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
		 
		//�Է��� ���̵� �̹� �ִ� ���̵���
		if(checkMemberModel != null) {
			mav.addObject("errCode",1); //("�̹� ���Ե� �̸��� �ּ��Դϴ�!");
			//mav.setViewName("/board/join"); //ȸ������â���� 
			//return mav;
			mav.setViewName("registMemberForm");
			return mav;
		}
		
		//ȸ�������� �Ǹ� �α������� �Ѿ��(�α��μ���)
		if(memberService.addMember(memberModel)) {
			mav.addObject("errCode",3); //("ȸ������ ó���� �Ϸ�Ǿ����ϴ�! �α����Ͻ�?");
			//mav.setViewName("/board/login"); //�α���ȭ������ ������
			//return mav;
			mav.setViewName("registMember");
			return mav;
		}
		//ȸ�������� �� �ȵǸ� ȸ������â���� ����
		else {
			mav.addObject("errCode",2); //("ȸ������ ó���� �����Ͽ����ϴ�. ��� �� �ٽ� �õ����ּ���");
			//mav.setViewName("/board/join"); //ȸ������â���� 
			//return mav;
			mav.setViewName("registMemberForm");
			return mav;
		}
	}
}
