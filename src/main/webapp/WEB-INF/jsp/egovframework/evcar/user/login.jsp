<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>EVCAR</title>
</head>
<body>

<form name="user" method="POST" action="/user/loginAct.mdo" onsubmit="">
<fieldset>
    <legend class="sr-only">로그인정보 입력폼 입니다.</legend>

    <label for="usrId">아이디를 입력하세요</label>
    <input id="usrId" name="usrId" placeholder="아이디" type="text" value="">

    <label for="usrPwd">비밀번호를 입력하세요</label>
    <input id="usrPwd" name="usrPwd" placeholder="비밀번호" class="passwd" type="password" value="">

    <p class="btn-set">
        <a class="btn main" href="javascript:;" onclick="loginAct()">로그인</a>
        <a class="btn sub" href="">회원가입</a>
    </p>
</fieldset>
</form>
<script type="text/javascript">
$(document).ready(function(){
   <c:if test="${!empty message}">
    alert('${message}');
    </c:if>
});
function loginAct(){
    document.user.submit();
}
</script>
</body>
</html>
