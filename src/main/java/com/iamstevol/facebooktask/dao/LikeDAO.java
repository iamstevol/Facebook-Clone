package com.iamstevol.facebooktask.dao;

import com.iamstevol.facebooktask.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;



public class LikeDAO {
    static Connection dbConnection = DBConnection.getConnection();

    public static boolean likePost(String user_Id, String post_Id, int action){
        boolean success = false;
        try{
            String query;
            PreparedStatement preparedStatement;

            if(action == 1){
                query = "insert into likes values (?,?)";
                preparedStatement = dbConnection.prepareStatement(query);
                preparedStatement.setString(1, post_Id);
                preparedStatement.setString(2, user_Id);

                preparedStatement.executeUpdate();
                success = true;
            }else{
                query = "delete from likes where user_id="+ user_Id +" and post_id="+post_Id;
                preparedStatement = dbConnection.prepareStatement(query);
                int result = preparedStatement.executeUpdate();

                if(result > 0) {
                    success = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }

}
