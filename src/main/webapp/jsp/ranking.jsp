<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.character" %>
<%@ page import="java.util.List" %> <!-- List をインポート -->
<%@ page import="model.Status"%>
<%Status status = (Status) request.getAttribute("status");%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/ranking.css">
    <title>9年後</title>
</head>

<body>
    <h1>ランキング</h1>

    <!-- 検索フォーム -->
    <form method="POST" action="searchServlet">
        <input type="text" name="characterName" placeholder="キャラクター名で検索">
        		<input type="hidden" name="name" value="<%=status.getName()%>"> 
				<input type="hidden" name="id"value="<%=status.getId()%>">
				<input type="hidden" name="hp" value="<%=status.getHp()%>">
				<input type="hidden" name="attack" value="<%=status.getAttack()%>">
				<input type="hidden" name="defense"value="<%=status.getDefense()%>"> 
				<input type="hidden"name="speed" value="<%=status.getSpeed()%>"> 
				<input type="hidden" name="itemid" value=1>
				<input type="hidden" name="dungeonid" value="<%=status.getDungeonid()%>">
				<button type="submit">検索</button>
    </form>
    
    

    <table>
        <thead>
            <tr>
                <th>Rank</th>
                <th></th>
                <th>Name</th>
                <th></th>
                <th>Score</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // リクエスト属性から dataList を取得
                List<character> dataList = (List<character>) request.getAttribute("dataList");

                // dataList が null でないことを確認
                if (dataList != null) {
                    int rank = 1;  // rank 変数はここで一度だけ宣言
                    for (character data : dataList) { 
            %>
                <tr>
                    <td><%= rank %>位</td>
                    <td></td>
                    <td><%= data.getCharacterName() %></td>
                    <td></td>
                    <td><%= data.getDungeonId() %></td>
                </tr>
                <% 
                    rank++; 
                    }
                } else {
                %>
                    <p>データがありません。</p>
                <% 
                } 
            %>
        </tbody>
    </table>

   
	
	<form action="/Dosukoi-Analytics/backServlet" method="POST">
				<input type="hidden" name="name" value="<%=status.getName()%>"> 
				<input type="hidden" name="id"value="<%=status.getId()%>">
				<input type="hidden" name="hp" value="<%=status.getHp()%>">
				<input type="hidden" name="attack" value="<%=status.getAttack()%>">
				<input type="hidden" name="defense"value="<%=status.getDefense()%>"> 
				<input type="hidden"name="speed" value="<%=status.getSpeed()%>"> 
				<input type="hidden" name="itemid" value=1>
				<input type="hidden" name="dungeonid" value="<%=status.getDungeonid()%>">
				<button type="submit">もどる</button>
			</form>
	
</body>

</html>
