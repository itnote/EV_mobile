<%@page import="java.net.InetAddress"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%-- 공통 common include --%>
<%@ include file="/pay/incMobileMerchant.jsp" %>
<%
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("expires", 0);
	response.setHeader("Pragma","no-cache");

	// 서버 IP 가져오기
	InetAddress inet = InetAddress.getLocalHost();

	// 서버 도메인 가져오기
	String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

	// 전문생성일시
	String ediDate = getyyyyMMddHHmmss();
	
	// [주의 !!!!!]
	// 상점서명키 (꼭 해당 상점키로 바꿔주세요)
	// 샘플소스에는 당사 테스트 상점 키로 셋팅되어 있으니 실서비스로 전환시에는 반드시 당사로부터 당사로부터 실서비스 상점키로 셋팅하시기 바랍니다.
	String MID 			= EgovProperties.getProperty("EVCAR.PAY.MID");		// MID
	String merchantKey 	= EgovProperties.getProperty("EVCAR.PAY.MERCHANTKEY");

	String goodsAmt = "100";		// 상품금액

	// [주의!!!!!]
	// 결제서버 URL 세팅
	// 실서비스 전환시 아래 주석처리 된 당사 실서비스 URL로 반드시 변경하여 주십시오.
	// globals.properties 추가됨
	String payActionUrl = EgovProperties.getProperty("EVCAR.PAY.ACTION.URL");
	
	// 암호화 데이터	
	String encryptData = encodeMD5HexBase64(ediDate + MID + goodsAmt + merchantKey);
	
	// 가상계좌 입금만료일
	// 가상계좌 미사용 상점은 아래 코드를 그대로 사용하시면 되고
	// 상점에서 가상계좌 서비스를 사용할시 가상계좌 입금마감일을  설정하는 곳입니다.
	// 가상계좌 입금 마감일은 기본적으로 D+1로 셋팅되어 있으며 입금마감일은 임의로 설정이 가능합니다.
	Timestamp toDay = new Timestamp((new Date()).getTime());
	Timestamp nxDay = getTimestampWithSpan(toDay, 1);
	String VbankExpDate = nxDay.toString();
	VbankExpDate = VbankExpDate.substring(0, 10); 
	VbankExpDate = VbankExpDate.replaceAll("-", "");
%>
<!DOCTYPE HTML>
<html>
<head>
<title> mainPay :: SmilePay </title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width">

</head>

<body onunload="">

