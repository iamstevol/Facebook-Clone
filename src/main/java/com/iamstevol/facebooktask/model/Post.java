package com.iamstevol.facebooktask.model;

public class Post {
    private String post_Id;
    private String post_title;
    private String post_body;
    private String post_image;
    private String name;
    private String email;
    private int likes;
    private int comments;
    private boolean likedPost;

    public Post(String post_title, String post_body, String post_image) {
        this.post_title = post_title;
        this.post_body = post_body;
        this.post_image = post_image;
    }

    public Post(String post_title, String post_body) {
        this.post_title = post_title;
        this.post_body = post_body;
    }

    public Post() {
    }

    public String getPost_Id() {
        return post_Id;
    }

    public void setPost_Id(String post_Id) {
        this.post_Id = post_Id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public boolean isLikedPost() {
        return likedPost;
    }

    public void setLikedPost(boolean likedPost) {
        this.likedPost = likedPost;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_Id='" + post_Id + '\'' +
                ", post_title='" + post_title + '\'' +
                ", post_body='" + post_body + '\'' +
                ", post_image='" + post_image + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                ", likedPost=" + likedPost +
                '}';
    }
}
