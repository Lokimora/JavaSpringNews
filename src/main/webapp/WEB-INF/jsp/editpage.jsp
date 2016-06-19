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

<c:url var="newsUrl" value="/news"/>
<c:url var="saveUrl" value="/news/edit"/>
<c:url var="createUrl" value="/news/add"/>
<c:url var="categoryUrl" value="/category/add"/>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <span class="navbar-brand">Новости</span>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${newsUrl}">Home</a></li>
            <li><a href="${createUrl}">Создать новость</a></li>
            <li><a href="${categoryUrl}">Создать категорию</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h1> Редактировкать новость</h1>
    <c:url var="saveUrl" value="/news/edit"/>
    <form:form modelAttribute="newsAttribute" id="news-form" method="POST" action="${saveUrl}" >


        <form:hidden path="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label" for="title">Заголовок</label>
                <div class="col-md7">
                    <form:input required="required" path="title" class="form-control input-sm" ></form:input>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label" for="content">Контент</label>
                <div class="col-md7">
                    <form:textarea rows="10" required="required" path="content" class="form-control input-sm"></form:textarea>
                </div>
            </div>
        </div>

        <p><b>Теги</b></p>
        <c:choose>
            <c:when test="${categories != null && categories.size() > 0}">
                <select multiple name="tags" size="10" required>

                    <c:forEach items="${categories}" var="tag">
                     <c:choose>
                        <c:when test="${newsAttribute.categories != null && newsAttribute.categories.contains(tag)}">
                            <option selected value="${tag.id}">${tag.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${tag.id}">${tag.name}</option>
                        </c:otherwise>
                     </c:choose>
                    </c:forEach>

                </select>
            </c:when>
            <c:otherwise>
                <p>Тегов нету. Создайте теги</p>
            </c:otherwise>
        </c:choose>






        <br />
        <br />

        <input type="submit" value="Редактировать" class="btn btn-warning">

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
