<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand">${requestScope.pageName}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <a class="nav-link nav-item" href="${pageContext.request.contextPath}/main">Main</a>
            <a class="nav-link nav-item" href="${pageContext.request.contextPath}/catalog">Catalog</a>
            <a class="nav-link nav-item" href="${pageContext.request.contextPath}/cart">Cart</a>
            <a class="nav-link nav-item" href="${pageContext.request.contextPath}/order">Order</a>
        </ul>
    </div>
</nav>
