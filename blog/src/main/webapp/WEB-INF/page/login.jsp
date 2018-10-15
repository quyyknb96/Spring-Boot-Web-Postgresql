<%--
  Created by IntelliJ IDEA.
  User: quytn
  Date: 10/08/2018
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="resource/css/login.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<%--<div class="login-page">--%>
    <%--<div class="form">--%>
         <%--<form:form class="login-form" action="login" method="post">--%>
             <%--<c:if test="${not empty error}"> ${error}</c:if>--%>
            <%--<input type="text" name="username" placeholder="username" autofocus="true" autocomplete="off"/>--%>
            <%--<input type="password" name="password" placeholder="password"/>--%>
            <%--<button type="submit">login</button>--%>
            <%--<p class="message">Not registered? <a href="#">Create an account</a></p>--%>
         <%--</form:form>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="login-page">
    <div class="form">
        <form class="register-form" action="register" method="post">
            <input required type="text" name="username" placeholder="username" autofocus="true" autocomplete="off"/>
            <input required type="password" name="password" placeholder="password"/>
            <input type="text" placeholder="name" name="name" autocomplete="off"/>
            <input type="text" placeholder="address" name="address" autocomplete="off"/>
            <input type="date" placeholder="date of birth" name="birth"/>
            <input type="text" placeholder="image" name="image" autocomplete="off"/>
            <button type="submit">CREATE</button>
            <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>
        <form class="login-form" action="login" method="post">
            <c:if test="${not empty error}"> ${error}</c:if>
            <input required type="text" name="username" placeholder="username" autofocus="true" autocomplete="off" />
            <input required type="password" name="password" placeholder="password"/>
            <button type="submit" >login</button>
            <p class="message">Not registered? <a href="#">Create an account</a></p>
            <p>Login now with <a style="font-size: 18px; color: blue;" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost/login-google&response_type=code &client_id=253986303488-9d2474numbpmhfoflpmbr5on7tabortq.apps.googleusercontent.com&approval_prompt=force">Google+</a></p>
        </form>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $('.message a').click(function(){
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
</script>
</html>
