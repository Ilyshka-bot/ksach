<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
    <title>Регистрация</title>
</head>

<body>
    <div class="container h-100 text-center" >
        <div class="row h-100  justify-content-center align-items-center" style="flex-direction: column">
    <%--@elvariable id="userForm" type=""--%>
        <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:errors path="username"></form:errors>
            <div> <span style="color: red; "> ${usernameError}</span></div>
          <form:input type="text" path="username" placeholder="Имя пользователя" cssClass="input"
                        autofocus="true"></form:input>

        <div>
            <form:errors path="password"></form:errors>
            <div><span style="color: red; ">${passwordEmpty}</span></div>
            <form:input type="password" path="password" placeholder="Пароль" cssClass="input"></form:input>
        </div>
        <div>
            <form:errors path="passwordConfirm"></form:errors>
            <div><span style="color: red; ">${passwordError}</span></div>
            <form:input type="password" path="passwordConfirm" cssClass="input"
                        placeholder="Подтверждение пароля" ></form:input>
        </div>
        <div>
            <form:errors path="mail"></form:errors>
            <div>  <span style="color: red; "> ${mailError}</span></div>
            <form:input type="text" path="mail" placeholder="Почта" cssClass="input"></form:input>
        <div>
            <form:errors path="fullname"></form:errors>
            <div><span style="color: red; ">${fullnameError}</span></div>
            <form:input type="text" path="fullname" placeholder="ФИО" cssClass="input"></form:input>
        </div>
        <%--@elvariable id="clientForm" type=""--%>
        <form:form method="POST" modelAttribute="clientForm">
            <div>
                <form:errors path="passportData"></form:errors>
                <div><span style="color: red; "> ${passportError}</span></div>
                <form:input type="text" path="passportData" placeholder="Паспортные данные" cssClass="input"></form:input>
            </div>
                 <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
        </form:form>
    </form:form>
            <div>
        <a href="/">Главная</a>
            </div>
        </div>
    </div>
</body>
</html>