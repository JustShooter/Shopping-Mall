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
    <title>Add/edit shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/header.jsp"/>

<%--Edit shop--%>

<h1 class="bg-light text-center">
    <c:choose>
        <c:when test="${shop != null}">
            Редактирование ${shop.getShopName()}
        </c:when>
        <c:otherwise>
            Создание нового магазина
        </c:otherwise>
    </c:choose>
</h1>

<div class="container px-5 my-5">
    <form class="row g-3 needs-validation" accept-charset="UTF-8"
          action="${pageContext.request.contextPath}/admin/Shops" method="post">
        <c:choose>
            <c:when test="${shop != null}">
                <input type="hidden" name="action" value="edit" hidden/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="action" value="new" hidden/>
            </c:otherwise>
        </c:choose>
        <div class="form-floating mb-3" <c:if test="${shop == null}">hidden</c:if>>
            <input class="form-control" name="shopId" id="shopId" type="text"
                   placeholder="Идентификационный номер"
                   value="${shop.getId()}" readonly tabindex="-1"/>
            <label for="shopId">Идентификационный номер</label>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="shopName" id="shopName" type="text"
                   placeholder="Название магазина"
                   required minlength="3" maxlength="250"
                   pattern="^[А-Яа-яA-Za-z0-9]+(?:\s+[А-Яа-яA-Za-z0-9]+)*$"
                   <c:if test="${shop != null}">value="${shop.getShopName()}"</c:if>/>
            <label for="shopName">Название магазина</label>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select" name="shopLocation" id="ShopLocation"
                    aria-label="Расположение магазина" required>
                <c:choose>
                    <c:when test="${shop != null}">
                        <option selected value="${shop.getLocation().getId()}">
                            Торговое место № ${shop.getLocation().getShopNumber()},
                                ${shop.getLocation().getFloor()} этаж.
                        </option>
                    </c:when>
                    <c:otherwise>
                        <option selected disabled value="">
                            Выберите торговое место.
                        </option>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${freeLocations}" var="freeLocation">
                    <option value="${freeLocation.getId()}">
                        Торговое место № ${freeLocation.getShopNumber()},
                            ${freeLocation.getFloor()} этаж.
                    </option>
                </c:forEach>
            </select>
            <label for="ShopLocation">Расположение магазина</label>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select" name="shopOwner" id="shopOwner"
                    aria-label="Владелец магазина" required>
                <c:if test="${shop == null}">
                    <option disabled value="" selected>
                        Выберите владельца.
                    </option>
                </c:if>
                <c:forEach var="shopOwner" items="${shopOwners}">
                    <option value="${shopOwner.getId()}"
                            <c:if test="${shop != null}">
                            <c:if test="${shopOwner.getId() == shop.getShopOwner().getId()}">
                                selected
                            </c:if>
                            </c:if>>
                            ${shopOwner.getOwnerName()}</option>
                </c:forEach>
            </select>
            <label for="shopOwner">Владелец магазина</label>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select" name="shopCategory" id="shopCategory"
                    aria-label="Категория магазина" required>
                <c:if test="${shop == null}">
                    <option disabled value="" selected>
                        Выберите категорию.
                    </option>
                </c:if>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.getId()}"
                            <c:if test="${shop != null}">
                            <c:if test="${category.getId() == shop.getCategory().getId()}">
                                selected
                            </c:if>
                            </c:if>>
                            ${category.getName()}
                    </option>

                </c:forEach>
            </select>
            <label for="shopCategory">Категория магазина</label>
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
