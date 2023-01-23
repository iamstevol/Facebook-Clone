<%@ page import="com.iamstevol.facebooktask.model.Comment" %>
<%@ page import="com.iamstevol.facebooktask.dao.CommentDAO" %>
<%@ page import="com.iamstevol.facebooktask.model.User" %>
<%@ page import="com.iamstevol.facebooktask.dao.UserDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.*" %>
<%@ page import="com.iamstevol.facebooktask.utility.CustomException" %>
<%--
  Created by IntelliJ IDEA.
  User: protek
  Date: 5/4/21
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comment Facebook Post</title>

    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
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
            crossorigin="anonymous"
    ></script>
    <script src="${pageContext.request.contextPath}/js/comment.js"></script>
</head>
<body>
<%
    String post_Id = session.getAttribute("post_Id").toString();
    System.out.println(post_Id);
//            query.substring(query.indexOf("=")+1);

    List<Comment> commentList = CommentDAO.getAllComment(post_Id);
    System.out.println(commentList);

    String user_Id = session.getAttribute("user_Id").toString();
    User user = null;
    try {
        user = UserDAO.getUserById(user_Id);
    } catch (SQLException | CustomException e) {
        throw new RuntimeException(e);
    }
    if(user_Id == null){
        session.setAttribute("Registration Error", "!!!Please Login first");
        response.sendRedirect("index.jsp");
    }
%>
<nav style="background: #3b5998" class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="#" style="color:#fff;"><h1>Facebook</h1></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" style="color: #fff;" href="${pageContext.request.contextPath}/home.jsp"><h3>Home</h3></a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<section style="margin: 60px auto; width: 60%; border: 2px solid #3b5998; padding: 15px" >
    <%if (commentList.size() != 0){%>
             <img src="./image/<%=commentList.get(0).getPostImage()%>" width="100%" height="60%"/>
             <h3>Post Title: <%=commentList.get(0).getTitle() %></h3>
             <%for (Comment comment:commentList) {%>
                <hr/>
                <p><%=comment.getComment()%> </p>
                <p><em>Edit Comment</em></p>
    <form action="${pageContext.request.contextPath}/EditCommentServlet" method="post">
        <textarea class="edit-comment" name="editedComment" placeholder="Edit comment here...">
        </textarea>

        <button type="submit" class="btn btn-primary btn-md mt-3 btn-block"
                <%
                    if(Objects.equals(comment.getUserId(), user.getUser_Id())){%>
                disabled
                <%}%>>
            Edit
        </button>
    </form>

<%--    <form action="${pageContext.request.contextPath}/DeleteCommentServlet" method="post">--%>
    <a href="${pageContext.request.contextPath}/DeleteCommentServlet">
        <button class="btn btn-primary btn-md mt-3 btn-block"
                    <%
                        if(Objects.equals(comment.getUserId(), user.getUser_Id())){%>
                        disabled
                    <%}%>>
                    Delete
        </button>
    </a>
<%--    </form>--%>
                <hr/>
            <%}
        }else{ %>
            <h1>No Comments !!!!</h1>
        <%}
    %>
</section>
</form>
</script>
</body>
</html>
