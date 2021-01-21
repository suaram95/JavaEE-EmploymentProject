<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page import="util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<%
    String regManagerMsg = "";
    if (request.getSession().getAttribute("regManagerMsg") != null) {
        regManagerMsg = (String) request.getSession().getAttribute("regManagerMsg");
        request.getSession().removeAttribute("regManagerMsg");
    }

    List<User> userList = (List<User>) request.getAttribute("allUsers");
    List<Task> taskList = (List<Task>) request.getAttribute("allTasks");
%>
<div style="border: 1px black">
    <b>Register new Manager</b><br>
    <p style="color: darkred"><%=regManagerMsg%>
    </p>
    <form action="/registerManager" method="post"><br>
        Name: <input type="text" name="name"><br>
        Surname: <input type="text" name="surname"><br>
        Age: <input type="number" name="age"><br>
        Gender: <select name="gender">
            <option value="MALE">MALE</option>
            <option value="FEMALE">FEMALE</option>
        </select><br>
        Work Experience: <input type="number" name="workExperience"><br>
        Username: <input type="text" name="username"><br>
        Password: <input type="text" name="password"><br>
        Staff type: <select name="staffType">
            <option value="ADMINISTRATIVE">ADMINISTRATIVE</option>
            <option value="ACCOUNTING">ACCOUNTING</option>
            <option value="HRM">HRM</option>
            <option value="IT">IT</option>
        </select><br>
        User Type: <select name="userType">
            <option value="ADMIN">ADMIN</option>
            <option value="SECTION_MANAGER">SECTION_MANAGER</option>
            <option value="EMPLOYEE">EMPLOYEE</option>
        </select><br>
        <input type="submit" value="Register">
    </form>
</div>
<br>

<div style="border: 1px black">
    <i><b>All Users:</b></i><br>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Age</td>
            <td>Gender</td>
            <td>Work Experience</td>
            <td>Username</td>
            <td>Staff Type</td>
            <td>User Type</td>
            <td>Action</td>
        </tr>
        <% for (User user : userList) {%>
        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><%=user.getAge()%>
            </td>
            <td><%=user.getGender()%>
            </td>
            <td><%=user.getWorkExperience()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getStaffType()%>
            </td>
            <td><%=user.getUserType()%>
            </td>
            <td><a href="/deleteUser?id=<%=user.getId()%>">Delete</a></td>
        </tr>
        <%}%>
    </table>
</div>
<br>
<div style="border: 1px black">
    <i><b>All Tasks:</b></i><br>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Description</td>
            <td>Created date</td>
            <td>Deadline</td>
            <td>Comments</td>
            <td>User</td>
        </tr>
        <% for (Task task : taskList) {%>
              <tr>
                  <td><%=task.getId()%></td>
                  <td><%=task.getName()%></td>
                  <td><%=task.getDescription()%></td>
                  <td><%=DateUtil.getStringFromDate(task.getCreatedDate())%></td>
                  <td><%=DateUtil.getStringFromDate(task.getDeadline())%></td>
                  <td><%=task.getComment()%></td>
                  <td><%=task.getUser().getName()+" "+task.getUser().getSurname()%></td>
              </tr>
        <%}%>
    </table>
</div>
</body>
</html>
