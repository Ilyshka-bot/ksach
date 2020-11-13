<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Войти в аккаунт</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
</head>

<body>

<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<script>
    document.addEventListener("DOMContentLoaded", resultAuth);//сработает после перенаправления на страницу error
    function resultAuth() {
        let paramsString = document.location.search;
        if(paramsString == "?error"){
            alert("Ошибка аутентификации!");
        }
    }

</script>
</sec:authorize>
<div class="container h-100" style="text-align: center">
    <div class="row h-100 justify-content-center align-items-center">
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
            <input name="username" type="text" placeholder="Имя пользователя" class="input"
                   autofocus="true"/><br>
            <input name="password" type="password" placeholder="Пароль" class="input"/>
            <h4><a href="/registration" style="font-size: 16px">Зарегистрироваться</a></h4>
        <button type="submit" class="btn btn-primary" id="button">Войти</button>
            </form>
        </div>
    </div>

</body>
</html>