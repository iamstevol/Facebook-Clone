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

@WebServlet(name = "EditCommentServlet", value = "/EditCommentServlet")
public class EditCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            //post id from client
            HttpSession httpSession = request.getSession();
            String post_Id = httpSession.getAttribute("post_Id").toString();
            System.out.println(post_Id);
            String comment = request.getParameter("editedComment");
            System.out.println(comment);

            //get current user in session

            String userId = httpSession.getAttribute("user_Id").toString();
            User user;
            try {
                user = UserDAO.getUserById(userId);
            } catch (SQLException | CustomException e) {
                throw new RuntimeException(e);
            }
//            String post_Id = CommentDAO.getPostId(postId);
            //from comment DOA
            if (CommentDAO.editComment(userId, post_Id, comment)) {
                response.getWriter().write("Success editing post");
            } else {
                response.getWriter().write("Error editing post or you don't have access to delete this comment");
            }
        }
    }
}
