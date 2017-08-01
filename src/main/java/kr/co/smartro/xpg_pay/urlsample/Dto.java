package kr.co.smartro.xpg_pay.urlsample;

public class Dto {
	
	public String PayMethod=""; 						// 지불수단 CARDBILL:신용카드
	public String ediDate=""; 							// 전문생성일시
	public String GoodsCnt="";						// 결제 상품 품목 개수
	public String GoodsName="";						// 거래 상품명	
	public String Amt="";								// 거래 금액	
	public String Moid="";								// 상품주문번호	
	public String MID="";								// 상점아이디
	public String ChannelType="";					// 채널유형	
	public String MallIP="";							// 상점서버IP
	public String MallReserved="";					// 상점예비정보	
	public String MallUserID="";					    // 회원사고객ID	
	public String BuyerName="";					    // 구매자명
	public String BuyerTel="";					    	// 구매자연락처
	public String BuyerEmail="";					    // 구매자메일주소
	public String ParentEmail="";					    // 보호자메일주소 사용안함
	public String BuyerAddr="";					    // 배송지주소
	public String BuyerPostNo="";					// 우편번호
	public String UserIP="";					        // 회원사고객 IP
	public String Language=""; 						// KR EN 응답메시지 영문 ,한글 구분
	public String VERIFY_V="";							// 검증값	EncryptData  같은용도
	public String ReturnURL="";						// 상점 결제결과 전송 URL	 추가20140408
	public String RetryURL	="";						// 상점 결제결과 Retry URL 추가20140408	
	public String SUB_ID="";							// 서브아이디                 추가20140408	
	
	//휴대폰 빌링 전용

	public String GoodsCl="";							// 결제구분 실물 =1 ,컨텐츠=0 추가20140609	
	
	//빌링키 전용
	public String BL_TID="";							// 서브아이디
	public String CardQuota="";						// 	할부기간
	
	//현금영수증 전용
	public String ReceiptSupplyAmt ="";         // 현금영수증 공급가액			추가20140408	
	public String ReceiptVAT ="";               // 현금영수증 부가세			추가20140408	
	public String ReceiptServiceAmt ="";        // 현금영수증 봉사료			추가20140408	
	public String ReceiptIdentity ="";  		// 현금영수증 발급번호			추가20140408	
	public String CashReceiptType ="";			// 현금영수증 용도구분	 1 : 발행(개인 소득공제), 2 : 발행(사업자 지출증빙)		추가20140408
	public String ReceiptAmt = "";				// 현금영수증 총금액
	public String ReceiptType = "";
	
	//암호화 모드
	public String ReturnType ="";				// 응답유형PSOT ,TEXT			추가20140408	
	public String BillKeyMode="";				// 필드 암호화 여부 (최초 빌링키 전용이었으나 모듈 통합으로 의미 변경)
	
	public String CardPoint = "";               //카드 포인트 추가 20151217
	
	
	//가상계좌 
	public String VbankBankCode = "";			// 가상계좌은행코드
	public String VbankExpDate = "";			// 입금예정일
	public String TransType = "";				// 거래형태(04 = 일괄 채번)
	
	
	
	/*
	 * GETTER / SETTER
	 */
	
