<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
    </head>
    <body>
    <p>Project Management System</p>
    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <td>First Name</td>
                <td>Last Name</td>
                <td>age</td>
         <%--       <td>Skills</td>     --%>
                <td>Sex</td>
                <td>Salary</td>

            </tr>
        </thead>
        <tbody>
             <c:forEach var="developer" items="${developers}">
                 <tr>
                     <td>${developer.firstName}</td>
                     <td>${developer.lastName}</td>
                     <td>${developer.age}</td>
                     <td>${developer.sex}</td>
                     <td>${developer.salary}</td>
                 </tr>
             </c:forEach>
        </tbody>
     </table>
    </body>
</html>