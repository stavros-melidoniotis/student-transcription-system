<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="container">
	<div class="container-content">
	
		<p>Δεν έχετε πρόσβαση στη συγκεκριμένη σελίδα</p>
		
		<div class="container-button">
            <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή
            </button>
            <button onclick="location.href='/IntranetSystem/logout'" type="button" class="logoutButton"> 
            	Αποσύνδεση του <sec:authentication property="principal.username"/></button>
        </div>
	</div>
</div>