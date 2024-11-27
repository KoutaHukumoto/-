
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Status"%>
<%
Status status = (Status) request.getAttribute("status");
%>

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
					<div class="item">
						<div class="item_child">
							<p>装備品：あまのさかほこ</p>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div class="subject"><%=request.getAttribute("s_id")%>：<%=request.getAttribute("d_id")%></div>
		<div class="result">
			<%=size%>問中：
			<%=request.getAttribute("total_answer")%>問正解
		</div>
		<div class="answer">解答</div>
		<div class="total_result">
			<%
			for (int i = 0; i < size; i++) {
				answer getAnswer = list.get(i);
			%>
			<div class="text">
				問題<%=i + 1%>:
				<%=getAnswer.getQuestionText()%>
			</div>
			<div class="model_answer">
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

		<input type="checkbox" id="popup">
		<div id="overlay">
			<label for="popup" id="bg_gray"></label>
			<div id="window">
				<div id="msg">HPが5上がった！！</div>
			</div>
		</div>

		<div class="next">
			<label for="popup" id="txt_label">次へ</label>
		</div>

		<div id="dojyo">
			<form action="/Dosukoi-Analytics/questionServlet" method="POST">
				<input type="hidden" name="name" value="<%=status.getName()%>">
				<input type="hidden" name="id" value="<%=status.getId()%>">
				<input type="hidden" name="hp" value="<%=status.getHp()%>">
				<input type="hidden" name="attack" value="<%=status.getAttack()%>">
				<input type="hidden" name="defense" value="<%=status.getDefense()%>">
				<input type="hidden" name="speed" value="<%=status.getSpeed()%>">
				<input type="hidden" name="item" value="あまのさかほこ"> <input
					type="hidden" name="itemEffect" value="攻撃力とすばやさを2倍にする">
				<button type="submit">道場へ戻る</button>
		</div>
	</div>
</body>
</html>
