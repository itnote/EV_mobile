package kr.co.smartro.xpg_pag.util;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * 전문을 파싱하여 Object array 로 처리한다. <br><br><br>
 *
 * StringTokenizer의 기능을 대신하기 위해 작성한 클래스로
 * 구분자로 구분한 데이터를 가지고 있는다.
 * 널이나 공백도 하나의 필드로 인식해서 값을 넘겨준다.
 *
 * @version 1.0
 * @author rywkim@inicis.com
 *
 */
public class  MessageTokenizer
{
	protected Object[] tokened_array=null;

	protected int token_count=0;
    private String returnStr;

	/**
	 * 생성자 
     *
	 * @param	plainText 	파싱할 전문
	 * @param   delim	    구분자
	 */
	public MessageTokenizer(String plainText, String delim)
	{
		tokenizerWithBlanks(plainText, delim);
	}
	/**
	* MessageTokenizer가 가지고 있는 전체 token의 갯수를 리턴한다.
    *
	* @return	tokenizer 갯수
	*/
	public int countTokens()
	{
		return tokened_array.length-token_count;
	}

	/**
	 * 넘겨줄 여분의 token 이 있는지 확인한다.
     *
	 * @return 	여분의 토큰을 가지고 있는지의 여부
	 */
	public boolean hasMoreTokens()
	{
		if(tokened_array.length>token_count)
			return true;
		return false;
	}

	/**
	 * 넘겨줄 여분의 token 이 있는지 확인한다.
     *
	 * @return 	여분의 토큰을 가지고 있는지의 여부
	 */
	public boolean hasMoreElements()
	{
		return hasMoreTokens();
	}


	/**
	 * MessageTokenizer 의 다음 token을 넘겨준다.
	 *
	 * @return	token 값
	 * @exception NoSUchElementException 더이상 넘겨줄 토큰이 없는경우 발생.
	 */
	public String nextToken() throws NoSuchElementException
	{
		if(token_count>=tokened_array.length)
			throw new NoSuchElementException("|9999|DATA 항목 갯수 오류|");
		token_count++;
		return tokened_array[token_count-1].toString();
	}


    /**
	 * MessageTokenizer 의 다음 token에 줄바꿈 기호를 삽입하여 넘겨준다. 
	 *
     * @param inpStr 특정 값에대해서만 ln을 삽입하는경우 
	 * @return	token 값
	 * @exception NoSUchElementException 더이상 넘겨줄 토큰이 없는경우 발생.
	 */
	public String nextTokenln(String inpStr) throws NoSuchElementException
	{
		if(token_count>=tokened_array.length)
			throw new NoSuchElementException("No more element.");
		token_count++;
        
        returnStr = tokened_array[token_count-1].toString();

        if ("inpStr".equals(returnStr))
        {
            return "\n" + inpStr;
        }

		return returnStr;
	}


    /**
	 * MessageTokenizer의 커서를 하나만큼 옮긴다. 
     * 블랭크를 알고있는경우 사용
	 *
	 * @exception NoSUchElementException 더이상 넘겨줄 토큰이 없는경우 발생.
	 */
    public void passToken() throws NoSuchElementException
    {
		if(token_count>=tokened_array.length)
			throw new NoSuchElementException("No more element.");
		token_count++;
    }


    /**
	 * 입력받은 스트링을 공백과 함께 delimiter로 자른다.
	 *
     * @param input 파싱할 문자열
     * @param delimiter 구분자
	 */
	protected void tokenizerWithBlanks (String input, String delimiter)
	{
		ArrayList array = new ArrayList();
		String token;
		int pos;
        int delimiterSize = delimiter.length();
		do{
			pos = input.indexOf(delimiter);
		   	if (pos >= 0)
			{
				token = input.substring(0, pos);
				input = input.substring(pos + delimiterSize);
			}
			else
			{
				token = input;
				input = "";
			}
			array.add(token);
		}while (pos >= 0);
		tokened_array = array.toArray();
	}


    /**
	 * MessageTokenizer의 모양을 본다.
     *
     * 잘못만들어서 안쓴다. -_-;
     *
	 */
    public String toString()
    {
        MessageTokenizer temp = this;
        StringBuffer sb = new StringBuffer();
        int count = 0;
        while (temp.hasMoreTokens())
        {
            sb.append(++count);
            sb.append(":");
            sb.append(this.nextToken());
            sb.append("\n");
        }

        return sb.toString();
    }

}
