<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="ko">
<head>
    <title>EVCAR</title>
</head>

<body>
<article id="content">
    <h3 class="sr-only">알수없는 오류가 발생하였습니다.</h3>

    <h4>알수없는 오류가 발생하였습니다.</h4>

    <p class="btn-set full">
        <a class="btn sub" href="javascript:;" onclick="fncGoAfterErrorPage()">뒤로가기</a>
    </p>

</article>
<script language="javascript">
    function fncGoAfterErrorPage() {
        history.back(-2);
    }
</script>
</body>
</html>
