<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <style>
        body {
            background: url('https://mdbootstrap.com/img/Photos/Others/images/95.jpg') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            background-size: cover;
            -o-background-size: cover;
            position: relative;
        }
    </style>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>All items of shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>

    <script type="text/javascript" class="init">
        $(document).ready(function () {

                $('#example').DataTable(
                    {
                        "ajax": {
                            "url": "/rest/shopItems/${shopId}",
                            "dataSrc": ""
                        },
                        dom: 'Bfrtip',
                        "columns": [
                            {"searchable": false, data: 'id'},
                            {"searchable": true, data: 'name'},
                            {"searchable": false, data: 'article'},
                            {"searchable": false, data: 'barcode'},
                            {"searchable": false, data: 'priceValue'},
                            {"searchable": false, data: 'discountPercentage',  render: function ( data, type, row ) {
                                    return data + '%';
                                }},
                            {
                                "searchable": false, data: null,
                                render: function (data, type, row) {

                                    return data.startDate + ' - ' + data.endDate;
                                }
                            }
                        ],
                        language: {
                            url: 'https://cdn.datatables.net/plug-ins/1.12.1/i18n/ru.json'
                        }
                    });
            }
        );
    </script>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-shop-window">
    <symbol id="shopping-mall" viewBox="0 0 16 16">
        <path fill-rule="evenodd" clip-rule="evenodd"
              d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h12V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zm2 .5a.5.5 0 0 1 .5.5V13h8V9.5a.5.5 0 0 1 1 0V13a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V9.5a.5.5 0 0 1 .5-.5z"></path>
    </symbol>
</svg>
<jsp:include page="${pageContext.request.contextPath}/header.jsp"/>

<h1 class="bg-light text-center">${shopName}</h1>

<%--@elvariable id="count" type="java.lang.Integer"--%>
<div class="bg-light">
    <table id="example" class="table table-bordered table-striped table-light table-hover" style="width:100%">
        <caption>All items of shop ${shopName}</caption>
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Наименование</th>
            <th scope="col">Артикул</th>
            <th scope="col">Штрих-код</th>
            <th scope="col">Цена</th>
            <th scope="col">Скидка</th>
            <th scope="col">Срок действия скидки</th>
        </tr>
        </thead>
        <%-- <tbody>
         <c:forEach items="${items}" var="category">
             <tr>
                 <td><c:out value="${count=1+count}"/></td>
                 <td><c:out value="${category.getName()}"/></td>
                 <td><c:out value="${category.getArticle()}"/></td>
                 <td><c:out value="${category.getBarcode()}"/></td>
                 <td><c:out value="${category.getPriceValue()}"/></td>
                 <td><c:out value="${category.getDiscountPercentage()}" default="0"/>%</td>
                 <td><c:out value="${category.getStartDate()}"/> - <c:out
                         value="${category.getEndDate()}"/></td>
             </tr>
         </c:forEach>
         </tbody>--%>
        <tfoot>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Наименование</th>
            <th scope="col">Артикул</th>
            <th scope="col">Штрих-код</th>
            <th scope="col">Цена</th>
            <th scope="col">Скидка</th>
            <th scope="col">Срок действия скидки</th>
        </tr>
        </tfoot>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</html>
