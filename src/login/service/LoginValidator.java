package login.service;

import login.model.LoginSessionModel;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginSessionModel.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		//로그인 자바빈이 타겟이라는 뜻??
		LoginSessionModel loginModel = (LoginSessionModel) target;
		
		//유저 아이디가 빈 값인지 체크한다
		if(loginModel.getUserId() == null || loginModel.getUserId().trim().isEmpty()) {
			errors.rejectValue("userId", "required"); //validation의 required 메세지를 userId에 담는다?
		}
		
		//유저 비밀번호가 빈 값인지 체크한다
		if(loginModel.getUserPw() == null || loginModel.getUserPw().trim().isEmpty()) {
			errors.rejectValue("userPw", "required"); //validation의 required 메세지를 userPw에 담는다? 꺼낼때는 form:error의 path에 loginModel.userPw
		}
	}

}
