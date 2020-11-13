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
        .table td{
            text-align: center
        }
    </style>
</head>
<body>
<h2 style="text-align: center">График работы</h2>
    <div style="text-align: center;margin: 20px;">
        <table class="table table-striped " id="table">
            <thead class="thead-dark" >
            <th>Порядковый_номер</th>
            <th>Название_экскурсии</th>
            <th>Статус</th>
            <th>Дата_начала</th>
            <th>Время_начала</th>
            <th>Время_окончания</th>
            <th><button type="submit" class="btn btn-outline-primary" id="b1" onclick="location.href='/employeeWork'">Назад</button></th>
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
                            <td><button type="submit" class="btn btn-warning">Изменить</button></td>
                        </form>
                </tr>
            </c:forEach>
        </table>
</div>
</body>
</html>