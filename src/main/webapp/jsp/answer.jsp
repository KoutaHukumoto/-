
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
	<script>
		function changeDisplay(idname) {
			var obj = document.getElementById(idname);
			obj.style.display = 'none';
		}

		function changeColor(idname) {
			var obj = document.getElementById(idname);
			obj.style.color = '#ff7f7f';
		}
	</script>

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
					<div class="up_status_hp" id="up_status_hp">
						→
						<%=request.getAttribute("up_status")%></div>
					攻撃 :<%=status.getAttack()%>
					<div class="up_status_attack" id="up_status_attack">
						→
						<%=request.getAttribute("up_status")%></div>
					防御 :<%=status.getDefense()%>
					<div class="up_status_defense" id="up_status_defense">
						→
						<%=request.getAttribute("up_status")%></div>
					すばやさ :<%=status.getSpeed()%>
					<div class="up_status_speed" id="up_status_speed">
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
				boolean isCorrect = getAnswer.getSelected_answer() != null
				&& getAnswer.getSelected_answer().equals(getAnswer.getAnswer());
				String backgroundColor = isCorrect ? "#ff7f7f" : "#7f7fff";
			%>
			<div class="text">
				問題<%=i + 1%>:
				<%=getAnswer.getQuestionText()%></div>
			<div class="model_answer">
				模範解答:
				<%=getAnswer.getAnswer()%></div>
			<div class="selected_answer"
				style="background-color: <%=backgroundColor%>;">
				選択した解答:
				<%=(getAnswer.getSelected_answer() == null || getAnswer.getSelected_answer().isEmpty())
		? "無回答"
		: getAnswer.getSelected_answer()%>
			</div>
			<%
			}
			%>
		</div>

		<%-- 道場button無くてもいい説いらない説 --%>

		<input type="checkbox" id="popup">
		<div id="overlay">
			<div for="popup" id="bg_gray"></div>
			<div id="window">
				<div id="msg">
					<%
					if (totalAnswer < 5) {
					%>
					報酬を獲得出来ませんでした…
					<%
					} else {
					if (request.getAttribute("change_status").equals("装備品")) {
					%>
					<div class="result_item">
						<%=request.getAttribute("change_status")%>を入手しました！！<br>
						効果：攻撃+999になる
						<form action="/Dosukoi-Analytics/questionServlet" method="POST">
							<input type="hidden" name="name" value="<%=status.getName()%>">
							<input type="hidden" name="id" value="<%=status.getId()%>">
							<button type="submit">いいえ</button>
						</form>
						<button>はい</button>
					</div>
					<%
					} else {
					%>
					<%=request.getAttribute("change_status")%>が<%=request.getAttribute("total_answer_status")%>上がった！！
					<%
					}
					}
					%>
				</div>
			</div>
		</div>

		<div class="next">
			<label for="popup" id="txt_label"
				<%if (request.getAttribute("change_status").equals("装備品")) {%>
				onclick="changeDisplay('dojyo');" <%} else {%>
				onclick="changeColor('up_status_<%=request.getAttribute("change_status_id")%>');"
				<%}%>>次へ</label>
		</div>

		<div id="dojyo">
			<form action="/Dosukoi-Analytics/questionServlet" method="POST">
				<input type="hidden" name="name" value="<%=status.getName()%>">
				<input type="hidden" name="id" value="<%=status.getId()%>">
				<button type="submit">道場へ戻る</button>
		</div>
	</div>

</body>
</html>
