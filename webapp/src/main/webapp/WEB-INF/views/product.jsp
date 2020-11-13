<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <title>CatalogItem</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="col-12">
    <table class="table table-bordered my-2">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Amount</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">${requestScope.storeItem.id}</th>
                <th scope="row">${requestScope.storeItem.name}</th>
                <th scope="row">${requestScope.storeItem.amount}</th>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>
