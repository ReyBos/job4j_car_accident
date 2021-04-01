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
<c:import url="../_menu.jsp"/>
<div class="container row">
    <div class="row valign-wrapper">
        <h5 class="col s12 center-align">Добавить инцидент</h5>
    </div>
    <div class="col s10 offset-s1">
        <div class="card horizontal">
            <div class="card-stacked row">
                <form class="col" action="<c:url value='/save'/>" method="post">
                    <div class="card-content">
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="name" type="text" name="name" class="validate" required>
                                <label class="" for="name">Нарушение</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="text" type="text" name="text" class="validate" required>
                                <label class="" for="text">Описание</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="address" type="text" name="address" class="validate" required>
                                <label class="" for="address">Адрес</label>
                            </div>
                            <div class="input-field col s6">
                                <select name="type.id" id="type_id">
                                    <c:forEach var="type" items="${types}" >
                                        <option value="${type.id}">${type.name}</option>
                                    </c:forEach>
                                </select>
                                <label class="" for="type_id">Тип</label>
                            </div>
                            <div class="input-field col s6">
                                <select name="rIds" multiple id="rIds">
                                    <c:forEach var="rule" items="${rules}" >
                                        <option value="${rule.id}">${rule.name}</option>
                                    </c:forEach>
                                </select>
                                <label class="" for="rIds">Статьи</label>
                            </div>
                        </div>
                    </div>
                    <div class="card-action center-align">
                        <button type="submit" class="waves-effect waves-light btn">
                            сохранить
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    $(document).ready(function() {
        $('select').formSelect();
    });
</script>
</body>
</html>