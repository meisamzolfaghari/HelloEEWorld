package ir.javaland.projects.servlet;

import ir.javaland.projects.model.Employee;
import ir.javaland.projects.model.User;
import ir.javaland.projects.repository.EmployeeRepos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register_emp")
public class RegisterServlet extends HttpServlet {

    @Override
    public void destroy() {
        EmployeeRepos.getInstance().deleteAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        User user = null;
//        Boolean foundCookie = false;
//        if (req.getCookies() != null)
//            for (Cookie cookie : req.getCookies()) {
//                if (cookie.getName().equals("sec_data")) {
//                    String val = cookie.getValue();
//                    if (LoginServlet.userMap.containsKey(val)) {
//                        user = LoginServlet.userMap.get(val).toString();
//                        foundCookie = true;
//                    }
//                }
//            }
//
//        if (!foundCookie)
//            resp.sendRedirect("error.html");

        HttpSession session = req.getSession(false);
        if (session != null)
            if ((user = (User) session.getAttribute("sec_data")) == null)
                resp.sendRedirect("error.html");

        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String personalNumber = req.getParameter("personal_number");

        if (personalNumber == null)
            personalNumber = "0";

        EmployeeRepos.getInstance().save(new Employee(firstName, lastName, Integer.parseInt(personalNumber)));

        writer.write("<html>" +
                "<body><h2>" + user +
                "</h2><h2>Data Saved Successfully...</h2>" +
                "<p>First Name: " + firstName + "</p>" +
                "<p>Last Name: " + lastName + "</p>" +
                "<p>First Name: " + personalNumber + "</p>" +
                "</body>" +
                "</html>");
    }
}
