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
		
		//ȸ������ insert�� �� �Ǿ����� Ȯ��
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
