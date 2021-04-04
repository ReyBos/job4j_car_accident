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
    <div class="col s6 offset-s3">
        <h4 class="header center-align">Регистрация</h4>
        <div class="card horizontal">
            <div class="card-stacked row">
                <form class="col s12" action="<c:url value="/reg" />" method="post">
                    <div class="card-content">
                        <div class="row">
                            <div class="input-field col s12">
                                <input placeholder="" id="username" type="text" name="username" class="validate" required>
                                <label class="active" for="username">Имя пользователя</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input placeholder="" id="password" type="password" name="password" class="validate" required>
                                <label class="active" for="password">Пароль</label>
                            </div>
                        </div>
                    </div>
                    <div class="card-action right-align">
                        <button type="submit" class="waves-effect waves-light btn">
                            зарегистрироваться<i class="material-icons right">send</i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>