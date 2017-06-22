<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" language="java" %>
    <title>이력조회</title>
</head>
<body>
<form name="listForm" action="<c:url value='/evcar/historySearch/list.mdo'/>" method="post" class="search">
    <input name="pageIndex" type="hidden" value="<c:out value='${BbsVO.pageIndex}'/>"/>
</form>
    <h3 class="sr-only">충전기 목록입니다.</h3>

    <ul class="history-list">
      <tbody>
          <c:if test="${historySearchList =='[]'}">
              <li>
                  <h4>이력조회 내역이 없습니다.</h4>
              </li>
          </c:if>
          <c:forEach var="history" items="${historySearchList}" varStatus="status">
            <li>
                <a href="/evcar/historySearch/info.mdo?setSno=${history.setSno}&usrSno=${history.usrSno}">
                    <h4>충전기ID <strong><c:out value="${history.cid}" /></strong></h4>
                    <i><c:out value="${history.pdt}" /><c:out value="${history.ptm}" /></i>
                    <p class="price"><c:out value="${history.cid}" />원/kw</p>
                    <p class="stat standby">결제대기</p>
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