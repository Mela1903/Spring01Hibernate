<%--
  Created by IntelliJ IDEA.
  User: agaflak
  Date: 11.10.2020
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Publisher add form</title>
</head>
<body>
<div>Add new publisher:</div>
<form:form method="post" modelAttribute="publisher">
    <div>
        <label for="name">Name</label>
        <form:input path="name"/>
        <form:errors path="name"/>
    </div>

    <div>
        <label for="nip">NIP</label>
        <form:input path="nip"/>
        <form:errors path="nip"/>
    </div>

    <div>
        <label for="regon">REGON</label>
        <form:input path="regon"/>
        <form:errors path="regon"/>
    </div>

    <input type="submit">

</form:form>
</body>
</html>
