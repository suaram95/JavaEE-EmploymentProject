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

@WebServlet(urlPatterns = "/departmentEmployee")
public class DepartmentEmployeeServlet extends HttpServlet {

    private TaskManager taskManager=new TaskManager();
    private UserManager userManager=new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");

        List<Task> taskByUser = taskManager.getTaskByUser(currentUser.getId());
        List<User> allUsers = userManager.getAllUsers();
        req.getSession().setAttribute("taskByUser",taskByUser);
        req.getSession().setAttribute("allUsers", allUsers);

        StaffType staffType = currentUser.getStaffType();
        switch (staffType){
            case IT:
                resp.sendRedirect("/itDepartmentEmployeePage.jsp");
                break;
            case HRM:
                resp.sendRedirect("/hrmDepartmentEmployeePage.jsp");
                break;
            case ACCOUNTING:
                resp.sendRedirect("/accountingDepartmentEmployeePage.jsp");
                break;
            case ADMINISTRATIVE:
                resp.sendRedirect("/administrativeDepartmentEmployeePage.jsp");
                break;
            default:
                resp.sendRedirect("/");
        }
    }
}
