<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Data</title>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");

    String editDataMsg="";
    if (request.getSession().getAttribute("editDataMsg") != null) {
        editDataMsg= (String) request.getSession().getAttribute("editDataMsg");
        request.getSession().removeAttribute("editDataMsg");
    }
%>
<a href="/departmentEmployee">Back</a>
<div style="border: 1px solid black">
    <table border="1">
        <tr>
            <td><%=currentUser.getName()%></td>
            <td><%=currentUser.getSurname()%></td>
            <td><%=currentUser.getAge()%></td>
            <td><%=currentUser.getGender()%></td>
            <td><%=currentUser.getPhoneNumber()%></td>
            <td><%=currentUser.getWorkExperience()%></td>
            <td><%=currentUser.getUsername()%></td>
            <td><%=currentUser.getPassword()%></td>
            <td><%=currentUser.getStaffType()%></td>
        </tr>
    </table>
</div>

<div>
    <p style="color: red"><%=editDataMsg%></p>
    <b>Edit Data</b><br>
    <form action="/editEmployeeData" method="post">
        Name: <input type="text" name="name" value="<%=currentUser.getName()%>"><br>
        Surname: <input type="text" name="surname" value="<%=currentUser.getSurname()%>"><br>
        Phone Number: <input type="text" name="phoneNumber" value="<%=currentUser.getPhoneNumber()%>"><br>
        Work Experience: <input type="number" name="workExperience" value="<%=currentUser.getWorkExperience()%>"><br>
        Password: <input type="text" name="password" value="<%=currentUser.getPassword()%>"><br>
        <input type="submit" value="Change">
    </form>
</div>

</body>
</html>
