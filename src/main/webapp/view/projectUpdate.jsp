<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
        <style><%@include file="/view/css/style.css"%></style>
        <meta http-equiv="Content-Type" content = "text/html; charset=utf-8">
    </head>

<body>
<c:import url="/view/navigate.jsp"/>
     <form name="projectForm" method="post" action="/projects/update">
          <fieldset>
              <legend>Project data</legend>
              <input type="hidden" name="id" value='${project.id}' />
              Project Name: <input type="text" name="name"
              value="<c:out value='${project.name}'/>" required/> <br/>
              <legend>Companies</legend>
                <c:forEach var="company" items="${companies}">
                  <input type="radio" name="company" value='${company.id}' required>
                  ${company.name}<br>
                </c:forEach>
              Description: <input type="text" name="description" value="<c:out value='${project.description}'/>"/> <br/>
              <legend>Customers</legend>
                 <c:forEach var="customer" items="${customers}">
                   <input type="radio" name="customer" value='${customer.name}' required>
                   ${customer.name}<br>
                 </c:forEach>
              Start Date: <input type="datetime" name="beginDate" required value="<c:out value='${project.beginDate}'/>">
               <br/>
                <input type="submit" value="${button_text} project" value="<c:out value='${project.beginDate}'/>"/>
                <p>You can add developers to project by updating or creating developers</p>
            </form>
        </body>
    </body>