<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form action="/IntranetSystem/secSuper/delete/delete_department" method="POST">
    <div class="container">
        <div class="container-content">
        
            <h1>Διαγραφή Αντιστοιχίας Τμήματος</h1>

            <c:choose>
                <c:when test="${departments.isEmpty()}">
                    <p>Δεν υπάρχουν αντίστοιχα τμήματα για διαγραφή.</p>
                </c:when>

                <c:otherwise>

                    <label for="deptodelete"><b>Επιλογή Τμήματος:</b></label>
                    <select id="deptodelete" name="depCode" required>

                        <!-- for each Department object the DB returns -->
                        <c:forEach var="department" items="${departments}">
                            <option value="${department.depCode}">${department.depName}</option>
                        </c:forEach>
                    </select>
                </c:otherwise>
            </c:choose>
            
            <div class="container-button">

                <button type="submit" class="mainButton">Διαγραφή Τμήματος</button>
                <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή
                </button>
                <button onclick="location.href='/IntranetSystem/logout'" type="button"
                        class="logoutButton"> Αποσύνδεση του
                    <sec:authentication property="principal.username"/>
                </button>

            </div>
        </div>
    </div>
</form>
