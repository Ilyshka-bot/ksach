<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Место пользователя</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
</head>
<body>
<div class="container h-100">
    <div class="row h-100  justify-content-center align-items-center" style="flex-direction: column">
        <h3 style="text-align: center; margin-bottom: 30px">Создание экскурсии</h3>
<div>
    <%--@elvariable id="objectsForm" type=""--%>
    <form:form method="post" modelAttribute="objectsForm">
           <div>
               <form:errors path="name"></form:errors>
               <div><span style="color: red; ">${nameExcursionError}</span></div>
               <form:input path="name" placeholder = "Название экскурсии"  style='width: 550px'></form:input>

           </div>
        <div>
<%--            <input type="checkbox" class="btn-check" path="objects" id="objects" autocomplete="off">--%>
<%--            <label class="btn btn-outline-primary" for="objects">Программа с использованием VR</label><br>--%>
            <form:checkbox class="btn-check" path="objects" value="VR" id="objects1"/>
    <form:label path="objects" for="objects1" style='width: 500px'>Программа с использованием VR</form:label><br>
            <form:checkbox path="objects" value="Graphic" id="objects2"/>
    <form:label path="objects" for="objects2" style='width: 500px'>Графическая программа по поиску неисправностей в комнате</form:label><br>
    <form:checkbox path="objects" value="Labirinth" id="objects3"/>
    <form:label path="objects" for="objects3" style='width: 500px'>Программа Лабиринт(при пожаре)</form:label><br>
    <form:checkbox path="objects" value="Storm" id="objects4"/>
    <form:label path="objects" for="objects4" style='width: 500px'>Программа по изучению ситуаций, что нужно делать во вр</form:label><br>
    <form:checkbox path="objects" value="Numbers" id="objects5"/>
    <form:label path="objects" for="objects5" style='width: 500px'>Программа вызов различных гос. номеров(101,102...)</form:label><br>
    <form:errors path="objects"></form:errors>
        ${objectsError}
        </div>
        <%--@elvariable id="viewExcursionForm" type=""--%>
            <form:form method="POST"  modelAttribute = "viewExcursionForm">
            <div style="margin: 5px">
                <form:select path="typeName" class="browser-default custom-select">
                    <form:option disabled="true" value="not excursion">Выберите тип экскурсии</form:option>
                    <form:option value="simple">Обычная</form:option>
                    <form:option value="study">Учебная</form:option>
                    <form:option value="many">Массовая</form:option>
                </form:select>
                <div style="text-align: center"><input type="submit" value="Отправить" class="btn btn-success" style="margin-top: 5px"></div>
            </div>
            </form:form>
    </form:form>
</div>
    <div><h4><a href="/clientWork">Назад</a></h4></div>
    </div>
</div>
</body>
</html>