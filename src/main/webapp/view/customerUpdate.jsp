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
     <form name="customerForm" method="post" action="/customers/update">
          <fieldset>
             <legend>Comapny data</legend>
             <input type="hidden" name="id" value='${customer.id}' />
             Name: <input type="text" name="name" value="<c:out value='${customer.name}'/>">
                </fieldset>
               <input type="submit" value="update customer" />
            </form>
        </body>
    </body>