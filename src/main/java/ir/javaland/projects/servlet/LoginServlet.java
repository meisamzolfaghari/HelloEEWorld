package ir.javaland.projects.servlet;

import ir.javaland.projects.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

//    static Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (firstName == null || lastName == null) {
            firstName = "";
            lastName = "";
        }

        if (username == null || username.trim().equals("")
                || password == null || password.trim().equals("")) {
            resp.sendRedirect("error.html");
        } else {
            User user = new User(firstName, lastName, username, password);
//            String randomVal = UUID.randomUUID().toString();
//            userMap.put(randomVal, user);
//
//            Cookie cookie =
//                    new Cookie("sec_data", randomVal);
//
//            cookie.setMaxAge(-1);
//            resp.addCookie(cookie);
//            ----------------------------------
            HttpSession session = req.getSession(true);
            session.setAttribute("sec_data", user);
            session.setMaxInactiveInterval(30);
//            session.invalidate();
            resp.sendRedirect("register.html");
        }
    }
}
