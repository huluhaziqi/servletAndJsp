package app01b;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "FormServlet", urlPatterns = "/form")
public class FormServlet extends HttpServlet {

    private String TITLE = "OLD FORM";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>" + TITLE + "</title></head>");
        writer.println("<body><h1>" + TITLE + "</h1>");
        writer.print("<form method='post'>");
        writer.print("<table>");
        writer.print("<tr>");
        writer.print("<td>name :</td>");
        writer.print("<td><input name='name' /></td>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.print("<td> address: </td>");
        writer.print("<td><textarea name='address' cols='40' rows='5'></textarea></td>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.print("<td> country : </td>");
        writer.print("<td><select name='country'>");
        writer.print("<option>united states</option>");
        writer.print("<option>China</option>");
        writer.print("<option>Canada</option>");
        writer.print("</select></td>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.print("<td>Delivery Method:</td>");
        writer.print("<td><input type='radio' name = 'deliveryMethod' value='first class'/>first class");
        writer.print("<input type='radio' name = 'deliveryMethod' value='second class'/>second class</td>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.print("<td>Shipping Instructions:</td>");
        writer.print("<td><textarea name = 'shippingInstructions'  cols='40' rows='5'></textarea>");
        writer.print("</td>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.println("<td>&nbsp;</td>");
        writer.print("<td><textarea name = 'shippingInstructions'  cols='40' rows='5'></textarea>");
        writer.print("</td>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.print("<td>Please send me the latest product catalog:</td>");
        writer.print("<td><input type = 'checkbox' name='catalogRequest'/>");
        writer.print("</td>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.println("<td>&nbsp;</td>");
        writer.print("<td><input type = 'reset'/>");
        writer.print("<input type = 'submit'/>");
        writer.print("</td>");
        writer.print("</tr>");

        writer.print("</table>");
        writer.print("</form>");
        writer.print("</body>");
        writer.print("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>");
        printWriter.print("<head>");
        printWriter.print("<title>" + TITLE + "</title>");
        printWriter.print("</head>");
        printWriter.print("<body>");
        printWriter.print("<h1>");
        printWriter.print(TITLE);
        printWriter.print("</h1>");

        printWriter.print("<table>");

        printWriter.print("<tr>");
        printWriter.print("<td>name</td>");
        printWriter.print("<td>" + req.getParameter("name") + "</td>");
        printWriter.print("</tr>");

        printWriter.print("<tr>");
        printWriter.print("<td>address</td>");
        printWriter.print("<td>" + req.getParameter("address") + "</td>");
        printWriter.print("</tr>");

        printWriter.print("<tr>");
        printWriter.print("<td>country</td>");
        printWriter.print("<td>" + req.getParameter("country") + "</td>");
        printWriter.print("</tr>");

        printWriter.print("<tr>");
        printWriter.print("<td>shipping Instruction:</td>");
        printWriter.print("<td>");
        String[] shippingInstructions = req.getParameterValues("shippingInstructions");
        if (shippingInstructions != null) {
            for (String s : shippingInstructions) {
                printWriter.print(s + "<br/>");
            }
        }
        printWriter.print("</td>");
        printWriter.print("</tr>");

        printWriter.print("<tr>");
        printWriter.print("<td>Delivery Method:</td>");
        printWriter.print("<td>");
        String deliveryMethod = req.getParameter("deliveryMethod");
        if (deliveryMethod != null) {
            printWriter.print(deliveryMethod);
        }
        printWriter.print("</td>");
        printWriter.print("</tr>");

        printWriter.print("<tr>");
        printWriter.print("<td>catalog Request:</td>");
        printWriter.print("<td>");
        String catalogRequest = req.getParameter("catalogRequest");
        if (catalogRequest != null) {
            printWriter.print("yes");
        } else {
            printWriter.print("no");
        }
        printWriter.print("</td>");
        printWriter.print("</tr>");


        printWriter.print("</table>");
        printWriter.print("<div style='border:1px solid #ddd; margin-top:40px,font-size:90%'>");
        printWriter.print("debug info <br/>");
        Enumeration<String> p = req.getParameterNames();
        while (p.hasMoreElements()) {
            String name = p.nextElement();
            printWriter.print(name + " :");
            String[] paramValues = req.getParameterValues(name);
            for (String paramValue : paramValues) {
                printWriter.print(paramValue + "<br/>");
            }
        }
        printWriter.print("</div>");
        printWriter.print("</body>");
        printWriter.print("</html>");
    }
}
