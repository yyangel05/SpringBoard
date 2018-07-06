package board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import board.dao.BoardDao;
import board.model.BoardCommentModel;
import board.model.BoardModel;

public class BoardService implements BoardDao {
	
	private SqlMapClientTemplate sqlMapClientTemplate;
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}


	@Override
	//��� �Խñ��� ���̺�κ��� �����´�
	public List<BoardModel> getBoardList(int startArticleNum, int endArticleNum) {
		// TODO Auto-generated method stub
		//����¡���� db���� �ؼ� ���������� ���� �ΰ��� �ؽøʿ� �־ �Ķ���ͷ� ������
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlMapClientTemplate.queryForList("board.getBoardList",valueMap);
	}


	@Override
	//������ �Խñ��� �������� �ڹٺ� ��´�
	public BoardModel getOneArticle(int idx) {
		// TODO Auto-generated method stub
		return (BoardModel) sqlMapClientTemplate.queryForObject("board.getOneArticle",idx);
	}


	@Override
	//�˻��� �Խñ��� ���̺�κ��� �����´�
	public List<BoardModel> searchArticle(String type, String keyword, int startArticleNum, int endArticleNum) {
		// TODO Auto-generated method stub
		//�˻��� ���� �˻�Ÿ�԰� Ű���带 ���ڷ� �ְ�, ����¡���� �Ϸ��� ���� �ΰ��� ���� �ִ´�
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlMapClientTemplate.queryForList("board.searchArticle",valueMap);
	}


	@Override
	//�ش� �Խñ��� ��� ������ ���̺�κ��� �����´�
	public List<BoardCommentModel> getCommentList(int idx) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("board.getCommentList", idx);
	}


	@Override
	//���� �ϳ��� ���� ������ ���� �ڹٺ� ��´�(������ �����ϱ� ����)
	public BoardCommentModel getOneComment(int idx) {
		// TODO Auto-generated method stub
		return (BoardCommentModel) sqlMapClientTemplate.queryForObject("board.getOneComment", idx);
	}


	@Override
	//�Խñ� �����ϱ�
	public boolean modifyArticle(BoardModel board) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.update("board.modifyArticle", board);
		return true;
	}


	@Override
	//�Խñ� �ۼ��ϱ�
	public boolean writeArticle(BoardModel board) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.insert("board.writeArticle",board);
		return true;
	}


	@Override
	//���� �ۼ��ϱ�
	public boolean writeComment(BoardCommentModel comment) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.insert("board.writeComment", comment);
		return true;
	}


	@Override
	//��ȸ�� ����
	public void updateHitcount(int hitcount, int idx) {
		// TODO Auto-generated method stub
		valueMap.put("hitcount", hitcount);
		valueMap.put("idx", idx);
		sqlMapClientTemplate.update("board.updateHitcount",valueMap);
		
	}


	@Override
	//��õ�� ����
	public void updateRecommendCount(int recommendcount, int idx) {
		// TODO Auto-generated method stub
		valueMap.put("recommendcount", recommendcount);
		valueMap.put("idx", idx);
		sqlMapClientTemplate.update("board.updateRecommendcount",valueMap);
	}


	@Override
	//�Խñ��� ���� ����
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return (Integer) sqlMapClientTemplate.queryForObject("board.getTotalNum");
	}


	@Override
	//�˻� ����� �Խñ� ���� ����
	public int getSearchTotalNum(String type, String keyword) {
		// TODO Auto-generated method stub
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		return (Integer) sqlMapClientTemplate.queryForObject("board.getSearchTotalNum", valueMap);
	}


	@Override
	//���� �����ϱ�
	public void deleteComment(int idx) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("board.deleteComment", idx);
	}


	@Override
	//�Խñ� �����ϱ�
	public void deleteArticle(int idx) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("board.deleteArticle", idx);
	}
	
	
}
