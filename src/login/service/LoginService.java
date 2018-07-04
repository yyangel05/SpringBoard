package login.service;

import login.dao.LoginDao;
import login.model.LoginSessionModel;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

//dbó���� ���񽺿��� �ϵ��� �Ѵ�
public class LoginService implements LoginDao {
	
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@Override
	public LoginSessionModel checkUserId(String userId) {
		// TODO Auto-generated method stub
		
		//�α��� �������� ������ ����� LoginSessionModel��ü�� ����
		return (LoginSessionModel) sqlMapClientTemplate.queryForObject("login.loginCheck", userId);
	}
	
}
