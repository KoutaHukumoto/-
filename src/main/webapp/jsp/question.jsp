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
		<% for (int i = 0; i < questionlist.size(); i++) { 
			question question = questionlist.get(i); 
		%>
			<div class="question">
				<p>問題 : <%= question.getQuestionText() %></p>
				<ul>
					<li><%= question.getAnswer() %></li>
					<li><%= question.getFakeAnswer1() %></li>
					<li><%= question.getFakeAnswer2() %></li>
					<li><%= question.getFakeAnswer3() %></li>
				</ul>
			</div>
		<% } %>

	<div class="back">
		<a href="./dojyo.html">もどる</a>
	</div>

	<div class="confirmation">
		<a href="#">答え合わせ</a>
	</div>
</body>
</html>