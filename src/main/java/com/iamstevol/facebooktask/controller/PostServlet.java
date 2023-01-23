package com.iamstevol.facebooktask.controller;

import com.iamstevol.facebooktask.dao.PostDAO;
import com.iamstevol.facebooktask.dao.UserDAO;
import com.iamstevol.facebooktask.model.Post;
import com.iamstevol.facebooktask.model.User;
import com.iamstevol.facebooktask.utility.CustomException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;


@WebServlet(name = "PostServlet", value = "/PostServlet")
@MultipartConfig
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            HttpSession httpSession = request.getSession();

            //fetch data from post form
            Part part = request.getPart("file");
            String imageName = part.getSubmittedFileName();
            String title = request.getParameter("title");
            String body = request.getParameter("body");

            //get current user from session
            String userId = httpSession.getAttribute("user_Id").toString();
        User currentUser;
        try {
            currentUser = UserDAO.getUserById(userId);
        } catch (SQLException | CustomException e) {
            throw new RuntimeException(e);
        }
//            String userId = currentUser.getUser_Id();

//            check if image name not empty
            if(imageName.equals("")){
                httpSession.setAttribute("message", "Enter a picture");
                response.sendRedirect("home.jsp");
                return;
            }
            //path to store image
            String path = "/Users/mac/Downloads/FacebookTask/src/main/webapp/image"+File.separator+imageName;

            InputStream in = part.getInputStream();

            boolean success = uploadFile(in, path);
            if(success) {
                Post post = new Post(title, body, imageName);
                try {
                    if(PostDAO.createPost(userId, post)) {
                        httpSession.setAttribute("message", "File uploaded successfully");
                        httpSession.setAttribute("post_Id", post.getPost_Id());
                        System.out.println(post.getPost_Id());
                    } else {
                        httpSession.setAttribute("message", "Error uploading image to database");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(imageName.equals("")) {
                Post post = new Post(title, body);
                try {
                    if(PostDAO.createPost(userId, post)) {
                        httpSession.setAttribute("message", "File uploaded successfully");
                    }else{
                        httpSession.setAttribute("message", "Error uploading image to database");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                httpSession.setAttribute("message", "error uploading file");
            }
            response.sendRedirect("home.jsp");
    }


    public boolean uploadFile(InputStream in, String path){
        boolean test = false;

        try{
            byte[] byt = new byte[in.available()];
            in.read(byt);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(byt);
            fileOutputStream.flush();
            fileOutputStream.close();
            test = true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return test;
    }
}

