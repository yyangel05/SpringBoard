<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <title>Bite On Bite</title>
        <meta charset="UTF-8">
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
    
<title><tiles:getAsString name="title"/></title>

</head>
<body>

 <!--DropDown Script-->
        <script>
            $(document).ready(function () {
                $('.Navigation-listItem').click(function (e) {
                    if ($(this).children('.Navigation-list.is-dropdown').hasClass('is-hidden')) {
                        $(".Navigation-list.is-dropdown").addClass('is-hidden');
                        $(this).children('.Navigation-list.is-dropdown').removeClass('is-hidden');
                    } else {
                        $(".Navigation-list.is-dropdown").addClass('is-hidden');
                    }
                });
                $('.Navigation-listItem .Navigation-list.is-dropdown').click(function (e) {
                    e.stopPropagation();
                });
            });

            $(document).click(function () {
                $(".Navigation-list.is-dropdown").addClass('is-hidden');
            });

            $(".Navigation-listItem.is-dropdown").click(function (e) {
                e.stopPropagation();
            });
        </script>
        <!--End DropDown Script-->

<%-- 	<div class="container-fluid">
		<tiles:insertAttribute name="header"/>
		<div class="container">
			<div class="col-md-12">
				<tiles:insertAttribute name="body" />
			</div>	
		</div>
		<tiles:insertAttribute name="footer" />
	</div> --%>
	
<tiles:insertAttribute name="header"/>
<hr/>
<tiles:insertAttribute name="body"/>
<hr/>
<tiles:insertAttribute name="footer"/>	


</body>
</html>