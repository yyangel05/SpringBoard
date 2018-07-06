package board.dao;

import java.util.List;

import board.model.BoardCommentModel;
import board.model.BoardModel;

public interface BoardDao {
	
	//��� �Խñ��� ���̺�κ��� �����´�
	List<BoardModel> getBoardList(int startArticleNum, int showArticleLimit);
	
	//������ �Խñ��� �������� �ڹٺ� ��´�
	BoardModel getOneArticle(int idx);
	
	//�˻��� �Խñ��� ���̺�κ��� �����´�
	List<BoardModel> searchArticle(String type, String keyword, int startArticleNum, int endArticleNum);
	
	//�ش� �Խñ��� ��� ������ ���̺�κ��� �����´�
	List<BoardCommentModel> getCommentList(int idx);
	
	//���� �ϳ��� ���� ������ ���� �ڹٺ� ��´�(������ �����ϱ� ����)
	BoardCommentModel getOneComment(int idx);
	
	//�Խñ� �����ϱ�
	boolean modifyArticle(BoardModel board);
	
	//�Խñ� �ۼ��ϱ�
	boolean writeArticle(BoardModel board);
	
	//���� �ۼ��ϱ�
	boolean writeComment(BoardCommentModel comment);
	
	//��ȸ�� ����
	void updateHitcount(int hitcount, int idx);
	
	//��õ�� ����
	void updateRecommendCount(int recommendcount, int idx);

	//�Խñ��� ���� ����
	int getTotalNum();
	
	//�˻� ����� �Խñ� ���� ����
	int getSearchTotalNum(String type, String keyword);
	
	//���� �����ϱ�
	void deleteComment(int idx);
	
	//�Խñ� �����ϱ�
	void deleteArticle(int idx);
	

}
