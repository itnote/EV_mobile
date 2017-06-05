<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String actionUrl = "https://tspay.smilepay.co.kr/cancel/payCancelProcess";	// TEST
	//String actionUrl = "https://tspay.smilepay.co.kr/cancel/payCancelProcess";	// REAL
%>
<!DOCTYPE HTML>
<html>
<head>
<title> mainPay :: SmilePay </title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width">
<link type="text/css" href="./css/style.css" rel="stylesheet" />
<script type="text/javascript" src="./js/incMobileMerchant.js"></script> 
<script type="text/javascript">
function goCancel() {
	
	
	var formNm = document.tranMgr;
	var selectType = selectedBoxValue(document.tranMgr.selectType);
	
	formNm.PayMethod.value = selectType;
	
	// TID validation
	if(formNm.TID.value == "") {
		alert("TID를 확인하세요.");
		return false;
	} else if(formNm.TID.value.length > 30 || formNm.TID.value.length < 30) {
		alert("TID 길이를 확인하세요.");
		return false;
	}
	// 취소금액
	if(formNm.CancelAmt.value == "") {
		alert("금액을 입력하세요.");
		return false;
	} else if(formNm.CancelAmt.value.length > 12 ) {
		alert("금액 입력 길이 초과.");
		return false;
	}
	var PartialValue = "";
	// 부분취소여부 체크 - 신용카드, 계좌이체 부분취소 가능
	for(var idx = 0 ; idx < formNm.PartialCancelCode.length ; idx++){
		if(formNm.PartialCancelCode[idx].checked){
			PartialValue = formNm.PartialCancelCode[idx].value;
			break;
		}
	}
	
	if(PartialValue == '1'){
		if(formNm.TID.value.substring(10,12) != '01' &&  formNm.TID.value.substring(10,12) != '02' &&  formNm.TID.value.substring(10,12) != '03'){
			alert("신용카드결제, 계좌이체, 가상계좌만 부분취소/부분환불이 가능합니다");
			return false;
		}
	}
	
	formNm.submit();
	return true;
}
</script>
</head>
<body>
<form name="tranMgr" method="post" action="<%= actionUrl %>">
	<input type="hidden" name="GoodsURL" />
	<div id="wrap">
		<div id="header"></div>
	
		<div id="container">
			<div class="contents_header">
				<h1 class="logo01"><img src="/images/common/logo01.png" width="47" alt="smatro S-pay"></h1>
				<p class="p_01">스마일페이 모바일 취소데모</p>
			</div>
			
			<div id="contents" class="contents_layout">
			
				<div class="user_information">
					<table class="table01" >
						<tr>
							<th>결제수단</th>
							<td>
								<select name="selectType">
									<option value="">[선택]</option>
									<option value="CARD">[신용카드]</option>
									<option value="BANK">[계좌이체]</option>
									<option value="VBANK">[가상계좌]</option>
									<option value="CELLPHONE">[휴대폰결제]</option>
									<option value="CLGIFT">[문화상품권]</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>TID</th>
							<td><input type="text" name="TID" id="TID" size="30" maxlength="30" /></td>
						</tr>
						<tr>
							<th>취소패스워드</th> 
							<td><input type="text" name="Cancelpw" value="123456" class="input_txt" />* 데모시 미입력</td>
						</tr>
						<tr>
							<th>취소금액</th>
							<td><input type="text" name="CancelAmt" class="input_txt" /></td>
						</tr>
						<tr>
							<th>취소사유</th>
							<td><input type="text" name="CancelMSG" value="고객요청" class="input_txt" ></td>
						</tr>
						<tr>
							<th>부분취소 여부</th>
							<td height="30" valign="middle">
								<input type="radio" name="PartialCancelCode" value="0" checked="checked"/>전체취소 
								<input type="radio" name="PartialCancelCode" value="1" />부분취소
							</td>
						</tr>
						<tr>
							<th>결제결과URL</th>
							<td><input type="text" name="ReturnURL" class="input_txt" value="https://tspay.smilepay.co.kr/returnCancelPay.jsp" ></td>
						</tr>
					</table>
					<div id="payBtnLayout" class="btn_layout" >
						<p>
							<img id="payBtn" src="/images/btn/payment01.png" width="90" alt="결제하기"  onclick="goCancel()">
						</p>
					</div>				
				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
	<input type="hidden" name="MID" size="20" value="SMTPAY001m"/>
	<input type="hidden" name="PayMethod"/>
	<input type="hidden" name="EncryptData"/>
	<input type="hidden" name="cc_ip" size="20" value="<%=request.getRemoteAddr()%>"/>
	<input type="hidden" name="EncodingType" value="euc-kr"/>
	<input type="hidden" name="MallResultFWD" value="Y" />
</form>
</body>
</body>
</html>