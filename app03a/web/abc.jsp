<%--
  Created by IntelliJ IDEA.
  User: linxiaobin
  Date: 2018/1/26
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<jsp:useBean id='hello' class="com.lin.Hello"/>
<jsp:setProperty name="hello" property="firstName" value="rewafewrf"/>
first name :
<jsp:getProperty name="hello" property="firstName"/>
<jsp:forward page="welcome.jsp">
    <jsp:param name="text" value="welcome"/>
</jsp:forward>
<jsp:include page="abc.jsp">
    <jsp:param name="text" value="how are you "/>
</jsp:include>
$END$
</body>
</html>
