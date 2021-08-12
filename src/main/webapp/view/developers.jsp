<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
    </head>
    <body>
    <p>Project Management System</p>
    <table border="1" cellpadding="2%" width="100%" >
        <thead>
            <tr align="center">
                <td>First Name</td>
                <td>Last Name</td>
                <td>age</td>
                <td>Skills</td>
                <td>Sex</td>
                <td>Salary</td>
                <td>Projects</td>
                <td/>
            </tr>
        </thead>
        <tbody align="center">
             <c:forEach var="developer" items="${developers}">
                 <tr>
                     <td>${developer.firstName}</td>
                     <td>${developer.lastName}</td>
                     <td>${developer.age}</td>
                     <td>
                         <ol>
                              <c:forEach var="skillLevel" items="${developer.skillLevels}">
                                    <li>${skillLevel.key}  ${skillLevel.value}</li>
                              </c:forEach>
                         </ol>
                     </td>
                     <td>${developer.sex}</td>
                     <td>${developer.salary}</td>
                     <td>
                        <ol>
                           <c:forEach var="project" items="${developer.projects}">
                           <li>${project}</li>
                           </c:forEach>
                           </ol>
                     </td>
                     <td border="0" cellpadding="0">
                     <p><button type = "update-button" style="background-color:#1E90FF">
                             update
                     </button></p>
                     <p><button type = "delete-button" style="background-color:#DC143C">
                                                  delete
                                          </button></p>
                     </td>
                 </tr>
             </c:forEach>
        </tbody>
     </table>
    <div align="center"> <button type = "button" style="background-color:#50ff50">
                                  Create new developer
                          </button></div>
    </body>
</html>