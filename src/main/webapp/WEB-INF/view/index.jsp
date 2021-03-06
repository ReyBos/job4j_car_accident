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
                        <th>Тип</th>
                        <th>Статья</th>
                        <th>Описание</th>
                        <th>Адрес</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accidents}" var="accident">
                        <tr>
                            <td>${accident.id}</td>
                            <td>${accident.name}</td>
                            <td>${accident.type.name}</td>
                            <td>
                                <c:forEach items="${accident.rules}" var="rule">
                                    ${rule.name}
                                </c:forEach>
                            </td>
                            <td>${accident.text}</td>
                            <td>${accident.address}</td>
                            <td class="right-align">
                                <a href='<c:url value="/update?id=${accident.id}"/>'>
                                    <i class="material-icons">edit</i>
                                </a>
                                <a href='<c:url value="/delete?id=${accident.id}"/>'>
                                    <i class="material-icons">delete</i>
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
