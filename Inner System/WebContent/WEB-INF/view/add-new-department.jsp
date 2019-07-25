<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<form:form action="/IntranetSystem/secSuper/add/new_department"
           method="POST" modelAttribute="department" accept-charset="UTF-8">

    <div class="container">
        <div class="container-content">

            <h1>Προσθήκη τμήματος</h1>
            <h5>Πληροφορίες νέου τμήματος</h5>

            <label for="depName"><b>Όνομα Τμήματος</b></label>
            <form:input path="depName" id="depName" type="text" required="required"/>

            <label for="belongingUni"><b>Όνομα Πανεπιστημίου</b></label>
            <select id="belongingUni" name="belongingUni" required>
            <!-- for each University object the DB returns -->
                <option value=""></option>
                <c:forEach var="uni" items="${unis}">
                    <option value="${uni.code}">${uni.uniName}</option>
                </c:forEach>
            </select>
            
            <label for="matchingDep"><b>Αντιστοίχηση με:</b></label>
            <select id="matchingDep" name="matchingDep" required>
            <!-- for each HuaDepartment object the DB returns -->
                <option value=""></option>
                <c:forEach var="hua_department" items="${hua_departments}">
                    <option value="${hua_department.code}">${hua_department.dep_name}</option>
                </c:forEach>
            </select>

            <div class="container-button">
                <button type="submit" class="mainButton">Προσθήκη Τμήματος</button>
                <button onclick="location.href='/IntranetSystem/home_page'"
                        type="button" class="secButton">Επιστροφή
                </button>
                <button onclick="location.href='/IntranetSystem/logout'" type="button" class="logoutButton"> 
                Αποσύνδεση του <sec:authentication property="principal.username"/></button>
            </div>
          </div>
        </div>
</form:form>





