<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Список заявок не выполненных</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">
    <script>
        var lengthRow = 0;
        document.addEventListener("DOMContentLoaded", function () {
            lengthRow = document.getElementById('table').rows.length;
            if(lengthRow == 1){
                window.location.href = '/clientWork';
                alert("Данных нет!");
            }
        });

        function showCancelOrder() {
            console.log(document.getElementById('error').textContent);
            let rowsLength = document.getElementById('table').rows.length;
            let serial = Number(document.querySelector('#serial_id').value);

            for(let i = 1; i < rowsLength; i++) {
                let table = document.getElementById('table');
                let res = table.rows [i].cells [0].innerHTML;

                console.log(res);

                if(res == serial){
                    alert("Заявка под номером " + res + " успешно отменена!");
                    break;
                }
            }


            if(document.getElementById('error').textContent != 'Некорректный номер' ){
            }
        }

    </script>
</head>
<body>
    <div class="container h-100" style="text-align: center">
        <div class="row h-100 align-items-center col" style="flex-direction: column">
    <h2>Список не принятых заказов</h2>
    <form action="${pageContext.request.contextPath}/clientNotOrderList" method="POST">
        <input type="hidden" name="action" value="cancel"/>
        <div style="margin: 10px">
        <input type="text" id="serial_id" placeholder="Порядковый номер" name = "serial_id" class="text" style="height: 38px">
            <button type="submit" class="btn btn-danger" style="vertical-align: unset" onclick="showCancelOrder()">Отменить заявку</button>
            <form:errors path="serial_id"></form:errors>
             <span style="color: red; " id="error"> ${serialError}</span>
        </div>
    </form>
    <div>
        <table class="table" id="table">
            <thead class="thead-light" >
            <th>Порядковый номер</th>
            <th>Дата заявки</th>
            <th>Название экскурсии</th>
            </thead>
            <c:forEach items="${myOrders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.dateOrder}</td>
                    <td>${order.excursion.name}</td>
                </tr>
            </c:forEach>
        </table>
        <button class="btn btn-outline-primary" onclick="location.href='/clientWork'">Назад</button>
    </div>
        </div>
    </div>
</body>
</html>