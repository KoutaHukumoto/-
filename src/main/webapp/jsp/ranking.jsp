<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 
<%@ page import="model.character"%>
<%@ page import="java.util.List"%>
<!-- List をインポート -->
<%@ page import="model.Status"%>
<%
Status status = (Status) request.getAttribute("status");
%>
 
<!DOCTYPE html>
<html>
 
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/ranking.css">
<title>9年後</title>
<link rel="stylesheet" href="css/ranking.css">
</head>
 
<body>
	<div class="h1">
		<h1>ランキング</h1>
	</div>
	<div class="main">
		<div class="serch">
 
			<!-- 検索フォーム -->
			<form method="POST" action="searchServlet">
				<input type="text" name="characterName" placeholder="キャラクター名で検索">
				<input type="hidden" name="name" value="<%=status.getName()%>">
				<input type="hidden" name="id" value="<%=status.getId()%>">
				<input type="hidden" name="hp" value="<%=status.getHp()%>">
				<input type="hidden" name="attack" value="<%=status.getAttack()%>">
				<input type="hidden" name="defense" value="<%=status.getDefense()%>">
				<input type="hidden" name="speed" value="<%=status.getSpeed()%>">
				<input type="hidden" name="itemid" value=1> <input
					type="hidden" name="dungeonid" value="<%=status.getDungeonid()%>">
				<div class="serch_btn">
					<button type="submit">検索</button>
				</div>
			</form>
			<form method="POST" action="searchServlet">
				<input type="hidden" name=null >
				<input type="hidden" name="name" value="<%=status.getName()%>">
				<input type="hidden" name="id" value="<%=status.getId()%>">
				<input type="hidden" name="hp" value="<%=status.getHp()%>">
				<input type="hidden" name="attack" value="<%=status.getAttack()%>">
				<input type="hidden" name="defense" value="<%=status.getDefense()%>">
				<input type="hidden" name="speed" value="<%=status.getSpeed()%>">
				<input type="hidden" name="itemid" value=1> <input
					type="hidden" name="dungeonid" value="<%=status.getDungeonid()%>">
				<div class="serch_btn">
					<button type="submit">検索リセット</button>
				</div>
			</form>
		</div>
 
		<div class="quiz">
		<table>
			<thead>
				<tr>
					<th>Rank</th>
 
					<th>Name</th>
 
					<th>階数</th>
				</tr>
			</thead>
			<tbody>
				<%
				// リクエスト属性から dataList を取得
				List<character> dataList = (List<character>) request.getAttribute("dataList");
 
				// dataList が null でないことを確認
				if (dataList != null) {
					for (character data : dataList) {
				%>
				<tr>
					<td><%=data.getRank()%>位</td>
 
					<td><%=data.getCharacterName()%></td>
 
					<td><%=data.getDungeonId()%></td>
				</tr>
				<%
				
				}
				} else {
				%>
				<p>データがありません。</p>
				<%
				}
				%>
			</tbody>	
		</table>
		</div>
	</div>
	</div>
	<div class="back_btn">
 
		<form action="/Dosukoi-Analytics/backServlet" method="POST">
			<input type="hidden" name="name" value="<%=status.getName()%>">
			<input type="hidden" name="id" value="<%=status.getId()%>"> <input
				type="hidden" name="hp" value="<%=status.getHp()%>"> <input
				type="hidden" name="attack" value="<%=status.getAttack()%>">
			<input type="hidden" name="defense" value="<%=status.getDefense()%>">
			<input type="hidden" name="speed" value="<%=status.getSpeed()%>">
			<input type="hidden" name="itemid" value=1> <input
				type="hidden" name="dungeonid" value="<%=status.getDungeonid()%>">
			<button type="submit">もどる</button>
		</form>
	</div>
 
</body>
 
</html>
 