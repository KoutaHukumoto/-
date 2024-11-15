<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Status"%>
<%
Status status = (Status) request.getAttribute("status");
%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>マイページ</title>
<link rel="stylesheet" href="css/mypage.css">
</head>

<body>
	<div class="container">
		<div class="header">
			<div class="page-number">マイページ</div>
		</div>

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

		<div class="item">
			<h2>装備品</h2>
			<div class="item_child">
				<p>あまのさかほこ</p>
				<p>攻撃力とすばやさを2倍にする</p>
			</div>
		</div>

		<div class="clear"></div>

		<div class="menu">
			<form action="/Dosukoi-Analytics/dungeonServlet" method="POST">
				<input type="hidden" name="name" value="<%=status.getName()%>">
				<inputtype ="hidden" name="id" value="<%=status.getId()%>">
				<input type="hidden" name="hp" value="<%=status.getHp()%>">
				<input type="hidden" name="attack" value="<%=status.getAttack()%>">
				<input type="hidden" name="defense" value="<%=status.getDefense()%>">
				<input type="hidden" name="speed" value="<%=status.getSpeed()%>">
				<input type="hidden" name="item" value="あまのさかほこ"> <input
					type="hidden" name="itemEffect" value="攻撃力とすばやさを2倍にする">
				<button type="submit">ダンジョン</button>
			</form>
			<a href="#training">道場</a> <a href="#ranking">ランキング</a>
		</div>

		<div class="logout">
			<a href="/Dosukoi-Analytics/logoutServlet">ログアウト</a>
		</div>
	</div>
</body>

</html>
