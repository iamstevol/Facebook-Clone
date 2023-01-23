package com.iamstevol.facebooktask.controller;

import com.iamstevol.facebooktask.dao.LikeDAO;
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

@WebServlet(name = "LikeServlet", value = "/LikeServlet")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //fetch data from post form client
            int action = Integer.parseInt(request.getParameter("action"));
            String post_Id = request.getParameter("post_Id");

            //get current user
            HttpSession httpSession = request.getSession();
            String userId = httpSession.getAttribute("user_Id").toString();
            User user;
            try {
                user = UserDAO.getUserById(userId);
            } catch (SQLException | CustomException e) {
            throw new RuntimeException(e);
            }

            //from like DOA
            if(LikeDAO.likePost(user.getUser_Id(), post_Id, action)){
                response.getWriter().write("Success liking/disliking post");
            }else{
                response.getWriter().write("Failed to liking post");
            }
    }
}
