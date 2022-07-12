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
            <title>All shops with items</title>
            <path fill-rule="evenodd" clip-rule="evenodd"
                  d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h12V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zm2 .5a.5.5 0 0 1 .5.5V13h8V9.5a.5.5 0 0 1 1 0V13a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V9.5a.5.5 0 0 1 .5-.5z"></path>
        </symbol>
    </svg>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Shops in category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="row gy-3 p-1">
        <div class="col-md-3">
            <div class="d-flex flex-column flex-shrink-0 p-3 bg-light" style="width: 280px;">
                <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32">
                        <use xlink:href="#shopping-mall"></use>
                    </svg>
                    <span class="fs-4">Категории</span>
                </a>
                <hr>

                <ul class="nav nav-pills flex-column mb-auto">
                    <c:forEach var="categoies" items="${allCategories}">
                        <li class="nav-item">
                            <a href="viewAllShops?category=${categoies.getId()}" class="nav-link link-dark"
                               data-bs-toggle="popover"
                               data-bs-trigger="hover focus"
                               tabindex="0"
                               data-bs-content="${categoies.getDescription()}">
                                    ${categoies.getName()}
                            </a>
                        </li>

                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="col-md-9">
            <div class="row gy-1">
                <c:forEach var="shop" items="${shopsInCategory}">
                    <div class="col-md-4">
                        <div class="card text-center">
                            <div class="card rounded-3 shadow-sm">
                                <h4 class="card-header">${shop.getShopName()}</h4>
                                <h5 class="card-title">
                                        ${shop.getLocation().getFloor()} эт.
                                    маг. № ${shop.getLocation().getShopNumber()}
                                        ${shop.getLocation().getDescription()}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${shop.getShopOwner().getOwnerName()}</h6>
                                <p class="card-text text-muted"><small>
                                    г.${shop.getShopOwner().getAddress().getCity()},
                                        ${shop.getShopOwner().getAddress().getStreetType()}
                                        ${shop.getShopOwner().getAddress().getStreet()}
                                    д. ${shop.getShopOwner().getAddress().getBuildingNumber()}
                                    оф.${shop.getShopOwner().getAddress().getOfficeNumber()}</small></p>
                                <p class="card-footer">
                                    <a href="viewShopItems?shopId=${shop.getId()}" class="card-link">Товары</a>
                                    <a href="#" class="card-link">Редактировать</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    const list = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
    list.map((el) => {
        let opts = {
            animation: false,
        }
        if (el.hasAttribute('data-bs-content-id')) {
            opts.content = document.getElementById(el.getAttribute('data-bs-content-id')).innerHTML;
            opts.html = true;
        }
        new bootstrap.Popover(el, opts);
    })
</script>
</body>
<jsp:include page="footer.jsp"/>
</html>
