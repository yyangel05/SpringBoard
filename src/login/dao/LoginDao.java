package login.dao;

import login.model.LoginSessionModel;

public interface LoginDao {
	
	//userid�� �ش��ϴ� ����� ��� �������� �����´�.
	LoginSessionModel checkUserId(String userId);

}
