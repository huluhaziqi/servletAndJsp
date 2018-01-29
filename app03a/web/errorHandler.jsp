<%--
  Created by IntelliJ IDEA.
  User: linxiaobin
  Date: 2018/1/29
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${pageContext.session.id} <br/>
${pageContext.request.servletPath} <br/>
${pageContext["request"]["servletPath"]} <br/>
${header.values().size()};
error has occurred <br/>
</body>
</html>
