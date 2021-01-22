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

@WebServlet(urlPatterns = "/itDepManager")
public class ITDepartmentManagerServlet extends HttpServlet {

    private UserManager userManager=new UserManager();
    private TaskManager taskManager=new TaskManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> allUsers = userManager.getAllUsers();
        List<Task> allTasks = taskManager.getAllTasks();
        req.setAttribute("allITUsers", allUsers);
        req.setAttribute("allITTasks", allTasks);
        req.getRequestDispatcher("/itDepartmentManagerPage.jsp").forward(req,resp);
    }
}
