<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <script src="<c:url value="/resources/js/jquery-3.0.0.min.js" />" ></script>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<div class="container">
<h1> Создать новость</h1>
<c:url var="saveUrl" value="/news/add"/>
    <form:form modelAttribute="newsAttribute" id="news-form" method="POST" action="${saveUrl}" >

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label" for="title">Заголовок</label>
                <div class="col-md7">
                    <input type="text" id="title" name="title" class="form-control input-sm" required>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label" for="content">Контент</label>
                <div class="col-md7">
                    <textarea id="content" name="content" class="form-control input-sm" required></textarea>
                </div>
            </div>
        </div>

        <p><b>Теги</b></p>
        <c:choose>
            <c:when test="${categories != null && categories.size() > 0}">
                <select multiple name="tags" size="4s" required>
                    <c:forEach items="${categories}" var="tag">
                        <option value="${tag.id}">${tag.name}</option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <p>Тегов нету. Создайте теги</p>
            </c:otherwise>
        </c:choose>






        <br />
        <br />

        <input type="submit" value="Добавить новость" class="btn btn-success">

    </form:form>
</div>
    <script>
        function call(){
            var serNews = $("#news-form").serialize();

            $.ajax({
                type: "POST",
                url: "${saveUrl}",
                data: serNews,
                success: function(data){
                    alert("SUCCESS");
                },

                error:  function(xhr, str){
                    alert('Возникла ошибка: ' + xhr.responseCode);
                }
            })
        }
    </script>


</body>
</html>
