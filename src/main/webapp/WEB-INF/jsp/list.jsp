<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <title>Title</title>
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <c:forEach items="${news}" var="v">

                    <div class="list-group">
                        <div class="list-group-item list-group-item-info">
                            <p>${v.title}</p>
                            <p>${v.content}</p>
                            <c:if test="${v.categories != null && v.categories.size() > 0}">
                                <ul class="list-inline">
                                    <c:forEach items="${v.categories}" var="cat">
                                        <li>${cat.name}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>


</ul>
</body>
</html>
