<%
/******************************************************************************
*
*	@ SYSTEM NAME		: 결제완료페이지
*	@ PROGRAM NAME		: returnPay.jsp
*	@ MAKER				: 
*	@ MAKE DATE			: 2012.04.03
*	@ PROGRAM CONTENTS	: 결제완료페이지
*
************************** 변 경 이 력 *****************************************
* 번호	작업자		작업일				변경내용
*	1	스마트로		2012.04.03			결제완료페이지
*-----  --------    ----------------    ----------------------------------------
*******************************************************************************/
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="./incMobileMerchant.jsp" %>
<%@ page import="java.util.*" %>
<%
	String PayMethod		= request.getParameter("PayMethod") == null ? "" : request.getParameter("PayMethod");
	String MID				= request.getParameter("MID") == null ? "" : request.getParameter("MID");
	String Amt				= request.getParameter("Amt") == null ? "" : request.getParameter("Amt");
	String BuyerName		= request.getParameter("BuyerName") == null ? "" : request.getParameter("BuyerName"); // 구매자명
	String GoodsName		= request.getParameter("GoodsName") == null ? "" : request.getParameter("GoodsName");
	String mallUserID       = request.getParameter("mallUserID") == null ? "" : request.getParameter("mallUserID"); // 구매자ID
	String TID              = request.getParameter("TID") == null ? "" : request.getParameter("TID");
	String Moid				= request.getParameter("OID") == null ? "" : request.getParameter("OID");	
	String AuthDate			= request.getParameter("AuthDate") == null ? "" : request.getParameter("AuthDate");
	String AuthCode			= request.getParameter("AuthCode") == null ? "" : request.getParameter("AuthCode");		
	String ResultCode		= request.getParameter("ResultCode") == null ? "" : request.getParameter("ResultCode");
	String ResultMsg		= request.getParameter("ResultMsg") == null ? "" : request.getParameter("ResultMsg");
	String MallReserved     = request.getParameter("MallReserved") == null ? "" : request.getParameter("MallReserved");
	String fn_cd			= request.getParameter("fn_cd") == null ? "" : request.getParameter("fn_cd");		
	String fn_name			= request.getParameter("fn_name") == null ? "" : request.getParameter("fn_name");
	String CardQuota		= request.getParameter("CardQuota") == null ? "" : request.getParameter("CardQuota");
	String BuyerEmail		= request.getParameter("BuyerEmail") == null ? "" : request.getParameter("BuyerEmail");
	String BuyerAuthNum		= request.getParameter("BuyerAuthNum") == null ? "" : request.getParameter("BuyerAuthNum");
	String ReceiptType		= request.getParameter("ReceiptType") == null ? "" : request.getParameter("ReceiptType");
	String SignValue		= request.getParameter("SignValue")==null?"":request.getParameter("SignValue");
	String BillTid			= request.getParameter("BillTid")==null?"":request.getParameter("BillTid");	
	String VerifyValue		= request.getParameter("VerifyValue")==null?"":request.getParameter("VerifyValue");	
	String BillKeyMode     	= request.getParameter("BillKeyMode")==null?"":request.getParameter("BillKeyMode");
	
	String merchantKey = "KiS8NWHjZ49FzG91HMI9hVXOSxYrvFBKzl2bYpr2ac7lg369iZxy0xhCJfg4juCuVH27mO/TQ4kG2qnjEr5Z4Q==";
	String VerifySignValue = encodeMD5HexBase64(TID.substring(0, 10) + ResultCode +TID.substring(10, 15) + merchantKey +TID.substring(15, TID.length()));
	String VerifyBillKValue ="";	

	String DecryptBillTid="";
	if("0".equals(BillKeyMode)) {
		try{
			kr.co.smartro.xpg_pay.crypt cy = new kr.co.smartro.xpg_pay.crypt();
			System.out.print("BillTid:"+BillTid);
			DecryptBillTid = cy.xpgClientDecrypt(merchantKey,BillTid);
			System.out.print("MID:"+MID);
			System.out.print("merchantKey:"+merchantKey);
			System.out.print("AuthDate:"+AuthDate);
			System.out.print("AuthCode:"+AuthCode);
			System.out.print("Amt:"+Amt);
			System.out.print("DecryptBillTid:"+DecryptBillTid);
			VerifyBillKValue = encodeMD5Base64(MID+merchantKey+AuthDate+AuthCode+DecryptBillTid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else {
		VerifyBillKValue = encodeMD5Base64(MID+merchantKey+AuthDate+AuthCode+Amt+BillTid);
	}
	
%>
<!DOCTYPE HTML>
<html>
<head>
<title> mainPay :: SmilePay </title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width">
<link type="text/css" href="/css/style.css" rel="stylesheet" />
</head>

<body>
	<div id="container">
		<div class="product_header">
			<h1 class="logo01"><img src="/images/common/product_header_logo.png" width="18"></h1>
			<p class="p_02">결제 결과 <span>Payment Results</span></p>
		</div><!-- //product_header -->
	   
		<div id="contents">
	
			<div class="product_information">
				<h2 class="title02">결제결과</h2>
			</div><!-- //product_information -->
			
			<div>
				<table class="table02">
					<tr>
						<th style="width:32%">지불수단</th>
						<td><%=PayMethod%></td>
					</tr>
					<tr>
						<th>상점ID</th>
						<td><%=MID%></td>
					</tr>
					<tr>
						<th>금액</th>
						<td><%=Amt%>원</td>
					</tr>
					<tr>
						<th>구매자명</th>
						<td><%=BuyerName%></td>
					</tr>
					<tr>
						<th>상품명</th>
						<td><%=GoodsName%></td>
					</tr>
					<tr>
						<th>승인번호 (AuthCode)</th>
						<td><%=AuthCode%></td>
					</tr>
					<tr>
						<th>주문번호</th>
						<td><%=Moid%></td>
					</tr>
					<tr>
						<th>구매자 ID</th>
						<td><%=mallUserID.equals("") ? "&nbsp;" : mallUserID%></td>
					</tr>
					<tr>
						<th>결과코드</th>
						<td><%=ResultCode%></td>
					</tr>
					<tr>
						<th>결과메시지</th>
						<td><%=ResultMsg%></td>
					</tr>
					<tr>
						<th>거래고유번호</th>
						<td><%=TID%></td>
					</tr>
					<tr>
						<th>응답 사인값</th>
						<td><%=SignValue %></td>
					</tr>
					<tr>
						<th>검증 사인값</th>
						<td><%=VerifySignValue %></td>
					</tr>
					<tr>
						<th>빌링키 응답 사인값</th>
						<td><%=VerifyValue %></td>
					</tr>
					<tr>
						<th>빌링키 검증 사인값</th>
						<td><%=VerifyBillKValue %></td>
					</tr>
					<tr>
						<th>빌링키</th>
						<td><%=BillTid %></td>
					</tr>
					<tr>
						<th>암호화 푼 빌링키</th>
						<td><%=DecryptBillTid %></td>
					</tr>
				</table>
			</div>
		</div><!-- //contents -->    
	</div><!-- //container -->
</body>
</html>