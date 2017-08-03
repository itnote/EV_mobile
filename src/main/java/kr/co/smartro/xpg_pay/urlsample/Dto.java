package kr.co.smartro.xpg_pay.urlsample;

public class Dto {
	
	public String PayMethod=""; 						// ���Ҽ��� CARDBILL:�ſ�ī��
	public String ediDate=""; 							// ���������Ͻ�
	public String GoodsCnt="";						// ���� ��ǰ ǰ�� ����
	public String GoodsName="";						// �ŷ� ��ǰ��	
	public String Amt="";								// �ŷ� �ݾ�	
	public String Moid="";								// ��ǰ�ֹ���ȣ	
	public String MID="";								// �������̵�
	public String ChannelType="";					// ä������	
	public String MallIP="";							// ��������IP
	public String MallReserved="";					// ������������	// ȸ�����ID
	public String MallUserID="";
	public String BuyerName="";					    // �����ڸ�
	public String BuyerTel="";					    	// �����ڿ���ó
	public String BuyerEmail="";					    // �����ڸ����ּ�
	public String ParentEmail="";					    // ��ȣ�ڸ����ּ� ������
	public String BuyerAddr="";					    // ������ּ�
	public String BuyerPostNo="";					// �����ȣ
	public String UserIP="";					        // ȸ����� IP
	public String Language=""; 						// KR EN ����޽��� ���� ,�ѱ� ����
	public String VERIFY_V="";							// ������	EncryptData  �����뵵
	public String ReturnURL="";						// ���� ������� ���� URL	 �߰�20140408
	public String RetryURL	="";						// ���� ������� Retry URL �߰�20140408	
	public String SUB_ID="";							// ������̵�                 �߰�20140408	
	
	//�޴��� ���� ����

	public String GoodsCl="";							// �������� �ǹ� =1 ,������=0 �߰�20140609	
	
	//����Ű ����
	public String BL_TID="";							// ������̵�
	public String CardQuota="";						// 	�ҺαⰣ
	
	//���ݿ����� ����
	public String ReceiptSupplyAmt ="";         // ���ݿ����� ���ް���			�߰�20140408	
	public String ReceiptVAT ="";               // ���ݿ����� �ΰ���			�߰�20140408	
	public String ReceiptServiceAmt ="";        // ���ݿ����� �����			�߰�20140408	
	public String ReceiptIdentity ="";  		// ���ݿ����� �߱޹�ȣ			�߰�20140408	
	public String CashReceiptType ="";			// ���ݿ����� �뵵����	 1 : ����(���� �ҵ����), 2 : ����(����� ��������)		�߰�20140408
	public String ReceiptAmt = "";				// ���ݿ����� �ѱݾ�
	public String ReceiptType = "";
	
	//��ȣȭ ���
	public String ReturnType ="";				// ��������PSOT ,TEXT			�߰�20140408	
	public String BillKeyMode="";				// �ʵ� ��ȣȭ ���� (���� ����Ű �����̾����� ��� �������� �ǹ� ����)
	
	public String CardPoint = "";               //ī�� ����Ʈ �߰� 20151217
	
	
	//������� 
	public String VbankBankCode = "";			// ������������ڵ�
	public String VbankExpDate = "";			// �Աݿ�����
	public String TransType = "";				// �ŷ�����(04 = �ϰ� ä��)
	
	
	
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
