<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация сотрудника</title>
</head>

<body>
<div>
    <%--@elvariable id="userForm" type=""--%>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация сотрудника</h2>
        <div>
            <form:input type="text" path="username" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="username"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <div>
            <form:input type="text" path="mail" placeholder="Mail"></form:input>
            <form:errors path="mail"></form:errors>
                ${mailError}
        </div>
        <div>
            <form:input type="text" path="fullname" placeholder="Fullname"></form:input>
        </div>

        <%--@elvariable id="employeeForm" type=""--%>
        <form:form method="POST" modelAttribute="employeeForm">
            <div>
                <form:input type="text" path="category" placeholder="Category"></form:input>
            </div>
            <div>
                <p>Выберите дату начала: <form:input type="date" name="calendar" path="date_start"></form:input>
            </div>
            <div>
                <p>Выберите дату окончания: <form:input type="date" name="calendar" path="date_end"></form:input>
            </div>
            <%--@elvariable id="postForm" type=""--%>
            <form:form method="POST"  modelAttribute = "postForm">
                <div>
                    <form:select path="name">
                        <form:option disabled="true" value="not worker">Выберите сотрудника</form:option>
                        <form:option value="Экскурсовод">Экскурсовод</form:option>
                        <form:option value="Работник">Работник</form:option>
                    </form:select>
                </div>
                <button type="submit">Зарегистрировать</button>
            </form:form>
        </form:form>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>