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
    <title>List books</title>
</head>
<body>
Books:
<table border="1">
    <tr>
        <th>Lp.</th>
        <th>Title</th>
        <th>Description</th>
        <th>Rating</th>
        <th>Publisher</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <c:forEach items="${books}" var="book" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${book.title}</td>
            <td>${book.description}</td>
            <td>${book.rating}</td>
            <td>${book.publisher.name}</td>
            <td>
                <a href="update/${book.id}/${book.title}">Edit</a>
            </td>
            <td>
                <a href="delete/${book.id}"/>Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
