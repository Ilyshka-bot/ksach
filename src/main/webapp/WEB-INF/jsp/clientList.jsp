<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Список пользователей</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">

</head>

<body>
<div style="text-align: center;margin: 200px;" id="content">
    <table class="table table-striped " id="table">
        <thead class="thead-dark" >
        <th>Порядковый_номер</th>
        <th>Имя_пользователя</th>
        <th>Почта</th>
        <th>ФИО</th>
        <th>Паспортные_данные</th>
        </thead>
        <c:forEach items="${allClients}" var ="client">
            <tr>
                <td>${client.id}</td>
                <td>${client.user.username}</td>
                <td>${client.user.mail}</td>
                <td>${client.user.fullname}</td>
                <td>${client.passportData}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit" class="btn btn-outline-primary" id="b1" onclick="location.href='/employeeWork'">Назад</button>

</div>
<div id="editor"></div>

<button id="cmd" class="btn btn-outline-primary">Generate PDF</button>
</body>

</html>
