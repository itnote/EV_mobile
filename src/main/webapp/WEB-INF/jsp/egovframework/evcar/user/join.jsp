<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>EVCAR</title>
</head>
<body>

<form name="user" action="<c:url value="/user/joinAct.mdo"/>" method="POST">
    <fieldset class="sign">
        <legend class="sr-only">회원정보를 입력하세요</legend>
        <h3 class="sr-only">회원가입 폼</h3>
        <h4 class="sr-only">기본정보</h4>
        <dl>
            <dt><label for="user-name">이름</label></dt>
            <dd><input id="user-name" name="usrNm" type="text" placeholder="이름을 입력하세요"></dd>
            <dt><label for="user-id">아이디</label></dt>
            <dd><input id="user-id" name="usrId" type="text" placeholder="아이디를 입력하세요"></dd>
            <dt><label for="user-passwd">비밀번호</label></dt>
            <dd><input id="user-passwd" name="usrPwd" type="password" placeholder="비밀번호를 입력하세요"></dd>
        </dl>

        <h4>신용카드</h4>
        <dl>
            <dt>
                <select id="card">
                    <option>카드선택</option>
                    <option>비씨카드</option>
                </select>
                <i class="fi icon-back"></i>
            </dt>
            <dd><input id="card-num" name="card-num" type="text" placeholder="****-****-****-****"></dd>
            <dt><label for="card-date">발급년월</label></dt>
            <dd>
                <p class="select">
                    <select>
                        <option>yyyy</option>
                    </select>
                    <i class="fi icon-back"></i>
                </p>
                <p class="select">
                    <select id="card-date">
                        <option>mm</option>
                    </select>
                    <i class="fi icon-back"></i>
                </p>

            </dd>
        </dl>

        <h4>티머니카드</h4>
        <dl>
            <dt>카드번호</dt>
            <dd><input id="user-name" name="user-name" type="text" placeholder="****-****-****-****"></dd>
            <dt><label for="tmoney-date">발급년월</label></dt>
            <dd>
                <p class="select">
                    <select id="tmoney-date">
                        <option>yyyy</option>
                    </select>
                    <i class="fi icon-back"></i>
                </p>
                <p class="select">
                    <select>
                        <option>mm</option>
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
function join(){
    document.user.submit();
}
</script>
</body>
</html>
