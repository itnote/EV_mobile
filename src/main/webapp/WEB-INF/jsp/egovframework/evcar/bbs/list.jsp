<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" language="java" %>
    <title>EVCAR</title>
</head>
<body>
<form name="listForm" action="<c:url value='/evcar/bbs/list.mdo'/>" method="GET" class="search">
    <input name="pageIndex" type="hidden" value="<c:out value='${BbsVO.pageIndex}'/>"/>
    <input name="bbsId" type="hidden" value="<c:out value='${BbsVO.bbsId}'/>"/>
</form>
    <h3 class="sr-only">게시판 목록 입니다.</h3>
    <ul class="board-list">
        <tbody>
            <c:if test="${BbsList =='[]'}">
                <li>
                    <h4>게시판 리스트가 없습니다.</h4>
                </li>
            </c:if>
        <c:forEach var="Bbs" items="${BbsList}" varStatus="status">
        <li>
            <a href="/evcar/bbs/info.mdo?bbsId=${Bbs.bbsId}&nttId=${Bbs.nttId}&pageIndex=${BbsVO.pageIndex}">
                <h4><c:out value="${Bbs.nttSj}" /></h4>
                <i><c:out value="${Bbs.frstRegistPnttm}" /></i>
            </a>
        </li>
            <c:set var="row" value="${row-1}"/>
        </c:forEach>
        <tbody>
    </ul>
    <div class="paging">
        <ui:pagination paginationInfo = "${paginationInfo}" type="pageImage" jsFunction="paging"/>
    </div>

<script type="text/javascript">
function paging(pageNum) {
    document.listForm.pageIndex.value = pageNum;
    document.listForm.submit();
}
</script>

</body>
</html>