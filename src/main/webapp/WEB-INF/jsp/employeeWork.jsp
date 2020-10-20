<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Рабочее место сотрудника</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h4><a href="/orderToComplete">Список доступных заявок для выполнения</a></h4>
    <h4><a href="/orderNotComplete">Список невыполненных заявок</a></h4>
    <h4><a href="/orderComplete">Список выполненных заявок</a></h4>
    <h4><a href="/clientList">Список пользователей</a></h4>
    <h4><a href="/employeeGraphic">График</a></h4>
    <h4><a href="/">Назад</a></h4>

</div>
</body>
</html>