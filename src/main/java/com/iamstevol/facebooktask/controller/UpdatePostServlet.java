package com.iamstevol.facebooktask.controller;

import com.iamstevol.facebooktask.dao.PostDAO;
import com.iamstevol.facebooktask.model.Post;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdatePostServlet", value = "/UpdatePostServlet")
public class UpdatePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

            HttpSession httpSession = request.getSession();

            //requests from the client
            String title = request.getParameter("post_title");
            String body = request.getParameter("post_body");
            String post_Id = request.getParameter("post_Id");

            Post post = new Post(title, body);

            //from post database
        try {
            if(PostDAO.updatePost(post_Id, post)){
                httpSession.setAttribute("message", "successful");
            }else{
                httpSession.setAttribute("message", "Error uploading data");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("home.jsp");
        }


}
