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
     <form name="developerForm" method=${method} action="/projects">
<c:set var="button_text" value="${method eq 'post' ? 'add': 'update'}"/>
          <fieldset>
              <legend>Project data</legend>
              Project Name: <input type="text" name="name" required /> <br/>
              <legend>Companies</legend>
                <c:forEach var="company" items="${companies}">
                  <input type="radio" name="company" value='${company.name}' required>
                  ${company.name}<br>
                </c:forEach>
              Description: <input type="text" name="description" /> <br/>
              <legend>Customers</legend>
                 <c:forEach var="customer" items="${customers}">
                   <input type="radio" name="customer" value='${customer.name}' required>
                   ${customer.name}<br>
                 </c:forEach>
              Start Date: <input type="datetime" name="beginDate" required placeholder="0000-00-00T00:00:000"> <br/>
                <input type="submit" value="${button_text} project" />
                <p>You can add developers to project by updating or creating developers</p>
            </form>
        </body>
    </body>