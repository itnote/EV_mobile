<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>카드관리</title></head>
    <body>
        <div class="card-info">
            <div class="card">
                <strong>결제카드</strong>
            </div>
        </div>
        <dl>
            <c:forEach items="${cardList}" var="item">
                <dt>${item.cardNm}</dt>
                <dd>${item.cardNo}</dd>
            </c:forEach>
        </dl>
        <c:choose>
            <c:when test="${empty cardList}">
                <p class="btn-set full">
                    <a class="btn sub full" href="<c:url value="/card/register.mdo"/>">카드정보등록</a>
                </p>
            </c:when>
            <c:otherwise>
                <p class="btn-set full">
                    <a class="btn sub full" href="<c:url value="/card/register.mdo"/>">카드정보변경</a>
                </p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
