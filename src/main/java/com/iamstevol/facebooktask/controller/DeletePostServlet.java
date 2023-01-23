package com.iamstevol.facebooktask.controller;

import com.iamstevol.facebooktask.dao.PostDAO;
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

@WebServlet(name = "DeletePostServlet", value = "/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // post id from client
        String post_Id = request.getParameter("post_Id");

        //get current user in session
        HttpSession session = request.getSession();
        String user_Id = session.getAttribute("user_Id").toString();
        User user;
        try {
            user = UserDAO.getUserById(user_Id);
        } catch (SQLException | CustomException e) {
            throw new RuntimeException(e);
        }

        //comment DOA
        try {
            if (PostDAO.deletePost(user.getUser_Id(), post_Id)) {
                response.getWriter().write("Success deleting post");
            } else {
                response.getWriter().write("Failed do delete post or you don't have access to delete this post");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
