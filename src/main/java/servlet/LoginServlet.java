package servlet;

import manager.UserManager;
import model.StaffType;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        StringBuilder loginErrorMsg = new StringBuilder();
        if (username == null || username.equals("")) {
            loginErrorMsg.append("Username field is required <br>");
        }
        if (password == null || password.equals("")) {
            loginErrorMsg.append("Password field is required <br>");
        }

        if (loginErrorMsg.toString().equals("")) {
            User currentUser = userManager.getUserByUsernameAndPassword(username, password);
            if (currentUser != null) {
                req.getSession().setAttribute("currentUser", currentUser);
                UserType userType = currentUser.getUserType();
                StaffType staffType = currentUser.getStaffType();
                switch (userType) {
                    case ADMIN:
                        resp.sendRedirect("/adminHome");
                        break;
                    case SECTION_MANAGER:
                        resp.sendRedirect("/departmentManager");
                        break;
                    case EMPLOYEE:
                        resp.sendRedirect("/departmentEmployee");
                        break;
                    default:
                        resp.sendRedirect("/index.jsp");
                }
            } else {
                loginErrorMsg.append("Invalid username or password!");
                req.getSession().setAttribute("loginErrorMsg", loginErrorMsg.toString());
                resp.sendRedirect("/index.jsp");
            }
        } else {
            req.getSession().setAttribute("loginErrorMsg", loginErrorMsg.toString());
            resp.sendRedirect("/index.jsp");
        }
    }
}
