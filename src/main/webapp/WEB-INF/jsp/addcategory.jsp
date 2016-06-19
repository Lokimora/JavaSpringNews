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
<c:url var="createUrl" value="/news/add"/>
<c:url var="categoryUrl" value="/category/add"/>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <span class="navbar-brand">Новости</span>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${newsUrl}">Home</a></li>
            <li><a href="${createUrl}"}>Создать новость</a></li>
            <li class="active"><a href="">Создать категорию</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h1> Создать новость</h1>
    <c:url var="saveUrl" value="/news/add"/>
    <form:form  id="category-form" method="POST" action="${categoryUrl}" >

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-label" for="name">Название категории</label>
                <div class="col-md7">
                    <input type="text" id="name" name="name" class="form-control input-sm" required>
                </div>
            </div>
        </div>



        <input type="submit" value="Добавить категорию" class="btn btn-success">

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
