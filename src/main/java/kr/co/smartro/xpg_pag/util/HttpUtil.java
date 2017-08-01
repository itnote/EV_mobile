package kr.co.smartro.xpg_pag.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * httpClient
 */
public class HttpUtil 
{
	private HttpClient client;
	private HostConfiguration hc;
	private NameValuePair[] params;
	private PostMethod postMethod;
	
	private static int CONNECTION_TIMEOUT = 60000;
	private static int RECEIVE_TIMEOUT = 15000;
	
	/**
	 * 생성자
	 */
	public HttpUtil()
	{
	}
		
	/**
	 * Post Method 방식으로 통신
	 * 
	 * @param request		요청할 데이터
	 * @param actionURL		요청할 URL
	 * @return
	 * @throws Exception
	 */

	public String sendByPost(Hashtable request, String actionURL) throws Exception
	{
		int hashSize = request.size();
		int statusCode = 0;
		String key = "";
		Enumeration keys = request.keys();

		try
		{
			client = new HttpClient();
			hc = new HostConfiguration();
			
			client.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT); // connection Timeout
			client.getHttpConnectionManager().getParams().setSoTimeout(RECEIVE_TIMEOUT);// receive Timeout

			postMethod = new PostMethod(actionURL);
			hc.setHost(postMethod.getURI().getHost(), postMethod.getURI().getPort());
			
			System.out.println("Connect to Client : " + actionURL);

			params = new NameValuePair[hashSize];
			for (int i=0; i<hashSize; i++)
			{
				key = (String) keys.nextElement();
				params[i]= new NameValuePair(key, (String) request.get(key));
				System.out.println("param" + (i+1) + " : " + key + " : " + request.get(key));
			}
			
			postMethod.setRequestBody(params);
			
			try 
		    {
		        statusCode = client.executeMethod(postMethod);
		    }
		    catch (Exception e)
		    {
		    	e.printStackTrace();
		    	return "ERROR:" + e.getMessage();
		    }
	
		    if( statusCode == 200 )
		    {
		    	String result1 = postMethod.getResponseBodyAsString();
		    	System.out.println("서버응답 : " + statusCode);
		    	System.out.println("Response from CLIENT : " + Tool.nullCheck(result1).trim());
		    	return result1;
		    }
			else
		    {
				String result2 = postMethod.getResponseBodyAsString();
				System.out.println("서버응답 : " + statusCode);
				System.out.println("Response from CLIENT : " + Tool.nullCheck(result2).trim());
				return "ERROR:ClientServerError:" + statusCode;
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
	    	return "ERROR:" + e.getMessage();
		}
		finally
		{
			if (postMethod != null) 
			{
				postMethod.releaseConnection();
			}

			client.getHttpConnectionManager().getConnection(hc).close();

			System.out.println("Release Connection success");
		}
	}
   
}
