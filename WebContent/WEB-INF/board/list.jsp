<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글 목록보기</title>
<%-- <link href="<%=request.getContextPath()%>/css/board.css" rel="stylesheet" type="text/css" /> --%>

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


<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript">

	function selectedOptionCheck() {
		$("#type > option[value=<%=request.getParameter("type")%>]").attr("selected", "true");
	}
	
	function moveAction(where) {
		switch (where) {
		case 1:
			location.href="write.yy";
			break;

		case 2:
			location.href="list.yy";
			break;
		}
	}

</script>

</head>
<body id="bloglist" onload="selectedOptionCheck()">

	<!--Subscribe us now-->
        <section id="subscribe">
            <div class="tv-bg-color tv-section-padding-30">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                        </div>                       
                        <div class="col-md-6 col-sm-6 col-xs-12">
							<form action="list.yy" method="get">
                                <div class="input-group">
                                    <table>
                                        <tr>
                                            <td>
                                                <select style="width: 100px; height: 35px; margin-right: 20px;" id="type" name="type">
                                              		<option value="subject">제목</option>
													<option value="content">내용</option>
													<option value="writer">작성자</option> 
                                                </select>
                                            </td>
                                            <td>
                                               <input style="width:  250px;" placeholder="Search Keyword" type="text" id="keyword" name="keyword" class="form-control tv-link-box placeholder-fix" 
													  value="<%if(request.getParameter("keyword") != null) {out.print(request.getParameter("keyword"));}
															   else{out.print("");}%>"/>
                                            </td>
                                            <td>
                                            <span class="input-group-btn">
                                                <button  style="width:  100px;" type="submit" class="btn tv-link-button">검색하기</button>
                                            </span>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--End Subscribe us now-->
	

        <!--Blog list Section-->
        <section id="innerbloglist" class="tv-section-padding">
            <div class="container">
                <div class="row tv-blog-row-margin">
                    <div class="col-md-12 padding-0 tv-blog-block-bgcolor col-sm-12 col-xs-12">
                        <div class="col-md-12 padding-0 col-sm-12 col-xs-12">
	                        <table class="table">
	                            <thead>
	                                <tr>
	                                    <th class="text-center">글번호</th>
	                                    <th class="text-center" width="500px">글내용</th>
	                                    <th class="text-center">작성자</th>
	                                    <th class="text-center">조회수</th>
	                                    <th class="text-center">추천수</th>
	                                    <th class="text-center">작성일</th>
	                                </tr> 
	                            </thead>
	                            <tbody>
	                                <c:forEach var="board" items="${boardList}">
										<tr>
											<td class="text-center">${board.rnum}</td>
											<td align="left" class="text-center">
												<c:if test="${board.comment >=10 }"><img src="<%=request.getContextPath() %>/img/hit.jpg"/></c:if>
												<a href="view.yy?idx=${board.idx}">${board.subject}</a></td>
											<td class="text-center">
												<c:choose>
													<c:when test="${board.writerId == userId }"> 
														<strong>${board.writer}</strong>
													</c:when>
													<c:otherwise>${board.writer}</c:otherwise> 
												</c:choose>
											</td>	
											<td class="text-center">${board.comment}</td>	
											<td class="text-center">${board.hitcount}</td>	
											<td class="text-center">${board.recommendcount}</td>	
											<td class="text-center">${board.writeDate}</td>
										</tr>
									</c:forEach>
	                            </tbody>
	                        </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12 tv-blog-block-bgcolor col-sm-12 col-xs-12">
                <div class="pagination">
                    <div class="container">
                        <div class="row">
                            <ul>
                                <li><a><i class="fa fa-angle-left"></i></a></li>
                                <li><span class="active">1</span></li>
                                <li><a>2</a></li>
                                <li><a>3</a></li>
                                <li><a>4</a></li>
                                <li><a>5</a></li>
                                <li><span class="pagination-dots">...</span></li>
                                <li><a>555</a></li>
                                <li><a><i class="fa fa-angle-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div align="center">
             ${pageHtml} 
            </div>
            
        </section>
        <!--End Blog list Section-->
	
	
	
	 <div align="center">
		<input class="btn input-contact-send"  type="button" value="목록" onclick="moveAction(2)"/>
		<input class="btn input-contact-send" type="button" value="쓰기" onclick="moveAction(1)"/>
	 </div>
	
	<%-- ${pageHtml} --%>

</body>
</html>