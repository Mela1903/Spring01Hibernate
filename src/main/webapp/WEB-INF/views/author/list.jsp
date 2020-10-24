<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: agaflak
  Date: 11.10.2020
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List authors</title>
</head>
<body>
Authors:
<table border="1" style="column-rule-width: 40px">
    <tr>
        <th>Lp.</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
        <th>PESEL</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <c:forEach items="${authors}" var="author" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${author.firstName}</td>
            <td>${author.lastName}</td>
            <td>${author.email}</td>
            <td>${author.pesel}</td>
            <td>
                <a href="">
                    <button style="size: 15px">Edit</button>
                </a>
            </td>
            <td>
                <a href=""/>
                <button style="size: 15px">Delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
