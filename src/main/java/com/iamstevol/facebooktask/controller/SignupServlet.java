package com.iamstevol.facebooktask.controller;

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

@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignupServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(firstName, lastName, email, password);
        UserDAO userDAO = new UserDAO();
//        try {
//            userDAO.registerUser(user);
//            System.out.println("Success");
//        } catch (SQLException | CustomException e) {
//            throw new RuntimeException(e);
//        }

        HttpSession session = req.getSession();
        try {
            if (!userDAO.registerUser(user)) {
                String errorMessage = "failed";
                session.setAttribute("Registration Status", errorMessage);
            }else{
                session.setAttribute("Registration Status", "successfully registered! Login to continue.");
                System.out.println("Success");
            }
        } catch (SQLException | CustomException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("hellojsp.jsp");
    }

}
