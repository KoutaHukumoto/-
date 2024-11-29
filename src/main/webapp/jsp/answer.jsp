
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
int totalAnswer = (int) request.getAttribute("total_answer");
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
					HP :<%=status.getHp()%>
					<div class="up_status">
						→
						<%=request.getAttribute("up_status")%></div>
					攻撃 :<%=status.getAttack()%>
					<div class="up_status">
						→
						<%=request.getAttribute("up_status")%></div>
					防御 :<%=status.getDefense()%>
					<div class="up_status">
						→
						<%=request.getAttribute("up_status")%></div>
					すばやさ :<%=status.getSpeed()%>
					<div class="up_status">
						→
						<%=request.getAttribute("up_status")%></div>
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
			<%=totalAnswer%>問正解
		</div>
		<div class="answer">
			<p>解答</p>
			正解：
			<div class="answer_red">赤色</div>
			不正解：
			<div class="answer_blue">青色</div>
		</div>
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
			<div class="selected_answer"
				style="background-color: <%=(getAnswer.getSelected_answer() != null && getAnswer.getSelected_answer().equals(getAnswer.getAnswer()))
		? "#ff7f7f"
		: "#7f7fff"%>;">
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
				<div id="msg">
					<%
					if (totalAnswer <= 5) {
					%>
					報酬を獲得出来ませんでした…
					<%
					} else {
					if (request.getAttribute("change_status").equals("装備品")) {
					%>
					<%=request.getAttribute("change_status")%>を入手しました！！<br>
					効果：攻撃+999になる
					<%
					} else {
					%>
					<%=request.getAttribute("change_status")%>が<%=totalAnswer%>上がった！！
					<%
					}
					}
					%>
				</div>
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
