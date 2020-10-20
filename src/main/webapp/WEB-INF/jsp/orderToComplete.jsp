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
        <th>ID_заявки</th>
        <th>Имя_пользователя_заказавшего</th>
        <th>Название_экскурсии</th>
        </thead>
        <c:forEach items="${ordersToComplete}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.userGet.username}</td>
                <td>${order.excursion.name}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/orderToComplete" method="post">
                        <input type="hidden" name="orderId" value="${order.id}"/>
                        <input type="hidden" name="action" value="get"/>
                        <button type="submit">Принять</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/employeeWork">Назад</a>
</div>
</body>
</html>
