package com.iamstevol.facebooktask.dao;

import com.iamstevol.facebooktask.connection.DBConnection;
import com.iamstevol.facebooktask.model.Post;
import com.iamstevol.facebooktask.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostDAO {
    static Connection dbConnection = DBConnection.getConnection();

    public static ResultSet getAllPost() throws SQLException {
        String query = "select * from post";
        Statement statement = dbConnection.createStatement();
        return statement.executeQuery(query);
    }

    public static boolean createPost(String user_Id, Post post) throws SQLException {
        boolean isSuccess = false;
        String post_Id = UUID.randomUUID().toString().replace("-", "");
        String query = "insert into post values(?,?,?,?,?)";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, post_Id);
        statement.setString(2, post.getPost_title());
        statement.setString(3, post.getPost_body());
        statement.setString(4, post.getPost_image());
        statement.setString(5, user_Id);
        int execute = statement.executeUpdate();
        if(execute == 1) {
            isSuccess = true;
        }
        statement.close();
        return isSuccess;
    }

    public static String getPostId(String post_userId) throws SQLException {
        String post_Id = null;
        String query = "select post_Id from post where post_userId =?";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, post_userId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            post_Id = resultSet.getString("post_Id");
        }
        return post_Id;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getPostId("3adfc8709a204dbbae9956088ed8bcd3"));
    }
    public static Post getPostById(String post_Id) throws SQLException {
        Post post = new Post();
        String query = "select * from post where post_Id = ?";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, post_Id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            post.setPost_Id(resultSet.getString("post_Id"));
            post.setPost_title(resultSet.getString("post_title"));
            post.setPost_body(resultSet.getString("post_body"));
            post.setPost_image(resultSet.getString("post_image"));
        }
        return post;
    }

    public static List<Post> getAllPost(User user) throws SQLException {
        List<Post> postList = new ArrayList<>();
        String query = "select * from post";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Post post = new Post();
            post.setPost_Id(resultSet.getString("post_Id"));
            post.setPost_title(resultSet.getString("post_title"));
            post.setPost_body(resultSet.getString("post_body"));
            post.setPost_image(resultSet.getString("post_image"));

//            Set Number of likes for a post
            String likesQuery = "select * from likes where post_Id = ?";
            PreparedStatement likeStatement = dbConnection.prepareStatement(likesQuery);
            likeStatement.setString(1, post.getPost_Id());
            ResultSet likeResult = likeStatement.executeQuery();
            int numOfRow = likeResult.getRow();
            post.setLikes(numOfRow);
            likeStatement.close();

//            set Number of comments on a post
            String commentQuery = "select * from comment where post_Id = ?";
            PreparedStatement commentStatement = dbConnection.prepareStatement(commentQuery);
            commentStatement.setString(1, post.getPost_Id());
            ResultSet commentResult = commentStatement.executeQuery();
            int numOfCommentRow = commentResult.getRow();
            post.setComments(numOfCommentRow);
            commentStatement.close();

//            Check if current user liked a post and return true
            String currentUserLikeQuery = "select * from likes where post_Id = ? and user_Id = ?";
            PreparedStatement currentUserLikeStatement = dbConnection.prepareStatement(currentUserLikeQuery);
            currentUserLikeStatement.setString(1, post.getPost_Id());
            currentUserLikeStatement.setString(2, user.getUser_Id());
            ResultSet currentUserLikeResult = currentUserLikeStatement.executeQuery();
            if(currentUserLikeResult.next()) {
                post.setLikedPost(true);
            }
            currentUserLikeResult.close();

            postList.add(post);
        }
        return postList;
    }

    public static boolean updatePost(String post_Id, Post post) throws SQLException {
        boolean success = false;
            String query = "update post set post_title=?, post_body=? where post_userId=?";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, post.getPost_title());
            statement.setString(2, post.getPost_body());
            statement.setString(3, post_Id);
            int result = statement.executeUpdate();
            if(result > 0) {
                success = true;
            }
            statement.close();
        return success;
        }

    public static boolean deletePost(String user_Id, String post_Id) throws SQLException {
        boolean success =  false;
            String query = "delete from post where post_id = ? and post_userId = ?";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, post_Id);
            statement.setString(2, user_Id);
            int result = statement.executeUpdate();
            if(result > 0) {
                success = true;
            }
        return success;
    }
}
