<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>

<html>
<head>
    <title>Место пользователя</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
    <script>
        function clickOrder(f) {
            let n = f.name.selectedIndex;
            if(n)
                alert(f.name.options[n].value + " успешно заказана.");
        }

        document.addEventListener("DOMContentLoaded",tableDown);
        document.addEventListener("DOMContentLoaded", buttonHeight);

        function tableDown() {
            let rowsLength = document.getElementById('table').rows.length;
            console.log(rowsLength);
            for(let i = 1; i < rowsLength; i++) {
                let table = document.getElementById('table');
                let res = table.rows [i].cells [2].innerHTML;
                res = res.replace(/\, /gi, ',<br>');
                res = res.replace(/$/, ';')
                table.rows [i].cells [2].innerHTML = res;
            }
        }
        function buttonHeight() {
            let height = document.getElementById('delete').clientHeight;
            document.getElementById('deltxt').clientHeight = height;
        }
    </script>
</head>
<body>

<h1 style="text-align: center">Заказ экскурсии</h1>
<div class="container h-100" >
    <div class="row h-100  justify-content-center" style="flex-direction: row">

        <div style="margin-top: 50px; text-align: right" >
            <button type="button" class=" btn btn-info" style="width: 300px; margin: 5px" onclick="location.href='/clientMakeExcursion'">Создать свою экскурсию</button><br>
            <button type="submit" class=" btn btn-info" style="width: 300px; margin: 5px" onclick="location.href='/clientOrderList'">Принятые заказы</button><br>
            <button type="submit" class=" btn btn-info" style="width: 300px; margin: 5px" onclick="location.href='/clientNotOrderList'">Не принятые заказы</button>
                <form action="${pageContext.request.contextPath}/clientWork" method="POST">
                    <select name="name" style="width: 190px;margin-bottom: 5px;margin-top: 5px" class="browser-default custom-select">
                        <option disabled="true" value="not excursion" >Выберите экскурсию</option>
                        <c:forEach items="${allExcursionsName}" var="excursionName">
                            <option value="${excursionName}">${excursionName}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-success" style="margin: 5px; width: 100px" onclick="clickOrder(this.form)">Заказать</button>
                </form>
                <form  style="vertical-align: top" action="${pageContext.request.contextPath}/clientWork" method="POST">

                    <input type="hidden" name="action" value="delete"/>
                    <input type="text" id="deltxt" style="width: 190px; height: 38px;" placeholder="Порядковый номер" name = "serial_id" class="form-group">
                    <button type="submit" class="btn btn-danger" style="margin: 5px;width: 100px;vertical-align: unset" id="delete">Удалить</button>
                        <form:errors path="serial_id"></form:errors>
                    <span style="color: red;"><br>${serialError}</span>
                </form>
            <button class="btn btn-outline-primary" style="width: 300px; margin-right: 5px" onclick="location.href='/'">Назад</button>
        </div>

        <div class="col">
            <table class="table" id="table">
                <caption style="text-align: center; caption-side: top; color: black"><h2>Список экскурсий</h2></caption>
                <thead class="thead-dark">
        <th style="text-align: center">Порядковый <br>номер</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Вид</th>
        <th style="text-align: center" >Итого<br>время</th>
        </thead>
        <c:forEach items="${allExcursions}" var="excursion">
            <tr>
                <td style="text-align: center" width="20%">${excursion.id}</td>
                <td width="15%">${excursion.name}</td>
                <td id="descriptionExc" width="30%">${excursion.description}</td>
                <td width="10%">${excursion.price}</td>
                <td width="12%">${excursion.viewExcursion.typeName}</td>
                <td width="12%">${excursion.time}</td>
            </tr>
        </c:forEach>
    </table>
        </div>

    </div>
 </div>
</body>
</html>