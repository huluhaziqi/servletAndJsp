<%@ page import="com.lin.Employee" %><%--
  Created by IntelliJ IDEA.
  User: linxiaobin
  Date: 2018/1/29
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>

<html>
<head>
    <title>employee</title>
</head>
<body>
${header['accept-language']}
<br/>
${pageContext.session.id}
<br/>
${requestScope.employee.name}, ${employee.address.city}

capital: ${capitals["Canada"]}
<%--<core:out value="value" [escapeXml="{true|false}"] [default="defaultValue"]/>--%>
<%--<core:out value="value" escapeXml="{true|false}" default="defaultValue"/>--%>
<core:out value="${sessionScope.myVar}" default="${applicationScope.myVar}"/>
<br/>
</body>
</html>
