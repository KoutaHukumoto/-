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
	<%
	for (int i = 0; i < questionlist.size(); i++) {
		question question = questionlist.get(i);
	%>
	<div class="question">
		<p>
			問題<%=i + 1%>:
			<%=question.getQuestionText()%></p>
	</div>
	<div class="answer">

		<div class="answer1">
			<input type="radio" id="1" name="answer<%=i%>"><label for="1">
				<%=question.getAnswer()%></label>
		</div>
		<div class="answer2">
			<input type="radio" id="2" name="answer<%=i%>"><label for="2"><%=question.getFakeAnswer1()%></label>
		</div>
		<div class="answer3">
			<input type="radio" id="3" name="answer<%=i%>"><label for="3"><%=question.getFakeAnswer2()%></label>
		</div>
		<div class="answer4">
			<input type="radio" id="4" name="answer<%=i%>"><label for="4"><%=question.getFakeAnswer3()%></label>
		</div>

	</div>
	<%
	}
	%>

	<div class="back">
		<a href="./dojyo.html">もどる</a>
	</div>

	<div class="confirmation">
		<a href="#">答え合わせ</a>
	</div>
</body>
</html>