	public String getReceiptAmt() {
		return ReceiptAmt;
	}
	public String getReceiptType() {
		return ReceiptType;
	}
	public void setReceiptType(String receiptType) {
		ReceiptType = receiptType;
	}
	public void setReceiptAmt(String receiptAmt) {
		ReceiptAmt = receiptAmt;
	}
	public String getVbankBankCode() {
		return VbankBankCode;
	}
	public void setVbankBankCode(String vbankBankCode) {
		VbankBankCode = vbankBankCode;
	}
	public String getVbankExpDate() {
		return VbankExpDate;
	}
	public void setVbankExpDate(String vbankExpDate) {
		VbankExpDate = vbankExpDate;
	}
	public String getTransType() {
		return TransType;
	}
	public void setTransType(String transType) {
		TransType = transType;
	}	
	public String getCardPoint() {
		return CardPoint;
	}
	public void setCardPoint(String cardPoint) {
		CardPoint = cardPoint;
	}
	public String getPayMethod() {
		return PayMethod;
	}
	public void setPayMethod(String payMethod) {
		PayMethod = payMethod;
	}
	public String getEdiDate() {
		return ediDate;
	}
	public void setEdiDate(String ediDate) {
		this.ediDate = ediDate;
	}
	public String getGoodsCnt() {
		return GoodsCnt;
	}
	public void setGoodsCnt(String goodsCnt) {
		GoodsCnt = goodsCnt;
	}
	public String getGoodsName() {
		return GoodsName;
	}
	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}
	public String getAmt() {
		return Amt;
	}
	public void setAmt(String amt) {
		Amt = amt;
	}
	public String getMoid() {
		return Moid;
	}
	public void setMoid(String moid) {
		Moid = moid;
	}
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
	}
	public String getChannelType() {
		return ChannelType;
	}
	public void setChannelType(String channelType) {
		ChannelType = channelType;
	}
	public String getMallIP() {
		return MallIP;
	}
	public void setMallIP(String mallIP) {
		MallIP = mallIP;
	}
	public String getMallReserved() {
		return MallReserved;
	}
	public void setMallReserved(String mallReserved) {
		MallReserved = mallReserved;
	}
	public String getMallUserID() {
		return MallUserID;
	}
	public void setMallUserID(String mallUserID) {
		MallUserID = mallUserID;
	}
	public String getBuyerName() {
		return BuyerName;
	}
	public void setBuyerName(String buyerName) {
		BuyerName = buyerName;
	}
	public String getBuyerTel() {
		return BuyerTel;
	}
	public void setBuyerTel(String buyerTel) {
		BuyerTel = buyerTel;
	}
	public String getBuyerEmail() {
		return BuyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		BuyerEmail = buyerEmail;
	}
	public String getParentEmail() {
		return ParentEmail;
	}
	public void setParentEmail(String parentEmail) {
		ParentEmail = parentEmail;
	}
	public String getBuyerAddr() {
		return BuyerAddr;
	}
	public void setBuyerAddr(String buyerAddr) {
		BuyerAddr = buyerAddr;
	}
	public String getBuyerPostNo() {
		return BuyerPostNo;
	}
	public void setBuyerPostNo(String buyerPostNo) {
		BuyerPostNo = buyerPostNo;
	}
	public String getUserIP() {
		return UserIP;
	}
	public void setUserIP(String userIP) {
		UserIP = userIP;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getVERIFY_V() {
		return VERIFY_V;
	}
	public void setVERIFY_V(String vERIFY_V) {
		VERIFY_V = vERIFY_V;
	}
	public String getReturnURL() {
		return ReturnURL;
	}
	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}
	public String getRetryURL() {
		return RetryURL;
	}
	public void setRetryURL(String retryURL) {
		RetryURL = retryURL;
	}
	public String getSUB_ID() {
		return SUB_ID;
	}
	public void setSUB_ID(String sUB_ID) {
		SUB_ID = sUB_ID;
	}
	public String getGoodsCl() {
		return GoodsCl;
	}
	public void setGoodsCl(String goodsCl) {
		GoodsCl = goodsCl;
	}
	public String getBL_TID() {
		return BL_TID;
	}
	public void setBL_TID(String bL_TID) {
		BL_TID = bL_TID;
	}
	public String getCardQuota() {
		return CardQuota;
	}
	public void setCardQuota(String cardQuota) {
		CardQuota = cardQuota;
	}
	public String getReceiptSupplyAmt() {
		return ReceiptSupplyAmt;
	}
	public void setReceiptSupplyAmt(String receiptSupplyAmt) {
		ReceiptSupplyAmt = receiptSupplyAmt;
	}
	public String getReceiptVAT() {
		return ReceiptVAT;
	}
	public void setReceiptVAT(String receiptVAT) {
		ReceiptVAT = receiptVAT;
	}
	public String getReceiptServiceAmt() {
		return ReceiptServiceAmt;
	}
	public void setReceiptServiceAmt(String receiptServiceAmt) {
		ReceiptServiceAmt = receiptServiceAmt;
	}
	public String getReceiptIdentity() {
		return ReceiptIdentity;
	}
	public void setReceiptIdentity(String receiptIdentity) {
		ReceiptIdentity = receiptIdentity;
	}
	public String getCashReceiptType() {
		return CashReceiptType;
	}
	public void setCashReceiptType(String cashReceiptType) {
		CashReceiptType = cashReceiptType;
	}
	public String getReturnType() {
		return ReturnType;
	}
	public void setReturnType(String returnType) {
		ReturnType = returnType;
	}
	public String getBillKeyMode() {
		return BillKeyMode;
	}
	public void setBillKeyMode(String billKeyMode) {
		BillKeyMode = billKeyMode;
	}
	

	

}
