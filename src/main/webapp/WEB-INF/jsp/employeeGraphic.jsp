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
    <style>
        .table td{
            text-align: center
        }
    </style>
</head>
<body>
<div>
    <h2>График работы</h2>
    <div class="table">
        <table>
            <thead>
            <th>Порядковый_номер</th>
            <th>Название_экскурсии</th>
            <th>Статус</th>
            <th>Дата_начала</th>
            <th>Время_начала</th>
            <th>Время_окончания</th>
            </thead>
            <c:forEach items="${employeeGraphic}" var="graphic">
                <tr>
                    <td>${graphic.id}</td>
                    <td>${graphic.order.excursion.name}</td>
                    <td>${graphic.order.completeOrNot}</td>
                    <td>${graphic.dateStart}</td>
                    <td>${graphic.timeStart}</td>
                    <td>${graphic.timeEnd}</td>
                        <form action="${pageContext.request.contextPath}/employeeGraphic" method="post">
                            <input type="hidden" name="graphicId" value="${graphic.id}"/>
                            <input type="hidden" name="action" value="edit"/>
                            <td><button type="submit">Изменить</button></td>
                        </form>
                </tr>
            </c:forEach>
        </table>
    </div>
    <h4><a href="/">Назад</a></h4>
</div>
</body>
</html>