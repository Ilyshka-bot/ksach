<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>

<div>
    <table>
        <thead>
        <th>Порядковый_номер</th>
        <th>Выполнено/Не_выполнено</th>
        <th>Имя_клиента_заказавшего</th>
        <th>Название_экскурсии</th>
        </thead>
        <c:forEach items="${ordersComplete}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.completeOrNot}</td>
                <td>${order.userOrder.username}</td>
                <td>${order.excursion.name}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/employeeWork">Назад</a>
</div>
</body>
</html>
