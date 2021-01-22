<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page import="util.DateUtil" %>
<%@ page import="model.StaffType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HRM Department</title>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    List<Task> taskByUser = (List<Task>) request.getSession().getAttribute("taskByUser");
    List<User> allUsers = (List<User>) request.getSession().getAttribute("allUsers");
%>
<a href="/logout">Logout</a><br>
<b>Welcome <%=currentUser.getName() + " " + currentUser.getSurname() + " !"%>
</b><br>
<a href="/editEmployeeData.jsp">Edit Data</a>

<div style="border: 1px black">
    <b>My Tasks</b><br>   <a href="/departmentEmployee">Refresh</a>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Description</td>
            <td>Created date</td>
            <td>Deadline</td>
            <td>Comments</td>
            <td>Status</td>
            <td>Action</td>
        </tr>
        <% for (Task task : taskByUser) {%>
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
            <td>
                <form action="/changeTaskStatus" method="post">
                    <input type="hidden" name="taskId" value="<%=task.getId()%>">
                    <select name="status" id="">
                        <option value="NEW">NEW</option>
                        <option value="DOING">DOING</option>
                        <option value="FINISHED">FINISHED</option>
                    </select>
                    <input type="submit" value="Change">
                </form>
            </td>
        </tr>

        <%}%>
    </table>
</div>
<br>
<div style="border: 1px black">
    <b>Department Employees</b><br>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Age</td>
            <td>Gender</td>
            <td>Phone Number</td>
            <td>Employee</td>
        </tr>
        <% for (User user : allUsers) {%>
        <%if (user.getStaffType() == StaffType.HRM) {%>
        <tr>
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
            <td><%=user.getUserType()%>
            </td>
        </tr>
        <%}%>
        <%}%>
    </table>

</div>
<br>
<div style="border: 1px black">
    <b>Other Department Employees</b><br>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Age</td>
            <td>Gender</td>
            <td>Phone Number</td>
            <td>Employee</td>
        </tr>
        <% for (User user : allUsers) {%>
        <%if (user.getStaffType()!=StaffType.HRM){%>
        <tr>
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
            <td><%=user.getUserType()%>
            </td>
        </tr>
        <%}%>
        <%}%>
    </table>
</div>
</body>
</html>
