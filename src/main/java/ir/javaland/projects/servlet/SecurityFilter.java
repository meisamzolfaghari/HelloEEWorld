package ir.javaland.projects.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", servletNames = {"RegisterServlet","ShowEmployee"})
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getSession(false) != null
                && request.getSession(false).getAttribute("sec_data") != null)
            chain.doFilter(req, resp);
        else
            ((HttpServletResponse) resp).sendRedirect("login.html");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
