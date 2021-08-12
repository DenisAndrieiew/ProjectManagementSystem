<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
    </head>
    <body>
    <p>Project Management System</p>
     <form name="developerForm" method=${method} action="/developers">
          <fieldset>
              <legend>Personal data</legend>
              FirstName: <input type="text" name="firstName"/> <br/>
              LastName: <input type="text" name="lastName"/> <br/>
              Age: <input type="number" name="age"/> <br/>
              Sex: <select id="sex" name="sex">
                         <option value="MALE">Male</option>
                         <option value="FEMALE">Female</option>
                       </select>
              </fieldset>
                 <fieldset>
                             <legend>Projects</legend>
                             <c:forEach var="project" items="${projects}">
                             <input type="checkbox" name="in_project" value=${project.name}>${project.name}<br>
                                                           </c:forEach>
                 </fieldset>
                 <fieldset>
                    <legend>Skills</legend>
                    <c:forEach var="brunch" items="${brunches}">
                    <div value=${brunch.brunch}>${brunch.brunch}
                    <select id="skill_level" name="skill_level">
                                 <option value="NONE">None</option>
                                 <option value="JUNIOR">Junior</option>
                                 <option value="MIDDLE">Middle</option>
                                 <option value="SENIOR">Senior</option>
                               </select></div>
                    </c:forEach>
                 </fieldset>
                <input type="submit" value="add developer" />
            </form>

        </body>

    </body>