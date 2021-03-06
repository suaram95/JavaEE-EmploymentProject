package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/adminHome")
public class AdminHomeServlet extends HttpServlet {

    private UserManager userManager=new UserManager();
    private TaskManager taskManager=new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allAdminUsers = userManager.getAllUsers();
        List<Task> allAdminTasks = taskManager.getAllTasks();

        req.getSession().setAttribute("allAdminUsers",allAdminUsers);
        req.getSession().setAttribute("allAdminTasks",allAdminTasks);
        resp.sendRedirect("admin.jsp");
    }
}
