<%@ page import="org.apache.xpath.SourceTree" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>메인</title>
</head>
<body>

<nav id="main_menu">
    <ul>

        <c:if test="${empty loginVO}">
        <li class="w2x">
            <a href="<c:url value="/user/login.mdo"/>">
                <p>로그인</p><i class="fi icon-peoples"></i></a>
        </li>
        </c:if>
        <c:if test="${!empty loginVO}">
        <li class="w2x">
            <a href="<c:url value="/user/logout.mdo"/>">
                <p>로그아웃</p><i class="fi icon-peoples"></i></a>
        </li>
        </c:if>

        <c:if test="${!empty loginVO}">
        <li>
            <a href="<c:url value="/card/status.mdo"/>">
                <p>카드관리</p><i class="fi icon-card"></i></a>
        </li>
        </c:if>
        <li>
            <a href="<c:url value="https://jevs.evwhere.co.kr"/>"   target="_blank">
                <p>충전기정보</p><i class="fi icon-plug"></i></a>
        </li>
        <c:if test="${!empty loginVO}">
        <li>
            <a href="/evcar/historySearch/list.mdo">
                <p>이력조회</p><i class="fi icon-list"></i></a>
        </li>
        </c:if>
        <c:if test="${!empty loginVO}">
        <li>
            <a href="/evcar/alert/list.mdo">
                <p>알림</p><i class="fi icon-ring "></i></a>
        </li>
        </c:if>
        <c:if test="${empty loginVO}">
        <%--<li>--%>
            <%--<a href="<c:url value="/user/join.mdo"/>">--%>
                <%--<p>회원가입</p><i class="fi icon-sign"></i></a>--%>
        <%--</li>--%>

            <li>
                <a href="<c:url value="/user/agree.mdo"/>">
                    <p>회원가입</p><i class="fi icon-sign"></i></a>
            </li>
        </c:if>

        <%--<li>--%>
            <%--<a href="/evcar/bbs/list.mdo?bbsId=BBSMSTR_000000000004" target="_blank">--%>
                <%--<p>전기차소식</p><i class="fi icon-ev"></i></a>--%>
        <%--</li>--%>


        <li>
            <a href="http://www.jejuevservice.com/bbs/board.php?bo_table=qna&me_code=6020"  target="_blank">
                <p>FAQ</p><i class="fi icon-faq"></i></a>
        </li>
        <%--<li>--%>
            <%--<a href="/evcar/bbs/list.mdo?bbsId=BBSMSTR_000000000001">--%>
                <%--<p>공지사항</p><i class="fi icon-notice"></i></a>--%>
        <%--</li>--%>
        <li>
            <a href="http://jejuevservice.com/bbs/board.php?bo_table=notice&me_code=6010" target="_blank">
                <p>공지사항</p><i class="fi icon-notice"></i></a>
        </li>


        <%--<li>--%>
            <%--<a href="/evcar/bbs/list.mdo?bbsId=BBSMSTR_000000000002">--%>
                <%--<p>도움말</p><i class="fi icon-help"></i></a>--%>
        <%--</li>--%>


    </ul>
</nav>
<%--<h2 class="mark">본 사이트는 웹접근성품질인증을 획득하였습니다.</h2>--%>
<footer id="footer">
    <address>
    </address>
</footer>
</body>
</html>
