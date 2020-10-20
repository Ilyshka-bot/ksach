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
    <h1>Заказ экскурсии</h1>
    <h2>Список экскурсий</h2>
    <h4><a href="/clientOrderList">Мои принятые заказы</a></h4>
    <h4><a href="/clientNotOrderList">Мои не принятые заказы</a></h4>
    <div>
        <table>
            <thead>
            <th>ID</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Вид</th>
            <th>Итого_время</th>
            </thead>
            <c:forEach items="${allExcursions}" var="excursion">
                <tr>
                    <td>${excursion.id}</td>
                    <td>${excursion.name}</td>
                    <td>${excursion.description}</td>
                    <td>${excursion.price}</td>
                    <td>${excursion.viewExcursion.name}</td>
                    <td>${excursion.time}</td>
                </tr>
            </c:forEach>
        </table>
    <%--@elvariable id="viewExcursionForm" type=""--%>
    <form:form method="POST"  modelAttribute = "viewExcursionForm">
    <div>
        <form:select path="name">
            <form:option disabled="true" value="not excursion">Выберите экскурсию</form:option>
            <form:option value="simple">Обычная</form:option>
            <form:option value="study">Учебная</form:option>
            <form:option value="many">Массовая</form:option>
        </form:select>
        <button type="submit">Заказать</button>
    </div>
    </form:form>
    </div>
    <h4><a href="/">Назад</a></h4>
</div>
</body>
</html>