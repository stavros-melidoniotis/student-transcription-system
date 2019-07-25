<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="/IntranetSystem/student/application/create" method="POST"
           onsubmit="return confirm('Οριστική υποβολή αίτησης;');"
           modelAttribute="application">
           
    <div class="container">
        <div class="container-content">
            <h1>Προβολή Αίτησης Μετεγγραφής</h1>

            <label for="amka"><b>ΑΜΚΑ:</b></label>
            <form:input minlength="11" maxlength="11" type="text" path="amka" id="amka"></form:input>
            <!--<form:input type="number" path="amka" id="amka" min="01010000000" max="31129999999"></form:input>-->

            <h3><b>Οικογενειακά Κριτήρια</b></h3>
            <label for="hasBrotherSameCity"><b>Έχετε αδελφό που σπουδάζει στην πόλη επιλογής;</b></label>
            <form:checkbox path="hasBrotherSameCity" value="true" id="hasBrotherSameCity"></form:checkbox>

            <h6>Κατηγορία 1</h6>
            <label for="noOfUnderageBrothers"><b>Πόσα άλλα ανήλικα αδέλφια έχετε;</b></label>
            <form:input min="0" type="number" path="noOfUnderageBrothers" id="noOfUnderageBrothers" required="required"></form:input>
            
            <h3><b>Οικονομικά Κριτήρια</b></h3>

            <label for="familyIncome"><b>Ποιό είναι το οικογενειακό σας εισόδημα;</b></label>
            <form:input min="0" type="number" id="familyIncome" path="familyIncome"></form:input>

            <h3><b>Κριτήρια Εντοπιότητας</b></h3>

            <b>Μόνιμη Κατοικία</b>

            <label for="dropDownList"></label>
            <div class="citySearch">
                <form:select id="dropDownList" path="cityOfOrigin">
                    <form:option value="Αγ. Νικόλαος Κρήτης">Αγ. Νικόλαος Κρήτης</form:option>
                    <form:option value="Αγρίνιο">Αγρίνιο</form:option>
                    <form:option value="Αθήνα">Αθήνα</form:option>
                    <form:option value="Αίγιο">Αίγιο</form:option>
                    <form:option value="Αλεξανδρούπολη">Αλεξανδρούπολη</form:option>
                    <form:option value="Αμαλιάδα">Αμαλιάδα</form:option>
                    <form:option value="Άμφισσα">Άμφισσα</form:option>
                    <form:option value="Αργοστόλι">Αργοστόλι</form:option>
                    <form:option value="Άρτα">Άρτα</form:option>
                    <form:option value="Βόλος">Βόλος</form:option>
                    <form:option value="Γρεβενά">Γρεβενά</form:option>
                    <form:option value="Διδυμότειχο">Διδυμότειχο</form:option>
                    <form:option value="Δράμα">Δράμα</form:option>
                    <form:option value="Ερμούπολη">Ερμούπολη</form:option>
                    <form:option value="Ζάκυνθος">Ζάκυνθος</form:option>
                    <form:option value="Ηγουμενίτσα">Ηγουμενίτσα</form:option>
                    <form:option value="Ηράκλειο">Ηράκλειο</form:option>
                    <form:option value="Θεσσαλονίκη">Θεσσαλονίκη</form:option>
                    <form:option value="Θήβα">Θήβα</form:option>
                    <form:option value="Ιεράπετρα">Ιεράπετρα</form:option>
                    <form:option value="Ιωάννινα">Ιωάννινα</form:option>
                    <form:option value="Καβάλα">Καβάλα</form:option>
                    <form:option value="Καλαμάτα">Καλαμάτα</form:option>
                    <form:option value="Καρδίτσα">Καρδίτσα</form:option>
                    <form:option value="Καρλόβασι">Καρλόβασι</form:option>
                    <form:option value="Καστοριά">Καστοριά</form:option>
                    <form:option value="Κατερίνη">Κατερίνη</form:option>
                    <form:option value="Κέρκυρα">Κέρκυρα</form:option>
                    <form:option value="Κιλκίς">Κιλκίς</form:option>
                    <form:option value="Κοζάνη">Κοζάνη</form:option>
                    <form:option value="Κομοτηνή">Κομοτηνή</form:option>
                    <form:option value="Κόρινθος">Κόρινθος</form:option>
                    <form:option value="Λαμία">Λαμία</form:option>
                    <form:option value="Λάρισα">Λάρισα</form:option>
                    <form:option value="Λευκάδα">Λευκάδα</form:option>
                    <form:option value="Ληξούρι">Ληξούρι</form:option>
                    <form:option value="Μεσολόγγι">Μεσολόγγι</form:option>
                    <form:option value="Μυτιλήνη">Μυτιλήνη</form:option>
                    <form:option value="Ναύπακτος">Ναύπακτος</form:option>
                    <form:option value="Ναύπλιο">Ναύπλιο</form:option>
                    <form:option value="Ξάνθη">Ξάνθη</form:option>
                    <form:option value="Ορεστιάδα">Ορεστιάδα</form:option>
                    <form:option value="Πάτρα">Πάτρα</form:option>
                    <form:option value="Πειραιάς">Πειραιάς</form:option>
                    <form:option value="Πρέβεζα">Πρέβεζα</form:option>
                    <form:option value="Πύργος">Πύργος</form:option>
                    <form:option value="Ρέθυμνο">Ρέθυμνο</form:option>
                    <form:option value="Ρόδος">Ρόδος</form:option>
                    <form:option value="Σάμος">Σάμος</form:option>
                    <form:option value="Σέρρες">Σέρρες</form:option>
                    <form:option value="Σητεία">Σητεία</form:option>
                    <form:option value="Σπάρτη">Σπάρτη</form:option>
                    <form:option value="Τρίκαλα">Τρίκαλα</form:option>
                    <form:option value="Τρίπολη">Τρίπολη</form:option>
                    <form:option value="Φλώρινα">Φλώρινα</form:option>
                    <form:option value="Χαλκίδα">Χαλκίδα</form:option>
                    <form:option value="Χανιά">Χανιά</form:option>
                    <form:option value="Χίος">Χίος</form:option>
                </form:select>
            </div>

            <h3><b>Επιλογή Τμήματος:</b></h3>
            <div class="depform:select">
                <label for="choice1"><b>Πρώτη επιλογή:</b></label>
                <form:select id="choice1" path="choice1" name="choice1" required="required">
                    <form:option value=""></form:option>
                    <form:option value="Τμήμα Πληροφορικής κ Τηλεματικής">Τμήμα Πληροφορικής κ Τηλεματικής</form:option>
                    <form:option value="Τμήμα Οικιακής Οικονομίας">Τμήμα Οικιακής Οικονομίας</form:option>
                    <form:option value="Τμήμα Γεωγραφίας">Τμήμα Γεωγραφίας</form:option>
                    <form:option value="Τμήμα Διαιτολογίας">Τμήμα Διαιτολογίας</form:option>
                </form:select>

                <label for="choice2"><b>Δεύτερη επιλογή:</b></label>
                <form:select id="choice2" path="choice2" name="choice2">
                    <form:option value="false">...</form:option>
                    <form:option value="Τμήμα Πληροφορικής κ Τηλεματικής">Τμήμα Πληροφορικής κ Τηλεματικής</form:option>
                    <form:option value="Τμήμα Οικιακής Οικονομίας">Τμήμα Οικιακής Οικονομίας</form:option>
                    <form:option value="Τμήμα Γεωγραφίας">Τμήμα Γεωγραφίας</form:option>
                    <form:option value="Τμήμα Διαιτολογίας">Τμήμα Διαιτολογίας</form:option>
                </form:select>
            </div>

            <div class="requiredDocuments">
                <h3><b>Δικαιολογητικά Έγγραφα</b></h3>

                <label for="oikogeneiakh_katastash">Πιστοποιητικό Οικογενειακής Κατάστασης:</label>
                <form:input type="file" id="oikogeneiakh_katastash" path="oikogeneiakh_katastash"
                            required="required"></form:input>

                <label for="ekkatharistiko">Εκκαθαριστικό Σημείωμα:</label>
                <form:input type="file" id="ekkatharistiko" path="ekkatharistiko" required="required"></form:input>

                <label for="bebaiwsh_spoudwn">Βεβαίωση Σπουδών Αδελφού (εφόσον ανήκετε στην υποκατηγορία 1):</label>
                <form:input type="file" id="bebaiwsh_spoudwn" path="bebaiwsh_spoudwn"></form:input>

                <label for="monimh_katoikia">Πιστοποιητικό Μόνιμης Κατοικίας</label>
                <form:input type="file" id="monimh_katoikia" path="monimh_katoikia" required="required"></form:input>

                <div class="container-button">

                    <button type="submit" class="mainButton">Ολοκλήρωση Αίτησης</button>
                    <button onclick="location.href='/IntranetSystem/home_page'"
                            type="button" class="secButton">Επιστροφή
                    </button>
                    <button onclick="location.href='/IntranetSystem/logout'" type="button"
                            class="logoutButton">Αποσύνδεση του
                        <sec:authentication property="principal.username"/>
                    </button>

                </div>
            </div>
        </div>
     </div>
</form:form>







