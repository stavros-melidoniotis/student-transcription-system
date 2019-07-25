<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="container">
    <div class="container-content">

        <c:choose>
            <c:when test="${check == null}">
                <p>Η αίτησή σας δεν έχει εξεταστεί ακόμα.</p>
            </c:when>
            <c:otherwise>
                <p>Ημερομηνία εξέτασης:</p>
                ${check.dateOfCheck}

                <c:choose>
                    <c:when test="${check.approved}">
                        <p>Κατάσταση:</p>
                        Αίτηση Εγκρίθηκε
                        <p>Τμήμα Έγκρισης:</p>
                        ${check.acceptedDep}
                    </c:when>
                    <c:otherwise>
                        <p>Κατάσταση:</p>
                        Αίτηση Απορρίφθηκε
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        
        <div class="container-button">

            <button onclick="location.href='/IntranetSystem/home_page'"
                    type="button" class="secButton">Επιστροφή</button>
            <button onclick="location.href='/IntranetSystem/logout'" type="button"
                    class="logoutButton"> Αποσύνδεση του
                <sec:authentication property="principal.username" />
            </button>

        </div>
    </div>
</div>