<script type="text/javascript" src="/assets/smartro/js/incMobileMerchant.js"></script>
<script>
    /**
     * URL 숨기기
     */
    window.addEventListener('load', function(){
        setTimeout(scrollTo, 0, 0, 1);
    }, false);


    function selectedBoxValue(objName) {
        return objName[objName.selectedIndex].value;
    }

    /**
     * 연동페이지
     * 1.상점별 결제수단 파악해서 쿠키에 넣어줄지 결정
     */
    function goInterface() {

        //var selectType = selectedBoxValue(document.tranMgr.selectType);

        var payFormNm = document.payForm;


        // 결제수단
        var formNm = document.tranMgr;
        var payFormNm = document.payForm;
        var BuyerEmail = formNm.BuyerEmail.value;
        var ParentEmail = formNm.ParentEmail.value;

        payFormNm.PayMethod.value = formNm.selectType.value;


        /* 필수값이 아닙니다.
        if(formNm.CustCI.value == '') {
            alert("고객본인인증CI값이없습니다..");
            return;
        }*/

        if(formNm.BillType.value == '') {
            alert("BillKey인증타입값을 확인하십시요.");
            return;
        }


        if(formNm.BillType.value != 'AA' && formNm.BillType.value != 'AR') {
            alert("BillKey인증타입값이 잘못 되었습니다.");
            return;
        }

        if(formNm.BillType.value == 'AR' && formNm.Amt.value != '100') {
            alert("빌링키발급시 상품 가격은 100원으로 입력 하세요.");
            return;
        }

        if(formNm.BillType.value == 'AR' && formNm.GoodsName.value != '빌링키인증') {
            alert("빌링키발급시 거래 상품명은 빌링키인증으로 하세요.");
            return;
        }

        // 메일주소 검증
        if(BuyerEmail == '') {
            alert("구매자 메일주소를 입력해 주세요.");
            return;
        } else {
            if(!EmailCheck(BuyerEmail)) {
                alert("구매자메일주소가 형식에 맞지 않습니다.");
                return;
            }
        }

        if(ParentEmail != '') {
            if(!EmailCheck(ParentEmail)) {
                alert("보호자메일주소가 형식에 맞지 않습니다.");
                return;
            }
        }

        // 주문번호 특수문자 체크
        if(isSpecial(formNm.Moid.value)) {
            alert("주문번호에는 특수문자가 허용되지 않습니다.");
            return;
        }

        payFormNm.GoodsCnt.value		= formNm.GoodsCnt.value;
        payFormNm.GoodsName.value		= formNm.GoodsName.value;
        payFormNm.Amt.value				= formNm.Amt.value;
        payFormNm.Moid.value			= formNm.Moid.value;
        payFormNm.MID.value				= formNm.MID.value;
        payFormNm.ReturnURL.value		= formNm.ReturnURL.value;
        payFormNm.RetryURL.value		= formNm.RetryURL.value;
        payFormNm.mallUserID.value		= formNm.mallUserID.value;
        payFormNm.BuyerName.value		= formNm.BuyerName.value;
        payFormNm.BuyerTel.value		= formNm.BuyerTel.value;
        payFormNm.BuyerEmail.value		= formNm.BuyerEmail.value;
        payFormNm.ParentEmail.value		= formNm.ParentEmail.value;
        payFormNm.BuyerAddr.value		= formNm.BuyerAddr.value;
        payFormNm.BuyerPostNo.value		= formNm.BuyerPostNo.value;
        payFormNm.MallIP.value			= formNm.MallIP.value;
        payFormNm.VbankExpDate.value	= formNm.VbankExpDate.value;
        payFormNm.MallReserved.value	= formNm.MallReserved.value;
        payFormNm.EncodingType.value	= formNm.EncodingType.value;
        payFormNm.OfferPeriod.value		= formNm.OfferPeriod.value;
        payFormNm.GoodsCl.value			= formNm.GoodsCl.value;
        payFormNm.SocketYN.value			= formNm.SocketYN.value;
        payFormNm.SocketReturnURL.value		= formNm.SocketReturnURL.value;
        payFormNm.EncodeParameters.value	= formNm.EncodeParameters.value;
        payFormNm.clientType.value	= formNm.clientType.value;
        payFormNm.StopURL.value		= formNm.StopURL.value;
        payFormNm.action = '<%= payActionUrl %>' + '/pay/interfaceURL';

        payFormNm.CustCI.value		= formNm.CustCI.value;
        payFormNm.BillType.value		= formNm.BillType.value;
        payFormNm.BillKeyMode.value		= formNm.BillKeyMode.value;

        payFormNm.submit();
        payFormNm.PayMethod.value = "";
        payFormNm.payType.value = "";

        return false;
    }

</script>

