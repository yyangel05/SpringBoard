<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join Member</title>
<!-- <link href="../css/main.css" rel="stylesheet" type="text/css"> -->
<!-- <link href="/SpringBoard/css/main.css" rel="stylesheet"> -->

  <!-- Bootstrap core CSS -->
    <link href="/SpringBoard/style/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="/SpringBoard/style/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="/SpringBoard/style/css/agency.min.css" rel="stylesheet">





<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<c:if test="${errCode == null }">
	<c:set var="errCode" value="\"\""></c:set>	
</c:if>

<script type="text/javascript">
	function errCodeCheck() {
		
		var errCode = ${errCode};
		if(errCode != null || errCode != "") {
			switch (errCode) {
			case 1:
				alert("이미 가입된 이메일 주소입니다!");
				break;
			case 2:
				alert("회원가입 처리가 실패하였습니다. 잠시 후 다시 시도해주세요");
				break;
			}
		}
	}
	
	function passwordCheck() {
		
		if($("#userPw").val() != $("#userPwCheck").val()) {
			
			alert("패스워드 입력이 일치하지 않습니다");
			$("#userPwCheck").focus();
			return false;
		}
		return true;
	}
</script>



</head>
<body onload="errCodeCheck()" id="page-top">

<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
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
    </nav>


<!--  --------------------------------------------------------------------------------- -->
	<spring:hasBindErrors name="MemberModel"/>
	<form:errors path="MemberModel"/>
	<form action="join.yy" method="post" onsubmit="return passwordCheck()">


    <!-- Contact -->
    <section id="contact">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <h2 class="section-heading text-uppercase">회원가입</h2>
            <h3 class="section-subheading text-muted">메일주소로 회원가입을 시도해주세요</h3>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <%-- <form id="contactForm" name="sentMessage" novalidate="novalidate"> --%>
              <div class="row">

                <div class="col-md-4">
                </div>


                <div class="col-md-4">
                  <div class="form-group">
                    <input class="form-control" id="userId" name="userId" type="text" placeholder="Your mail address *" required="required" data-validation-required-message="Please enter your name.">
                    <p class="help-block text-danger"></p>
                  </div>
                  <div class="form-group">
                    <input class="form-control" id="userPw" name="userPw" type="password" placeholder="Your password *" required="required" data-validation-required-message="Please enter your email address.">
                    <p class="help-block text-danger"></p>
                  </div>
                  <div class="form-group">
                    <input class="form-control" id="userPwCheck" name="userPwCheck" type="password" placeholder="Your password re *" required="required" data-validation-required-message="Please enter your name.">
                    <p class="help-block text-danger"></p>
                  </div>
                  <div class="form-group">
                    <input class="form-control" id="userName" name="userName" type="text" placeholder="Your name *" required="required" data-validation-required-message="Please enter your email address.">
                    <p class="help-block text-danger"></p>
                  </div>
                </div>

                 <div class="col-md-4">
                </div>
              
                <div class="clearfix"></div>
                <div class="col-lg-12 text-center">

                  <div id="success"></div>
                  <button id="sendMessageButton" class="btn btn-primary btn-xl text-uppercase" type="submit">register</button>
                  <button id="sendMessageButton" class="btn btn-primary btn-xl text-uppercase" type="reset">rewrite</button>
                </div>


              </div>
           <%--  </form> --%>
          </div>
        </div>
      </div>
    </section>

	</form>

    <!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <span class="copyright">Copyright &copy; Your Website 2018</span>
          </div>
          <div class="col-md-4">
            <ul class="list-inline social-buttons">
              <li class="list-inline-item">
                <a href="#">
                  <i class="fa fa-twitter"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <i class="fa fa-facebook"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <i class="fa fa-linkedin"></i>
                </a>
              </li>
            </ul>
          </div>
          <div class="col-md-4">
            <ul class="list-inline quicklinks">
              <li class="list-inline-item">
                <a href="#">Privacy Policy</a>
              </li>
              <li class="list-inline-item">
                <a href="#">Terms of Use</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>





<%-- <div class="wrapper">
	<h3>회원가입</h3>
	<spring:hasBindErrors name="MemberModel"/>
	<form:errors path="MemberModel"/>
	<form action="join.yy" method="post" onsubmit="return passwordCheck()">
		<fieldset>
		
			<label for="userId">&nbsp;메일주소 : </label>
			<input type="text" id="userId" name="userId" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.userId"/></span><br />
			
			<label for="userPw">&nbsp;비밀번호 : </label>
			<input type="password" id="userPw" name="userPw" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.userPw"/></span><br />
			
			<label for="userPwCheck">&nbsp;비밀번호 확인 : </label>
			<input type="password" id="userPwCheck" name="userPwCheck" class="loginInput"/><br />
		
			<label for="userName">&nbsp;회원이름 : </label>
			<input type="text" id="userName" name="userName" class="loginInput"/>
			<span class="error"><form:errors path="MemberModel.userName"/></span><br />
			
			<center>
				<input type="submit" value="확인" class="submitBt"/>
				<input type="reset" value="재작성" class="submitBt"/><br /><br />
				<a href="<%=request.getContextPath() %>/login.yy">로그인 페이지로</a>
			</center>
		</fieldset>
	</form>
</div> --%>

</body>
</html>