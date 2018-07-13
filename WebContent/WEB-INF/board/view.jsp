<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 보기 : ${board.subject }</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/SpringBoard/style/images/favicon.png" rel="icon"/>
        <link href="/SpringBoard/style/css/magnific-popup.css" rel="stylesheet">
        <link href="/SpringBoard/style/css/bootstrap.min.css" rel="stylesheet">
        <link href="/SpringBoard/style/css/style.css" rel="stylesheet">
        <link href="/SpringBoard/style/css/responsive.css" rel="stylesheet">

        <script src="/SpringBoard/style/js/jquery.min.js" type= "text/javascript"></script>
        <script src="/SpringBoard/style/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/SpringBoard/style/js/magnific-popup.js" type="text/javascript"></script>
        <script src="/SpringBoard/style/js/jquery.imagesloaded.js" type="text/javascript"></script>
        <script src="/SpringBoard/style/js/masonry.pkgd.min.js" type="text/javascript"></script>
        <script src="/SpringBoard/style/js/custom.js" type="text/javascript"></script>


<script type="text/javascript">

	function errCodeCheck() {
		var errCode = <%= request.getParameter("errCode") %>;
		if(errCode != null || errCode != "") {
			switch (errCode) {
			case 1:
				alert("잘못된 접근 경로입니다");
				break;
			case 2:
				alert("댓글이 있어 글을 삭제하실 수 없습니다");
				break;
			}
		}
	}
	
	function commentDelete(commentIdx, linkedArticleNum) {
		if(confirm("선택하신 덧글을 삭제하시겠습니까?")) {
			location.href=("commentDelete.yy?idx=" + commentIdx + "&linkedArticleNum=" + linkedArticleNum);
		}
	}
	
	function moveAction(where) {
		switch (where) {
		case 1:
			if(confirm("글을 삭제하시겠습니까?")) {
				location.href="delete.yy?idx=${board.idx}";
			}
			break;
		case 2:
			if(confirm("글을 수정하시겠습니까?")) {
				location.href="modify.yy?idx=${board.idx}";
			}
			break;
		case 3:
				location.href="list.yy";
			break;
		case 4:
			if(confirm("글을 추천하시겠습니까?")) {
				location.href="recommend.yy?idx=${board.idx}";
			}
			break;
		}
	}	
		

</script>

</head>
<body onload="errCodeCheck()">


        <!--Blog Single Post Section-->
        <section class="tv-section-padding" id="innerblogleftsidebar">
            <div class="container">
                <div class="row">
   
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-12 col-sm-12 col-xs-12 blog_item_details">
                           
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="title">오징어칩이 되고싶어</div>
                            </div>
                            <div class="col-md-8 col-sm-6 col-xs-12">
                                <div class="date">작성자 - ${board.writer}</div>
                                <div class="autor">조회수 ${board.hitcount}</div>
                                <a class="blog_category_url">추천수 ${board.recommendcount}</a>

                                <div class="date">${board.writeDate}</div>
                            </div>
                            <div class="col-md-4 col-sm-6 col-xs-12 blog_item_social">
                                <ul>
                                    <li><a><i class="fa fa-facebook fa-lg"></i></a></li>
                                    <li><a><i class="fa fa-linkedin fa-lg"></i></a></li>
                                    <li><a><i class="fa fa-behance fa-lg"></i></a></li>
                                    <li><a><i class="fa fa-twitter fa-lg"></i></a></li>
                                </ul>
                            </div>
                        </div>

                        <article class="col-md-12 blog_post_content" style="margin-top: 50px;">
                           
                            <!-- <a class="button button_small">download</a> -->
							<c:if test="${board.fileName != null }">
								<tr>
									<td colspan="4" align="left"><span class="date">첨부파일:&nbsp;
									<a href="/SpringBoard/download/download.yy?idx=${board.idx }&${board.fileName }" target="_blank">${board.fileName}</a></span>
									</td>
								</tr>
							</c:if>

                            <h4>Content</h4>
                            <p>${board.content }</p><br/><br/>
                        </article>
                        <div class="coments">
                            <div class="col-md-12">
                                <div class="comments-title">Comments</div>
                                <div class="coments_content">	
                                
								<c:forEach var="comment" items="${commentList}">
                                
                                    <div class="col-md-12 coment_item">
                                        <div class="row">
                                            <div class="col-sm-2 col-md-2 col-lg-2 coment_item_l">
                                                <div class="autor">${comment.writer}</div>
                                                <c:if test="${comment.writerId == userId }">
													<a onclick="commentDelete(${comment.idx}, ${board.idx })"><small>댓글 삭제</small></a>
												</c:if>
                                            </div>
                                            <div class="col-sm-10 col-md-10 col-lg-10 coment_item_r">
                                                <div class="coment_item_cintent">
                                                  ${comment.writeDate }
                                                </div>
                                                <div class="coment_item_cintent">
                                                  	<p>${comment.content }</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                              	</c:forEach>     
                                </div>
                                <!-- Leave a comment -->
                                <div class="leave_coment">
                                    <div class="col-md-12"> 
                                        <div class="tv-comment-title">Leave a comment</div>
                                        <div class="leave_coment_content"> 
                                           <form action="commentWrite.yy" method="post">
                                           <div class="row">
                                           		<input type="hidden" id="writer" name="writer" value="${userName }"/>
												<input type="hidden" id="writerId" name="writerId" value="${userId }"/>
												<input type="hidden" id="linkedArticleNum" name="linkedArticleNum" value="${board.idx }"/>
                                                
                                                <div class="col-md-10 input"> 
                                                    <textarea placeholder="덧글을 입력해주세요" required="required"  id="content" name="content" rows="10"></textarea>
                                                </div>

                                                <div class="col-md-2 tv-comment-send" style="margin-top: 8px;"> 
                                                    <input class="bottom_color" name="btnsend" value="rewrite" type="reset">
                                                    <input class="bottom_color" name="btnsend" value="Send" type="submit">
                                                </div>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!-- Leave a comment end --> 

                                <!-- Button Bundle -->

                                <div class="col-md-12" align="center">
                                    <c:choose>
										<c:when test="${board.writerId == userId }">
											<input type="button" class="btn input-contact-send" style="border-radius: 5px;" value="Delete"  onclick="moveAction(1)"/>
	                                        <input type="button" class="btn input-contact-send" style="border-radius: 5px;" value="Modify" onclick="moveAction(2)" />
											<input type="button" class="btn input-contact-send" style="border-radius: 5px;" value="Goto-List"  onclick="moveAction(3)"/>
	                                        
										</c:when>
										<c:otherwise>
											<input type="button" class="btn input-contact-send" style="border-radius: 5px;" value="Recommend"  onclick="moveAction(4)"/>
	                                        <input type="button" class="btn input-contact-send" style="border-radius: 5px;" value="Goto-List" onclick="moveAction(3)" />
										</c:otherwise>
									</c:choose>    
                                </div>   
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--End Blog Grid Section-->


