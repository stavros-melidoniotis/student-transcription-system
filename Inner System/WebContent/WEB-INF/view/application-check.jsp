<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="container">
    <div class="container-content">

        <label>ΑΜΚΑ φοιτητή:</label>
        <p>${application.amka}</p>

        <label>Aδερφός/ή που σπουδάζει στην ίδια πόλη:</label>
        <c:set var="brotherSameCity" scope="session"
               value="${application.hasBrotherSameCity}"/>
        <c:choose>
            <c:when test="${application.hasBrotherSameCity}">
                <p>Ναι</p>
            </c:when>
            <c:otherwise>
                <p>Όχι</p>
            </c:otherwise>
        </c:choose>

        <label>Aνήλικα αδέρφια:</label>
        <p>${application.noOfUnderageBrothers}</p>

        <label>Oικογενειακό εισόδημα:</label>
        <p>${application.familyIncome}</p>

        <label>Πόλη καταγωγής:</label>
        <p>${application.cityOfOrigin}</p>

        <label>Επιλογή πρώτη:</label>
        <p>${application.choice1}</p>

        <label>Επιλογή δεύτερη:</label>
        <p>${application.choice2}</p>

        <label>Πόντοι:</label>
        <p>${application.points}</p>


		<form action="/IntranetSystem/transAdmin/application/download" method="POST">

	        <strong>Δικαιολογητικά:</strong>

	        <c:choose>
	            <c:when test="${application.hasBrotherSameCity}">
	            	<button type="submit" name="document" value="bebaiwsh_spoudwn">Λήψη Βεβαίωσης Σπουδών</button>	
	            </c:when>
	            <c:otherwise></c:otherwise>
	        </c:choose>
	
	        <button type="submit" name="document" value="oikogeneiakh_katastash">Λήψη Οικογενειακής Κατάστασης</button>
	        <button type="submit" name="document" value="ekkatharistiko">Λήψη Εκκαθαριστικού</button>
	        <button type="submit" name="document" value="monimh_katoikia">Λήψη Βεβαίωσης Μόνιμης Κατοικίας</button>
			
		</form>


        <form action="/IntranetSystem/transAdmin/application/checked_application" method="POST">

            <label for="acceptedDep"><strong>Τμήμα Εισαγωγής:</strong></label> 
            <select name="acceptedDep" id="acceptedDep">
                <option value=""></option>
                <option value="Τμήμα Πληροφορικής κ Τηλεματικής">Τμήμα Πληροφορικής κ Τηλεματικής</option>
                <option value="Τμήμα Γεωγραφίας">Τμήμα Γεωγραφίας</option>
                <option value="Τμήμα Διαιτολογίας">Τμήμα Διαιτολογίας</option>
                <option value="Τμήμα Οικιακής Οικονομίας">Τμήμα Οικιακής Οικονομίας</option>
            </select>
            <input type="radio" name="check" value="approved" required>Έγκριση</br>
            <input type="radio" name="check" value="rejected" required>Απόρριψη</br></br>
            
            Αριθμός Κατάταξης: <input type="number" min="0" value="0" name="position">
            		
			<div class="container-button">

	            <button type="submit" class="mainButton">Ολοκλήρωση Ελέγχου</button>
	            <button onclick="location.href='/IntranetSystem/home_page'"
	                    type="button" class="secButton">Επιστροφή
	            </button>
	            <button onclick="location.href='/IntranetSystem/logout'" type="button"
	                    class="logoutButton">Αποσύνδεση του
	                <sec:authentication property="principal.username"/>
	            </button>

        	</div>
        </form>
    </div>
</div>



