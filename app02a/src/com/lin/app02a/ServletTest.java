package com.lin.app02a;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "servletTest", urlPatterns = {"/servletTest"})
public class ServletTest extends HttpServlet {

    private static final long serialVersionUID = 42738942443L;

    private List<String> londonAttractions;

    private List<String> parisAttractions;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        if(city == null){
            showMainPage(req,resp);
        }else {
            if(city.equals("london") || city.equals("paris")){
                showAttractions(req,resp,city);
            }
        }
    }

    @Override
    public void init() throws ServletException {
        londonAttractions = Arrays.asList("bucking", "london eye", "national muesum", "ddfd", "dfdsf", "fdewere");
        parisAttractions = Arrays.asList("erwwer", "wrwq eye", "ewewwr muesum", "erwrewr", "wrqqw", "qwwewqew");
    }

    private void showMainPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();
            printWriter.print("<html>" +
                    "<head>" +
                    "<title>top 10 tourist attractions" +
                    "</title>" +
                    "</head>" +
                    "<body> " +
                    "please select a city:" +
                    "<br/> <a href = '?city=london'>london</a> " +
                    "<br/> <a href = '?city=paris'>paris</a> " +
                    "</body>" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAttractions(HttpServletRequest req, HttpServletResponse res, String city) {
        try {
            int page = 1;
            String pageParam = req.getParameter("page");
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
                if (page > 2) {
                    page = 1;
                }
            }

            List<String> attractions = null;
            if (city.equals("london")) {
                attractions = londonAttractions;
            } else if (city.equals("paris")) {
                attractions = parisAttractions;
            }

            res.setContentType("text/html");
            PrintWriter printWriter = res.getWriter();
            printWriter.print("<html>" +
                    "<head>" +
                    "<title>top 10 tourist attractions" +
                    "</title>" +
                    "</head><body>");
            int start = page * 5 - 5;
            for (int i = start; i < start + 5; i++) {
                printWriter.print(attractions.get(i) + "<br/>");
            }
            printWriter.println("<hr style = 'color:blue'/> " +
                    "<a href='?city=" + city + "&page=1'> page 1 </a>");
            printWriter.println("<hr style = 'color:blue'/> " +
                    "<a href='?city=" + city + "&page=2'> page 2 </a>");
            printWriter.println("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
