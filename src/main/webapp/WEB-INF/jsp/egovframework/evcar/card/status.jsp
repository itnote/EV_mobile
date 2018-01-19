<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>카드관리</title></head>
    <body>
        <div class="card-info">
            <div class="card">
                <strong>결제카드</strong>
                <%--<p>${loginVO.usrCard}</p>--%>
            </div>
        </div>
        <dl>
            <%--
            <dt>총 결제금액</dt>
            <dd><c:out value="${loginVO.totSetamt}" default="0"/>원/kw</dd>
            <dt>총 주입충전량</dt>
            <dd><c:out value="${loginVO.totChw}" default="0"/>kw</dd>
            <dt>총 미수금액</dt>
            <dd class="red"><c:out value="${loginVO.totUnclAmt}" default="0"/>원/kw</dd>
            --%>
            <c:forEach items="${cardList}" var="item">
                <dt>${item.fnName}</dt>
                <dd>${item.cardSno}</dd>
            </c:forEach>
        </dl>
        <p class="btn-set full">
            <a class="btn sub full" href="<c:url value="/card/register.mdo"/>">카드정보등록</a>
            <%--<a class="btn main half" href="">미수금 결제하기</a>--%>
        </p>
    </body>
</html>
