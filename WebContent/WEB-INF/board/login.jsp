<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<!-- <link href="/SpringBoard/css/main.css" rel="stylesheet">  기존 css -->

<c:if test="${errCode == null }">
	<c:set var="errCode" value="\"\""></c:set>
</c:if>

<script type="text/javascript">
	function checkErrCode() {
		
		var errCode = ${errCode};
		if(errCode != null || errCode != "") {
			switch (errCode) {
			case 1:
				alert("가입된 이메일 주소가 아닙니다!");
				break;
			case 2:
				alert("비밀번호가 일치하지 않습니다!");
				break;
			case 3:
				alert("회원가입 처리가 완료되었습니다! 로그인하실?");
				location.href = "<%=request.getContextPath()%>/login.yy";
				break;	
			}
		}
	}

</script>
</head>

<body id="blogsingleleftsidebar" onload="checkErrCode()">

<!-- Navigation -->
   <!--  <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">SpringBoard</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav text-uppercase ml-auto">
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="/SpringBoard/login.yy">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="/SpringBoard/member/join.yy">Register</a>
            </li>
          </ul>
        </div>
      </div>
    </nav> -->
<!-- --------------------------------------------------------------------------------------- -->

	<spring:hasBindErrors name="LoginModel"/>
	<form:errors path="LoginModel"/>
	<form action="login.yy" method="post">


  <!--Banner Section-->
        <div class="tv-banner-image tv-blog-banner-img" style="background: rgba(0, 0, 0, 0) url('/SpringBoard/style/images/Image24.jpeg') no-repeat scroll center top / cover;">
            <div class="tv-banner-title">
                <h1>로그인</h1>
            </div>
        </div>
        <!--End Banner Section-->

        <!--Contact Us Section-->
        <section id="contact-us" class="tv-section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="contact-block-color">
                            <div class="row">
                                    <div class="contact-us-box">
                                        <div class="row">
                                            <div class="col-md-4 col-sm-4 col-xs-12 contact-box-area"></div>
                                            <div class="col-md-4 col-sm-4 col-xs-12 contact-box-area">
                                                <h3 class="contact-title" align="center">
                                                    login Form
                                                </h3>
                                                <div class="contact-form">
                                                    <form>
                                                       
                                                        <div class="form-group">
                                                            <input class="form-control" placeholder="Your mail address *" type="text"  id="userId" name="userId" value="${userId}">
                                                        </div>
                                                        <div class="form-group">
                                                            <input class="form-control" placeholder="Your password *" type="password" id="userPw" name="userPw" >
                                                        </div>
                                                        <div align="center">
                                                            <button type="submit" class="btn btn-contact-send">login</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                           <div class="col-md-4 col-sm-4 col-xs-12 contact-box-area"></div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--Contact Us Section-->


    <!-- Contact -->
   <%--  <section id="contact">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <h2 class="section-heading text-uppercase">로그인 화면</h2>
            <h3 class="section-subheading text-muted">메일주소로 로그인을 시도해주세요</h3>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
              <div class="row">

                <div class="col-md-4">
                </div>

                <div class="col-md-4">
                  <div class="form-group">
                    <input class="form-control" id="userId" name="userId"  type="text" placeholder="Your mail address *" required="required" value="${userId}" >
                    <span class="error"><form:errors path="LoginModel.userId"/></span><br/>
                    <p class="help-block text-danger"></p>
                  </div>
                  <div class="form-group">
                    <input class="form-control" id="userPw" name="userPw"  type="password" placeholder="Your password *" required="required" >
                    <span class="error"><form:errors path="LoginModel.userPw"/></span><br/><br/>
                    <p class="help-block text-danger"></p>
                  </div>
                </div>

                 <div class="col-md-4">
                </div>
              
                <div class="clearfix"></div>
                <div class="col-lg-12 text-center">

                  <div id="success">
                  <button id="sendMessageButton" class="btn btn-primary btn-xl text-uppercase" type="submit">Login</button>
                </div>


              </div>
          </div>
        </div>
      </div>
    </section>
    
    </form> --%>
   

<%-- <div class="wrapper">
	<h3>스프링 게시판</h3>
	<spring:hasBindErrors name="LoginModel"/>
	<form:errors path="LoginModel"/>
	<form action="login.yy" method="post">
		<fieldset>
			<label for="userId">메일주소 : </label>
			<input type="text" id="userId" name="userId" class="loginInput" value="${userId}"/>
			<span class="error"><form:errors path="LoginModel.userId"/></span><br/>
			
			<label for="userId">비밀번호 : </label>
			<input type="password" id="userPw" name="userPw" class="loginInput"/>
			<span class="error"><form:errors path="LoginModel.userPw"/></span><br/><br/>
		
			<center>
				<input type="submit" value="로그인" class="submitBt"/><br/><br/>
				<a href="<%=request.getContextPath() %>/member/join.yy">회원가입</a>
			</center>
		</fieldset>
	</form>
</div> --%>






</body>
</html>