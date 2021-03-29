<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Accident</title>
</head>
<body class="container">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Описание</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rows}" var="row" varStatus="count">
            <tr>
                <td>${count.index + 1}</td>
                <td>${row}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
