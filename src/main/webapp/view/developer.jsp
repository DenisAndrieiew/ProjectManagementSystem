<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
        <style><%@include file="/view/css/style.css"%></style>
    </head>

<body>
<c:import url="/view/navigate.jsp"/>
     <form name="developerForm" method="post" action="/developers">
          <fieldset>
              <legend>Personal data</legend>
              FirstName: <input type="text" name="firstName">
              LastName: <input type="text" name="lastName"/> <br/>
              Age: <input type="number" name="age"/>
              Salary:   <input type="number" name="salary"/>
              Sex: <select id="sex" name="sex">
                         <option value="MALE">Male</option>
                         <option value="FEMALE">Female</option>
                   </select><br/>
              Comments: <input type="text" name = "comments">
              </fieldset>
                 <fieldset>
                             <legend>Projects</legend>
                             <c:forEach var="project" items="${projects}">
                             <input type="checkbox" name="in_project" value='${project.name}'>${project.name}<br>
                                                           </c:forEach>
                 </fieldset>
                 <fieldset>
                    <legend>Skills</legend>
                    <c:forEach var="brunch"  items="${brunches}">
                    <div value=${brunch.brunch}>${brunch.brunch}
                    <select id="skill_level" name="skill_level">
                                 <option value="None">None</option>
                                 <option value="Junior">Junior</option>
                                 <option value="Middle">Middle</option>
                                 <option value="Senior">Senior</option>
                               </select></div>
                    </c:forEach>
                 </fieldset>
                <input type="submit" value="add developer" />
            </form>

        </body>

    </body>