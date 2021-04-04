<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav>
    <div class="nav-wrapper container">
        <a class="brand-logo">Accident</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <security:authorize access="isAuthenticated()">
            <li>
                Пользователь: ${user.username}
            </li>
            <li>
                <a href='<c:url value="/" />'>Главная</a>
            </li>
            <li>
                <a href='<c:url value="/create" />'>Добавить инцидент</a>
            </li>
            <li>
                <a href='<c:url value="/logout" />'>Выйти</a>
            </li>
            </security:authorize>
            <security:authorize access="!isAuthenticated()">
            <li>
                <a href='<c:url value="/login" />'>Войти</a>
            </li>
            </security:authorize>
        </ul>
    </div>
</nav>