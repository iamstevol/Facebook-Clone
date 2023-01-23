package com.iamstevol.facebooktask.model;

public class Comment {
    private String title;
    private String postImage;
    private String user_Id;
    private String comment;

    public Comment(String title, String postImage, String user_Id, String comment) {
        this.title = title;
        this.postImage = postImage;
        this.user_Id = user_Id;
        this.comment = comment;
    }

    public Comment(String user_Id, String comment) {
        this.user_Id = user_Id;
        this.comment = comment;
    }

    public Comment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getUserId() {
        return user_Id;
    }

    public void setUserId(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "title='" + title + '\'' +
                ", postImage='" + postImage + '\'' +
                ", userId=" + user_Id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
