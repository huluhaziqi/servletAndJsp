<%@ page import="com.lin.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>books jsp</title>
</head>
<body>
hello
<c:forEach items="${requestScope.books}" var="book" varStatus="status">

    <c:if test="${status.count % 2 == 0}">
        <tr style="background: #eeeeff;">
    </c:if>
    <c:if test="${status.count % 2 == 1}">
        <tr style="background: #dedeff">
    </c:if>
    <td>${book.isbn}</td>
    <br/>
    <td>${status.count}</td>
    <br/>
    <td>${book.title}</td>
    </tr>
</c:forEach>

<br/>second
<br/>
<c:forEach items="${requestScope.books}" var="book" varStatus="status">

    ${book.isbn}<c:if test="${!status.last}">,</c:if>
    <br/>
</c:forEach>
</body>
</html>
