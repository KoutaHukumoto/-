<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.question"%>
<%@ page import="java.util.List"%>
<%
List<question> questionlist = (List<question>) request.getAttribute("questionlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題</title>
<link rel="stylesheet" href="css/question.css">
</head>
<body>
	<div class="title">科目：難易度</div>
	<div class="question">
		<p>
			問題:
			<%=question.getQuestionText()%></p>
		<ul>
			<li><%=question.getOption1()%></li>
			<li><%=question.getOption2()%></li>
			<li><%=question.getOption3()%></li>
			<li><%=question.getOption4()%></li>
		</ul>
	</div>

	<div class="back">
		<a href="./dojyo.html">もどる</a>
	</div>

	<div class="confirmation">
		<a href="#">答え合わせ</a>
	</div>
</body>
</html>