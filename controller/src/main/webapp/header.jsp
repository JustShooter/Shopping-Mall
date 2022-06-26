<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<nav class="navbar-light bg-light">
    <div class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="index.jsp"
               class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32">
                    <use xlink:href="#shopping-mall"></use>
                </svg>
                <span class="fs-4">Shopping-mall</span>
            </a>

            <ul class="nav nav-pills">
                <li class="nav-item"><a href="index.jsp" class="nav-link active" aria-current="page">На главную</a></li>
                <li class="nav-item"><a href="viewAllItems" class="nav-link">Все магазины с товарами</a></li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="modal" href="#shops">Товары в магазине</a>
                </li>
                <li class="nav-item">
                    <a href="#discounts" data-bs-toggle="modal" class="nav-link">Поиск скидки</a></li>
            </ul>
        </header>
    </div>
</nav>
<!-- Modal -->
<div class="modal fade" id="shops" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Выберите магазин:</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="viewShopItems">
                <div class="modal-body">


                    <select name="shopId" class="form-select" aria-label="Default select example">
                        <option selected>Выберите магазин</option>
                        <c:forEach items="${sessionScope.shopList}" var="shops">
                            <option value="${shops.getId()}">${shops.getShopName()}</option>
                        </c:forEach>
                    </select>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-primary">Показать товары</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal -->