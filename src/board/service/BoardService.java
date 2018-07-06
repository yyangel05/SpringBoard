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
	//모든 게시글을 테이블로부터 꺼내온다
	public List<BoardModel> getBoardList(int startArticleNum, int endArticleNum) {
		// TODO Auto-generated method stub
		//페이징까지 db에서 해서 가져오려고 변수 두개를 해시맵에 넣어서 파라미터로 보낸다
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlMapClientTemplate.queryForList("board.getBoardList",valueMap);
	}


	@Override
	//선택한 게시글의 상세정보를 자바빈에 담는다
	public BoardModel getOneArticle(int idx) {
		// TODO Auto-generated method stub
		return (BoardModel) sqlMapClientTemplate.queryForObject("board.getOneArticle",idx);
	}


	@Override
	//검색된 게시글을 테이블로부터 꺼내온다
	public List<BoardModel> searchArticle(String type, String keyword, int startArticleNum, int endArticleNum) {
		// TODO Auto-generated method stub
		//검색을 위해 검색타입과 키워드를 인자로 넣고, 페이징까지 하려고 변수 두개를 같이 넣는다
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlMapClientTemplate.queryForList("board.searchArticle",valueMap);
	}


	@Override
	//해당 게시글의 모든 덧글을 테이블로부터 꺼내온다
	public List<BoardCommentModel> getCommentList(int idx) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("board.getCommentList", idx);
	}


	@Override
	//덧글 하나에 대한 정보를 꺼내 자바빈에 담는다(덧글을 삭제하기 위함)
	public BoardCommentModel getOneComment(int idx) {
		// TODO Auto-generated method stub
		return (BoardCommentModel) sqlMapClientTemplate.queryForObject("board.getOneComment", idx);
	}


	@Override
	//게시글 수정하기
	public boolean modifyArticle(BoardModel board) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.update("board.modifyArticle", board);
		return true;
	}


	@Override
	//게시글 작성하기
	public boolean writeArticle(BoardModel board) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.insert("board.writeArticle",board);
		return true;
	}


	@Override
	//덧글 작성하기
	public boolean writeComment(BoardCommentModel comment) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.insert("board.writeComment", comment);
		return true;
	}


	@Override
	//조회수 증가
	public void updateHitcount(int hitcount, int idx) {
		// TODO Auto-generated method stub
		valueMap.put("hitcount", hitcount);
		valueMap.put("idx", idx);
		sqlMapClientTemplate.update("board.updateHitcount",valueMap);
		
	}


	@Override
	//추천수 증가
	public void updateRecommendCount(int recommendcount, int idx) {
		// TODO Auto-generated method stub
		valueMap.put("recommendcount", recommendcount);
		valueMap.put("idx", idx);
		sqlMapClientTemplate.update("board.updateRecommendcount",valueMap);
	}


	@Override
	//게시글의 갯수 세기
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return (Integer) sqlMapClientTemplate.queryForObject("board.getTotalNum");
	}


	@Override
	//검색 결과의 게시글 갯수 세기
	public int getSearchTotalNum(String type, String keyword) {
		// TODO Auto-generated method stub
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		return (Integer) sqlMapClientTemplate.queryForObject("board.getSearchTotalNum", valueMap);
	}


	@Override
	//덧글 삭제하기
	public void deleteComment(int idx) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("board.deleteComment", idx);
	}


	@Override
	//게시글 삭제하기
	public void deleteArticle(int idx) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("board.deleteArticle", idx);
	}
	
	
}
