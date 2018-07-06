package board.dao;

import java.util.List;

import board.model.BoardCommentModel;
import board.model.BoardModel;

public interface BoardDao {
	
	//모든 게시글을 테이블로부터 꺼내온다
	List<BoardModel> getBoardList(int startArticleNum, int showArticleLimit);
	
	//선택한 게시글의 상세정보를 자바빈에 담는다
	BoardModel getOneArticle(int idx);
	
	//검색된 게시글을 테이블로부터 꺼내온다
	List<BoardModel> searchArticle(String type, String keyword, int startArticleNum, int endArticleNum);
	
	//해당 게시글의 모든 덧글을 테이블로부터 꺼내온다
	List<BoardCommentModel> getCommentList(int idx);
	
	//덧글 하나에 대한 정보를 꺼내 자바빈에 담는다(덧글을 삭제하기 위함)
	BoardCommentModel getOneComment(int idx);
	
	//게시글 수정하기
	boolean modifyArticle(BoardModel board);
	
	//게시글 작성하기
	boolean writeArticle(BoardModel board);
	
	//덧글 작성하기
	boolean writeComment(BoardCommentModel comment);
	
	//조회수 증가
	void updateHitcount(int hitcount, int idx);
	
	//추천수 증가
	void updateRecommendCount(int recommendcount, int idx);

	//게시글의 갯수 세기
	int getTotalNum();
	
	//검색 결과의 게시글 갯수 세기
	int getSearchTotalNum(String type, String keyword);
	
	//덧글 삭제하기
	void deleteComment(int idx);
	
	//게시글 삭제하기
	void deleteArticle(int idx);
	

}
