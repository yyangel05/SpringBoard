package member.service;

import member.dao.MemberDao;
import member.model.MemberModel;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class MemberService  implements MemberDao {
	
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@Override
	public boolean addMember(MemberModel memberModel) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.insert("member.addMember",memberModel);
		MemberModel checkAddMember = findByUserId(memberModel.getUserId());
		
		//회원가입 insert가 잘 되었는지 확인
		if(checkAddMember == null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public MemberModel findByUserId(String userId) {
		// TODO Auto-generated method stub
		return (MemberModel) sqlMapClientTemplate.queryForObject("member.findByUserId", userId);
	}
	
	

}
