<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 글 쓰기</title>


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



<link href="<%=request.getContextPath()%>/css/board.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	function writeFormCheck() {
		if($("#subject").val() == null || $("#subject").val() == "") {
			alert("제목을 입력해 주세요");
			$("#subject").focus();
			return false
		}
		if($("#content").val() == null || $("#content").val() == "") {
			alert("내용을 입력해 주세요");
			$("#content").focus();
			return false
		}
	}
</script>

</head>
<body id="blogsingleleftsidebar">


        <!--Contact Us Section-->
        <section id="contact-us" class="tv-section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="contact-block-color">
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="contact-us-box">
                                        <table>
                                            <tr>
                                                <h3 class="contact-title" align="center">
                                                   	 새 글 쓰기
                                                </h3>
                                            </tr>
                                        </table>
                                           
                                        <div class="row">
                                            <div class="col-md-2 col-sm-12 col-xs-12 contact-box-area">
                                                <div class="contact-form">
                                                    <form>

                                                    </form>
                                                </div>
                                            </div>
                                            <div class="col-md-8 col-sm-12 col-xs-12 contact-box-area">
                                                <div class="contact-form">
                                                    <form action="write.yy" method="post" onsubmit="return writeFormCheck()" enctype="multipart/form-data">
                                                        <div class="form-group">
                                                            <input class="form-control" placeholder="Subject" type="text" id="subject" name="subject" >
															<input type="hidden" id="writer" name="writer" value="${userName }"/>
															<input type="hidden" id="writerId" name="writerId" value="${userId }" />	
                                                        </div>
                                                        <div class="form-group"  >
                                                            <textarea style="height: 300px;" class="form-control" placeholder="Content" id="content" name="content"></textarea>
                                                        </div>
                                                         <div class="form-group">
                                                            <!-- <button type="submit" class="btn btn-contact-send">File Select</button> -->
                                                            <input type="file" id="file" name="file" /><span class="date">&nbsp;&nbsp;*&nbsp;임의로 파일명이 변경될 수 있습니다.</span>
                                                        </div>

                                                        <div class="form-group" align="center">
															 <input style="border-radius: 5px;" type="reset" class="btn input-contact-send" value="Rewrite" />
                                                            <input style="border-radius: 5px;" type="submit" class="btn input-contact-send" value="Complete" />
                                                        </div>    
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--Contact Us Section-->
       

</body>
</html>