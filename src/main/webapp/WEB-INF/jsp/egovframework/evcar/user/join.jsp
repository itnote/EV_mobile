<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>EVCAR</title>
</head>
<body>

<form name="user" id="user" action="<c:url value="/user/joinAct.mdo"/>" method="POST">
    <fieldset class="sign">
        <legend class="sr-only">회원정보를 입력하세요</legend>
        <h3 class="sr-only">회원가입 폼</h3>

        <h4 class="">회원정보</h4>
        <dl>
            <dt><label for="user-id">아이디</label></dt>
            <dd><input id="user-id" name="usrId" type="text" placeholder="아이디를 입력하세요"></dd>
            <dt><label for="user-passwd">비밀번호</label></dt>
            <dd><input id="user-passwd" name="usrPwd" type="password" placeholder="비밀번호를 입력하세요"></dd>
            <dt><label for="user-repasswd">재입력</label></dt>
            <dd><input id="user-repasswd" name="usrPwd_re" type="password" placeholder="비밀번호를 입력하세요"></dd>
        </dl>

        <h4 class="">기본정보</h4>
        <dl>
            <dt><label for="user-name">이름</label></dt>
            <dd><input id="user-name" name="usrNm" type="text" placeholder="이름을 입력하세요"></dd>
            <dt><label for="user-cel">휴대전화</label></dt>
            <dd><input id="user-cel" name="usrCel" type="text" placeholder="전화번호를 입력하세요"></dd>
            <dt><label for="user-email">이메일</label></dt>
            <dd><input id="user-email" name="usrEmail" type="text" placeholder="이메일주소를 입력하세요"></dd>
        </dl>

        <h4>티머니카드</h4>
        <input type="hidden" name="usrCardList[0].cardCd" value="tmoney"/>
        <dl>
            <dt><label for="usrCardList[0].cardSno">카드번호</label></dt>
            <dd><input id="usrCardList[0].cardSno" name="usrCardList[0].cardSno" type="text" placeholder="****-****-****-****"></dd>
            <dt><label for="usrCardList[0].cardRegYear">발급년월</label></dt>
            <dd>
                <p class="select">
                    <select id="usrCardList[0].cardRegYear" name="usrCardList[0].cardRegYear">
                        <option>YYYY</option>
                        <c:forEach var="h" begin="2000" end="2017" step="1">
                            <option value="${h}">${h}</option>
                        </c:forEach>
                    </select>
                    <i class="fi icon-back"></i>
                </p>
                <p class="select">
                    <select name="usrCardList[0].cardRegMonth">
                        <option>YY</option>
                        <c:forEach var="h" begin="1" end="12" step="1">
                            <c:choose>
                                <c:when test="${h < 10}">
                                    <c:set var="brthMM" value="0${h}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="brthMM" value="${h}"/>
                                </c:otherwise>
                            </c:choose>
                            <option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(Emplyrinfo.brth, 4, 6) ==  brthMM}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
                        </c:forEach>
                    </select>
                    <i class="fi icon-back"></i>
                </p>
            </dd>
        </dl>

        <p class="btn-set full">
            <a class="btn sub" href="javascript:;" onclick="join()">회원가입</a>
        </p>

    </fieldset>
</form>

<script type="text/javascript">
    function join() {

        $( "#user" ).submit();
    }
    /*var validator = new FormValidator('user', [{
        name: 'usrId',
        display: 'required',
        rules: 'required'
    }, {
        name: 'usrPwd',
        rules: 'required'
    }, {
        name: 'usrPwd_re',
        display: 'password confirmation',
        rules: 'required|matches[password]'
    }, {
        name: 'email',
        rules: 'valid_email',
        depends: function () {
            return Math.random() > .5;
        }
    }], function (errors, event) {
        if (errors.length > 0) {
            // Show the errors
            console.log(errors);
        }
    });*/
</script>
</body>
</html>
