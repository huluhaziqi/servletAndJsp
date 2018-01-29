<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: linxiaobin
  Date: 2018/1/26
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
Welcome
<%=Calendar.getInstance().getTime()%>
<%!
    public String getTodayDate() {
        return new Date().toString();
    }
%>
<%=getTodayDate()
%>
<%
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
    String s = dateFormat.format(new Date());
    System.out.println("today is " + s);
    Enumeration<String> enumeration = request.getHeaderNames();
    while (enumeration.hasMoreElements()) {
        String e = enumeration.nextElement();
        String header = request.getHeader(e);
        System.out.println(header);
    }
    System.out.println("server info" + application.getServerInfo());
    System.out.println("config info" + config.getServletName());
    System.out.println("session info" + session.getId());
%>

<%!
    public void jspInit(){
        System.out.println("jsp init");
    }
    public void jspDestroy(){
        System.out.println("jsp destroy");
    }
%>
</body>
</html>
