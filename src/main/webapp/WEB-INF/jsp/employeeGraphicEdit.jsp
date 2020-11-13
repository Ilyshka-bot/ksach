
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Место экскурсовода</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
    <style>
        input[type=time] {
            color: #2a2c2d;
            font-size: 14px;
            font-family: helvetica;
            width: 160px;
        }

    </style>
</head>
<body>
<h2 style="text-align: center">Изменение графика</h2>

<div class="container">
    <div class="row align-items-center justify-content-center">
    <form action="${pageContext.request.contextPath}/employeeGraphicEdit" method="post" class="column-header">
        <div>
            Дата начала:
            <input type="date" name="dateStart" style="margin: 5px"/>
            <div class="smallDiv" style="color: red"> <form:errors path="fullname"></form:errors>
                ${dateStartError}</div>
        </div>
        <div >
            Время начала:
            <input type="time" width="100px" name="timeStart" style="margin: 5px"/>
            <div class="smallDiv" style="color: red"> <form:errors path="fullname"></form:errors>
                ${timeStartError}</div>
            <button type="submit" class="btn btn-success" style="margin: 5px">Установить</button>
            <button type="button" class="btn btn-outline-primary" id="b1" onclick="location.href='/employeeGraphic'">Назад</button>
        </div>


    </form>
    </div>
</div>
</body>
</html>