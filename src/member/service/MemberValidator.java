package member.service;

import member.model.MemberModel;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		//회원정보 자바빈이 타겟??
		MemberModel memberModel = (MemberModel) target;
		
		//입력한 메일주소가 값이 비어있으면
		if(memberModel.getUserId() == null || memberModel.getUserId().trim().isEmpty()) {
			errors.rejectValue("userId", "required");
		}
		
		//입력한 비밀번호가 값이 비어있으면
		if(memberModel.getUserPw() == null || memberModel.getUserPw().trim().isEmpty()) {
			errors.rejectValue("userPw", "required");
		}
		
		//입력한 회원이름이 값이 비어있으면
		if(memberModel.getUserName() == null || memberModel.getUserName().trim().isEmpty()) {
			errors.rejectValue("userName", "required");
		}
		
	}
	
}