<form name="tranMgr" method="post" action="" >

	<input type="hidden" name="GoodsURL" 			value="" 											/><%----%>
	<input type="hidden" name="selectType"			value="CARDBILL" 									/><%--결제수단 CARDBILL:신용카드빌링 / CPBILLRG:휴대폰빌링 --%>
	<input type="hidden" name="GoodsCnt"			value="1" 											/><%--상품갯수 --%>
	<input type="hidden" name="GoodsName"			value="빌링키인증" 									/><%--빌링키인증 --%>
	<input type="hidden" name="Amt"					value="<%=goodsAmt %>" 								/><%--상품가격 --%>
	<input type="hidden" name="Moid"				value="mnoid1234567890" 							/><%--상품주문번호 --%>
	<%--<input type="hidden" name="Moid"				value="${smartroKey}" 								/>상품주문번호 --%>
	<input type="hidden" name="MID"					value="<%=MID %>" 									/><%--회원사아이디 --%>
	<input type="hidden" name="SUB_ID"				value="" 											/><%--서브몰아이디 --%>
	<input type="hidden" name="ReturnURL"			value="<%=domain %>/card/returnBillPay.mdo" 		/><%--결제결과전송 URL --%>
	<input type="hidden" name="RetryURL"			value="<%=domain %>/card/inform.mdo" 				/><%--결제결과 Retry URL --%>
	<input type="hidden" name="StopURL"				value="<%=domain %>/card/stopUrl.mdo" 				/><%--결제결과 Stop URL --%>
	<input type="hidden" name="mallUserID"			value="smartro_id" 									/><%--회원사 고객 ID --%>

	<%--<input type="hidden" name="BuyerName"			value="홍길동" 										/>구매자명 --%>
	<%--<input type="hidden" name="BuyerTel"			value="0212345678" 									/>구매자연락처 --%>
	<%--<input type="hidden" name="BuyerEmail"			value="smpark@smartro.co.kr" 						/>구매자 메일 --%>

	<input type="hidden" name="ParentEmail"			value="" 											/><%--보호자 메일 --%>
	<input type="hidden" name="BuyerAddr"			value="" 											/><%--배송지주소 --%>
	<input type="hidden" name="BuyerPostNo"			value="" 											/><%--우편번호 --%>
	<input type="hidden" name="MallIP"				value="" 											/><%--Mail IP --%>
	<input type="hidden" name="MallReserved"		value="" 											/><%--상점예비정보 --%>
	<input type="hidden" name="VbankExpDate"		value="" 											/><%--가상계좌 입금기한 --%>
	<input type="hidden" name="EncodingType"		value="utf8" 										/><%--인코딩타입 euckr / utf8 --%>
	<input type="hidden" name="GoodsCl"				value="0" 											/><%--결제구분 1:실물 / 0:컨텐츠--%>
	<input type="hidden" name="OfferPeriod"			value="" 											/><%--용역제공기간--%>
	<input type="hidden" name="SocketYN"			value="N" 											/><%--소켓이용유무--%>
	<input type="hidden" name="SocketReturnURL"		value="" 											/><%--소켓 상점 결제결과 전송 URL--%>
	<input type="hidden" name="EncodeParameters"	value="Amt,CardNo,CardExpire,CardPwd" 				/><%--소켓암호화 파라미터--%>
	<input type="hidden" name="clientType"			value="WEB" 										/><%--클라이언트 구분--%>
	<input type="hidden" name="CustCI"				value="12345678"									/><%--고객매칭정보--%>
	<input type="hidden" name="BillType"			value="AR" 											/><%--AR:빌링키발급 / AA:결제+빌링키발급 --%>
	<input type="hidden" name="BillKeyMode"			value="0" 											/><%--0:빌링키 암호화 리턴 / 1:빌링키 평문리턴 --%>

	<fieldset class="sign">
		<legend class="sr-only">카드정보를 입력하세요</legend>
		<h3 class="sr-only">카드정보 폼</h3>
		<h4 class="sr-only">카드인증</h4>

		<dl>
			<dt><label for="BuyerName">이름</label></dt>
			<dd>
				<input id="BuyerName" name="BuyerName" type="text" placeholder="이름을 입력하세요" value="${loginVO.usrNm}">
			</dd>
			<dt><label for="BuyerTel">연락처</label></dt>
			<dd>
				<input id="BuyerTel" name="BuyerTel" type="text" placeholder="연락처를 입력하세요" value="${loginVO.usrCel}">
			</dd>
			<dt><label for="BuyerEmail">이메일</label></dt>
			<dd>
				<input id="BuyerEmail" name="BuyerEmail" type="text" placeholder="이메일을 입력하세요" value="${loginVO.usrEmail}">
			</dd>
		</dl>

		<p class="btn-set full">
			<a class="btn sub" href="javascript:;" onclick="goInterface()">카드인증</a>
		</p>
	</fieldset>
</form>

<form name="payForm" method="post">
	<input type="hidden" name="PayMethod" >
	<input type="hidden" name="payType" >
	<input type="hidden" name="GoodsCnt" >
	<input type="hidden" name="GoodsName">
	<input type="hidden" name="Amt">
	<input type="hidden" name="Moid">
	<input type="hidden" name="MID">
	<input type="hidden" name="ReturnURL">
	<input type="hidden" name="RetryURL">
	<input type="hidden" name="StopURL">
	<input type="hidden" name="mallUserID">
	<input type="hidden" name="BuyerName">
	<input type="hidden" name="BuyerTel">
	<input type="hidden" name="BuyerEmail">
	<input type="hidden" name="ParentEmail">
	<input type="hidden" name="BuyerAddr">
	<input type="hidden" name="BuyerPostNo">
	<input type="hidden" name="UserIP" value="<%=request.getRemoteAddr()%>">
	<input type="hidden" name="MallIP">
	<input type="hidden" name="VbankExpDate">
	<input type="hidden" name="EncryptData" value=<%= encryptData %>>
	<input type="hidden" name="MallReserved">
	<input type="hidden" name="MallResultFWD" value="N">
	<input type="hidden" name="TransType" value="0">
	<input type="hidden" name="EncodingType">
	<input type="hidden" name="GoodsCl">
	<input type="hidden" name="clientType" />
	<input type="hidden" name="urlScheme" value="" />
	<input type="hidden" name="ediDate" value="<%=ediDate%>">
	<input type="hidden" name="OfferPeriod">
	<input type="hidden" name="SocketYN">
	<input type="hidden" name="SocketReturnURL">
	<input type="hidden" name="EncodeParameters">
	<input type="hidden" name="CustCI">
	<input type="hidden" name="BillType">
	<input type="hidden" name="BillKeyMode">
</form>
</body>
</body>
</html>