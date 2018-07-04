package member.dao;

import member.model.MemberModel;

public interface MemberDao {
	
	//ȸ�������� �����ϴ� �޼���. �������� ��/�������� ����
	boolean addMember(MemberModel memberModel);
	
	//���� ���̵� �ش��ϸ� ��� ������ �����´�.
	MemberModel findByUserId(String userId);
}


