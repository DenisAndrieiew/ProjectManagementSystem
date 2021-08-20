<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Management System</title>
        <style><%@include file="/view/css/style.css"%></style>
    </head>

<body>
<c:import url="/view/navigate.jsp"/>
     <form name="developerForm" method=post action="/developers/update">

          <fieldset>
              <legend>Personal data</legend>
              <input type="hidden" name="id" value='${developer.id}' />
              <c:out value='${developer.firstName}'/>
              <c:out value='${developer.lastName}'/>
              FirstName: <input type="text" name="firstName" value="<c:out value='${developer.firstName}'/>">
              LastName: <input type="text" name="lastName" value="<c:out value='${developer.lastName}'/>"/> <br/>
              Age: <input type="number" name="age" value="<c:out value='${developer.age}'/>"/>
              Salary:   <input type="number" name="salary" value="<c:out value='${developer.salary}'/>"/>
              Sex: <select id="sex" name="sex">
                         <option value="MALE">Male</option>
                         <option value="FEMALE">Female</option>
                   </select><br/>
              Comments: <input type="text" name = "comments" value="<c:out value='${developer.comments}'/>">
              </fieldset>
                 <fieldset>
                             <legend>Projects</legend>
                             <c:forEach var="project" items="${projects}">
                             <input type="checkbox" name="in_project" value='${project.id}'>${project.name}<br>
                             <input type="hidden" name="project_id" value='${project.id}' />
                             <br>
                                                           </c:forEach>
                 </fieldset>
                 <fieldset>
                    <legend>Skills</legend>
                    <c:forEach var="brunch"  items="${brunches}">
                         <div value=${brunch}>${brunch}
                         <select id="skill_level" name="skill_level">
                                     <option value="None">None</option>
                                     <c:forEach var="level" items="${skillLevels}">
                                     <option value=${level}> ${level}</option>
                                     </c:forEach>
                                     </select></div>
                    </c:forEach>
                 </fieldset>
                <input type="submit" value="update developer" />
            </form>

        </body>

    </body>