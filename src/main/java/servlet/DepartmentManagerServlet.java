package servlet;


import manager.TaskManager;
import manager.UserManager;
import model.StaffType;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/departmentManager")
public class DepartmentManagerServlet extends HttpServlet {

    private UserManager userManager=new UserManager();
    private TaskManager taskManager=new TaskManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");

        List<User> allUsers = userManager.getAllUsers();
        List<Task> allTasks = taskManager.getAllTasks();

        req.getSession().setAttribute("allUsers", allUsers);
        req.getSession().setAttribute("allTasks", allTasks);

        StaffType staffType = currentUser.getStaffType();
        switch (staffType){
            case ADMINISTRATIVE:
                resp.sendRedirect("/administrativeDepartmentManagerPage.jsp");
                break;
            case ACCOUNTING:
                resp.sendRedirect("/accountingDepartmentManagerPage.jsp");
                break;
            case HRM:
                resp.sendRedirect("/hrmDepartmentManagerPage.jsp");
                break;
            case IT:
                resp.sendRedirect("/itDepartmentManagerPage.jsp");
                break;
            default:
                resp.sendRedirect("/");
        }
    }
}
