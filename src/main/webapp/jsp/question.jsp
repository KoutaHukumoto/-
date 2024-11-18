<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.question"%>
<%@ page import="java.util.List"%>
<%
List<question> questionlist = (List<question>) request.getAttribute("questionlist");
%>
<%
question category = (question) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題</title>
<link rel="stylesheet" href="css/question.css">
</head>
<body>
	<div class="title"><%=category.getCategory()%>：<%=category.getDifficulty()%></div>
	<div class="quiz">
		<form action="/Dosukoi-Analytics/answerServlet" method="POST">
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
					<input type="radio" id="1_<%=i%>" name="answer_<%=i%>"> <label
						for="1_<%=i%>"> <%=question.getAnswer()%>
					</label>
				</div>
				<div class="answer2">
					<input type="radio" id="2_<%=i%>" name="answer_<%=i%>"> <label
						for="2_<%=i%>"> <%=question.getFakeAnswer1()%>
					</label>
				</div>
				<div class="answer3">
					<input type="radio" id="3_<%=i%>" name="answer_<%=i%>"> <label
						for="3_<%=i%>"> <%=question.getFakeAnswer2()%>
					</label>
				</div>
				<div class="answer4">
					<input type="radio" id="4_<%=i%>" name="answer_<%=i%>"> <label
						for="4_<%=i%>"> <%=question.getFakeAnswer3()%>
					</label>
				</div>
			</div>

			<%
			}
			%>
		
	</div>

	<div class="back">
		<a href="./dojyo.html">もどる</a>
	</div>
	<div class="confirmation">
		<input type="hidden" name="s_id" value="<%=category.getCategory()%>">
		<input type="hidden" name="d_id" value="<%=category.getDifficulty()%>">
		<button type="submit" name="size"
			value="<%=question.getQuestionText()%>">答え合わせ</button>
	</div>
	</form>
</body>
</html>
