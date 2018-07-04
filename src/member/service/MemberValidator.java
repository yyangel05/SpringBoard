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
		
		//ȸ������ �ڹٺ��� Ÿ��??
		MemberModel memberModel = (MemberModel) target;
		
		//�Է��� �����ּҰ� ���� ���������
		if(memberModel.getUserId() == null || memberModel.getUserId().trim().isEmpty()) {
			errors.rejectValue("userId", "required");
		}
		
		//�Է��� ��й�ȣ�� ���� ���������
		if(memberModel.getUserPw() == null || memberModel.getUserPw().trim().isEmpty()) {
			errors.rejectValue("userPw", "required");
		}
		
		//�Է��� ȸ���̸��� ���� ���������
		if(memberModel.getUserName() == null || memberModel.getUserName().trim().isEmpty()) {
			errors.rejectValue("userName", "required");
		}
		
	}
	
}
