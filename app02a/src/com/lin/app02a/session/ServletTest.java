package com.lin.app02a.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/ServletTest", urlPatterns = {"/products", "/productDetails", "/showCart", "/addToCart"})
public class ServletTest extends HttpServlet {

    private static final long serialVersionUID = 23489432L;

    private static final String CART_ATTRIBUTE = "cart";

    private List<Product> productList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uri = req.getRequestURI();
            if (uri.endsWith("/products")) {
                sendProducts(req, resp);
            } else if (uri.endsWith("/productDetails")) {
                sendProductDetails(req, resp);
            } else if (uri.endsWith("/showCart")) {
                showCart(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Product product = getProduct(id);
            HttpSession session = req.getSession();
            if (product != null && quantity > 0) {
                ShoppingItem shoppingItem = new ShoppingItem(product, quantity);
                List<ShoppingItem> list = (List<ShoppingItem>) session.getAttribute(CART_ATTRIBUTE);
                if (list == null) {
                    list = new ArrayList<>();
                    session.setAttribute(CART_ATTRIBUTE, list);
                }
                list.add(shoppingItem);
            }
            sendProducts(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        productList.add(new Product(1, "a", "wrrwwe", 239F));
        productList.add(new Product(2, "b", "rewe", 102F));
        productList.add(new Product(3, "c", "werew21", 340F));
        productList.add(new Product(4, "d", "1221", 2323f));
        productList.add(new Product(5, "e", "5532343ere", 343f));
    }

    private Product getProduct(int id) {
        if (productList == null) {
            return null;
        } else {
            for (Product product : productList) {
                if (product.getId() == id) {
                    return product;
                }
            }
        }
        return null;
    }

    private void sendProducts(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType("text/html");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.print("<html>" +
                "<head>" +
                "<title>sendProducts" +
                "</title>" +
                "</head>" +
                "<body>");
        for (Product product : productList) {
            printWriter.print("<li>" + product.getName() + "(" + product.getDescription() + ")"
                    + "<a href = 'productDetails?id=" + product.getId() + "' > details</a>");
        }
        printWriter.print("<a href = 'showCart'>view cart</a>");
        printWriter.print("</body>");
        printWriter.print("</html>");
    }

    private void sendProductDetails(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            int id = Integer.parseInt(httpServletRequest.getParameter("id"));
            Product product = getProduct(id);
            httpServletResponse.setContentType("text/html");
            PrintWriter printWriter = httpServletResponse.getWriter();
            if (product != null) {
                printWriter.print("<html>");
                printWriter.print("<head>");
                printWriter.print("<title>send product details");
                printWriter.print("</title>");
                printWriter.print("</head>");
                printWriter.print("<body>");
                printWriter.print("<h2>product details</h2>");
                printWriter.print("<form method = 'post' action='addToCart'>");
                printWriter.print("<input type = 'hidden' name = 'id' value = " + id + ">");
                printWriter.print("<table>");
                printWriter.print("<tr><td>name: </td><td>" + product.getName() + "</td></tr>");
                printWriter.print("<tr><td>description: </td><td>" + product.getDescription() + "</td></tr>");
                printWriter.print("<br/>");
                printWriter.print("<tr><input name='quantity'/></tr>");
                printWriter.print("<tr><input type='submit' value='buy'/></tr>");
                printWriter.print("<tr><a href='products'>product list</a></tr>");
                printWriter.print("</table>");
                printWriter.print("</form>");
                printWriter.print("</body>");
                printWriter.print("</html>");
            } else {
                printWriter.print("no product found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCart(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            HttpSession httpSession = httpServletRequest.getSession();
            List<ShoppingItem> list = (List<ShoppingItem>) httpSession.getAttribute(CART_ATTRIBUTE);
            httpServletResponse.setContentType("text/html");
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.print("<html>");
            printWriter.print("<head>");
            printWriter.print("<title>Shopping Cart");
            printWriter.print("</title>");
            printWriter.print("</head>");
            printWriter.print("<body>" +
                    "<a href='products'>product list</a>");
            if (list != null) {
                double total = 0.0D;
                printWriter.print("<table>");
                for (ShoppingItem shoppingItem : list) {
                    printWriter.print("<tr><td>" + shoppingItem.getProduct().getName() + "</td></tr>");
                    printWriter.print("<tr><td>" + shoppingItem.getProduct().getDescription() + "</td></tr>");
                    printWriter.print("<tr><td>" + shoppingItem.getProduct().getId() + "</td></tr>");
                    printWriter.print("<tr><td>" + shoppingItem.getProduct().getPrice() + "</td></tr>");
                    printWriter.print("<tr><td>" + shoppingItem.getQuantity() + "</td></tr>");
                    printWriter.print("<tr><td>" + shoppingItem.getQuantity() * shoppingItem.getProduct().getPrice() + "</td></tr>");
                    total += shoppingItem.getQuantity() * shoppingItem.getProduct().getPrice();
                }
                printWriter.print("<tr><td> total :" + total + "</td></tr>");
                printWriter.print("</table>");
            }
            printWriter.print("</body>");
            printWriter.print("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
