<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page import="util.DateUtil" %>
<%@ page import="model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<%
    String registerMsg = "";
    if (request.getSession().getAttribute("registerMsg") != null) {
        registerMsg = (String) request.getSession().getAttribute("registerMsg");
        request.getSession().removeAttribute("registerMsg");
    }

    User currentUser = (User) request.getSession().getAttribute("currentUser");

    List<User> adminUserList = (List<User>) request.getSession().getAttribute("allAdminUsers");
    List<Task> adminTaskList = (List<Task>) request.getSession().getAttribute("allAdminTasks");
%>
<a href="/logout">Logout</a><br>
<b>Welcome <%=currentUser.getName() + " " + currentUser.getSurname() + " !"%>
</b><br>
<div style="border: 1px black solid; width: 45%">
    <b>Register new Manager</b><br>
    <p style="color: darkred"><%=registerMsg%>
    </p>
    <form action="/register" method="post">
        Name: <input type="text" name="name"><br><br>
        Surname: <input type="text" name="surname"><br><br>
        Age: <input type="number" name="age"><br><br>
        Gender: <select name="gender">
            <option value="MALE">MALE</option>
            <option value="FEMALE">FEMALE</option>
        </select><br><br>
        Phone Number: <input type="text" name="phoneNumber"><br><br>
        Work Experience: <input type="number" name="workExperience"><br><br>
        Username: <input type="text" name="username"><br><br>
        Password: <input type="text" name="password"><br><br>
        Staff type: <select name="staffType">
            <option value="ADMINISTRATIVE">ADMINISTRATIVE</option>
            <option value="ACCOUNTING">ACCOUNTING</option>
            <option value="HRM">HRM</option>
            <option value="IT">IT</option>
        </select><br><br>
        User Type: <select name="userType">
            <option value="ADMIN">ADMIN</option>
            <option value="SECTION_MANAGER">SECTION_MANAGER</option>
            <option value="EMPLOYEE">EMPLOYEE</option>
        </select><br><br>
        <input type="submit" value="Register">
    </form>
</div>
<br>

<div style="border: 1px black solid">
    <i><b>All Users:</b></i><br>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Phone Number</th>
            <th>Work Experience</th>
            <th>Username</th>
            <th>Staff Type</th>
            <th>User Type</th>
            <th>Action</th>
        </tr>
        <% for (User user : adminUserList) {%>
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
            <td><%=user.getPhoneNumber()%>
            </td>
            <td><%=user.getWorkExperience()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getStaffType()%>
            </td>
            <td><%=user.getUserType()%>
            </td>
            <td><%if (user.getUserType() != UserType.ADMIN) {%>
                <a href="/deleteUser?id=<%=user.getId()%>">Delete</a></td>
            <%}%>
        </tr>
        <%}%>
    </table>
</div>
<br>
<div style="border: 1px black solid">
    <i><b>All Tasks:</b></i><br>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Description</td>
            <td>Created date</td>
            <td>Deadline</td>
            <td>Comments</td>
            <td>Status</td>
            <td>User</td>
        </tr>
        <% for (Task task : adminTaskList) {%>
        <tr>
            <td><%=task.getId()%>
            </td>
            <td><%=task.getName()%>
            </td>
            <td><%=task.getDescription()%>
            </td>
            <td><%=DateUtil.getStringFromDate(task.getCreatedDate())%>
            </td>
            <td><%=DateUtil.getStringFromDate(task.getDeadline())%>
            </td>
            <td><%=task.getComment()%>
            </td>
            <td><%=task.getTaskStatus()%>
            </td>
            <td><%=task.getUser().getName() + " " + task.getUser().getSurname()%>
            </td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
