package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.StaffType;
import model.Task;
import model.TaskStatus;
import model.User;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String deadline = req.getParameter("deadline");
        String comment = req.getParameter("comment");
        String userId = req.getParameter("userId");

        StringBuilder addTaskMsg = new StringBuilder();
        if (name == null || name.equals("")) {
            addTaskMsg.append("Name field is required <br>");
        }
        if (description == null || description.equals("")) {
            addTaskMsg.append("Description field is required <br>");
        }
        if (deadline == null || deadline.equals("")) {
            addTaskMsg.append("Deadline field is required <br>");
        }

        if (addTaskMsg.toString().equals("")) {
            Task task=Task.builder()
                    .name(name)
                    .description(description)
                    .createdDate(new Date())
                    .deadline(DateUtil.getDateFromString(deadline))
                    .comment(comment)
                    .taskStatus(TaskStatus.NEW)
                    .user(userManager.getUserById(Integer.parseInt(userId)))
                    .build();
            taskManager.addTask(task);
            addTaskMsg.append("Task was successfully added!");
            req.getSession().setAttribute("addTaskMsg", addTaskMsg.toString());
            User currentUser = (User) req.getSession().getAttribute("currentUser");
            if (currentUser.getStaffType() == StaffType.ADMINISTRATIVE) {
                resp.sendRedirect("/adminDepManager");
            } else if (currentUser.getStaffType() == StaffType.ACCOUNTING) {
                resp.sendRedirect("/accountDepManager");
            } else if (currentUser.getStaffType() == StaffType.HRM) {
                resp.sendRedirect("/hrmDepManager");
            } else if (currentUser.getStaffType() == StaffType.IT) {
                resp.sendRedirect("/itDepManager");
            } else {
                resp.sendRedirect("/");
            }
        } else {
            addTaskMsg.append("Something went wrong!");
            req.getSession().setAttribute("addTaskMsg", addTaskMsg.toString());
        }
    }
}
