<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/news.css"/>" rel="stylesheet">
    <title>Title</title>
</head>
<body>



<c:url var="saveUrl" value="/news/edit"/>
<c:url var="createUrl" value="/news/add"/>
<c:url var="categoryUrl" value="/category/add"/>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <span class="navbar-brand">Новости</span>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="">Home</a></li>
            <li><a href="${createUrl}">Создать новость</a></li>
            <li><a href="${categoryUrl}">Создать категорию</a></li>
        </ul>
    </div>
</nav>

    <div class="container">

        <h2 align="center" >Всякие новости</h2>

        <br/>


            <c:forEach items="${news}" var="v">
                <c:url var="deleteUrl" value="/news/delete?id=${v.getid()}" />
                <c:url var="editUrl" value="/news/edit?id=${v.getid()}" />
                <c:url var="createUrl" value="/news/add"/>

                <div class="row" style="border-bottom: 1px solid #999">
                    <div class="row"  style="background-color: #8a9196; padding: 10px; border-radius:5px;">
                        <div class="col-md-10" >
                            <h4>${v.title}</h4>
                        </div>
                        <div class="col-md-2" >
                            <span><fmt:formatDate value="${v.date}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12" >
                            <div class="well" style="margin-top: 15px; background-color: #1c1e22">
                                <p>${v.content}</p>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-bottom: 5px">
                        <div class="col-md-9" >
                            <c:choose>
                                <c:when test="${v.categories != null && v.categories.size() > 0}">
                                    <ul class="list-inline">
                                        <li>Теги: </li>
                                        <c:forEach items="${v.categories}" var="cat">
                                            <li class="btn btn-default">${cat.name}</li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <p>Тегов нету</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-3"  >
                            <button value="delete" class="btn btn-default"><a href="${deleteUrl}">Удалить</a></button>
                            <button value="edit" class="btn btn-default"><a href="${editUrl}">Редактировать</a></button>
                        </div>
                    </div>
                </div>
                <br/>

            </c:forEach>

         </div>

</body>
</html>
