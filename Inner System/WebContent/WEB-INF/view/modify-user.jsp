<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="/IntranetSystem/admin/user/modified_user" method="POST">
    <div class="container">
        <div class="container-content">
            <h1>Επεξεργασία Στοιχείων Χρήστη</h1>

            <label for="username"><b>Όνομα Χρήστη:</b></label>
            <input name="username" type="email" value="${user.username}" required/>

            <label for="password"><b>Κωδικός Πρόσβασης:</b></label>
            <input type="password" name="password" id="password" required/>

            <label for="authority"><b>Ρόλος:</b></label>
            <select id="authority" name="authority" required>
                <option value=""></option>
                <option value="ROLE_STUDENT">Φοιτητής</option>
                <option value="ROLE_SECSUPER">Προϊστάμενος Γραμματείας</option>
                <option value="ROLE_TRANSADMIN">Διοικητικός Υπάλληλος</option>
                <option value="ROLE_ADMIN">Διαχειριστής</option>
            </select>

            <label for="enabled"><b>Ενεργός:</b></label>
            <input type="radio" name="enabled" value="true"/> Ναι
            <input type="radio" name="enabled" value="false"/> Όχι

            <div class="container-button">

                <button type="submit" class="mainButton">Τροποποίηση Χρήστη</button>
                <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή
                </button>
                <button onclick="location.href='/IntranetSystem/logout'" type="button" class="logoutButton">
                    Αποσύνδεση του  <sec:authentication property="principal.username"/>
                </button>

            </div>
        </div>
    </div>
</form:form>






