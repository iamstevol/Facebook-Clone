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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "DeleteCommentServlet", value = "/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            //post_Id from client

            //get current in session
            HttpSession httpSession = request.getSession();
            String post_Id = httpSession.getAttribute("post_Id").toString();
            String userId = httpSession.getAttribute("user_Id").toString();
            User user;
            try {
                user = UserDAO.getUserById(userId);
            } catch (SQLException | CustomException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Post_Id in Delete comment is " + post_Id);
            //comment DOA

            if (CommentDAO.deleteComment(post_Id, userId)) {
                response.getWriter().write("Success deleting comment");
            } else {
                httpSession.setAttribute("message", "error deleting comment or you don't have access to delete this comment");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
