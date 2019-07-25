<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="${pageContext.request.contextPath}/authUser" method="POST">
    <div class="container">
        <div class="container-content">
            <h1>Σύνδεση Χρήστη</h1>

            <label for="username"><b>Όνομα Χρήστη</b></label>
            <input type="text" placeholder="Εισάγετε το όνομα χρήστη σας" name="username" id="username" required>

            <label for="password"><b>Κωδικός Πρόσβασης</b></label>
            <input type="password" placeholder="Εισάγετε τον κωδικό σας" name="password" id="password" required>
            
            <c:if test="${param.error != null}">
                <i>Λάθος στοιχεία χρήστη!</i>
            </c:if>
            
            <div class="container-button">
                <button type="submit" class="mainButton">Σύνδεση</button>
            </div>
        </div>
    </div>
</form:form>
	