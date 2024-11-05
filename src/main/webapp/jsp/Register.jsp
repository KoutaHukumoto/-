<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="model.Status" %>
<% Status status = (Status) session.getAttribute("status"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了</title>
</head>
<body>
	<script>
		function goToPage() {
			window.location.href = 'toppage.html';
		}
	</script>
	<h1>登録が完了しました！</h1>
	<p>ID:<%= status.getId() %></p>
	<p>ニックネーム:<%= status.getName() %></p>
	<p>パスワード:<%= status.getPass() %></p>

	<button type="button" onclick="goToPage()">戻る</button>
</body>
</html>