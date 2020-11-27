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
<h2 style="text-align: center">График работы</h2>

<div class="container h-100" >
    <div class="row h-100 justify-content-center" >
        <div style="margin-top: 20px">
            <input type="submit" class="btn btn-success btnIndex" value = "Добавить сотрудника" onclick="location.href='/regEmp'"/><br>
            <input type="submit" class="btn btn-outline-primary btnIndex" value = "Главная" onclick="location.href='/'"/><br>
            <input type = "submit" class = "btn btn-primary btnIndex" value = "Отчет в pdf" onclick="location.href='${pageContext.request.contextPath }/report'"/>
        </div>
        <div style="text-align: center;margin: 20px;">
            <table class="table table-striped " id="table">
                <thead class="thead-dark" >
                <th>ID</th>
                <th>Имя_пользователя</th>
                <th>Почта</th>
                <th>ФИО</th>
                <th>Роль</th>
                <th></th>
                </thead>
                <c:forEach items="${allUsers}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.mail}</td>
                        <td>${user.fullname}</td>
                        <td>${user.role.name}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/admin" method="post">
                                <input type="hidden" name="userId" value="${user.id}"/>
                                <input type="hidden" name="action" value="delete"/>
                                <input type="submit" class="btn btn-danger" value = "Удалить"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
