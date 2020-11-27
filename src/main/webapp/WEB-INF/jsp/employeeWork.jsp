<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Рабочее место сотрудника</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
</head>
<body>
<div class="container h-100" style="text-align: center">
    <div class="row h-100 justify-content-center align-items-center" style="flex-direction: column">
        <button type="submit" class="btn btn-secondary btnIndex" id="b1" onclick="location.href='/orderToComplete'">Доступные заявки</button>
        <button type="submit" class="btn btn-danger btnIndex" id="b2" onclick="location.href='/orderNotComplete'">Невыполненные заявки</button>
        <button type="submit" class="btn btn-success btnIndex" id="b3" onclick="location.href='/orderComplete'">Выполненные заявки</button>
        <button type="submit" class="btn btn-info btnIndex" id="b4" onclick="location.href='/clientList'">Пользователи</button>
        <button type="submit" class="btn btn-info btnIndex" id="b5" onclick="location.href='/employeeGraphic'">График</button>
        <button type="submit" class="btn btn-outline-primary btnIndex" id="b6" onclick="location.href='/'">Назад</button>
    </div>
</div>
</body>
</html>