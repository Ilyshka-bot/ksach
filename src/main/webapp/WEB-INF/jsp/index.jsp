<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
</head>
<body>
<div class="container h-100 text-center">
    <div class="row h-100 justify-content-center align-items-center" style="flex-direction: column">
    <sec:authorize access="!isAuthenticated()">
        <h4>Информационная система по организации экскурсий<br> центром безопасности МЧС</h4>
        <br><br><br><br><br>
        <button type="submit" class="btnIndex btn btn-primary" onclick="location.href='/login'">Войти</button>
            <button type="submit" class="btnIndex btn btn-outline-primary" onclick="location.href='/registration'">Зарегистрироваться</button>
    </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <h3>Имя пользователя: ${pageContext.request.userPrincipal.name}</h3>
            <h4><a href="/logout">Выйти</a></h4>
    </sec:authorize>
    <sec:authorize access="hasRole('USER')">
    <h4><a href="/clientWork">Заказать экскурсию</a></h4>
    </sec:authorize>
    <sec:authorize access="hasRole('EMPLOYEE')">
    <h4><a href="/employeeWork">Работа с заказами</a></h4>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
    <h4><a href="/admin">Панель админа</a></h4>
    </sec:authorize>

    </div>
</div>
</body>
</html>