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
    <title>Book add form</title>
</head>
<body>
<div>Add new book:</div>
<form:form method="post" modelAttribute="author">
    <div>
        <label for="firstName">First name</label>
        <form:input path="firstName"/>
        <form:errors path="firstName" cssStyle="color: #ff7f50"/>
    </div>

    <div>
        <label for="lastName">Last name</label>
        <form:input path="lastName"/>
        <form:errors path="lastName"/>
    </div>

    <div>
        <label for="email">Email</label>
        <form:input path="email"/>
        <form:errors path="email"/>
    </div>


    <div>
        <label for="pesel">PESEL</label>
        <form:input path="pesel" />
        <form:errors path="pesel"/>
    </div>

    <input type="submit">

</form:form>
</body>
</html>
