package login.service;

import login.dao.LoginDao;
import login.model.LoginSessionModel;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

//db처리는 서비스에서 하도록 한다
public class LoginService implements LoginDao {
	
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@Override
	public LoginSessionModel checkUserId(String userId) {
		// TODO Auto-generated method stub
		
		//로그인 쿼리문을 실행한 결과를 LoginSessionModel객체에 저장
		return (LoginSessionModel) sqlMapClientTemplate.queryForObject("login.loginCheck", userId);
	}
	
}
