
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <script src="<c:url value="/resources/js/jquery-3.0.0.min.js" />" ></script>
</head>
<body>

<h1> Создать новость</h1>
<c:url var="saveUrl" value="/news/add"/>
    <form:form modelAttribute="newsAttribute" id="news-form" method="POST" action="${saveUrl}" onsubmit="call()">
        <form:label path="title">Заголовок</form:label>
        <form:input path="title"></form:input>

        <form:label path="content">Содержание</form:label>
        <form:textarea path="content"></form:textarea>

        <input type="text" name="categories">
        <input type="submit" value="Save">

    </form:form>

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
