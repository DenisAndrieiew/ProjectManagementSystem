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
                <td>Skills</td>
                <td>Sex</td>
                <td>Salary</td>
                <td>Projects</td>

            </tr>
        </thead>
        <tbody>
             <c:forEach var="developer" items="${developers}">
                 <tr>
                     <td>${developer.firstName}</td>
                     <td>${developer.lastName}</td>
                     <td>${developer.age}</td>
                     <td>
                        <table border="0" cellpadding="0">
                        <thead><tr>
                                <td></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="skillLevel" items="${developer.skillLevels}">
                                <tr>
                                    <td>${skillLevel.key}</td>
                                    <td>${skillLevel.value}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        </table>
                     </td>
                     <td>${developer.sex}</td>
                     <td>${developer.salary}</td>
                     <td>
                        <table border="0" cellpadding="0">
                            <td>
                                <tbody>
                                    <c:forEach var="project" items="${developer.projects}">
                                        <tr>
                                            <td>${project}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody></td>
                        </table></td>
                 </tr>
             </c:forEach>
        </tbody>
     </table>
    </body>
</html>