package board.model;

public class BoardCommentModel {
	
	private int idx; //µ¡±Û¹øÈ£
	private String writer; //µ¡±ÛÀÛ¼ºÀÚ
	private String content; //µ¡±Û³»¿ë
	private String writeDate; //µ¡±ÛÀÛ¼ºÀÏ
	private int linkedArticleNum; //µ¡±ÛÀÌ ´Þ¸° ±Û¹øÈ£
	private String writerId; //À¯Àúid
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getLinkedArticleNum() {
		return linkedArticleNum;
	}
	public void setLinkedArticleNum(int linkedArticleNum) {
		this.linkedArticleNum = linkedArticleNum;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	

}
