<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <form name="user" id="user" action="<c:url value="/user/joinAct.mdo"/>" onsubmit="return dosubmit(this)" method="POST">
        <fieldset class="sign">
            <legend class="sr-only">회원정보를 입력하세요</legend>
            <h3 class="sr-only">회원가입 폼</h3>

            <h4>T-머니 인증카드 정보</h4>
            <dl>
                <dt><label for="acrdCrdNo">카드번호</label></dt>
                <dd><input class="input-card quantity" id="acrdCrdNo" name="acrdCrdNo" type="text" placeholder="****-****-****-****" onkeypress="isCardCheck=false;">
                    <button type="button" onclick="CardCheck()">중복체크</button>
                    <i class="essential">필수항목입니다</i>
                </dd>
            </dl>
            <h4 class="">회원정보</h4>
            <dl>
                <dt><label for="userId">아이디</label></dt>
                <dd>
                    <input id="userId" name="userId" type="text" placeholder="아이디를 입력하세요" onkeypress="isIdCheck=false;" >
                    <button type="button" onclick="IdCheck()">중복체크</button>
                    <i class="essential">필수항목입니다</i>
                </dd>
                <dt><label for="pwdNo">비밀번호</label></dt>
                <dd><input id="pwdNo" name="pwdNo" type="password" placeholder="비밀번호를 입력하세요"><i class="essential">필수항목입니다</i></dd>
                <dt><label for="pwdNo_re">재입력</label></dt>
                <dd><input id="pwdNo_re" name="pwdNo_re" type="password" placeholder="비밀번호를 입력하세요"><i class="essential">필수항목입니다</i></dd>
            </dl>
            <h4 class="">기본정보</h4>
            <dl>
                <dt><label for="userNm">이름</label></dt>
                <dd><input id="userNm" name="userNm" type="text" placeholder="이름을 입력하세요"><i class="essential">필수항목입니다</i></dd>
                <dt><label for="telNo">휴대전화</label></dt>
                <dd><input class="input-phone quantity" id="telNo" name="telNo" type="text" placeholder="전화번호를 입력하세요"><i class="essential">필수항목입니다</i></dd>
                <dt><label for="userEmail">이메일</label></dt>
                <dd><input id="userEmail" name="userEmail" type="text" placeholder="이메일주소를 입력하세요"><i class="essential">필수항목입니다</i></dd>
            </dl>
            <p class="btn-set full">
                <a class="btn sub" href="javascript:;" onclick="join()">회원가입</a>
            </p>
        </fieldset>
    </form>
<script type="text/javascript">
    function dosubmit(frm) {

        var userId = $('input[id^=userId]').val();
        var pwdNo = $('input[id^=pwdNo]').val();
        var pwdNo_re = $('input[id^=pwdNo_re]').val();
        var userNm = $('input[id^=userNm]').val();
        var cel = $('input[id^=telNo]').val();
        var userEmail = $('input[id^=userEmail]').val();
        var card = $('input[id^=acrdCrdNo]').val();

        //핸드폰 번호 정규식
        var regCel = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
        //이메일 정규식
        var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        //아이디 정규식
        var regId = /^[A-Za-z0-9_-]{4,12}$/;

        if(userId ==''){
            alert("아이디를 입력하세요.");
            return false;
        }else if(isIdCheck==false){
            alert('ID 중복 체크를 하세요.');
            return false;
        }else if(pwdNo == '') {
            alert('비밀번호를 입력하세요.');
            return false;
        }else if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{6,15}$/.test(pwdNo)) {
            alert("비밀번호는 숫자, 영문, 특수문자 조합으로 6~15자리를 사용해야 합니다.");
            $('#pwdNo').val('');
            $('#pwdNo_re').val('');
            return false;
        }else if(pwdNo_re == ''){
            alert('비밀번호를 재입력 입력하세요.');
            return false;
        }else if(pwdNo_re != pwdNo){
            alert('비밀번호를 다시 입력하세요.');
            $('#pwdNo_re').val('');
            return false;
        }else if(userNm ==''){
            alert('이름을 입력하세요.');
            return false;
        }else if(cel ==''){
            alert('휴대전화를 입력하세요.');
            return false;
        }else if ( !regCel.test( $('#telNo').val())) {
            alert("잘못된 휴대폰 번호입니다. 숫자만 입력하세요.");
            $('#telNo').val('');
            return false
        }else if(userEmail ==''){
            alert('이메일 주소를 입력하세요.');
            return false;
        }else if( !regEmail.test( $('#userEmail').val())) {
            alert('올바른 이메일 주소를 입력하세요.');
            $('#userEmail').val('');
            return false;
        }else if(card == ''){
            alert('카드번호를 입력하세요.');
            return false;
        }else if(card.length < 16){
            alert('카드번호를 16자리 입력하세요.');
            return false;
        }else if(isCardCheck==false){
            alert('카드 중복 체크를 하세요.');
            return false;
        }else{
            return true;
        }
    }
    var isIdCheck = false;
    function IdCheck() {
        var userId = $('#userId').val();
        var pattern = /^\s+|\s+$/g;
        $.ajax({
            type:"POST",
            url:"<c:url value='/user/ajax/IdCheck.do'/>",
            data:{
                userId:userId
            },
            success : function (data) {
                if(userId == ""){
                    alert("아이디를 입력해 주세요.");
                    isIdCheck = false;
                    return false;
                }else if( $('#userId').val().replace(/\s/g,"").length == 0){
                    alert('공백이 들어갈 수 없습니다.');
                    $('#userId').val('');
                    return false;
                }else if(data > 0){
                    alert("이미 존재하는 ID 입니다.");
                    $('#userId').val('');
                    isIdCheck = false;
                    return false;
                }else{
                    alert("사용 가능 합니다.");
                    isIdCheck = true;
                    return false;
                }
            },
            error:function (request,status,error) {
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }
    var isCardCheck = false;
    function CardCheck() {
        var usrCard = $('#acrdCrdNo').val();
        if(usrCard.length < 16){
            alert("카드번호를 16자리 입력하세요.");
            $('#acrdCrdNo').val('');
            return false;
        }
        $.ajax({
            type:"POST",
            url:"<c:url value='/user/ajax/CardCheck.do'/>",
            data:{
                acrdCrdNo:usrCard,
                cardCd:'tmoney'
            },
            success : function (data) {
                if(usrCard == ""){
                    alert("카드 번호를 입력해 주세요.");
                    isCardCheck = false;
                    return false;
                }else if(data > 0){
                    alert("이미 존재하는 카드 입니다.");
                    $('#acrdCrdNo').val('');
                    isCardCheck = false;
                    return false;
                }else{
                    alert("사용 가능 합니다.");
                    isCardCheck = true;
                    return false;
                }
            },
            error:function (request,status,error) {
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }
    //핸드폰 번호 formatting
    var cleavePhone = new Cleave('.input-phone', {
        delimiter: '-',
        blocks: [3, 4, 4],
        uppercase: true
    });
    //카드번호 formatting
    var cleaveCard = new Cleave('.input-card', {
        delimiter: '-',
        blocks: [4, 4, 4,4],
        uppercase: true
    });
    //숫자만 입력
    $(function(){
        $('.quantity').keypress(function(event){
            if (event.which && (event.which  > 47 && event.which  < 58 || event.which == 8)) {
            } else {
                event.preventDefault();
            }
        });
    });
    function join() {
        if($('form#user').valid())
            $( "#user" ).submit();
    }
</script>
</body>
</html>