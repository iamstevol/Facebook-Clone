package com.iamstevol.facebooktask.controller;

import com.iamstevol.facebooktask.dao.CommentDAO;
import com.iamstevol.facebooktask.dao.UserDAO;
import com.iamstevol.facebooktask.model.User;
import com.iamstevol.facebooktask.utility.CustomException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PostCommentServlet", value = "/PostCommentServlet")
public class PostCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //fetch data from post form
        String comment = request.getParameter("comment");
        String post_Id = request.getParameter("post_Id");

        System.out.println(post_Id);

        //get current user
        HttpSession httpSession = request.getSession();
        String userId = httpSession.getAttribute("user_Id").toString();
        User currentUser;
        try {
            currentUser = UserDAO.getUserById(userId);
        } catch (SQLException | CustomException e) {
            throw new RuntimeException(e);
        }

        //from the comment DOA

        if(CommentDAO.createComment(userId,post_Id,comment)){
            httpSession.setAttribute("message", "successful");
            httpSession.setAttribute("user",currentUser);
        }else{
            httpSession.setAttribute("user",currentUser);
            httpSession.setAttribute("message", "Error posting comment");
        }

        response.sendRedirect("home.jsp");
    }
}
