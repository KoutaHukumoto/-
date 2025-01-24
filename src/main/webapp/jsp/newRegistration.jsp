<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.character"%>
<%
List<character> list = (List<character>) session.getAttribute("list");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>新規登録</title>
	<link rel="stylesheet" href="css/newRegistration.css">
	<meta name="csrf-token" content="your-csrf-token-here">
</head>

<body>

	<div class="point">名前・パスワードのルール<br>・名前は3文字以上10文字以下<br>・パスワードは5文字以上<br>・パスワードは英数字であること<br>・名前とパスワードに特殊記号は使用できません</div>
	<script src="js/security.js"></script>

	<form action="/Dosukoi-Analytics/RegisterServlet" method="POST" onsubmit="return check()">
		<div class="form">
			<label>ニックネーム:</label>
			<input type="text" name="name" class="form-control"><br>

			<label>パスワード:</label>
			<input type="password" name="pass" class="form-control"><br>

			<label>もう一度入力:</label>
			<input type="password" name="Password again" class="form-control"><br>
		</div>
		<div class="newreg">
			<p id="error-message" style="color:red;"></p>
		</div>
		<div class="newbutton">
			<button type="submit">新規登録</button>
		</div>
	</form>

	<br>

	<div class="backbutton">
		<button onclick="location.href='toppage.html'">戻る</button>
	</div>


	<script>
	
		function check() {
			const name = document.querySelector('input[name="name"]').value;
			const pass1 = document.querySelector('input[name="pass"]').value;
			const pass2 = document.querySelector('input[name="Password again"]').value;
			const errorMessage = document.getElementById('error-message');
			const regex = /^[a-zA-Z0-9]+$/; // パスワード用の英数字のみ許可する正規表現
			const nameRegex = /^[a-zA-Z0-9ぁ-んァ-ヶ一-龥々ー]+$/; // 名前に使用可能な文字を制限

			// 名前の長さを確認（5～8文字）
			if (name.length < 3 || name.length > 10) {
				errorMessage.textContent = "名前は3文字以上10文字以下にしてください。";
				return false;
			}

			// 名前に特殊記号が含まれていないか確認
			if (!nameRegex.test(name)) {
				errorMessage.textContent = "名前に特殊記号は使用できません。";
				return false;
			}

            <%for (int i = 0; i < list.size(); i++) {%>
            if (name === "<%=list.get(i)%>") {
                errorMessage.textContent = "同名が存在しています。";
                return false;
            }
        <%}%>
			
			// パスワードの長さを確認（5文字～）
			if (pass1.length < 5) {
				errorMessage.textContent = "パスワードは5文字以上にしてください。";
				return false;
			}

			
			// パスワードが英数字のみかどうかを確認
			if (!regex.test(pass1)) {
				errorMessage.textContent = "パスワードは英数字のみ使用できます。";
				return false;
			}

			// パスワードの一致を確認
			if (pass1 !== pass2) {
				errorMessage.textContent = "パスワードが一致しません。";
				return false;
			}
			
			// エラーメッセージをリセット
			errorMessage.textContent = "";
			return true;
		}
	</script>
</body>

</html>