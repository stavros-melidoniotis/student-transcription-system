<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="/IntranetSystem/admin/user/created_user"
           method="POST" modelAttribute="user">

    <div class="container">
        <div class="container-content">

            <h1>Προσθήκη Νέου Χρήστη</h1>

            <label for="username"><b>Όνομα Χρήστη</b></label>
            <form:input path="username" type="email" required="required"/>

            <label for="password"><b>Κωδικός Πρόσβασης</b></label>
            <form:input type="password" path="password" id="password" required="required"/>

            <label for="authority"><b>Ρόλος</b></label>
            <select id="authority" name="authority" required="required">
                <option value="ROLE_STUDENT">Φοιτητής</option>
                <option value="ROLE_SECSUPER">Προϊστάμενος Γραμματείας</option>
                <option value="ROLE_TRANSADMIN">Διοικητικός Υπάλληλος</option>
                <option value="ROLE_ADMIN">Διαχειριστής</option>
            </select>
             
            <label for="enabled"><b>Ενεργός</b></label>
            <form:checkbox path="enabled" id="enabled"/>

            <div class="container-button">
                <button type="submit" class="mainButton">Προσθήκη Χρήστη</button>
                <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή
                </button>
                <button onclick="location.href='/IntranetSystem/logout'"
                        type="button" class="logoutButton">Αποσύνδεση του
                    <sec:authentication property="principal.username"/>
                </button>
            </div>
        </div>
    </div>
</form:form>