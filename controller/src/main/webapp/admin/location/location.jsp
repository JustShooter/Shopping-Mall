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
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-shop-window">
        <symbol id="shopping-mall" viewBox="0 0 16 16">
            <path fill-rule="evenodd" clip-rule="evenodd"
                  d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h12V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zm2 .5a.5.5 0 0 1 .5.5V13h8V9.5a.5.5 0 0 1 1 0V13a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V9.5a.5.5 0 0 1 .5-.5z"></path>
        </symbol>
    </svg>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Add/edit location</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/header.jsp"/>

<%--Edit shop--%>

<h1 class="bg-light text-center">
    <c:choose>
        <c:when test="${location != null}">
            Редактирование торгового места № ${location.getShopNumber()}
            на ${location.getFloor()} этаже
        </c:when>
        <c:otherwise>
            Создание нового торгового места
        </c:otherwise>
    </c:choose>
</h1>

<div class="container px-5 my-5">
    <form class="row g-3 needs-validation" accept-charset="UTF-8"
          action="${pageContext.request.contextPath}/admin/Locations" method="post">
        <c:choose>
            <c:when test="${location != null}">
                <input type="hidden" name="action" value="edit" hidden/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="action" value="new" hidden/>
            </c:otherwise>
        </c:choose>
        <div class="form-floating mb-3" <c:if test="${location == null}">hidden</c:if>>
            <input class="form-control" name="locationId" id="locationId" type="text"
                   placeholder="Идентификационный номер"
                   value="${location.getId()}" readonly tabindex="-1"/>
            <label for="locationId">Идентификационный номер</label>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="shopNumber" id="shopNumber" type="text"
                   placeholder="Номер торгового места"
                   required minlength="1" maxlength="10"
                   pattern="^[0-9]+(?:\s+[А-Яа-яA-Za-z0-9]+)*$"
                   <c:if test="${location != null}">value="${location.getShopNumber()}"</c:if>/>
            <label for="shopNumber">Номер торгового места</label>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="floor" id="floor" type="number"
                   step="1" placeholder="Этаж"
                   required min="1" max="10"
                   <c:if test="${location != null}">value="${location.getFloor()}"</c:if>/>
            <label for="floor">Этаж</label>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="description" id="description" type="text"
                   placeholder="Описание"
                   required minlength="3" maxlength="250"
                   pattern="^[А-Яа-яA-Za-z0-9]+(?:\s+[А-Яа-яA-Za-z0-9]+)*$"
                   <c:if test="${location != null}">value="${location.getDescription()}"</c:if>/>
            <label for="description">Описание</label>
        </div>
        <div class="d-grid">
            <button class="btn btn-primary btn-lg" type="submit">Сохранить</button>
            <a class="btn btn-danger btn-lg" type="button"
               href="${pageContext.request.contextPath}/admin/Shops">Назад</a>
        </div>
    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</html>
