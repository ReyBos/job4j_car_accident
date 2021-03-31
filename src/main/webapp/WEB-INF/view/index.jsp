<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <title>Accident</title>
</head>
<body>
    <c:import url="_menu.jsp"/>
    <div class="container row">
        <div class="row valign-wrapper">
            <h5 class="col s12 center-align">Инциденты</h5>
        </div>
        <div class="card">
            <div class="card-content">
                <table class="highlight">
                    <thead>
                    <tr>
                        <th>&nbsp;#&nbsp;</th>
                        <th>Нарушение</th>
                        <th>Описание</th>
                        <th>Адрес</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accidents}" var="entry">
                        <tr>
                            <td>${entry.key}</td>
                            <td>${entry.value.name}</td>
                            <td>${entry.value.text}</td>
                            <td>${entry.value.address}</td>
                            <td class="right-align">
                                <a href='<c:url value="/edit?id=${entry.value.id}"/>'>
                                    <i class="material-icons">edit</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
