<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form action="/IntranetSystem/secSuper/new_positions" method="POST">
    <div class="container">
        <div class="container-content">
            
            <h1>Ενημέρωση διαθέσιμων θέσεων μετεγγραφής</h1>

            <table>
                <tr>
                    <td><label for="informatics_pos">Τμήμα Πληροφορικής κ' Τηλεματικής</label></td>
                    <td><input type="number" min="0" name="informatics_pos" id="informatics_pos" value="${informatics_pos}"/></td>
                </tr>

                <tr>
                    <td><label for="geography_pos">Τμήμα Γεωγραφίας</label></td>
                    <td><input type="number" min="0" name="geography_pos" id="geography_pos" value="${geography_pos}"/></td>
                </tr>

                <tr>
                    <td><label for="home_economics_pos">Τμήμα Οικιακής Οικονομίας</label></td>
                    <td><input type="number" min="0" name="home_economics_pos" id="home_economics_pos" value="${home_economics_pos}"/></td>
                </tr>

                <tr>
                    <td><label for="dietology_pos">Τμήμα Διαιτολογίας</label></td>
                    <td><input type="number" min="0" name="dietology_pos" id="dietology_pos" value="${dietology_pos}"/></td>
                </tr>
            </table>

            <div class="container-button">

                <button type="submit" class="mainButton">Ενημέρωση Θέσεων</button>
                <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή</button>
                <button onclick="location.href='/IntranetSystem/logout'" type="button" class="logoutButton">
                    Αποσύνδεση του <sec:authentication property="principal.username" />
                </button>

            </div>
        </div>
    </div>
</form>
