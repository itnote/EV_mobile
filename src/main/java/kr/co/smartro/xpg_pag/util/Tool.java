package kr.co.smartro.xpg_pag.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Tool 
{
    /**
     * 현재날짜를 YYYYMMDDHHMMSS로 리턴
     */
    public  static final synchronized String getyyyyMMddHHmmss(){
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
    public  static final String encodeMD5Base64(String str){
    	return new String(Base64.encodeBase64(DigestUtils.md5(str)));
    }
     
    public  static final String encodeMD5HexBase64(String pw){
    	return new String(Base64.encodeBase64(DigestUtils.md5Hex(pw).getBytes()));
    }
    
    /**
     * <pre>
     * MD5+Base64
     * </pre>
     * @param pw
     * @return String
     */
    public  static final String UrlencodeBase64(String str){
    	return new String(Base64.encodeBase64(str.getBytes()));
    }
    public  static final String UrldecodeBase64(String str){
    	return new String(Base64.decodeBase64(str.getBytes()));
    }
    
	/**
	 * 응답메세지 파싱
	 * @param plainText
	 * @param delim
	 * @param delim2
	 * @return
	 */
    public static Hashtable<String,String> parseMessage(String plainText, String delim, String delim2)
	{
		Hashtable<String,String> retData = new Hashtable<String,String>();
		MessageTokenizer message = new MessageTokenizer(plainText, delim);
		String temp = "";
		
		while(message.hasMoreTokens())
		{
			temp = message.nextToken();
			if (!"".equals(temp))
				retData.put( temp.substring(0,temp.indexOf(delim2)),temp.substring(temp.indexOf(delim2)+1).trim() );
		}
		return retData;
	}


	/**
	 * 응답메세지 파싱, EUC-KR 디코딩 내재
	 * @param plainText
	 * @param delim
	 * @param delim2
	 * @return
	 */
    public static Hashtable<String,String> parseMessageForEucKr(String plainText, String delim, String delim2)
	{
		Hashtable<String,String> retData = new Hashtable<String,String>();
		MessageTokenizer message = new MessageTokenizer(plainText, delim);
		String temp = "";
		
		while(message.hasMoreTokens())
		{
			temp = message.nextToken();
			if (!("".equals(temp.trim()) || null == temp))
			{
				try {
					retData.put( temp.substring(0,temp.indexOf(delim2)), URLDecoder.decode(temp.substring(temp.indexOf(delim2)+1).trim(), "EUC-KR"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e){
					System.out.println("temp : [" + temp +"]");
					e.printStackTrace();
				}
			}
		}
		return retData;
	}

    /**
     * 스트링 null 체크
     * @param o
     * @return
     */
    public static String nullCheck(Object o)
    {
 	   String temp = "";
 	   
 	   if (o != null)
 	   {
 	   	   temp = (String) o;
 	   	   
 	   	   try
 	   	   {
 	   	       temp = (temp.length()>0 && "null".equals(temp.trim().toLowerCase()) )? "":temp;
 	   	   }catch(Exception exx){
 	   		   exx.printStackTrace();
 	   	   }
 	   	   
 	   }
 	   
 	   return temp;
    }
    
	/**
	 * 문자열의 캐릭터 인코딩을 "8859_1"로 변환하고 다시 "EUC-KR"로 변환
	 * <code>str</code>이 <code>NULL</code>또는 공문자열이면 공문자열("")을 반환한다.
	 * @param	str 변환할 문자열
	 * @return	변환된 문자열
	 */
	public static String convertTo8859_1NEuckr(String str)
	{
		String rtv = "";
		if(str != null && !str.equals(""))
		{
			try {
				rtv = new String(str.getBytes("8859_1"), "utf-8");
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
		}
		return rtv;
	}
	
	public static String urlEncodeEuckr(String str)
	{
		try {
			str =  URLEncoder.encode(str, "euc-kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String urlDecodeEuckr(String str)
	{
		try {
			str =  URLDecoder.decode(str, "euc-kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
