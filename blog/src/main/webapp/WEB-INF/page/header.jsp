<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: quytn
  Date: 10/08/2018
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Spring</title>
    <meta charset="utf-8">
    <title>Collapsible sidebar using Bootstrap 4</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Our Custom CSS -->
    <link rel="stylesheet" href="/resource/css/style.css">


    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>
</head>
<body>
<div class="wrapper">
    <!-- Sidebar  -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>Spring boot Web</h3>
        </div>

        <ul class="list-unstyled components">
            <p><a href="/home" class="article">${sessionScope.get("user").name}</a></p>
            <li>
                <c:choose>
                    <c:when test="${ currentUser != null && currentUser.id != sessionScope.get('user').id}">
                        <a href="/u/${currentUser.id}/home">Home</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/home">Home</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${currentUser != null && currentUser.id != sessionScope.get('user').id}">
                        <a href="/u/${currentUser.id}/profile">Profile</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/profile">Profile</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${currentUser != null && currentUser.id != sessionScope.get('user').id}">
                        <a href="/u/${currentUser.id}/grandude">Grandude</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/grandude">Grandude</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <a href="/user">User</a>
            </li>
        </ul>
    </nav>

    <!-- Page Content  -->
    <div id="content">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Page</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Page</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Page</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
</body>
</html>
