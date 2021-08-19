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
    <p>CUSTOMERS</p>
    <table border="1" cellpadding="2%" width="100%" >
        <thead>
            <tr align="center">
            <td>Id</td>
                <td>Company Name</td>
                <td>Projects</td>
                <td/>
            </tr>
        </thead>
        <tbody align="center">
             <c:forEach var="var" items="${customers}">
                 <tr>
                 <td>${var.id}</td>
                     <td>${var.name}</td>
                     <td>
                        <ol>
                           <c:forEach var="project" items="${var.projects}">
                           <li>${project.name}</li>
                           </c:forEach>
                           </ol>
                     </td>
                     <td border="0" cellpadding="0">
                     <p><a href="/customers/update?id=${var.id}">
                            <button type = "update-button" style="background-color:#1E90FF">
                             update
                     </button></a></p>
                     <p><a href="/customers/delete?id=${var.id}">
                     <button type = "delete-button" style="background-color:#DC143C">
                                                  delete
                                          </button></p>
                     </td>
                 </tr>
             </c:forEach>
        </tbody>
     </table>
    <div align="center"> <a href="/customers/new">
    <button type = "button" style="background-color:#50ff50">
        Create new customer
    </button></a></div>
    </body>
</html>

