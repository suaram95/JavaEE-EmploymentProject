<%@ page import="java.util.List" %>
<%@ page import="util.DateUtil" %>
<%@ page import="model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMINISTRATIVE DEPARTMENT</title>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    List<User> userList = (List<User>) request.getAttribute("allAdminUsers");
    List<Task> taskList = (List<Task>) request.getAttribute("allAdminTasks");

    String addTaskMsg = "";
    if (request.getSession().getAttribute("addTaskMsg") != null) {
        addTaskMsg = (String) request.getSession().getAttribute("addTaskMsg");
        request.getSession().removeAttribute("addTaskMsg");
    }

    String registerMsg = "";
    if (request.getSession().getAttribute("registerMsg") != null) {
        registerMsg = (String) request.getSession().getAttribute("registerMsg");
        request.getSession().removeAttribute("registerMsg");
    }
%>
<a href="/logout">Logout</a><br>
<b>Welcome <%=currentUser.getName() + " " + currentUser.getSurname() + " !"%>
</b><br>
<div style="border: 1px black solid; width: 45%">
    <b>Register new Employee</b><br>
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
    </select><br><br>
        User Type: <select name="userType">
        <option value="EMPLOYEE">EMPLOYEE</option>
    </select><br><br>
        <input type="submit" value="Register">
    </form>
</div>
<br>
<div style="border: 1px black solid; width: 40%">
    <p><%=addTaskMsg%>
    </p>
    <i><b>Add task:</b></i><br>
    <form action="/addTask" method="post">
        Name: <input type="text" name="name"><br>
        Description: <input type="text" name="description"><br>
        Deadline: <input type="date" name="deadline"><br>
        Comment: <input type="text" name="comment"><br>
        User: <select name="userId">
        <% for (User user : userList) {%>
        <%if (user.getUserType() == UserType.EMPLOYEE && user.getStaffType() == StaffType.ADMINISTRATIVE) {%>
        <option value="<%=user.getId()%>"><%=user.getName() + " " + user.getSurname()%>
        </option>
        <%}%>
        <%}%>
    </select><br>
        <input type="submit" value="ADD">
    </form>
</div>
<br>

<div style="border: 1px black solid">
    <i><b>Tasks of Employees:</b></i><br>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Description</td>
            <td>Created date</td>
            <td>Deadline</td>
            <td>Comments</td>
            <td>Status</td>
            <td>Employee</td>
            <td>Action</td>
        </tr>
        <% for (Task task : taskList) {%>
        <% if (task.getUser().getStaffType() == StaffType.ADMINISTRATIVE) {%>
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
            <td><%if (task.getTaskStatus() == TaskStatus.FINISHED) {%>
                <a href="/deleteTask?id=<%=task.getId()%>">Delete</a></td>
            <%}%>
        </tr>
        <%}%>
        <%}%>
    </table>
</div>
<br>

<div style="border: 1px black solid">
    <i><b>Administrative Department Employees:</b></i><br>
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
            <th>Staff</th>
            <th>Employee</th>
        </tr>
        <% for (User user : userList) {%>
        <%if (user.getStaffType() == StaffType.ADMINISTRATIVE) {%>
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
            <td><%=user.getName() + " " + user.getSurname()%>
            </td>
        </tr>
        <%}%>
        <%}%>
    </table>
</div>
<br>
<div style="border: 1px black solid">
    <i><b>Office All Employees:</b></i><br>
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
            <th>Staff</th>
            <th>Employee</th>
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
            <td><%=user.getPhoneNumber()%>
            </td>
            <td><%=user.getWorkExperience()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getStaffType()%>
            </td>
            <td><%=user.getName() + " " + user.getSurname()%>
            </td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>