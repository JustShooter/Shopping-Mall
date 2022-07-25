<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<nav class="navbar-light bg-light">
    <div class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="${pageContext.request.contextPath}/"
               class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32">
                    <use xlink:href="#shopping-mall"></use>
                </svg>
                <span class="fs-4">Shopping-mall</span>
            </a>

            <ul class="nav nav-pills">
                <li class="nav-item"><a href="${pageContext.request.contextPath}/" class="nav-link active"
                                        aria-current="page">На главную</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Меню администратора
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/ShopOwners">Владельцы магазинов</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/Locations">Торговые места</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/Categories">Категории</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/Shops">Все магазины</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/Items">Все товары</a></li>
                    </ul>
                </li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/viewShopsInCategory" class="nav-link">Магазины</a></li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="modal" href="#shops">Меню владельца магазина</a>
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

                    <select name="shopId" class="form-select" aria-label="Default select example" required>
                        <option selected disabled>Выберите магазин</option>
                        <c:forEach items="${applicationScope['shopList']}" var="shops">
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

<!-- Modal ERROR -->
<c:if test="${error != null}">
    <div class="modal" id="modalError" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticErrorLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticErrorLabel">Ошибка!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <p>${error}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                </div>
            </div>
        </div>
    </div>
        <script type="text/javascript">
            var myModal = new bootstrap.Modal(
                document.getElementById('modalError')
            )
            myModal.show()
    </script>
</c:if>
<!-- Modal ERROR -->