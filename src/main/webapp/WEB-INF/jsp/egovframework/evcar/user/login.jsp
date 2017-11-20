<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>

<form id="user" name="user" method="POST" action="/user/loginAct.mdo" onsubmit="">
    <input type="hidden" id="pushKey" name="pushKey"/>
    <input type="hidden" id="pushType" name="pushType"/>
    <fieldset>
        <legend class="sr-only">로그인정보 입력폼 입니다.</legend>

        <label class="sr-only" for="userId">아이디를 입력하세요</label>
        <input id="userId" name="userId" placeholder="아이디" type="text" value="">

        <label class="sr-only" for="pwdNo">비밀번호를 입력하세요</label>
        <input id="pwdNo" name="pwdNo" placeholder="비밀번호" class="passwd" type="password" value="">

        <p class="btn-set">
            <a class="btn main" href="javascript:;" onclick="loginAct()">로그인</a>
            <a class="btn sub" href="<c:url value="/user/join.mdo"/>">회원가입</a>
        </p>
    </fieldset>
</form>
<script type="text/javascript">
    $(document).ready(function () {
        <c:if test="${!empty message}">
        alert('${message}');
        </c:if>

        $('form#user input').on('keypress', function(e) {
            if (e.which == 13) {/* 13 == enter key@ascii */
                loginAct()
            }
        });

        // 안드로이드 체크
        if(typeof(MYEV) !== 'undefined') {
            alert(MYEV.callGetId());
            document.user.pushType.value = 'ANDROID';
            document.user.pushKey.value = MYEV.callGetId();
        }


        $('form#user').validate({
            rules: {
                userId: {required: true},
                pwdNo: {required: true}
            },
            messages: {
                userId: {required: '아이디를 입력하세요.'},
                pwdNo: {required: '비밀번호를 입력하세요.'}
            },
            submitHandler: function (frm) {

                frm.submit();
            },
            success: function (e) {

            }
        });
    });
    function loginAct() {
        if($('form#user').valid()) document.user.submit();
    }
</script>
</body>
</html>
