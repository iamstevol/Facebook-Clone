package com.iamstevol.facebooktask.utility;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "nameservlet", value = "/name_servlet", initParams = {
        @WebInitParam(name = "website_name", value = "Devlon.com"),
})
public class NameServlet extends HttpServlet {
    private String name = "Olukunle";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String message = (String) request.getAttribute("message");
//        HttpSession session = request.getSession(); // Using Session

        Cookie[] cookies = request.getCookies();
        ServletContext context = getServletContext();
        String message = null;
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("message")) {
                message = cookie.getValue();
            }
        }

//        String message = (String) session.getAttribute("message");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>" + getInitParameter("website_name") + "</h1>");
        writer.println("<h2>" + message + " " + name + "</h2>");
        writer.println("<p>" + "The name of the site owner is " + context.getInitParameter("site_owner") + "</p>");
        writer.println("</html></body>");
    }
}
