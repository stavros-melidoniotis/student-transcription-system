<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- Check if applications list is empty -->
<div class="container">
    <div class="container-content">
        <c:choose>
            <c:when test="${applications.isEmpty()}">
                <p>Δεν υπάρχουν αιτήσεις προς εξέταση.</p>
            </c:when>
            <c:otherwise>
                Αίτηση φοιτητή με ΑΜΚΑ:
                <form action="/IntranetSystem/transAdmin/application/check" method="POST">
                    <select name="amka" required>
                        <!-- for each Application object the DB returns -->
                        <c:forEach var="application" items="${applications}">
                            <option>${application.amka}</option>
                        </c:forEach>
                    </select>
                    
                    <div class="container-button">

			            <button type="submit" class="mainButton">Επιλογή Αίτησης</button>
			            <button onclick="location.href='/IntranetSystem/home_page'"
			                    type="button" class="secButton">Επιστροφή</button>
			            <button onclick="location.href='/IntranetSystem/logout'" type="button"
                    			class="logoutButton">Αποσύνδεση του <sec:authentication property="principal.username" />
            			</button>
        			</div>
                    
                </form>
            </c:otherwise>
        </c:choose>
        
        
    </div>
</div>
