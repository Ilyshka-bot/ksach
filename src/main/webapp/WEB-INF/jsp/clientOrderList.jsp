<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Место пользователя</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h2>Список заказов</h2>
    <div>
        <table>
            <thead>
            <th>Порядковый_номер</th>
            <th>Дата_заявки</th>
            <th>Название_экскурсии</th>
            <th>ФИО_экскурсовода</th>
            <th>Статус</th>
            </thead>
            <c:forEach items="${myOrders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.dateOrder}</td>
                    <td>${order.excursion.name}</td>
                    <td>${order.userGet.fullname}</td>
                    <td>${order.completeOrNot}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <h4><a href="/clientWork">Назад</a></h4>
</div>
</body>
</html>