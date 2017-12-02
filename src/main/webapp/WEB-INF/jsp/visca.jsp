<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">

<body>
<form:form method="post" action="/runCommand" modelAttribute="model">

    <form:label path="command">Komenda: </form:label>
    <form:select path="command">
        <form:options items="${commandTypes}" itemLabel="command"/>
    </form:select>
    <br/>
    <form:label path="param">Parametr:</form:label>
    <form:input path="param"/>
    <input type="submit" value="Wykonaj"/>

</form:form>
 <div style="color: red;">
     ${error}
 </div>

 Rezultat: ${result}
</body>

</html>
