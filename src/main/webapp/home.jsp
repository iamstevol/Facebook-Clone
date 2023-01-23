<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iamstevol.facebooktask.dao.UserDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.iamstevol.facebooktask.utility.CustomException" %>
<%@ page import="com.iamstevol.facebooktask.model.User" %>
<%@ page import="com.iamstevol.facebooktask.dao.PostDAO" %>
<%@ page import="com.iamstevol.facebooktask.model.Post" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Facebook Home Page</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>timeline</title>
    <!-- JQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous">
    </script>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="css/home.css" />
</head>
<body>
<%
    String user_Id = session.getAttribute("user_Id").toString();
    User user = null;
    try {
        user = UserDAO.getUserById(user_Id);
    } catch (SQLException | CustomException e) {
        throw new RuntimeException(e);
    }

    System.out.println(user);

    if(session.getAttribute("message") != null){%>
        <div class="alert alert-primary" role="alert">
            <%=session.getAttribute("message").toString()%>
        </div>
    <%}

    if(user_Id == null) {
        session.setAttribute("Registration Error", "!!!Please Login first");
        response.sendRedirect("index.jsp");
    }else {
            List<Post> postList;
        try {
            postList = PostDAO.getAllPost(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(postList);
    %>
<!-- ============================================================================================================== -->

<!-- ============================================================================================================== -->

        <nav class="navbar navbar-expand-lg navbar-success bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#" style="font-size: larger">Facebook</a>
                <form class="d-flex">
                    <input
                            class="me-2 searchbar"
                            type="search"
                            placeholder="Search Facebook...."
                            aria-label="Search"
                    />
                </form>
                <button
                        class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse topnav" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">
                                <i class="fa fa-shopping-bag pictures" aria-hidden="true"></i>
<%--                                <img src="image/marketplace.png" class="pictures alt="/>--%>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"
                            ><i class="fa fa-users pictures" aria-hidden="true"></i>
<%--                                <img src="image/group.png" class="pictures alt="/>--%>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">
                                <i class="fa fa-television" aria-hidden="true"></i>
<%--                                <img src="image/watch.png" class="pictures alt=" />--%>
                                <p class="yourfeed"></p>
                            </a>
                        </li>
                        <li class="nav-item"></li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fa fa-globe pictures" aria-hidden="true"></i>
<%--                                <img src="image/friends.png" class="pictures alt="/>--%>
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a
                                    class="nav-link dropdown-toggle"
                                    href="#"
                                    id="navbarDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                    style="color:#fff;"
                            ><i class="fa fa-user-circle-o pix" aria-hidden="true"></i>
<%--                                <img src="image/CJ.jpeg" href="./image/CJ.jpeg" class="pix" alt="" />--%>
                                <%=user.getFirstName() +" "+ user.getLastName() %>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="index.jsp">Logout</a></li>
                                <li><a class="dropdown-item" href="#">Setting</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#">friends</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <a class="nav-link active" aria-current="page" href="#"
                        ><i class="fa fa-television" aria-hidden="true"></i>
<%--                            <img src="image/marketplace.png" class="pictures alt="/>--%>
                        </a>
                        <a class="nav-link active" aria-current="page" href="#">
                            <i class="fa fa-users pictures" aria-hidden="true"></i>
<%--                            <img src="image/group.png" class="pictures alt="/>--%>
                        </a>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3">
                    <div class="fix">
                        <div>
                            <img src="image/Stephen.jpeg" href="image/Stephen.jpeg" class="pix" alt="" />
                            <p class="yourfeed"><%=user.getFirstName() +" "+ user.getLastName() %> </p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-globe pictures" aria-hidden="true"></i>
<%--                            <img src="image/friends.png" class="pictures alt=" />--%>
                            <p class="yourfeed">Friends</p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-users pictures" aria-hidden="true"></i>
<%--                            <img src="image/group.png" class="pictures alt=" />--%>
                            <p class="yourfeed">Groups</p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-shopping-bag pictures" aria-hidden="true"></i>
<%--                            <img src="image/marketplace.png" class="pictures alt=" />--%>
                            <p class="yourfeed">Marketplace</p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-television" aria-hidden="true"></i>
<%--                            <img src="image/watch.png" class="pictures alt=" />--%>
                            <p class="yourfeed">Watch</p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-calendar" aria-hidden="true"></i>
<%--                            <img src="image/Events.png" class="pictures alt=" />--%>
                            <p class="yourfeed">Event</p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-bookmark" aria-hidden="true"></i>
<%--                            <img src="image/Saved.png " class="pictures alt=" />--%>
                            <p class="yourfeed">save</p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-sticky-note" aria-hidden="true"></i>
<%--                            <img src="image/Pages.png " class="pictures alt=" />--%>
                            <p class="yourfeed">Pages</p>
                        </div>
                        <div class="yourfeed1">
                            <i class="fa fa-chevron-down" aria-hidden="true"></i>
<%--                            <img src="image/semore.png" class="pictures alt=" />--%>
                            <p class="yourfeed">See more</p>
                        </div>
                        <p class="text">Your Shortcuts</p>
                        <img src="" alt="" />
                        <p>
                            <small
                            >Privacy · Terms · Advertising · Ad Choices · Cookies · ·
                                Facebook © 2021</small
                            >
                        </p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="scroller like-icon" style="height:  700px">

                        <%for (Post post:postList) {%>
                        <div class="linear" style="background-color: #ffffff">
                            <div class="titleText">
                                <h2 style="color: #fff; padding: 10px; background-color: dodgerblue"><%=post.getPost_title()%></h2>
                            </div>
                            <% if (post.getPost_image() != null && post.getPost_image().length()>1) {%>
                            <div class="postImage">
                                <img src="./image/<%=post.getPost_image()%>" class="majorpix" alt="" />
                            </div>
                            <% } else { %>
                            <img src="./image/<%=post.getPost_image()%>" class="majorpix" alt="" style="display: none"/>
                            <% } %>
                        </div>
                        <div class="body">
                            <p><%=post.getPost_body()%></p>
                        </div>
                        <hr>
                                <div class="comment_like_share mb-2">
                                    <div class="like">
                                        <i onclick="like(<%=post.getPost_Id()%>, <%=user.getUser_Id()%>)" id="<%=post.getPost_Id()%>"
                                           style="color: #ffffff" class="fa fa-thumbs-o-up" aria-hidden="true">
                                            <span class="likes"><%=post.getLikes()%></span>
                                            <span style="display:none;" class="thumb"><%=post.getPost_Id()%></span></i>
                                            <p style="display: none" class="userLiked"><%=post.isLikedPost()%></p>

                                    </div>
                                    <div class="comment">
                                        <form action="${pageContext.request.contextPath}/CommentServlet" method="post">
                                        <input style="display:none" name="post_Id" value="<%=post.getPost_Id()%>"/>
                                            <button type="submit" class="btn btn-primary btn-md mt-3 btn-block">
                                            <i class="fa fa-commenting-o" aria-hidden="true">
                                        <span><%=post.getComments()%></span>
                                        </i>
                                            </button>
                                            </form>
                                    </div>
                                    <div class="edit">
                                        <form action="${pageContext.request.contextPath}/edit.jsp" method="post">
                                            <input style="display:none" name="post_Id" value="<%=post.getPost_Id()%>"/>
                                            <button type="submit" class="btn btn-primary btn-md mt-3 btn-block">
                                        <i class="fa fa-edit" aria-hidden="true" onclick="edit(<%=post.getPost_Id()%>)">
                                        </i>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="delete">
                                        <i class="fa fa-remove" aria-hidden="true" id="delete" onclick="del(<%=post.getPost_Id()%>)">
                                        </i>
                                    </div>
                                </div>
                                <div class="row mb-2">
                                   <form action="${pageContext.request.contextPath}/PostCommentServlet" method="post">
<%--                                       <% String post_Id = post.getPost_Id();--%>
<%--                                       request.setAttribute("post_Id", post_Id); %>--%>
                                        <input style="display:none" name="post_Id" value="<%=post.getPost_Id()%>"/>
                                        <textarea
                                                name="comment"
                                                class="form-control drag"
                                                placeholder="What's on your mind <%=user.getLastName()%>?"
                                        ></textarea>
                                       <button type="submit" class="btn btn-primary btn-md mt-3 btn-block">
                                               Comment
                                       </button>
                                   </form>
                                </div>
<%--                        <%}%>--%>
                            <%}
                        %>
                    </div>
                </div>
                <div class="col-lg-3 third">
                    <div class="postArea">
                        <form action="${pageContext.request.contextPath}/PostServlet" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="post-title" class="form-label">Title</label>
                                <input
                                        type="text"
                                        name="title"
                                        class="form-control"
                                        id="post-title"
                                        placeholder="Post Title"
                                        required
                                />
                            </div>
                            <div class="mb-2">
                        <textarea
                                style="border-radius: 30px"
                                name="body"
                                id="post"
                                cols="30"
                                rows="2"
                                class="form-control"
                                aria-describedby="emailHelp"
                                placeholder="Whats on your mind <%=user.getLastName()%>"
                                required
                        ></textarea>
                            </div>
                            <div class="row post-buttons mb-4">
                                <div class="col-md-6 col-btn">
                                    <label class="form-label" for="customFile">Photo</label>
                                    <input type="file" name="file" class="form-control" id="customFile"/>
                                </div>
                                <div class="col-md-6 col-btn">
                                    <p></p>
                                    <button type="submit" class="btn btn-primary btn-md mt-3 btn-block">
                                        Post
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer></footer>
<script>
    function com(postId){
        //alert("i got here");
         window.location.href = "/comment.jsp?post="+postId;
        // window.location.href = "/comment.jsp";
    }
</script>
        <script
                src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
                integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
                crossorigin="anonymous"
        ></script>
        <script
                src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
                integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
                crossorigin="anonymous"
        ></script>
        <script src="https://use.fontawesome.com/aed9ef824b.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <%}
%>
</body>
</html>


