package com.iamstevol.facebooktask.dao;

import com.iamstevol.facebooktask.connection.DBConnection;
import com.iamstevol.facebooktask.model.User;
import com.iamstevol.facebooktask.utility.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {
    static Connection dbConnection = DBConnection.getConnection();

    public static ResultSet getAllUser() throws SQLException {
        String query = "select * from user";
        Statement statement = dbConnection.createStatement();
        return statement.executeQuery(query);
    }
    public static List<String> getAllUserEmail() throws SQLException {
        ResultSet allUser = getAllUser();
        List<String> userEmail = new ArrayList<>();
        while (allUser.next()) {
            userEmail.add(allUser.getString("user_email"));
        }
        return userEmail;
    }
    public boolean registerUser(User user) throws SQLException, CustomException {
        boolean isSuccess = false;
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        String query = "insert into user values(?,?,?,?,?)";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, uuid);
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        if(!getAllUserEmail().contains(user.getEmail())) {
            statement.executeUpdate();
            isSuccess = true;
        } else {
                throw new CustomException("Id already exist");
        }
        statement.close();

        return isSuccess;
    }

    public static String getUserId(String email, String password) throws SQLException, CustomException {
        String user_Id = null;
        String query = "select user_Id from user where user_email = ? and user_password = ?";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            user_Id = resultSet.getString("user_Id");
            statement.close();
        } else {
            throw new CustomException("User not found! Please enter correct details.");
        }
        return user_Id;
    }

    public static User getUserById(String user_Id) throws SQLException, CustomException {
        User user = new User();
        String query = "select * from user where user_Id = ?";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, user_Id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("user_email"));
            user.setPassword(resultSet.getString("user_password"));
            statement.close();
        } else {
            throw new CustomException("User not found! Please enter correct details.");
        }
        return user;
    }

    public static boolean loginUser(String email, String password) throws SQLException {
        boolean isSuccess = false;
        String query = "select user_Id from user where user_email = ? and user_password = ?";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            isSuccess = true;
            statement.close();
        }
        return isSuccess;
    }

}
