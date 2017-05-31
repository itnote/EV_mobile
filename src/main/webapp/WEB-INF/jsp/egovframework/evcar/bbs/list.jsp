<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" language="java" %>
    <title>EVCAR</title>
</head>
<body>
<form name="listForm" action="<c:url value='/evcar/bbs/list.mdo'/>" method="post" class="search">
</form>
    <h3 class="sr-only">게시판 목록 입니다.</h3>



    <ul class="board-list">
        <tbody>
            <c:if test="${BbsList =='[]'}">
                <li>
                    <h4>게시판 내역이 없습니다.</h4>
                </li>
            </c:if>
        <c:forEach var="Bbs" items="${BbsList}" varStatus="status">
        <li>
            <a href="">
                <h4><c:out value="${Bbs.nttSj}" /></h4>
                <i><c:out value="${Bbs.lastUpdtPnttm}" /></i>
            </a>
        </li>
            <c:set var="row" value="${row-1}"/>
        </c:forEach>
        <tbody>
    </ul>


    <div class="paging">
        <a href="" class="arrow">이전 <i>◀</i></a>
        <a href="">1</a>
        <strong>2</strong>
        <a href="">3</a>
        <a href="">4</a>
        <a href="">5</a>
        <a href="" class="arrow">다음 <i>▶</i></a>
    </div>
</body>
</html>