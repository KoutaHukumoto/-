<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.character"%>

<%  List<character> dataList = (List<character>) request.getAttribute("dataList");  %>

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

	<table>
		<thead>
			<tr>
				<th>Rank</th>
				<th></th>
				<th>Name</th>
				<th>Score</th>
			</tr>
		</thead>
		<tbody>
			<%int rank = 1;%>
			
			<%
			for (character data : dataList) {
			%>
			<tr>
				<td><%=rank%>位<td>
				<td><%=data.getCharacterName()%></td>
				<td><%=data. getDungeonId()%></td>
			</tr>
			<%
			rank++;
			}
			%>
		</tbody>
	</table>
	<a href="mypage.html" class="btn btn-secondary btn-lg" role="button">もどる</a>

</body>
</html>