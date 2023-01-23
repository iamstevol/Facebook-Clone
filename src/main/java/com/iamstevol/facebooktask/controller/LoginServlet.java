package com.iamstevol.facebooktask.controller;

import com.iamstevol.facebooktask.dao.UserDAO;
import com.iamstevol.facebooktask.utility.CustomException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String user_Id;
        try {
            user_Id = UserDAO.getUserId(username, password);
        } catch (SQLException | CustomException e) {
            throw new RuntimeException(e);
        }

        boolean isSuccess;
        try {
            isSuccess = UserDAO.loginUser(username, password);
            if(isSuccess) {

                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("user_Id", user_Id);

                response.sendRedirect("home.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
