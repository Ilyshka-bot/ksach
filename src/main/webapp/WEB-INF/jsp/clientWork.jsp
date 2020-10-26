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
        function getNewDescrtp(){

            var newString = 'sdfsdf, sdfsdfsd,s dfsdfsd,dssd'.replace(/,(.*?)/, "\n");
            console.log(newString);
        }


        document.addEventListener("DOMContentLoaded",tableDown);//сработает после перенаправления на страницу error

        function tableDown() {
            var table = document.getElementById('table'),
                cells = table.getElementsByTagName('td');

            for (var i=0,len=cells.length; i<len; i++){
                cells[i].onclick = function(){
                    console.log(this.innerHTML);
                    /* if you know it going to be numeric:
                    console.log(parseInt(this.innerHTML),10);
                    */
                }
            }
        }
    </script>
</head>
<body>

<h1 style="text-align: center">Заказ экскурсии</h1>
<div class="container h-100">
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
                <form action="${pageContext.request.contextPath}/clientWork" method="POST">

                    <input type="hidden" name="action" value="delete"/>

                    <input type="text" style="width: 190px; height: 35px; margin-top: 5px" placeholder="Порядковый номер" name = "serial_id" class="form-group">

                    <button type="submit" class="btn btn-danger" style="margin: 5px;width: 100px">Удалить</button>
                    <form:errors path="serial_id"></form:errors>
                    <div style="text-align: left"><span style="color: red; ">${serialError}</span></div>
                </form>
            <button class="btn btn-outline-primary" style="width: 300px; margin-right: 5px" onclick="location.href='/'">Назад</button>
        </div>

        <div class="col">
            <h2 style="text-align: center">Список экскурсий</h2>
            <table class="table" id="table">
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
                <td>${excursion.viewExcursion.typeName}</td>
                <td width="10%">${excursion.time}</td>
            </tr>
        </c:forEach>
    </table>
        </div>

    </div>
 </div>
</body>
</html>