<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<div class="container">
    <div class="container-content">

        <h1>Σύστημα Διαχείρισης Μετεγγραφών</h1>

        <!-- Each section is available only for users with specified role -->

        <!-- Student section -->
        <!-- <sec:authorize access="hasRole('STUDENT')"> -->
        <!--    <a class="menuLinks" href="${pageContext.request.contextPath}/student/application/fill">Δημιουργία Αίτησης</a> -->
        <!--    <a class="menuLinks" href="${pageContext.request.contextPath}/student/application/check_progress">Έλεγχος Αίτησης</a> -->
        <!-- </sec:authorize> -->

        <!-- secSuper section -->
        <sec:authorize access="hasRole('SECSUPER')">
            <a class="menuLinks" href="${pageContext.request.contextPath}/secSuper/add">Προσθήκη Τμήματος</a>
            <a class="menuLinks" href="${pageContext.request.contextPath}/secSuper/delete">Διαγραφή Τμήματος</a>
            <a class="menuLinks" href="${pageContext.request.contextPath}/secSuper/update_dep_pos">Ανανέωση Θέσεων Μετεγγραφών</a>
        </sec:authorize>

        <!-- transAdmin section -->
        <sec:authorize access="hasRole('TRANSADMIN')">
            <a class="menuLinks" href="${pageContext.request.contextPath}/transAdmin/application/select">Επιλογή Αίτησης για Εξέταση</a>
        </sec:authorize>


        <!-- Admin section -->
        <sec:authorize access="hasRole('ADMIN')">
            <a class="menuLinks" href="${pageContext.request.contextPath}/admin/user/add">Προσθήκη Χρήστη</a>
            <a class="menuLinks" href="${pageContext.request.contextPath}/admin/user/delete">Διαγραφή Χρήστη</a>
            <a class="menuLinks" href="${pageContext.request.contextPath}/admin/user/select">Τροποποίηση Στοιχείων Χρήστη</a>
        </sec:authorize>

        <div class="container-button">

            <button onclick="location.href='/IntranetSystem/logout'" type="button"
                    class="logoutButton"> Αποσύνδεση του
                <sec:authentication property="principal.username"/>
            </button>

        </div>
    </div>
</div>
