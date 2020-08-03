<%--
  Created by IntelliJ IDEA.
  User: pdd20
  Date: 2020/8/1
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>添加书籍</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h1>
					<small>书城——修改书籍</small>
				</h1>
				<h1>${sessionScope.username}</h1>
			</div>
		</div>
	</div>
	
	<form action="${pageContext.request.contextPath}/book/updateBook">
		<input type="hidden"  name="bookID" value="${book.bookID}">
		<div class="form-group">
			<laber for="bookName">书籍标题:</laber>
			<input type="text" class="form-control" name="bookName" value="${book.bookName}" required/>
		</div>
		<div class="form-group">
			<laber for="bookCounts">书籍数量:</laber>
			<input type="text" class="form-control" name="bookCounts" value="${book.bookCounts}" required/>
		</div>
		<div class="form-group">
			<laber for="detail">书籍详情:</laber>
			<input type="text" class="form-control" name="detail" value="${book.detail}" required/>
		</div>
		<button type="submit" class="btn btn-default">修改</button>
	</form>
</div>
</body>
</html>
