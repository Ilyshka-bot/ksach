<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация сотрудника</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
</head>

<body>
<h2 style="text-align: center">Регистрация сотрудника</h2>

<div class="container h-100 text-center" >
    <div class="row h-100  justify-content-center align-items-center" style="flex-direction: column">
    <%--@elvariable id="userForm" type=""--%>
    <form:form method="POST" modelAttribute="userForm">
        <div><form:input type="text" path="username" placeholder="Имя пользователя" cssClass="input"
                         autofocus="true"></form:input></div>
        <div class="smallDiv" style="color: red"> <form:errors path="username"></form:errors>
                ${usernameError}</div>

        <div>  <form:input type="password" path="password" placeholder="Пароль" cssClass="input"></form:input></div>
        <div class="smallDiv" style="color: red"> <form:errors path="password"></form:errors>
                ${passwordEmpty}</div>
        <div>  <form:input type="password" path="passwordConfirm" cssClass="input"
                           placeholder="Подтверждение пароля" ></form:input></div>
        <div class="smallDiv" style="color: red">  <form:errors path="passwordConfirm"></form:errors>
                ${passwordError}</div>
        <div>    <form:input type="text" path="mail" placeholder="Почта" cssClass="input"></form:input></div>
        <div class="smallDiv" style="color: red">  <form:errors path="mail"></form:errors>
                ${mailError}</div>
        <div>  <form:input type="text" path="fullname" placeholder="ФИО" cssClass="input"></form:input></div>
        <div class="smallDiv" style="color: red"> <form:errors path="fullname"></form:errors>
                ${fullnameError}</div>

        <%--@elvariable id="employeeForm" type=""--%>
        <form:form method="POST" modelAttribute="employeeForm">
            <div>  <form:input type="text" path="experience" placeholder="Опыт работы" cssClass="input"></form:input></div>
            <div class="smallDiv" style="color: red"> <form:errors path="experience"></form:errors>
                    ${experienceError}</div>
            <div>
                <p>Дата начала:<br> <form:input type="date" name="calendar" path="date_start" id = "current"></form:input>
                <script>
                    function currentDate(){
                        var d = new Date(),
                        new_value = d.toISOString().slice(0,10);
                        document.getElementById("current").value=new_value;
                    }
                    currentDate();
                </script>
            </div>
            <div>
                <p>Дата окончания:<br> <form:input type="date" name="calendar" path="date_end" value="0000-00-00"></form:input>
                ${dateendError}
            </div>
            <%--@elvariable id="postForm" type=""--%>
            <form:form method="POST"  modelAttribute = "postForm">
                <div>
                    <form:select path="name">
                        <form:option disabled="true" value="not worker">Выберите сотрудника</form:option>
                        <form:option value="Экскурсовод">Экскурсовод</form:option>
                        <form:option value="Работник">Работник</form:option>
                    </form:select>
                </div>
                <input type = "submit" class="btn btn-success btnIndex" value = "Зарегистрировать" /><br>
                <input type = "button" class="btn btn-outline-primary btnIndex" value = "Назад" onclick="location.href='/admin'"/>
            </form:form>
        </form:form>
    </form:form>
    </div>
</div>
</body>
</html>