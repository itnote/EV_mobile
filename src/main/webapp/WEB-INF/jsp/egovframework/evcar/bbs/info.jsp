<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" language="java" %>
    <title>EVCAR</title>
</head>
<body>
    <h3 class="sr-only">게시판 상세 입니다.</h3>
    <div class="board-view">
        <div class="head">
            <h4><c:out value="${BbsVO.nttSj}" /></h4>
            <ul>
                <li>
                    조회수 <strong><c:out value="${BbsVO.rdcnt}" /></strong>
                </li>
                <li>
                    작성일 <strong><c:out value="${BbsVO.frstRegistPnttm}" /></strong>
                </li>
            </ul>
        </div>
        <div class="content">
            <c:out value="${BbsVO.nttCn}" />
        </div>
    </div>
    <p class="btn-set full">
        <a class="btn sub" href="/evcar/bbs/list.mdo?bbsId=${BbsVO.bbsId}&pageIndex=${BbsVO.pageIndex}">목록으로</a>
    </p>
</body>
</html>