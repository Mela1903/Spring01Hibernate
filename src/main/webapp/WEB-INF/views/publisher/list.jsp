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
    <title>List publishers</title>
</head>
<body>
Publishers:
<table border="1" style="column-rule-width: 40px">
    <tr>
        <th>Lp.</th>
        <th>Name</th>
        <th>NIP</th>
        <th>REGON</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <c:forEach items="${publishers}" var="publish" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${publish.name}</td>
            <td>${publish.nip}</td>
            <td>${publish.regon}</td>
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
