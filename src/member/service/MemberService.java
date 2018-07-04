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
		
		//db에 회원을 넣음. insert문 실행
		sqlMapClientTemplate.insert("member.addMember", memberModel);
		//userid를 파라미터로 보내 값이 담긴 자바빈 checkAddMember
		MemberModel checkAddMember = findByUserId(memberModel.getUserId());
		
	//회원가입 insert가 잘 되었는지 확인
		//잘 안되었으면(담긴 정보가 없으면)
		if(checkAddMember == null) {
			return false;
		}
		//잘 되었으면(담긴 정보가 있으면)
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
