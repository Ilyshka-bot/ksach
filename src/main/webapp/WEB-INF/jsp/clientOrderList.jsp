<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Принятые заказы</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/verticalAndMargin.css">

    <script>
        document.addEventListener("DOMContentLoaded",showData);
        function showData() {
            let rowsLength = document.getElementById('table').rows.length;
            console.log(rowsLength);
            if(rowsLength == 1){
                window.location.href = '/clientWork';
                alert("Данных нет!");
            }
        }
        document.addEventListener('DOMContentLoaded', () => {

            const getSort = ({ target }) => {
                const order = (target.dataset.order = -(target.dataset.order || -1));
                const index = [...target.parentNode.cells].indexOf(target);
                const collator = new Intl.Collator(['en', 'ru'], { numeric: true });
                const comparator = (index, order) => (a, b) => order * collator.compare(
                    a.children[index].innerHTML,
                    b.children[index].innerHTML
                );

                for(const tBody of target.closest('table').tBodies)
                    tBody.append(...[...tBody.rows].sort(comparator(index, order)));

                for(const cell of target.parentNode.cells)
                    cell.classList.toggle('sorted', cell === target);
            };

            document.querySelectorAll('.table_sort thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));

        });
    </script>

</head>
<body>
<div>
        <div class="container h-100">
            <div class="row h-100  justify-content-center align-items-center col" style="flex-direction: column">
                <h2>Список заказов</h2>
                <table class="table table_sort" id="table">
            <thead class="thead-light" >
            <th>Порядковый номер</th>
            <th>Дата заявки</th>
            <th>Название экскурсии</th>
            <th>ФИО экскурсовода</th>
            <th>Статус</th>
            </thead>
            <c:forEach items="${myOrders}" var="order">
                <tr style="text-align: center">
                    <td width="20%">${order.id}</td>
                    <td>${order.dateOrder}</td>
                    <td>${order.excursion.name}</td>
                    <td>${order.userGet.fullname}</td>
                    <td>${order.completeOrNot}</td>
                </tr>
            </c:forEach>
        </table>
                <button class="btn btn-outline-primary" onclick="location.href='/clientWork'">Назад</button>
            </div>
        </div>
</div>
</body>
</html>