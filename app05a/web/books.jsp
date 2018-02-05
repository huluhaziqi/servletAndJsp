<%@ page import="com.lin.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>books jsp</title>
</head>
<body>
hello
<table>
<c:forEach items="${requestScope.books}" var="book" varStatus="status">

    <%--<c:if test="${!status.last}">--%>
    <%--<tr style="background: #442244">--%>
    <%--</c:if>--%>
    <%--<c:if test="${status.last}">--%>
    <%--<tr style="background:#448755">--%>
    <%--</c:if>--%>
    <c:if test="${status.count%2 ==0}">
        <tr style="background:#448755">
    </c:if>
    <c:if test="${status.count%2 ==1}">
        <tr style="background:#234562">
    </c:if>
    <td>isbn : ${book.isbn}</td>
    <td>title : ${book.title}</td>
    <br/>
    </tr>
</c:forEach>
</table>
<br/>
<br/>

<c:forEach items="${requestScope.bigCities}" var="mapItem" varStatus="status">
    <tr>
        <td>
            value : ${mapItem.key}
        </td>
        <td>
            <c:forEach items="${mapItem.value}" var="city" varStatus="status2">
                ${city}<br/><c:if test="${!status2.last}">,</c:if>
            </c:forEach>
        </td>
    </tr>
    <br/>
</c:forEach>
<br/>second
<br/>
<table>
    <tr style="background:#448755">
        <td>Country</td>
        <td>Cities</td>
    </tr>
    <c:forEach items="${requestScope.books}" var="book" varStatus="status">
        <c:if test="${status.count%2 ==0}">
            <tr style="background:#448755">
        </c:if>
        <c:if test="${status.count%2 ==1}">
            <tr style="background: #234562">
        </c:if>
        <td>isbn : ${book.isbn}</td>
        <td>title : ${book.title}</td>
        <br/>
        </tr>
    </c:forEach>
</table>
</body>
</html>
