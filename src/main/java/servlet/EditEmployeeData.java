package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/editEmployeeData")
public class EditEmployeeData extends HttpServlet {

    private UserManager userManager=new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phoneNumber = req.getParameter("phoneNumber");
        String workExperience = req.getParameter("workExperience");
        String password = req.getParameter("password");

        StringBuilder editDataMsg=new StringBuilder();

        if (name==null||name.equals("")){
            editDataMsg.append("Name field is required!  <br>");
        }
        if (surname==null||surname.equals("")){
            editDataMsg.append("Surname field is required! <br>");
        }
        if (phoneNumber==null||phoneNumber.equals("")){
            editDataMsg.append("Phone Number field is required! <br>");
        }
        if (workExperience==null||workExperience.equals("")){
            editDataMsg.append("Work Experience field is required! <br>");
        }
        if (password==null||password.equals("")){
            editDataMsg.append("Password field is required! <br>");
        }

        if (editDataMsg.toString().equals("")){
            User currentUser = (User) req.getSession().getAttribute("currentUser");
            if (currentUser!=null){
                currentUser.setName(name);
                currentUser.setSurname(surname);
                currentUser.setPhoneNumber(phoneNumber);
                currentUser.setWorkExperience(Integer.parseInt(workExperience));
                currentUser.setPassword(password);

                userManager.updateUserData(currentUser.getId(), currentUser);
                editDataMsg.append("Data was edited");
                req.getSession().setAttribute("editDataMsg", editDataMsg.toString());
                resp.sendRedirect("editEmployeeData.jsp");
            } else {
                editDataMsg.append("Something went wrong");
                resp.sendRedirect("/");
            }
        } else {
            req.getSession().setAttribute("editDataMsg", editDataMsg.toString());
            resp.sendRedirect("editEmployeeData.jsp");
        }
    }
}
