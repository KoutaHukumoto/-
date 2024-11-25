<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.character" %>
<%@ page import="java.util.List" %> <!-- List をインポート -->
<%@ page import="model.Status"%>
<%Status status = (Status) request.getAttribute("status");%>

<head>
	<meta charset="UTF-8">
	<title>道場</title>
	<link rel="stylesheet" href="css/dojyo.css">
	<script>
		function setSubject(subject) {
			// hidden要素に科目を設定
			document.getElementById("s_id").value = subject;
		}

		function setDifficulty(difficulty) {
			// hidden要素に難易度を設定
			document.getElementById("d_id").value = difficulty;
		}
	</script>
</head>

<body>
	<div class="container">
		<div class="header">
			<div class="page-number">道場</div>
		</div>
		<div class="character">
			<div class="avatar-section">
				<img src="画像/avater.jpg" alt="avatar">
				<p>田中</p>
			</div>

			<div class="status">
				<div class="status_child">
					<p>HP : 100</p>
					<p>攻撃 : 10</p>
					<p>防御 : 10</p>
					<p>すばやさ : 10</p>
					<div class="item">
						<div class="item_child">
							<p>装備品：あまのさかほこ</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<form action="/Dosukoi-Analytics/dojyoServlet" method="POST">
			<input type="hidden" name="name" value="<%=status.getName()%>"> 
			<input type="hidden" name="id"value="<%=status.getId()%>">
			<input type="hidden" name="hp" value="<%=status.getHp()%>">
			<input type="hidden" name="attack" value="<%=status.getAttack()%>">
			<input type="hidden" name="defense"value="<%=status.getDefense()%>"> 
			<input type="hidden"name="speed" value="<%=status.getSpeed()%>"> 
			<input type="hidden" name="item" value="あまのさかほこ">
			<input type="hidden" name="itemEffect" value="攻撃力とすばやさを2倍にする">
			<!-- hidden inputs for subject and difficulty -->
			<input type="hidden" name="s_id" id="s_id" value="">
			<input type="hidden" name="d_id" id="d_id" value="">

			<div class="subject">
				<!-- ボタンをクリックで科目を設定 -->
				<label for="popup" onclick="setSubject('国語')">国語</label>
				<label for="popup" onclick="setSubject('数学')">数学</label>
				<label for="popup" onclick="setSubject('英語')">英語</label>
				<label for="popup" onclick="setSubject('理科')">理科</label>
				<label for="popup" onclick="setSubject('社会')">社会</label>
			</div>

			<div id="ex">
				<a> 攻撃UP！</a>
				<a> 体力UP！</a>
				<a> 防御UP！</a>
				<a> すばやさUP！</a>
				<a> 装備品GET！</a>
			</div>

			<input type="checkbox" id="popup">
			<div id="overlay">
				<label for="popup" id="bg_gray"></label>
				<div id="window">
					<label for="popup" id="btn_cloth">
						<span></span>
					</label>
					<div id="msg">
						<!-- ボタンをクリックで難易度を設定 -->
						<button id="link1" type="submit" onclick="setDifficulty('初級')">初級</button>
						<button id="link2" type="submit" onclick="setDifficulty('中級')">中級</button>
						<button id="link3" type="submit" onclick="setDifficulty('上級')">上級</button>
					</div>
				</div>
			</div>
		</form>
		<div class="mypage">
			<a href="./mypage.html">マイページ</a>
		</div>
	</div>


</body>

</html>