<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Список выполненных заявок</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
</head>

<body>
<div style="text-align: center;margin: 20px;">
    <table class="table table-striped " id="table">
        <thead class="thead-dark" >
        <th>Порядковый_номер</th>
        <th>Статус</th>
        <th>Имя_пользователя_заказчика</th>
        <th>Название_экскурсии</th>
        <th>Описание</th>
        </thead>
        <c:forEach items="${ordersComplete}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.completeOrNot}</td>
                <td>${order.userOrder.username}</td>
                <td>${order.excursion.name}</td>
                <td>${order.excursion.description}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit" class="btn btn-outline-primary" id="b1" onclick="location.href='/employeeWork'">Назад</button>
</div>
</body>
</html>
