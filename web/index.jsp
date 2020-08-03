<%--
  Created by IntelliJ IDEA.
  User: pdd20
  Date: 2020/7/31
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<script src="${pageContext.request.contentType}/static/jquery.js"></script>
	<title>首页</title>
	<style type="text/css">
        a {
            text-decoration: none;
            color: black;
            font-size: 18px;
        }

        h3 {
            width: 250px;
            height: 50px;
            margin: 200px auto;
            text-align: center;
            line-height: 50px;
            background: darkcyan;
            border-radius: 25px;
        }
	</style>
	<script>
        function b() {
            $.post({
                url: "${pageContext.request.contextPath}/book/ajax",
                data: {"name": $("#input").val()},
                success: function (data) {
                    alert(data)
                }
            })

        }
	</script>
</head>
<body>
<h1>${sessionScope.username}</h1>
<h3><a href="${pageContext.request.contextPath}/book/getAllBooks">查询所有图书</a></h3>
<h1>${sessionScope.username}</h1>
用户名：
<%--测试ajax--%>
<input type="text" name="name" id="input" onblur="b()">


<form action="${pageContext.request.contextPath}/file/upload" enctype="multipart/form-data" method="post">
	上传文件
	<input type="file" name="file">
	<input type="submit" value="上传">
</form>
<form action="${pageContext.request.contextPath}/file/download" method="get">
	输入你要下载的文件：
	<input type="text" name="fileName">
	<input type="submit" value="下载">
</form>
</body>
</html>
