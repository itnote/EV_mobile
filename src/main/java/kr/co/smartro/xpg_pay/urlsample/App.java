package kr.co.smartro.xpg_pay.urlsample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


public class App 
{

	public  String UrlCall(String CallUrl , Dto dtos ) 
	{
		try 
		{
			System.out.println("Start UrlCall ");

			StringBuffer buffer = new StringBuffer();
			buffer.append("?").append("PayMethod").append("=").append(dtos.getPayMethod()).append("&"); 
			buffer.append("ediDate").append("=").append(dtos.getEdiDate()).append("&"); 
			buffer.append("GoodsCnt").append("=").append(dtos.getGoodsCnt()).append("&"); 
			buffer.append("GoodsName").append("=").append(dtos.getGoodsName()).append("&"); 
			buffer.append("Amt").append("=").append(dtos.getAmt()).append("&"); 
			buffer.append("Moid").append("=").append(dtos.getMoid()).append("&"); 
			buffer.append("MID").append("=").append(dtos.getMID()).append("&"); 
			buffer.append("BL_TID").append("=").append(dtos.getBL_TID()).append("&"); 
			buffer.append("CardQuota").append("=").append(dtos.getCardQuota()).append("&"); 
			buffer.append("ChannelType").append("=").append(dtos.getChannelType()).append("&"); 
			buffer.append("VERIFY_V").append("=").append(dtos.getVERIFY_V()).append("&"); 
			buffer.append("MallIP").append("=").append(dtos.getMallIP()).append("&"); 
			buffer.append("MallReserved").append("=").append(dtos.getMallReserved()).append("&"); 
			buffer.append("MallUserID").append("=").append(dtos.getMallUserID()).append("&"); 
			buffer.append("BuyerName").append("=").append(dtos.getBuyerName()).append("&"); 
			buffer.append("BuyerTel").append("=").append(dtos.getBuyerTel()).append("&"); 
			buffer.append("BuyerEmail").append("=").append(dtos.getBuyerEmail()).append("&"); 
			buffer.append("ParentEmail").append("=").append(dtos.getParentEmail()).append("&"); 
			buffer.append("BuyerAddr").append("=").append(dtos.getBuyerAddr()).append("&"); 
			buffer.append("BuyerPostNo").append("=").append(dtos.getBuyerPostNo()).append("&"); 
			buffer.append("UserIP").append("=").append(dtos.getUserIP()).append("&"); 
			buffer.append("ReturnURL").append("=").append(dtos.getReturnURL()).append("&");  //추가20140408
			buffer.append("RetryURL").append("=").append(dtos.getRetryURL()).append("&");  //추가20140408		
			buffer.append("SUB_ID").append("=").append(dtos.getSUB_ID()).append("&");  //추가20140408
			
			buffer.append("ReceiptSupplyAmt").append("=").append(dtos.getReceiptSupplyAmt()).append("&");  //현금 영수증 전용 추가20140408
			buffer.append("ReceiptVAT").append("=").append(dtos.getReceiptVAT()).append("&");  //현금 영수증 전용 추가20140408
			buffer.append("ReceiptServiceAmt").append("=").append(dtos.getReceiptServiceAmt()).append("&");  //현금 영수증 전용 추가20140408
			buffer.append("ReceiptIdentity").append("=").append(dtos.getReceiptIdentity()).append("&");  //현금 영수증 전용 추가20140408
			buffer.append("CashReceiptType").append("=").append(dtos.getCashReceiptType()).append("&");  //현금 영수증 전용 추가20140408
			
			buffer.append("GoodsCl").append("=").append(dtos.getGoodsCl()).append("&");  //휴대폰 빌링 전용 추가20140609
			
			buffer.append("ReturnType").append("=").append(dtos.getReturnType()).append("&");  //추가20140408	
			buffer.append("CardPoint").append("=").append(dtos.getCardPoint()).append("&"); 

			buffer.append("VbankBankCode").append("=").append(dtos.getVbankBankCode()).append("&"); //추가 20160121 
			buffer.append("VbankExpDate").append("=").append(dtos.getVbankExpDate()).append("&"); //추가 20160121
			buffer.append("TransType").append("=").append(dtos.getTransType()).append("&"); //추가 20160121
			buffer.append("ReceiptAmt").append("=").append(dtos.getReceiptAmt()).append("&"); //추가 20160121
			buffer.append("ReceiptType").append("=").append(dtos.getReceiptType()).append("&"); //추가 20160121

			
			buffer.append("BillKeyMode").append("=").append(dtos.getBillKeyMode()); 
	
			
			
			URL url = new URL(CallUrl+buffer.toString());
			System.out.println(" url: "+url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			System.out.println(" OutputStreamWriter start UrlCall ");
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "EUC-KR"); // 서버는  "EUC-KR" 입니다 변경하지 마세요..
			System.out.println(" OutputStreamWriter  buffer "+buffer.toString());
			//wr.write(buffer.toString());
			wr.flush();
			System.out.println(" OutputStreamWriter flush UrlCall ");
			BufferedReader rd = new BufferedReader(new InputStreamReader( connection.getInputStream()));  // 서버는  "EUC-KR" 입니다 필요시 UTF-8 형으로 변경하세요.
			System.out.println(" BufferedReader UrlCall ");
			String result = "";
			String line;
			while ((line = rd.readLine()) != null)
			{
				result += line;
			}
			wr.close();
			rd.close();
			System.out.println("result :"+result);
			System.out.println(" End UrlCall ");
			return result;
		} 
		catch (Exception e) 
		{
			System.out.println(" UrlCall Exception :"+e.toString());
			return "1";
		}
	}
	
    /**
     * 현재날짜를 YYYYMMDDHHMMSS로 리턴
     */
    public  final synchronized String getyyyyMMddHHmmss(){
    	/** yyyyMMddHHmmss Date Format */
    	SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    	
    	return yyyyMMddHHmmss.format(new Date());
    }
    /**
     * <pre>
     * MD5+Base64
     * </pre>
     * @param pw
     * @return String
     */
    public  final String encodeMD5Base64(String str){
    	return new String(Base64.encodeBase64(DigestUtils.md5(str)));
    }
     
    public  final String encodeMD5HexBase64(String pw){
    	return new String(Base64.encodeBase64(DigestUtils.md5Hex(pw).getBytes()));
    }
    
    /**
     * <pre>
     * MD5+Base64
     * </pre>
     * @param pw
     * @return String
     */
    public  final String UrlencodeBase64(String str){
    	return new String(Base64.encodeBase64(str.getBytes()));
    }
    public  final String UrldecodeBase64(String str){
    	return new String(Base64.decodeBase64(str.getBytes()));
    }
	
}

