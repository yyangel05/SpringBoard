package member.dao;

import member.model.MemberModel;

public interface MemberDao {
	
	//회원가입을 수행하는 메서드. 수행결과를 참/거짓으로 리턴
	boolean addMember(MemberModel memberModel);
	
	//유저 아이디에 해당하면 모든 정보를 가져온다.
	MemberModel findByUserId(String userId);
}


