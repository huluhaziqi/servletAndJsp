package com.lin.app02a.hidden;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer", "/editCustomer", "/updateCustomer"})
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 348942L;

    private List<Customer> customers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uri = req.getRequestURI();
            if (uri.endsWith("/customer")) {
                method1(resp);
            } else if (uri.endsWith("/editCustomer")) {
                method2(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int customerId = 0;
            customerId = Integer.parseInt(req.getParameter("id"));
            Customer customer = getCustomer(customerId);
            if (customer != null) {
                customer.setName(req.getParameter("name"));
                customer.setCity(req.getParameter("city"));
            }
            method1(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setCity("beijing");
        customer1.setName("tongzhou");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setCity("shanghai");
        customer2.setName("minghang");
        customers.add(customer2);
    }

    private void method1(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.print("<html>" +
                "<head>" +
                "<title>Customers" +
                "</title>" +
                "</head>" +
                "<body>" +
                "<h2>Customers" +
                "</h2>");
        printWriter.print("<ul>");
        for (Customer customer : customers) {
            printWriter.print("<li>" + customer.getName() + "(" + customer.getCity() + ") (" +
                    "<a href = 'editCustomer?id=" + customer.getId() + "'>edit</a>");
        }
        printWriter.print("</ul>");
        printWriter.print("</body>");
        printWriter.print("</html>");
    }

    private Customer getCustomer(int id) {
        for (Customer customer : customers) {
            if (id == customer.getId()) {
                return customer;
            }
        }
        return null;
    }

    private void method2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = getCustomer(id);
        if (customer != null) {
            printWriter.print("<html>" +
                    "<head>" +
                    "<title> edit customer" +
                    "</title>" +
                    "</head>" +
                    "<body>" +
                    "<h2> edit customer" +
                    "</h2>" +
                    "<form method = 'post' action = 'updateCustomer'>" +
                    "<input type = 'hidden' name ='id' value = '" + customer.getId() + "'/>" +
                    "<table>" +
                    "<tr>" +
                    "<td>name:</td>" +
                    "<td>" +
                    "<input name='name' value='" + customer.getName().replaceAll("'", "&#39;") + "'/>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>city:</td>" +
                    "<td>" +
                    "<input name='city' value='" + customer.getCity().replaceAll("'", "&#39;") + "'/>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "<input type = 'submit' value = 'update'/>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</form>" +
                    "</body>" +
                    "</html>");
        } else {
            printWriter.print("not found customer");
        }
    }
}
