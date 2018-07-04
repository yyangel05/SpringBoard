package login.dao;

import login.model.LoginSessionModel;

public interface LoginDao {
	
	//userid에 해당하는 사람의 모든 정보들을 가져온다.
	LoginSessionModel checkUserId(String userId);

}
