<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="/IntranetSystem/student/create" method="POST"
           onsubmit="return confirm('Είστε βέβαιος για την ακρίβεια των στοιχείων σας;');"
           modelAttribute="student">
    <div class="container">
        <div class="container-content">
            
            <h1>Εγγραφή στο σύστημα μετεγγραφών</h1>

            <label for="fullName"><b>Ονοματεπώνυμο:</b></label>
            <form:input type="text" path="fullName" id="realname" required="required"></form:input>

            <label for="currDep"><b>Τρέχων Τμήμα Φοίτησης</b></label>
            <form:input type="text" path="currDep" id="currDep" required="required"></form:input>

            <label for="semester"><b>Τρέχων εξάμηνο φοίτησης</b></label>
            <form:input type="number" path="semester" id="semester" required="required"></form:input>

            <label for="id"><b>Αριθμός Μητρώου:</b></label>
            <form:input type="text" path="id" id="id" required="required"></form:input>

            <label for="amka"><b>Αριθμός Μητρώου Κοινωνικής Ασφάλισης:</b></label>
            <form:input type="number" path="amka" id="amka" required="required"></form:input>

            <label for="email"><b>Ηλεκτρονική Διεύθυνση Ταχυδρομείου:</b></label>
            <form:input type="email" path="email" id="email" required="required"></form:input>

            <label for="password"><b>Κωδικός Πρόσβασης:</b></label>
            <form:input type="password" path="password" id="password" required="required"></form:input>

            <div class="container-button">

                <button type="submit" class="mainButton">Εγγραφή</button>
                <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή
                </button>
                <button onclick="location.href='/IntranetSystem/logout'" type="button" class="logoutButton">
                    Αποσύνδεση του<sec:authentication property="principal.username"/>
                </button>

            </div>
        </div>
    </div>
</form:form>

