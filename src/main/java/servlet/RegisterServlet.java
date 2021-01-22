package servlet;

import exception.DuplicateUserException;
import manager.UserManager;
import model.Gender;
import model.StaffType;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager=new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String phoneNumber = req.getParameter("phoneNumber");
        String workExperience = req.getParameter("workExperience");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String staffType = req.getParameter("staffType");
        String userType = req.getParameter("userType");

        StringBuilder registerMsg = new StringBuilder();
        if (name == null || name.equals("")) {
            registerMsg.append("Name field is required! <br>");
        }
        if (surname == null || surname.equals("")) {
            registerMsg.append("Surname field is required! <br>");
        }
        if (phoneNumber == null || phoneNumber.equals("")) {
            registerMsg.append("Phone Number field is required! <br>");
        }
        if (username == null || username.equals("")) {
            registerMsg.append("Username field is required! <br>");
        }
        if (password == null || password.equals("")) {
            registerMsg.append("Password field is required! <br>");
        }

        if (registerMsg.toString().equals("")){
            try {
                userManager.addUser(User.builder()
                        .name(name)
                        .surname(surname)
                        .age(Integer.parseInt(age))
                        .gender(Gender.valueOf(gender))
                        .phoneNumber(phoneNumber)
                        .workExperience(Integer.parseInt(workExperience))
                        .username(username)
                        .password(password)
                        .staffType(StaffType.valueOf(staffType))
                        .userType(UserType.valueOf(userType))
                        .build());
                req.getSession().setAttribute("registerMsg",registerMsg.toString());

                User currentUser = (User) req.getSession().getAttribute("currentUser");
                StaffType currentUserStaffType = currentUser.getStaffType();
                if (currentUserStaffType==StaffType.ADMINISTRATIVE){
                    resp.sendRedirect("/adminDepManager");
                } else if (currentUserStaffType==StaffType.ACCOUNTING){
                    resp.sendRedirect("/accountDepManager");
                } else if (currentUserStaffType==StaffType.HRM){
                    resp.sendRedirect("/hrmDepManager");
                } else if (currentUserStaffType==StaffType.IT){
                    resp.sendRedirect("/itDepManager");
                } else if (currentUser.getUserType()==UserType.ADMIN){
                    resp.sendRedirect("/adminHome");
                } else {
                    resp.sendRedirect("/");
                }
            } catch (DuplicateUserException e) {
                registerMsg.append("Manager with username: "+username+" already exists");
                req.getSession().setAttribute("registerMsg",registerMsg.toString());
                resp.sendRedirect("/adminHome");
            }
        } else {
            resp.sendRedirect("/admin.jsp");
        }
    }
}
