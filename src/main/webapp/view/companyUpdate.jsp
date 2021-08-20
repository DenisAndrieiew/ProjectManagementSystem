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
     <form name="companyForm" method="post" action="/companies/update">
          <fieldset>
             <legend>Comapny data</legend>
             <input type="hidden" name="id" value='${company.id}' />
             Name: <input type="text" name="name" value="<c:out value='${company.name}'/>">
                </fieldset>
               <input type="submit" value="update company" />
            </form>
        </body>
    </body>