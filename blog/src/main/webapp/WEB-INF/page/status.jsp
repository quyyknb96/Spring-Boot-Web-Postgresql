<%--
  Created by IntelliJ IDEA.
  User: quytn
  Date: 14/08/2018
  Time: 14:20
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
    <style>
        .media div .glyphicon:hover{
            color: blue;
            cursor: pointer;
        }
    </style>
<body>

    <jsp:include page="header.jsp"></jsp:include>


    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Post Content Column -->
            <div class="col-lg-8">

                <!-- Author -->
                <p class="lead">
                    by
                    <a href="/u/${status.student.id}/home">${status.student.name}</a>
                </p>

                <hr>

                <!-- Date/Time -->
                <p>Posted on ${status.datePrint}</p>

                <hr>

                <p style="color: midnightblue;">${status.content}</p>

                <hr>

                <!-- Comments Form -->
                <div class="card my-4">
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <textarea class="form-control" rows="2" name="content" id="contentStatusStatic" style="resize: none;"></textarea>
                                <input type="hidden" id="statusId" name="status" value="${status.id}" />
                                <input type="hidden" id="studentId" name="student" value="${sessionScope.get('user').id}">
                            </div>
                            <button type="button" class="btn btn-primary" id="buttonSubmitComment" disabled>Đăng bình luận</button>
                        </form>
                    </div>
                </div>

                <div id="insertComent"></div>

                <!-- Single Comment -->

                <c:forEach items="${status.comments}" var="comment">
                    <div class="media mb-4" style="display: inline-block; width: 100%; position: relative">
                        <img height="50px" width="50px" class="d-flex mr-3 rounded-circle" src="${comment.student.image}" alt="" style="display: inline-block">
                        <div class="media-body" style="display: inline-block; width: auto; margin-left: 20px;">
                            <div style="display: none" class="commentId">begin${comment.id}end</div>
                            <h5 class="mt-0" style="margin-top: 0px;">${comment.student.name}</h5>
                            <span class="content">${comment.content}</span>
                            <h6>in ${comment.datePrint}</h6>
                        </div>
                        <div style="position: absolute; top: 0px; bottom: auto; left: auto; right: 5%;">
                            <c:if test="${sessionScope.get('user').id == comment.student.id}"><span data-toggle="modal" data-target="#ModalEdit" class="glyphicon glyphicon-wrench"></span></c:if>
                            <c:if test="${sessionScope.get('user').id == comment.student.id || sessionScope.get('user').id == status.student.id}"><span data-toggle="modal" data-target="#ModalDelete" class="glyphicon glyphicon-remove-sign" style="margin-left: 5px;"></span></c:if>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <!-- /.container -->
        </div>
    </div>


    <jsp:include page="footer.jsp"></jsp:include>

    <div>
        <div class="modal fade" id="ModalEdit" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">EDIT</h4>
                    </div>
                    <div class="modal-body">
                        <input class="commentId" type="hidden" name="id" >
                        <textarea  name="content"   style="resize: none;" class="form-control content" rows="2"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default dismiss" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="confirmEditComment" data-dismiss="modal">Edit</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div>
        <div class="modal fade" id="ModalDelete" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-body">
                        <input class="commentId" type="hidden" name="id">
                        <p>Bạn có chắc chắc muốn xóa bình luận này !!!</p>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default dismiss" data-dismiss="modal">Close</button>
                         <button type="button" class="btn btn-primary" id="confirmDeleteComment" data-dismiss="modal">Delete</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script>
        $(document).ready(function () {
            $("#contentStatusStatic").keyup(function () {
                var a = $(this).val();
                if(a == ""){
                    $(this).parents().eq(1).children("button[type=button]").prop("disabled",true);
                }else {
                    $(this).parents().eq(1).children("button[type=button]").prop("disabled",false);
                }
            });

            $("#buttonSubmitComment").click(function () {
                var status = $("#statusId").val();
                var content = $("#contentStatusStatic").val();
                var student = $("#studentId").val();

                $("#buttonSubmitComment").prop("disabled", true);
                $("#contentStatusStatic").val("");
                $.ajax({
                    type : "GET",
                    contentType : "application/json",
                    url : "${home}comment/post",
                    data : {
                        status : status,
                        content : content,
                        student : student
                    },
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                        var result= "<div class=\"media mb-4\" style=\"display: inline-block; width: 100%; position: relative\">\n" +
                            "                        <img height=\"50px\" width=\"50px\" class=\"d-flex mr-3 rounded-circle\" src=\""+data.student.image+"\" alt=\"\" style=\"display: inline-block\">\n" +
                            "                        <div class=\"media-body\" style=\"display: inline-block; width: auto; margin-left: 20px;\">\n" +
                            "                            <div style=\"display: none\" class=\"commentId\">begin"+data.id+"end</div>\n" +
                            "                            <h5 class=\"mt-0\" style=\"margin-top: 0px;\">"+data.student.name+"</h5>\n" +
                            "                            <span class=\"content\">"+data.content+"</span>\n" +
                            "                            <h6>in "+data.datePrint+"</h6>\n" +
                            "                        </div>\n" +
                            "                        <div style=\"position: absolute; top: 0px; bottom: auto; left: auto; right: 5%;\">\n" +
                            "                            <span data-toggle=\"modal\" data-target=\"#ModalEdit\" class=\"glyphicon glyphicon-wrench\"></span>\n" +
                            "                            <span data-toggle=\"modal\" data-target=\"#ModalDelete\" class=\"glyphicon glyphicon-remove-sign\" style=\"margin-left: 5px;\"></span>\n" +
                            "                        </div>\n" +
                            "                    </div>";
                        $("#insertComent").prepend(result);
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                    }
                });
            });

            $(".media div").on("click","span.glyphicon",function () {
                var id = $(this).parents().eq(1).children("div[class=media-body]").children("div[class=commentId]").text();
                id = id.replace('begin','');
                id = id.replace('end','');
                //console.log(id);
                var content = $(this).parents().eq(1).children("div[class=media-body]").children("span[class=content]").text();
                //console.log(content);
                $("div .modal-content .modal-body .commentId").val(id);
                $("div .modal-content .modal-body .content").val(content);
            });

            $("#insertComent").on("click","span.glyphicon",function () {
                var id = $(this).parents().eq(1).children("div[class=media-body]").children("div[class=commentId]").text();
                id = id.replace('begin','');
                id = id.replace('end','');
                //console.log(id);
                var content = $(this).parents().eq(1).children("div[class=media-body]").children("span[class=content]").text();
                //console.log(content);
                $("div .modal-content .modal-body .commentId").val(id);
                $("div .modal-content .modal-body .content").val(content);
            });
            
            $("#confirmDeleteComment").click(function () {
                var id = $(this).parents().eq(1).children("div[class=modal-body]").children("input[class=commentId]").val();
                $.ajax({
                    type : "GET",
                    contentType : "application/json",
                    url : "${home}comment/delete",
                    data : {
                        id : id
                    },
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                        var x = 'begin' + data.id+ 'end';
                        $(".wrapper .container .col-lg-8 .media .media-body .commentId:contains('"+x+"')").parents().eq(1).remove();
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                    }
                });
            });


            $("#confirmEditComment").click(function () {
                var id = $(this).parents().eq(1).children("div[class=modal-body]").children("input[class=commentId]").val();
                var content = $(this).parents().eq(1).children("div[class=modal-body]").children("textarea").val();
                $.ajax({
                    type : "GET",
                    contentType : "application/json",
                    url : "${home}comment/edit",
                    data : {
                        id : id,
                        content : content
                    },
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                        var x = 'begin' + data.id+ 'end';
                        $(".wrapper .container .col-lg-8 .media .media-body .commentId:contains('"+x+"')").parents().eq(0).children("span").text(data.content);
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                    }
                });
            });
        });
    </script>


</body>
</html>
