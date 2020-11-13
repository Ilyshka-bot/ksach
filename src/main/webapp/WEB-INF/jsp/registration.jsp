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
        <h2 style="margin-bottom: 20px">Регистрация</h2>
            <div><form:input type="text" path="username" placeholder="Имя пользователя" cssClass="input"
                                              autofocus="true"></form:input></div>
            <div class="smallDiv" style="color: red"> <form:errors path="username"></form:errors>
             ${usernameError}</div>

            <div>  <form:input type="password" path="password" placeholder="Пароль" cssClass="input"></form:input></div>
            <div class="smallDiv" style="color: red"> <form:errors path="password"></form:errors>
                ${passwordEmpty}</div>
            <div>  <form:input type="password" path="passwordConfirm" cssClass="input"
                            placeholder="Подтверждение пароля" ></form:input></div>
            <div class="smallDiv" style="color: red">  <form:errors path="passwordConfirm"></form:errors>
                ${passwordError}</div>
            <div>    <form:input type="text" path="mail" placeholder="Почта" cssClass="input"></form:input></div>
            <div class="smallDiv" style="color: red">  <form:errors path="mail"></form:errors>
                 ${mailError}</div>
            <div>  <form:input type="text" path="fullname" placeholder="ФИО" cssClass="input"></form:input></div>
            <div class="smallDiv" style="color: red"> <form:errors path="fullname"></form:errors>
                ${fullnameError}</div>

        <%--@elvariable id="clientForm" type=""--%>
        <form:form method="POST" modelAttribute="clientForm">

            <div>  <form:input type="text" path="passportData" placeholder="Паспортные данные" cssClass="input"></form:input></div>
                       <div class="smallDiv" style="color: red"> <form:errors path="passportData"></form:errors>
                           ${passportError}</div>
                       <button type="submit" class="btn btn-primary" style="margin-top: 10px">Зарегистрироваться</button>
        </form:form>
    </form:form>
        <a href="/">Главная</a>
        </div>
    </div>
</body>
</html>