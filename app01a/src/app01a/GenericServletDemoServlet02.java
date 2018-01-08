package app01a;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GenericServletDemoServlet02", urlPatterns = "/my03",
        initParams = {@WebInitParam(name = "admin", value = "xiaobin"),
                @WebInitParam(name = "email", value = "linxiaobin@126.com")})
public class GenericServletDemoServlet02 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        String admin = servletConfig.getInitParameter("admin");
        String email = servletConfig.getInitParameter("email");
        servletResponse.setContentType("text/html");
        PrintWriter printWriter = servletResponse.getWriter();
        printWriter.print("<html><head></head> <body>admin : " + admin +
                "<br/>" + "email : " + email+ " </body> </html>");
    }
}