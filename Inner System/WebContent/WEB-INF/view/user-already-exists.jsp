<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="container">
    <div class="container-content">
        <h2>Το όνομα χρήστη υπάρχει ήδη. Παρακαλώ δοκιμάστε ξανά.</h2>

        <div class="container-button">

            <button onclick="location.href='/IntranetSystem/home_page'"
                    type="button" class="secButton">Επιστροφή</button>
            <button onclick="location.href='/IntranetSystem/logout'" type="button" class="logoutButton">
                Αποσύνδεση του<sec:authentication property="principal.username" />
            </button>

        </div>
    </div>
</div>