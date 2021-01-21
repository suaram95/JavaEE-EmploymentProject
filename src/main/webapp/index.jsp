<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%
    String loginErrorMsg="";
    if (request.getSession().getAttribute("loginErrorMsg")!=null){
        loginErrorMsg= (String) request.getSession().getAttribute("loginErrorMsg");
        request.getSession().removeAttribute("loginErrorMsg");
    }
%>
<b>Welcome to Employment Project!</b> <br>
<i>Please login</i><br>
<form action="/login" method="post">
    <p style="color: darkred"><%=loginErrorMsg%></p>
    Username: <input type="text" name="username"><br> <br>
    Password: <input type="password" name="password"><br><br>
    <input type="submit" value="Login">
</form>

</body>
</html>