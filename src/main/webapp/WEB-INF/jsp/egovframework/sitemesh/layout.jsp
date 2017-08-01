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
    case "/user/login.mdo":
        bodyClass="login";
        break;
    case "/user/loginAct.mdo":
        bodyClass="login";
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
    <title>EVCAR - <decorator:title default="home"/></title>

    <link href="<c:url value="/assets/css/style.css"/>" rel="stylesheet">
    <!-- javaScript  -->
    <script src="<c:url value="/assets/js/jquery-1.11.3.min.js"/>"></script>
    <script src="<c:url value="/assets/js/jquery.validate.js"/>"></script>

    <script src="<c:url value="/assets/js/common.js"/>"></script>
    <script src="<c:url value="/assets/js/evcar.js"/>"></script>

    <script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=21b110c76efc5127c52ddc183aa80a46"></script>
    <script src="<c:url value="/assets/js/initMap.js"/>"></script>

    <script src="<c:url value="/assets/js/cleave.min.js"/>"></script>
    <script src="<c:url value="/assets/js/cleave-phone.kr.js"/>"></script>
</head>
<body class="<%=bodyClass%>">

<!-- S:skip_navi -->
<div class="skip_navi"> <a class="sr-only sr-only-focusable" href="#header">헤더 바로가기</a> <a class="sr-only sr-only-focusable" href="#content">본문 바로가기</a> <a class="sr-only sr-only-focusable" href="#footer">푸터 바로가기</a> </div>
<!-- E:skip_navi -->

<!-- S:header -->
<% if(!"sub".equals(bodyClass)) {%>
<header id="header">
    <h1><a href="/">EVCAR</a></h1>
</header>
<% } else { %>
<header id="header">
    <h1 class="sr-only"><a href="/">EVCAR</a></h1>
    <h2><a href="<c:url value="/main.mdo"/>"><decorator:title default="EVCAR"/></a></h2>

    <button class="back" href="javascript:;" onclick="history.back();">뒤로가기<i class="fi icon-back"></i></button>
    <button class="menu" href="javascript:;">메뉴열기<i class="fi icon-menu"></i></button>
</header>
<nav id="sub-menu">
    <ul>
        <c:if test="${empty loginVO}">
        <li>
            <a href="<c:url value="/user/login.mdo"/>">로그인</a>
        </li>
        </c:if>
        <c:if test="${!empty loginVO}">
            <li>
                <a href="<c:url value="/user/logout.mdo"/>">로그아웃</a>
            </li>
        </c:if>
        <c:if test="${!empty loginVO}">
        <li>
            <a href="/card/status.mdo">카드관리</a>
        </li>
        </c:if>
        <li>
            <a href="/charger/station.mdo">충전기정보</a>
        </li>
        <c:if test="${!empty loginVO}">
        <li>
            <a href="/evcar/historySearch/list.mdo">이력조회</a>
        </li>
        </c:if>
        <c:if test="${!empty loginVO}">
        <li>
            <a href="/evcar/alert/list.mdo">알림</a>
        </li>
        </c:if>
        <c:if test="${empty loginVO}">
        <li>
            <a href="<c:url value="/user/join.mdo"/>">회원가입</a>
        </li>
        </c:if>
        <li>
            <a href="/evcar/bbs/list.mdo?bbsId=BBSMSTR_000000000004">전기차소식</a>
        </li>
        <li>
            <a href="/evcar/bbs/list.mdo?bbsId=BBSMSTR_000000000003">FAQ</a>
        </li>
        <li>
            <a href="/evcar/bbs/list.mdo?bbsId=BBSMSTR_000000000001">공지사항</a>
        </li>
        <li>
            <a href="/evcar/bbs/list.mdo?bbsId=BBSMSTR_000000000002">도움말</a>
        </li>
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