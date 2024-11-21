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
					問題<%=i + 1%>: <input type="hidden" name="text_<%=i%>"
						value="<%=question.getQuestionText()%>">
					<%=question.getQuestionText()%>
				</p>
			</div>
			<div class="answer">
   				 <%
    				List<String> choices = question.getShuffledChoices();
    				for (int j = 0; j < choices.size(); j++) {
    			 %>
    			<div class="answer<%=j + 1%>">
        			<input type="radio" id="<%=j + 1%>_<%=i%>" name="answer_<%=i%>"
           			 value="<%=choices.get(j)%>"> 
        			<label for="<%=j + 1%>_<%=i%>">
            		<%=choices.get(j)%>
        			</label>
    			</div>
    			<%
    			}
    			%>
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
		<button type="submit" name="size" value="<%=questionlist.size()%>">答え合わせ</button>
	</div>
	</form>
</body>
</html>