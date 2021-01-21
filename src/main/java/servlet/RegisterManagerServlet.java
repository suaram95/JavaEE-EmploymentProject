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

@WebServlet(urlPatterns = "/registerManager")
public class RegisterManagerServlet extends HttpServlet {

    private UserManager userManager=new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String workExperience = req.getParameter("workExperience");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String staffType = req.getParameter("staffType");
        String userType = req.getParameter("userType");

        StringBuilder regManagerMsg = new StringBuilder();
        if (name == null || name.equals("")) {
            regManagerMsg.append("Name field is required! <br>");
        }
        if (surname == null || surname.equals("")) {
            regManagerMsg.append("Surname field is required! <br>");
        }
        if (username == null || username.equals("")) {
            regManagerMsg.append("Username field is required! <br>");
        }
        if (password == null || password.equals("")) {
            regManagerMsg.append("Password field is required! <br>");
        }

        if (regManagerMsg.toString().equals("")){
            try {
                userManager.addUser(User.builder()
                        .name(name)
                        .surname(surname)
                        .age(Integer.parseInt(age))
                        .gender(Gender.valueOf(gender))
                        .workExperience(Integer.parseInt(workExperience))
                        .username(username)
                        .password(password)
                        .staffType(StaffType.valueOf(staffType))
                        .userType(UserType.valueOf(userType))
                        .build());
                req.getSession().setAttribute("regManagerMsg",regManagerMsg.toString());
                resp.sendRedirect("/adminHome");
            } catch (DuplicateUserException e) {
                regManagerMsg.append("Manager with username: "+username+" already exists");
                req.getSession().setAttribute("regManagerMsg",regManagerMsg.toString());
                resp.sendRedirect("/adminHome");
            }
        } else {
            resp.sendRedirect("/admin.jsp");
        }
    }
}
