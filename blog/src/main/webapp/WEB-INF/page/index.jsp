<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Collapsible sidebar using Bootstrap 4</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div>

        <c:if test="${sessionScope.get('user').id == currentUser.id}">
            <div class="header">
                <h3 class="text-muted">Facebook Wall Design</h3>
            </div>

            <div>
                <div class="row">
                    <div class="col-md-2"><img src="${currentUser.image}" width="
                200px" height="180px" class="img-circle"/></div>
                    <div class="col-md-10">
                        <input type="hidden" name="student" value="${currentUser.id}" form="formPost">
                        <textarea name="content" form="formPost" style="resize: none;" class="form-control" id="feedbox" rows="3"></textarea>
                        <br>
                        <form action="/status/post" method="post" id="formPost">
                            <button type="submit" id="button" class="btn btn-default">Post</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <hr>
        <div id="insert"></div>
        <c:choose>
            <c:when test="${currentUser.statuses.size() > 0}">
                <c:forEach items="${currentUser.statuses}" var="status">
                    <div class="block">
                        <div class="row" id="feed">
                            <div class="col-md-2"><img src="${currentUser.image}" class="img-circle" width="160px" height="140px"/></div>
                            <div class="col-md-10">
                                <div><b>${currentUser.name}</b>
                                    <div class="pull-right text-muted">
                                        <c:if test="${sessionScope.get('user').id == currentUser.id}">
                                        <span  data-toggle="modal" data-target="#ModalDelete" class="modalClick" >Delete</span>
                                        <span  data-toggle="modal" data-target="#ModalEdit" class="modalClick" >Edit</span>
                                        </c:if>
                                        <a href="/status/${status.id}" class="modalClick">See more</a>
                                    </div>
                                </div>
                                <div class="id" style="display: none">${status.id}</div>
                                <div class="content"> ${status.content} </div>
                                <div class="text-muted"> <small>posted in ${status.datePrint}</small></div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div>Không có bài viết nào để hiển thị</div>
            </c:otherwise>
        </c:choose>
    </div>



    <jsp:include page="footer.jsp"></jsp:include>

    <div>
        <div class="modal fade" id="ModalDelete" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">DELETE</h4>
                    </div>
                    <div class="modal-body">
                        <input class="statusId" type="hidden" name="id" form="confirmDelete">
                        <p>Bạn có chắc chắc muốn xóa trạng thái này !!!</p>
                    </div>
                    <div class="modal-footer">
                        <form action="/status/delete" id="confirmDelete" method="post">
                            <button type="button" class="btn btn-default dismiss" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

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
                        <input class="statusId" type="hidden" name="id" form="confirmEdit">
                        <textarea  name="content" form="confirmEdit"  style="resize: none;" class="form-control content" rows="8"></textarea>
                    </div>
                    <div class="modal-footer">
                        <form action="/status/edit" id="confirmEdit" method="post">
                            <button type="button" class="btn btn-default dismiss" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Edit</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <script>
        $(document).ready(function () {
            $(".modalClick").click(function () {
                var id = $(this).parents().eq(2).children("div[class=id]").text();
                $(".modal-body").children("input[class=statusId]").val(id);

                var content = $(this).parents().eq(2).children("div[class=content]").text();
                $(".modal-body").children("textarea").val(content);
            });

            $(".dismiss").click(function () {
                $(".modal-body").children("input").val("");
                $(".modal-body").children("textarea").text("");
            });
        });
    </script>
</body>
</html>