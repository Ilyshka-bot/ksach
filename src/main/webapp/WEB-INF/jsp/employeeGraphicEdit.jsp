
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Место экскурсовода</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h2>Изменение графика</h2>
    <form action="${pageContext.request.contextPath}/employeeGraphicEdit" method="post">
        Дата_начала: <input type="date" name="dateStart"/>${dateStartError}<br>
        Время_начала: <input type="time" name="timeStart"/>${timeStartError}<br>

        <button type="submit">Установить</button>
    </form>
    <h4><a href="/employeeGraphic">Назад</a></h4>
</div>
</body>
</html>