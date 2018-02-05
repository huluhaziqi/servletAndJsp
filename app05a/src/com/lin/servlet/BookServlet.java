package com.lin.servlet;

import com.lin.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/books"})
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 234894L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("978-0980839616",
                "Java 7: A Beginner's Tutorial", 45.00);
        Book book2 = new Book("978-0980331608",
                "Struts 2 Design and Programming: A Tutorial",
                49.95);
        Book book3 = new Book("978-0975212820",
                "Dimensional Data Warehousing with MySQL: A "
                        + "Tutorial", 39.95);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        Map<String,String[]> bigCities = new HashMap<>();
        bigCities.put("Australia",new String[]{"sydney","melbourne","perth"});
        bigCities.put("New Zealand",new String[]{"auckland","christchurch","wellington"});
        bigCities.put("Indonesia",new String[]{"jakarta","surabaya","medan"});
        req.setAttribute("books", books);
        req.setAttribute("bigCities",bigCities);
        RequestDispatcher dispatcher = req.getRequestDispatcher("books.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
