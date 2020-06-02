<%@page import="java.net.InetAddress"%>
<%@page import="java.util.*"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
System.out.println("dddddd111dd22");


//    Timestamp toDay = new Timestamp((new Date()).getTime());
//    Timestamp nxDay = getTimestampWithSpan(toDay, 1);
//    String VbankExpDate = nxDay.toString();
//    VbankExpDate = VbankExpDate.substring(0, 10);
//    VbankExpDate = VbankExpDate.replaceAll("-", "");
//

    String  ymd ="JJ180524";
    String  orderSno =  "jeju"  + ( System.currentTimeMillis() / 1000);
    String  orderSno1 =      (""  + ( System.currentTimeMillis() / 1000)).substring(6,10);


    System.out.println("JJ180524:" + orderSno1);
    System.out.println(ymd + orderSno1);


%>