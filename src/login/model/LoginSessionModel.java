package login.model;

public class LoginSessionModel {
	
	private String userId; //ȸ�����̵�
	private String userPw; //ȸ����й�ȣ
	private String userName; //ȸ���̸�
	private boolean auth; //��������
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	
	

}
