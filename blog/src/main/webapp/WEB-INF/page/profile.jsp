<%--
  Created by IntelliJ IDEA.
  User: quytn
  Date: 13/08/2018
  Time: 10:20
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

    <form action="/profile/edit" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Username</label>
            <div class="col-sm-10">
                <input autocomplete="off" type="text" class="form-control form-control-lg" readonly value="${currentUser.username}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Name</label>
            <div class="col-sm-10">
                <input autocomplete="off" type="text" name="name" class="form-control form-control-lg" id="staticName" placeholder="Name" readonly value="${currentUser.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Birth</label>
            <div class="col-sm-10">
                <input autocomplete="off" type="date" name="birth" class="form-control form-control-lg" id="staticBirth" readonly value="${currentUser.birth}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label col-form-label-lg">Address</label>
            <div class="col-sm-10">
                <input autocomplete="off" name="address" type="text" class="form-control form-control-lg" id="staticAddress" placeholder="Address" readonly value="${currentUser.address}">
            </div>
        </div>
        <c:if test="${sessionScope.get('user').id == currentUser.id}">
        <button type="button" class="btn btn-primary" id="submit">Change</button>
        <button type="button"class="btn btn-warning" id="cancel">Cancel</button>
        </c:if>
    </form>


    <jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $(document).ready(function () {
        let a = $("#staticName").val();
        let b = $("#staticBirth").val();
        let c = $("#staticAddress").val();
        let x = 1;
        $("#submit").click(function () {
            $("#submit").text("Update");
            $("#staticName").attr("readonly",false);
            $("#staticName").attr("class","form-control");
            $("#staticBirth").attr("readonly",false);
            $("#staticBirth").attr("class","form-control");
            $("#staticAddress").attr("readonly",false);
            $("#staticAddress").attr("class","form-control");
            if(x==0) {
                $("#submit").attr("type", "submit");
            }else {
                x--;
            }
        });
        $("#cancel").click(function () {
            x++;
            $("#staticName").val(a);
            $("#staticBirth").val(b);
            $("#staticAddress").val(c);

             $("#staticName").attr("readonly",true);
             $("#staticBirth").attr("readonly",true);
             $("#staticAddress").attr("readonly",true);

            $("#staticName").attr("class","form-control form-control-plaintext");
            $("#staticBirth").attr("class","form-control form-control-plaintext");
            $("#staticAddress").attr("class","form-control form-control-plaintext");

            $("#submit").attr("type","button");
            $("#submit").text("Change");
        });
    });
</script>
</html>
