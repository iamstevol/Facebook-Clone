package com.iamstevol.facebooktask.controller;

import com.iamstevol.facebooktask.dao.CommentDAO;
import com.iamstevol.facebooktask.dao.UserDAO;
import com.iamstevol.facebooktask.model.Comment;
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
import java.util.List;

@WebServlet(name = "CommentServlet", value = "/CommentServlet")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {

            //fetch data from post form
//            String postId = request.getAttribute("post_Id").toString();
                String post_Id = request.getParameter("post_Id");
            System.out.println("Post Id: " + post_Id);

            //get current user
            HttpSession httpSession = request.getSession();
            String userId = httpSession.getAttribute("user_Id").toString();
            User currentUser;
            try {
                currentUser = UserDAO.getUserById(userId);
            } catch (SQLException | CustomException e) {
                throw new RuntimeException(e);
            }
            System.out.println(currentUser);
//            String post_Id = CommentDAO.getPostId(postId);
//            System.out.println(post_Id);
            httpSession.setAttribute("post_Id", post_Id);
            //from the comment DOA
            List<Comment> comments = CommentDAO.getAllComment(post_Id);

            if(!comments.isEmpty()){
                out.println("comments retrieved successfully");
                httpSession.setAttribute("message", "successful");
                httpSession.setAttribute("user",currentUser);
                httpSession.setAttribute("comments",comments);
                httpSession.setAttribute("post_Id", post_Id);
            }else{
                out.print("404 not found");
                httpSession.setAttribute("user",currentUser);
                httpSession.setAttribute("message", "No comment associated with post");
            }

            response.sendRedirect("comment.jsp");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
