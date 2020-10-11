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
    <title>Student form with binding</title>
</head>
<body>
<div>Student form with binding:</div>
<form:form method="post" modelAttribute="student">
    <div>
        <label for="firstName">First Name</label>
        <form:input path="firstName"/>
    </div>
    <div>
        <label for="lastName">Last Name</label>
        <form:input path="lastName"/>
    </div>
    <div>
        <label for="gender">Gender</label>
        Male: <form:radiobutton path="gender" value="M" id="gender"/>
        Female: <form:radiobutton path="gender" value="F" id="gender"/>
    </div>
    <div>
        <label for="country">Country</label>
        <form:select path="country" items="${countries}" />
    </div>
    <div>
        <label for="notes">Notes</label>
        <form:textarea path="notes"/>
    </div>
    <div>
        <label for="mailingList">Mailing list Y/N</label>
        <form:checkbox path="mailingList" id="mailingList"/>
    </div>
    <div>
        <label for="programmingSkills">Programming skills</label>
        <form:select path="programmingSkills" items="${skills}" multiple="true"/>
    </div>
    <div>
        <label for="hobbies">Hobbies</label>
        <form:checkboxes path="hobbies" items="${hobbies}"/>
    </div>

    <input type="submit">

</form:form>
</body>
</html>
