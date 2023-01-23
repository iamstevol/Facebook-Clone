<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Facebook Clone</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap 4.5 CSS-->
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
            crossorigin="anonymous"
    />

    <!-- Bootstrap JS Requirements -->
    <script
            src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
    ></script>
    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"
    ></script>
    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"
    ></script>
    <!-- My css -->
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<%
    if(session.getAttribute("Registration Error") != null){%>
<div class="alert alert-success" role="alert">
    <%=session.getAttribute("Registration Status").toString()%>
</div>
<%}
    //delete session
    session.invalidate();
%>
<section class="container main-content">
    <div class="flex-item-left" style="margin-top: -10px;">
        <div>
            <h1  style="position: absolute;  left: 15%;">facebook</h1>
        </div>
        <div class="logo_body" style="position: absolute;  left: 15%; top: 20%;">
            <%--            <img class="logobdy" src="https://static.xx.fbcdn.net/rsrc.php/v3/yx/r/iauQmlDrivH.png">--%>
            <p class="like_font font1" style="color: #083987; margin-left: 20px; margin-bottom: 5px;"> Thanks for stopping by!</p>
            <p class="like_font" style=" font-family: arial; font-size: 25px; margin-left: -30px; inline-size: 350px;
    overflow-wrap: break-word;">
                Facebook helps you connect and share with the people in your life..</p>
            <%--            <h3>--%>
            <%--                Facebook helps you connect and share<br/>--%>
            <%--                with the people in your life.--%>
            <%--            </h3>--%>
        </div>
    </div>
    <div class="flex-item-right">
        <div class="shadow p-3 mb-5 bg-white rounded">
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <!-- ################################################################# -->
                <div class="form-group">
                    <input
                            id="input1"
                            class="form-control form-control-lg"
                            type="text"
                            name="email"
                            placeholder="Email address"
                            required
                    />
                    <input
                            id="input2"
                            class="form-control form-control-lg"
                            type="password"
                            name="password"
                            placeholder="Password"
                            required
                    />
                    <button
                            id="btn1"
                            type="submit"
                            class="btn btn-primary btn-lg btn-block"
                    >
                        Log in
                    </button>
                    <p id="password">Forgotten password?</p>
                    <hr/>
                    <button
                            onclick="modal()"
                            id="btn2"
                            type="button"
                            class="btn btn-lg btn-block btn-lg btn-block"
                    >
                        Create New Account
                    </button>
                </div>
            </form>
        </div>
        <p style="text-align: center; font-size: 14px">
            <a href="" style="text-decoration: none; color: black"
            ><strong>Create a Page</strong></a
            >
            for a celebrity, band or business.
        </p>
    </div>
</section>
<section class="container">
    <div id="modal" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title">
                        <h1>Sign Up</h1>
                        <p>It's quick and easy.</p>
                    </div>
                    <button
                            onclick="work()"
                            id="close"
                            type="button"
                            class="close"
                            data-dismiss="modal"
                            aria-label="Close"
                    >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/SignUpServlet" method="get">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <input
                                        type="text"
                                        name="firstname"
                                        class="form-control"
                                        id="inputEmail4"
                                        placeholder="First name"
                                        required
                                />
                            </div>
                            <div class="form-group col-md-6">
                                <input
                                        type="text"
                                        name="lastname"
                                        class="form-control"
                                        id="inputPassword4"
                                        placeholder="Lastname"
                                        required
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <input
                                    type="text"
                                    name="email"
                                    class="form-control"
                                    id="inputAddress"
                                    placeholder="Mobile number or email address"
                                    required
                            />
                        </div>
                        <div class="form-group">
                            <input
                                    type="password"
                                    name="password"
                                    class="form-control"
                                    id="inputAddress2"
                                    placeholder="New password"
                                    required
                            />
                        </div>
                        <div>
                            <small style="color: #7d7d7d">
                                By clicking Sign Up, you agree to our Terms, Data Policy and
                                Cookie Policy.
                            </small>
                            <small style="color: #7d7d7d">
                                You may receive SMS notifications from us and can opt out at
                                any time.
                            </small>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-success btn-lg btn-block">
                            Sign up
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<footer>
    <p>
        <small><span style="color: #989898">English (UK)</span> Hausa Português (Brasil) Français (France) Español العربية Italiano 日本語 Deutsch
            Bahasa Indonesia हिन्दी
        </small>
    <hr style="width: 75%; margin-bottom: -1px;  margin-top: 4px; padding: 4px;"/>
    </p>
    <p>
        <small>Sign Up Log In Messenger Facebook Lite Watch People Pages Page
            categories Places Games Locations Market place Facebook Pay
        </small>
    </p>
    <p>
        <small>Groups Jobs Oculus Portal Instagram Local Fund raisers Services Voting
            Information Centre About Create ad Create Page Developers
        </small>
    </p>
    <p><small> Careers Privacy Cookies Ad Choices Terms Help Settings </small></p>
    <p class="copyright"><small>Facebook © 2021</small></p>
</footer>
</body>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</script>
</html>