</body>
</html>


<%-- 

<div class="wrapper">
	<table class="boardView">
		<tr>
			<td colspan="4"><h3>${board.subject }</h3></td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<th>조회수</th>
			<th>추천수</th>
			<th>작성일</th>		
		</tr>
		<tr>
			<td>${board.writer }</td>
			<td>${board.hitcount }</td>
			<td>${board.recommendcount }</td>
			<td>${board.writeDate }</td>		
		</tr>
		
		<tr>
			<th colspan="4">내용</th>
		</tr>
		<c:if test="${board.fileName != null }">
		<tr>
			<td colspan="4" align="left"><span class="date">첨부파일:&nbsp;
			<a href="/SpringBoard/download/download.yy?idx=${board.idx }&${board.fileName }" target="_blank">${board.fileName}</a></span>
			</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="4" align="left"><p>${board.content }</p><br/><br/></td>
		</tr>
	</table>
	
	<table class="commentView">
		<tr>
			<th colspan="2">덧글</th>
		</tr>
		<c:forEach var="comment" items="${commentList}">
		<tr>
			<td class="writer">
				<p>${comment.writer}
				<c:if test="${comment.writerId == userId }">
					<br/><a onclick="commentDelete(${comment.idx}, ${board.idx })"><small>댓글 삭제</small></a>
				</c:if>
				</p>
			</td>	
			<td class="content" align="left">
				<span class="date">${comment.writeDate }</span>
				<p>${comment.content }</p>
			</td>
		</tr>
		</c:forEach>
		
		<tr>
			<td class="writer"><strong>댓글 쓰기</strong></td>
			<td class="content">
			<form action="commentWrite.yy" method="post">
				<input type="hidden" id="writer" name="writer" value="${userName }"/>
				<input type="hidden" id="writerId" name="writerId" value="${userId }"/>
				<input type="hidden" id="linkedArticleNum" name="linkedArticleNum" value="${board.idx }"/>
				<textarea id="content" name="content" class="commentForm"></textarea>
				<input type="submit" value="확인" class="commentBt"/>
			</form>	
			</td>
		</tr>
	</table>
	
	<br/>
	<c:choose>
		<c:when test="${board.writerId == userId }">
			<input type="button" value="삭제" class="writeBt" onclick="moveAction(1)"/>
			<input type="button" value="수정" class="writeBt" onclick="moveAction(2)"/>
			<input type="button" value="목록" class="writeBt" onclick="moveAction(3)"/>
		</c:when>
		<c:otherwise>
			<input type="button" value="추천" class="writeBt" onclick="moveAction(4)"/>
			<input type="button" value="목록" class="writeBt" onclick="moveAction(3)"/>
		</c:otherwise>
	</c:choose>

</div> --%>