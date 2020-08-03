<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pdd20
  Date: 2020/7/31
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>查看所有书籍</title>
	<%--<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>--%>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h1>
					<small style="font-size: 38px">书城——显示所有书籍</small>
				</h1>
				<h1>${sessionScope.username}</h1>
				<div style="float: right;"><span style="color: red;font-size: 18px;font-weight: bold">${error}</span></div>
			</div>
			<div class="row">
				<div class="col-md-8 column">
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/book/goAddBook">添加书籍</a>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/book/getAllBooks">显示所有书籍</a>
				</div>
				
				<div class="col-md-4 column">
					<div style="float: right">
						<form action="${pageContext.request.contextPath}/book/queryBookByQueryString"
						      class="form-inline">
							<input type="text" placeholder="请输入你要查找的书的名字" name="bookName">
							<input type="submit" value="查询" class="btn btn-primary">
						</form>
					</div>
				</div>
				
			</div>
			<%--<div class="col-md-4 column"></div>
			<div class="col-md-4 column" style="float: right">
				<form action="#" class="form-inline">
					<input type="text" placeholder="请输入要查询书籍的名字">
					<input type="submit" value="查询" class="btn btn-primary">
				</form>
			</div>--%>
		</div>
	</div>
	<div class=" row clearfix">
		<div class="col-md-12 column">
			<table class="table table-hover table-striped">
				<thead>
				<tr>
					<th>书籍编号</th>
					<th>书籍标题</th>
					<th>书籍数量</th>
					<th>书籍详情</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="book" items="${list}">
					<tr>
						<td>${book.bookID}</td>
						<td>${book.bookName}</td>
						<td>${book.bookCounts}</td>
						<td>${book.detail}</td>
						<td>
							<a href="${pageContext.request.contextPath}/book/goUpdate/${book.bookID}">修改</a>
							&nbsp;|&nbsp;
							<a href="${pageContext.request.contextPath}/book/delBook/${book.bookID}">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
