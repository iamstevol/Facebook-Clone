package com.iamstevol.facebooktask.dao;

import com.iamstevol.facebooktask.connection.DBConnection;
import com.iamstevol.facebooktask.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CommentDAO {
    static Connection dbConnection = DBConnection.getConnection();
    public static boolean createComment(String user_Id, String post_Id, String comment) {
        boolean result = false;
        try{
            String query = "insert into comment values (?,?,?)";
            PreparedStatement commentStatement = dbConnection.prepareStatement(query);
            commentStatement.setString(1, user_Id);
            commentStatement.setString(2, post_Id);
            commentStatement.setString(3, comment);
            commentStatement.executeUpdate();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static List<Comment> getAllComment(String postId){
        List<Comment> commentList = new ArrayList<>();
        try{
            String query = "select u.user_id, p.post_title, p.post_image, c.comment " +
                    "from comment c join post p on c.post_id = p.post_id " +
                    "join user u on u.user_id = c.user_id  where p.post_id = ?";
            PreparedStatement commentStatement = dbConnection.prepareStatement(query);
            commentStatement.setString(1, postId);
            ResultSet resultSet =  commentStatement.executeQuery();

            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setUserId(resultSet.getString("user_id"));
                comment.setTitle(resultSet.getString("post_title"));
                comment.setPostImage(resultSet.getString("post_image"));
                comment.setComment(resultSet.getString("comment"));
                commentList.add(comment);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return commentList;
    }

    public static boolean editComment(String user_Id, String post_Id, String comment){
        boolean status = false;
        try {
            String query = "update comment set comment=? where post_id=? and user_id=?";
            PreparedStatement commentStatement = dbConnection.prepareStatement(query);
            commentStatement.setString(1, comment);
            commentStatement.setString(2, post_Id);
            commentStatement.setString(3,user_Id);

            int result = commentStatement.executeUpdate();
            if(result > 0) {
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    public static boolean deleteComment(String post_Id, String user_Id){
        boolean status =  false;

        try {
            String query = "delete from comment where post_id=? and user_id=?";
            PreparedStatement commentStatement = dbConnection.prepareStatement(query);
            commentStatement.setString(1, post_Id);
            commentStatement.setString(2,user_Id);

            int result = commentStatement.executeUpdate();
            if(result > 0) {
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
