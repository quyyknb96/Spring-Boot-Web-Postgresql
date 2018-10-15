<%--
  Created by IntelliJ IDEA.
  User: quytn
  Date: 13/08/2018
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div>
    <c:forEach items="${userAll}" var="user">
        <div class="block">
            <div class="row" id="feed">
                <div class="col-md-2"><img src="${user.image}" class="img-circle" width="200px" height="180px"/></div>
                <div class="col-md-10">
                    <div><a href="/u/${user.id}/home"><b>${user.name}</b></a>
                        <div class="pull-right text-muted" id="delete">${user.birth}</div></div>
                    <div>  </div>
                    <div class="text-muted"> <small>${user.address}</small></div>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
