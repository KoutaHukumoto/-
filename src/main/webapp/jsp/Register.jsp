<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="model.Status" %>
<% Status status = (Status) session.getAttribute("status"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了</title>
<link rel="stylesheet" href="css/Register.css">
</head>
<body>
	<script>
		function goToPage() {
			window.location.href = 'toppage.html';
		}
	</script>
	<div class="comp">
	<h1>登録が完了しました！</h1>
	</div>
	
	<div class="ava">
	<label>アバター:</label>
	</div>
	
	<div class="status">
	<label class="inp">ID:</label> 
	<label class="ginp"><%= status.getId() %></label>
	<label class="inp">ニックネーム:</label>
	<label class="ginp"><%= status.getName() %></label>
	<label class="inp">パスワード:</label>
	<label class="ginp"><%= status.getPass() %></label>
	</div>

	<div class="kickback">
	<button type="button" onclick="goToPage()">完了</button>
	</div>
</body>
</html>