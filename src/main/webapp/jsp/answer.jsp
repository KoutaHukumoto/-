
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Status"%>
<%Status status = (Status) request.getAttribute("status");%>

<%@ page import="model.answer,java.util.ArrayList"%>
<%
int size = (int) request.getAttribute("size");
%>
<%
ArrayList<answer> list = (ArrayList<answer>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>答え合わせ</title>
<link rel="stylesheet" href="css/answer.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<div class="page-number">結果</div>
		</div>
		<div class="character">
			<div class="avatar-section">
				<img src="画像/avater.jpg" alt="avatar">
				<p><%=status.getName()%></p>
				<p>
					ID :<%=status.getId()%></p>
			</div>

			<div class="status">
				<h2>ステータス</h2>
				<div class="status_child">
					<p>
						HP :<%=status.getHp()%></p>
					<p>
						攻撃 :<%=status.getAttack()%></p>
					<p>
						防御 :<%=status.getDefense()%></p>
					<p>
						すばやさ :<%=status.getSpeed()%></p>
				</div>
			</div>




		</div>

		<div class="subject"><%=request.getAttribute("s_id")%>：<%=request.getAttribute("d_id")%></div>
		<div class="result">
			<%=size%>問中：
			<%=request.getAttribute("total_answer")%>問正解
		</div>
		<div class="kaito">解答</div>
		<div class="total_result">
			<%
			for (int i = 0; i < size; i++) {
				answer getAnswer = list.get(i);
			%>
			<div class="text">
				問題<%=i + 1%>:
				<%=getAnswer.getQuestionText()%>
			</div>
			<div class="answer">
				模範解答:
				<%=getAnswer.getAnswer()%>
			</div>
			<div class="selected_answer">
				選択した解答:
				<%
			if (getAnswer.getSelected_answer() == null || getAnswer.getSelected_answer().isEmpty()) {
			%>
				無回答
				<%
			} else {
			%>
				<%=getAnswer.getSelected_answer()%>
				<%
				}
				%>
			</div>
			<%
			}
			%>
		</div>
	
			<%-- 道場button無くてもいい説いらない説 --%>
		
		<div class="next">
			<a href="#">次へ</a>
		</div>
	</div>
</body>
</html>
