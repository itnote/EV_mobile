<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" language="java" %>
    <title>EVCAR</title>
</head>
    <body>
        <h3 class="sr-only">현재충전상태 입니다.</h3>
        <dl>
            <dt>충전기ID</dt>
            <dd>${HistorySearchVO.cid}</dd>
            <dt>소요시간</dt>
            <dd>${HistorySearchVO.pdt}${HistorySearchVO.ptm}</dd>
            <dt>충전량</dt>
            <dd>${HistorySearchVO.cwh}</dd>
            <dt>결제금액</dt>
            <dd>${HistorySearchVO.setAmt} <i>충전요금 1kwh 당 313.1원</i></dd>
        </dl>
        <p class="btn-set full">
            <a class="btn sub" href="/evcar/historySearch/list.mdo">충전이력조회</a>
        </p>
    </body>
</html>