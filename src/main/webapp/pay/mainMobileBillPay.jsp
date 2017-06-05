<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%-- 공통 common include --%>
<%@ include file="./incMobileMerchant.jsp" %>
<%
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("expires", 0);
	response.setHeader("Pragma","no-cache");

	// 서버 IP 가져오기
	InetAddress inet = InetAddress.getLocalHost();

	// 전문생성일시
	String ediDate = getyyyyMMddHHmmss();
	
	// [주의 !!!!!]
	// 상점서명키 (꼭 해당 상점키로 바꿔주세요)
	// 샘플소스에는 당사 테스트 상점 키로 셋팅되어 있으니 실서비스로 전환시에는 반드시 당사로부터 당사로부터 실서비스 상점키로 셋팅하시기 바랍니다.
	String merchantKey = "KiS8NWHjZ49FzG91HMI9hVXOSxYrvFBKzl2bYpr2ac7lg369iZxy0xhCJfg4juCuVH27mO/TQ4kG2qnjEr5Z4Q==";
	String MID = "SMTPAY003m";		// MID
	
	String goodsAmt = "100";		// 상품금액
	
	// [주의!!!!!]
	// 결제서버 URL 세팅
	// 실서비스 전환시 아래 주석처리 된 당사 실서비스 URL로 반드시 변경하여 주십시오.
	String payActionUrl = "https://tspay.smilepay.co.kr";	// Test
	//String payActionUrl = "https://smpay.smilepay.co.kr";	// Real
	
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

        var selectType = selectedBoxValue(document.tranMgr.selectType);

        var payFormNm = document.payForm;
        payFormNm.PayMethod.value = selectType;

        // 결제수단
        var formNm = document.tranMgr;
        var payFormNm = document.payForm;
        var BuyerEmail = formNm.BuyerEmail.value;
        var ParentEmail = formNm.ParentEmail.value;



        if(formNm.CustCI.value == '') {
            alert("고객본인인증CI값이없습니다..");
            return;
        }

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
	<input type="hidden" name="GoodsURL" />
	<div id="wrap">
		<div id="header"></div>
	
		<div id="container">
			<div class="contents_header">
				<h1 class="logo01"><img src="/assets/smartro/images/common/logo01.png" width="47" alt="smatro S-pay"></h1>
				<p class="p_01">스마일페이 모바일 결제데모</p>
			</div>
			
			<div id="contents" class="contents_layout">
			
				<div class="user_information">
					<table class="table01" >
						<tr>
							<th style="width:32%;">결제수단</th>
							<td>
								<select name="selectType">
									<option value="">[선택]</option>
									<option value="CARDBILL">[신용카드 빌링]</option>
									<option value="CPBILLRG">[휴대폰빌링]</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>상품갯수</th> 
							<td><input type="text" name="GoodsCnt" value="1" class="input_txt" pattern="[0-9]*" /></td>
						</tr>
						<tr>
							<th>상품명</th>
							<td><input type="text" name="GoodsName" value="빌링키인증" class="input_txt" /></td>
						</tr>
						<tr>
							<th>상품가격</th>
							<td><input type="text" name="Amt" value="<%= goodsAmt %>" class="input_txt" size="10" pattern="[0-9]*" /></td>
						</tr>
						<tr>
							<th>상품주문번호</th>
							<td><input type="text" name="Moid" value="mnoid1234567890" class="input_txt" ></td>
						</tr>
						<tr>
							<th>회원사아이디</th>
							<td><input type="text" name="MID" class="input_txt" value="<%= MID %>"></td>
						</tr>
						<tr>
							<th>서브몰아이디</th>
							<td><input type="text" name="SUB_ID" class="input_txt" ></td>
						</tr>
						<tr>
							<th>결제결과전송<br />URL</th>
							<!-- 상점에 맞게 URL 수정해 주세요 -->
							<td><input type="text" name="ReturnURL" class="input_txt" value="<%=payActionUrl%>/returnMobileBillPay.jsp"></td>
						</tr>
						<tr>
							<th>결제결과<br />RetryURL</th>
							<!-- 상점에 맞게 URL 수정해 주세요 -->
							<td><input type="text" name="RetryURL" class="input_txt" value="<%=payActionUrl%>/informMobile.jsp"></td>
						</tr>
						<tr>
							<th>결제중지<br />Stop URL</th>
							<!-- 상점에 맞게 URL 수정해 주세요 -->
							<td><input type="text" name="StopURL" class="input_txt" value="<%=payActionUrl%>/stopUrl.jsp"></td>
						</tr>						
						<tr>
							<th>회원사<br />고객 ID</th>
							<td><input type="text" name="mallUserID" class="input_txt" value="smartro_id"></td>
						</tr>
						<tr>
							<th>구매자명</th>
							<td><input type="text" name="BuyerName" class="input_txt" value="홍길동"></td>
						</tr>
						<tr>
							<th>구매자연락처</th>
							<td><input type="text" name="BuyerTel" class="input_txt" value="0212345678"></td>
						</tr>
						<tr>
							<th>구매자<br />메일주소</th>
							<td><input type="text" name="BuyerEmail" class="input_txt" value="smpark@smartro.co.kr"></td>
						</tr>
						<tr>
							<th>보호자<br />메일주소</th>
							<td><input type="text" name="ParentEmail" class="input_txt" ></td>
						</tr>
						<tr>
							<th>배송지주소</th>
							<td><input type="text" name="BuyerAddr" class="input_txt" value="서울 양천구 목동서로 201"></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><input type="text" name="BuyerPostNo" class="input_txt" value="158714"></td>
						</tr>
						<tr>
							<th>Mail IP</th>
							<td><input type="text" name="MallIP" class="input_txt" value="<%= inet.getHostAddress() %>"></td>
						</tr>
						<tr>
							<th>상점예비정보</th>
							<td><input type="text" name="MallReserved" class="input_txt" value="MallReserved"></td>
						</tr>										
						<tr>
							<th>가상계좌<br />입금기한</th>
							<td><input type="text" name="VbankExpDate" class="input_txt" value="<%= VbankExpDate %>"></td>
						</tr>
						<tr>
							<th>인코딩타입</th>
							<td>
								<select	name="EncodingType">
									<option value="">[선택]</option>
									<option value="euckr">[EUC-KR]</option>
									<option value="utf8" selected="selected">[UTF-8]</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>결제구분</th>
							<td>
								<select name="GoodsCl" class="input">
									<option value="1" selected="selected">실물</option>
									<option value="0">컨텐츠</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>용역제공기간</th>
							<td>
								<input name="OfferPeriod" class="input" size="20" value="2012121020121230">
							</td>
						</tr>
						<tr>
							<th>소켓이용유무</th>
							<td>
								<input name="SocketYN" class="input" size="20" value="N">
							</td>
						</tr>
						<tr>
							<th>소켓 상점 결제결과 전송 URL</th>
							<td>
								<!-- 상점에 맞게 URL 수정해 주세요 -->
								<input id="SocketReturnURL" class="input" size="20" value="">
							</td>
						</tr>
						<tr>
							<th>소켓암호화 파라미터</th>
							<td>
								<input name="EncodeParameters" class="input" size="20" value="Amt,CardNo,CardExpire,CardPwd">
							</td>
						</tr>
						<tr>
							<th>클라이언트 구분</th>
							<td>
								<input name="clientType" class="input" size="20" value="WEB">
							</td>
						</tr>
						<tr>
							<th>고객매칭정보</th>
							<td>
								<input name="CustCI" class="input" size="20">
							</td>
						</tr>
						<tr>
							<th>빌링키발급유형</th>
							<td>
								<select name="BillType" class="input">
									<option value="" >[선택]</option>
									<option value="AR" >[빌링키발급]</option>
									<option value="AA" >[결제+빌링키발급]</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>빌링키발급유형</th>
							<td>
								<select name="BillKeyMode" class="input">
									<option value="0" >[빌링키 암호화 리턴]</option>
									<option value="1" >[빌링키 평문리턴]</option>
								</select>
							</td>
						</tr>
					</table>
					<div id="payBtnLayout" class="btn_layout" >
						<p>
							<img id="payBtn" src="/assets/smartro/images/btn/payment01.png" width="90" alt="결제하기"  onclick="goInterface()">
						</p>
					</div>				
				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
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