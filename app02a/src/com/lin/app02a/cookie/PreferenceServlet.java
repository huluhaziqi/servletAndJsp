package com.lin.app02a.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "PreferenceServlet", urlPatterns = {"/preference"})
public class PreferenceServlet extends HttpServlet {

    private static final long serialVersionUID = 32344L;
    private static final String MENU =
            "<div style='background:#e8e8e8;padding:15px'>" +
                    "<a href= 'cookieClass'>Cookie Class</a>&nbsp;&nbsp;" +
                    "<a href= 'cookieInfo'>Cookie Info</a>&nbsp;&nbsp;" +
                    "<a href= 'preference'>Preference</a> </div>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>" +
                "<head>" +
                "<title>Preference</title>" +
                "</head>" +
                "<body>" +
                MENU +
                "<form method = 'post'>" +
                "<table>" +
                "<tr>" +
                "<td>Title font size : </td>" +
                "<td><select name='titleFontSize'>" +
                "<option>large</option>" +
                "<option>x-large</option>" +
                "<option>xx-large</option>" +
                "</select></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Title style and weight : </td>" +
                "<td><select name='titleStyleAndWeight' multiple>" +
                "<option>italic</option>" +
                "<option>bold</option>" +
                "<option>location</option>" +
                "</select></td>" +
                "</tr>" +
                "<tr>" +
                "<td>Max Record : </td>" +
                "<td><select name='maxRecord'>" +
                "<option>5</option>" +
                "<option>10</option>" +
                "<option>15</option>" +
                "</select></td>" +
                "</tr>" +
                "<tr>" +
                "<td rowspan='2'><input type='submit' value='Set'>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</form>" +
                "</body>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titleFontSize = req.getParameter("titleFontSize");
        String maxRecord = req.getParameter("maxRecord");
        String[] titleStyleAndWeight = req.getParameterValues("titleStyleAndWeight");
        resp.addCookie(new Cookie("titleFontSize", titleFontSize));
        resp.addCookie(new Cookie("maxRecord", maxRecord));

        Cookie cookie = new Cookie("titleStyle", "");
        resp.addCookie(cookie);
        cookie = new Cookie("titleWeight", "");
        resp.addCookie(cookie);
        cookie = new Cookie("titleLocation", "");
        resp.addCookie(cookie);
        for (String s : titleStyleAndWeight) {
            if (s.equals("bold")) {
                resp.addCookie(new Cookie("titleStyle", "bold"));
            } else if (s.equals("italic")) {
                resp.addCookie(new Cookie("titleWeight", "weight"));
            } else if (s.equals("location")) {
                resp.addCookie(new Cookie("titleLocation", "location"));
            }
        }
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>" +
                "<title>preference" +
                "</title>" +
                "<body>" +
                MENU +
                "<br/>titleFontSize :" + titleFontSize +
                "<br/>maxRecord :" + maxRecord);
        for (String s : titleStyleAndWeight) {
            printWriter.print("<li> " + s + "</li>");
        }
        printWriter.print(
                "</body></html>");
    }
}
