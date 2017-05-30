<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%
String servletPath = request.getServletPath();
String bodyClass = "";
switch (servletPath){
    case "/main.mdo":
        bodyClass="main";
        break;
    default:
        bodyClass="sub";
        break;
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><decorator:title default="INTRANET"/></title>

    <link href="<c:url value="/assets/css/style.css"/>" rel="stylesheet">
    <!-- javaScript  -->
    <script src="<c:url value="/assets/js/jquery-1.11.3.min.js"/>"></script>
    <script src="<c:url value="/assets/js/common.js"/>"></script>
</head>
<body class="<%=bodyClass%>">

<!-- S:skip_navi -->
<div class="skip_navi"> <a class="sr-only sr-only-focusable" href="#header">헤더 바로가기</a> <a class="sr-only sr-only-focusable" href="#content">본문 바로가기</a> <a class="sr-only sr-only-focusable" href="#footer">푸터 바로가기</a> </div>
<!-- E:skip_navi -->

<!-- S:header -->
<% if("main".equals(bodyClass)) {%>
<header id="header">
    <h1><a href="/">중속충전기</a></h1>
</header>
<% } else { %>
<header id="header">
    <h1 class="sr-only"><a href="/">중속충전기</a></h1>
    <h2><a href="/">알림</a></h2>

    <button class="back" href="">뒤로가기<i class="fi icon-back"></i></button>
    <button class="menu" href="">메뉴열기<i class="fi icon-menu"></i></button>
</header>
<nav id="sub-menu">
    <ul>
        <li><a href="">로그인</a></li>
        <li><a href="">카드관리</a></li>
        <li><a href="">충전기정보</a></li>
        <li><a href="">이력조회</a></li>
        <li><a href="">알림</a></li>
        <li><a href="">회원가입</a></li>
        <li><a href="">서비스관리</a></li>
        <li><a href="">전기차소식</a></li>
        <li><a href="">FAQ</a></li>
        <li><a href="">공지사항</a></li>
        <li><a href="">도움말</a></li>
    </ul>
    <button class="close" herf="">메뉴닫기<i class="fi icon-close"></i></button>
</nav>
<% } %>
<!-- E:header -->

<!-- S:content -->
<article id="content">
    <decorator:body/>
</article>
<!-- E:content -->

<!-- S:footer -->
<footer id="footer">
    <address>
    </address>
</footer>
<!-- E:footer -->
</body>
</html>