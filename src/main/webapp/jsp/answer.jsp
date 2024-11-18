<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="subject">科目：難易度</div>
		<div class="result"><%=request.getAttribute("size") %>問中： 10問正解
		</div>
		<div class="dojyo">
			<a href="./dojyo.html">道場</a>
		</div>
		<div class="next">
			<a href="#">次へ</a>
		</div>
	</div>
</body>
</html>