<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Clients</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>

<div>
    <table>
        <thead>
        <th>ID</th>
        <th>Имя_пользователя</th>
        <th>Пароль</th>
        <th>Почта</th>
        <th>ФИО</th>
        <th>Паспортные_данные</th>
        </thead>
        <c:forEach items="${allClients}" var ="client">
            <tr>
                <td>${client.id}</td>
                <td>${client.user.username}</td>
                <td>${client.user.password}</td>
                <td>${client.user.mail}</td>
                <td>${client.user.fullname}</td>
                <td>${client.passportData}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/employeeWork">Назад</a>
</div>
</body>
</html>
