<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
        <style><%@include file="/view/css/style.css"%></style>
    </head>
    <body>
    <c:import url="/view/navigate.jsp"/>
    <p>Project Management System</p>
    <p>PROJECTS</p>
    <table border="1" cellpadding="2%" width="100%" >
        <thead>
            <tr align="center">
                <td>Id</td>
                <td>Project name</td>
                <td>Description</td>
                <td>Customer</td>
                <td>Start date</td>
                <td>Companie</td>
                <td>Cost</td>
                <td>Developers list</td>
            </tr>
        </thead>
        <tbody align="center">
             <c:forEach var="project" items="${projects}">
                 <tr>
                     <td>${project.id}</td>
                     <td>${project.name}</td>
                     <td>${project.description}</td>
                     <td>${project.customer}</td>
                     <td>${project.beginDate}</td>
                     <td>${project.company}</td>
                     <td>${project.cost}</td>
                     <td><ol>
                             <c:forEach var="developer" items="${project.developers}">
                                <li>${developer}</li>
                             </c:forEach>
                         </ol>
                     </td>
                     <td border="0" cellpadding="0">
                     <p><a href="#">
                            <button type = "update-button" style="background-color:#1E90FF">
                                update
                     </button></a></p>
                     <p><a href="#">
                            <button type = "delete-button" style="background-color:#DC143C">
                                delete
                            </button>
                     </p>
                     </td>
                 </tr>
             </c:forEach>
        </tbody>
     </table>
    <div align="center"> <a href="#">
    <button type = "button" style="background-color:#50ff50">
        Create new project
    </button></a></div>
    </body>
</html>

