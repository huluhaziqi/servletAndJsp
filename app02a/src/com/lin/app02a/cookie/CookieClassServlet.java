package com.lin.app02a.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CookieClassServlet", urlPatterns = {"/cookieClass"})
public class CookieClassServlet extends HttpServlet {
    private static final long serialVersionUID = 43289243L;

    private String[] methods = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(10);
        Cookie[] cookies = req.getCookies();
        Cookie titleFontSize = null;
        Cookie maxRecord = null;
        Cookie titleStyle = null;
        Cookie titleWeight = null;
        Cookie titleLocation = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("titleFontSize")) {
                    titleFontSize = cookie;
                }
                if (cookie.getName().equals("maxRecord")) {
                    maxRecord = cookie;
                }
                if (cookie.getName().equals("titleStyle")) {
                    titleStyle = cookie;
                }
                if (cookie.getName().equals("titleWeight")) {
                    titleWeight = cookie;
                }
                if (cookie.getName().equals("titleLocation")) {
                    titleLocation = cookie;
                }
            }
        }
        int maxR = 5;
        if (maxRecord != null) {
            maxR = Integer.parseInt(maxRecord.getValue());
        }
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>" +
                "<head>" +
                "<title>" +
                "Cookie class" +
                "</title>" +
                "<body>" +
                "</body>" +
                PreferenceServlet.MENU +
                "<br/>" + titleFontSize.getValue() +
                "<br/>" + maxRecord.getValue() +
                "<br/>" + titleStyle.getValue() +
                "<br/>" + titleWeight.getValue() +
                "<br/>" + session.getId() +
                "<br/>" + session.getMaxInactiveInterval() +
                "<div>here are some method </div>" +
                "<ul>");
        for (int i = 0; i < maxR; i++) {
            printWriter.print("<li>" + methods[i] + "</li>");
        }
        printWriter.print(
                "</ul>" +
                        "</head>" +
                        "</html>");
        System.out.println("session id :" + session.getId());
        System.out.println("session maxInactiveInterval :" + session.getMaxInactiveInterval());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
