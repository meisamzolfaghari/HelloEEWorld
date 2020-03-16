package ir.javaland.projects.servlet;

import ir.javaland.projects.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowEmployee", urlPatterns = "/show-emp")
public class ShowEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee saved_emp = (Employee) req.getAttribute("saved_emp");

        if (saved_emp == null)
            resp.sendRedirect("error.html");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        writer.write("<html>" +
                "<body>" +
                "<h2>Data Saved Successfully...</h2>" +
                "<p>First Name: " + saved_emp.getFirstName() + "</p>" +
                "<p>Last Name: " + saved_emp.getLastName() + "</p>" +
                "<p>First Name: " + saved_emp.getPersonalNumber() + "</p>");
        req.getRequestDispatcher("/footer").include(req, resp);
        writer.write("</body>" +
                "</html>");
    }
}
