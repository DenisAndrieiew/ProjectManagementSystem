<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
        <style><%@include file="/view/css/style.css"%></style>
    </head>

<body>
<c:import url="/view/navigate.jsp"/>
     <form name="developerForm" method="post" action="/customers">
          <fieldset>
              <legend>Customers data</legend>
              Name: <input type="text" name="name">
                 </fieldset>
                <input type="submit" value="add customer" />
            </form>

        </body>

    </body>