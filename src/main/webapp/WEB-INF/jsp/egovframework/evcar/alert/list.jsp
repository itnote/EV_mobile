<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" language="java" %>
    <title>알림정보</title>
</head>
<body>
    <form name="listForm" action="<c:url value='/evcar/alert/list.mdo'/>" method="post" class="search">
    </form>
    <h3 class="sr-only">알림 목록입니다.</h3>
    <ul class="alert-list">
        <tbody>
        <c:if test="${alertList =='[]'}">
            <li>
                <h4>알림 내역이 없습니다.</h4>
            </li>
        </c:if>
        <c:forEach var="alert" items="${alertList}" varStatus="status">
        <li>
            <i><c:out value="${alert.createdAt}" /></i>
            <h4><c:out value="${alert.alarm}" /></h4>
            <p></p>
        </li>
        <c:set var="row" value="${row-1}"/>
        </c:forEach>
        </tbody>
    </ul>
</body>
</html>