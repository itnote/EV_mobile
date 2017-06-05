<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>

<%!
	/**
	 * 기준날짜에서 몇일 전,후의 날짜를 구한다.
	 * @param	sourceTS	기준날짜
	 * @param	day			변경할 일수
	 * @return	기준날짜에서 입력한 일수를 계산한 날짜
	 */
	public static Timestamp getTimestampWithSpan(Timestamp sourceTS, long day)
			throws Exception {
		Timestamp targetTS = null;

		if (sourceTS != null) {
			targetTS = new Timestamp(sourceTS.getTime()
					+ (day * 1000 * 60 * 60 * 24));
		}
		return targetTS;
	}

	/**
	 * 현재날짜를 YYYYMMDDHHMMSS로 리턴
	 */
	public final synchronized String getyyyyMMddHHmmss() {
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
	public static final String encodeMD5Base64(String str) {
		return new String(Base64.encodeBase64(DigestUtils.md5(str)));
	}

	public static final String encodeMD5HexBase64(String pw) {
		return new String(Base64.encodeBase64(DigestUtils.md5Hex(pw).getBytes()));
	}
%>