<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form action="/IntranetSystem/admin/user/deleted_user" method="POST">
    <div class="container">
        <div class="container-content">
            <h1>Διαγραφή Χρήστη</h1>

            <label for="">Χρήστης:</label>
            <select name="username">
                <!-- for each User object the DB returns -->
                <c:forEach var="user" items="${users}">
                    <option value="${user.username}">${user.username}</option>
                </c:forEach>
            </select>

            <div class="container-button">

                <button type="submit" class="mainButton">Διαγραφή Χρήστη</button>
                <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή</button>
                <button onclick="location.href='/IntranetSystem/logout'" type="button"
                        class="logoutButton"> Αποσύνδεση του
                    <sec:authentication property="principal.username" />
                </button>

            </div>
        </div>
    </div>
</form>