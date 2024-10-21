<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Account" %>


    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TOP Page</title>
    <style>
        .error {
            color: red;
            display: none; 
        }
      
        body {
        	
            background-color: black;
            color: white;
            display: flex;
            justify-content: center; /* 水平方向に中央揃え */
            align-items: center; /* 垂直方向に中央揃え */
            height: 100vh; /* ビューポートの高さを占める */
            margin: 0; /* マージンをリセット */
        }
        
        h1 {
            font-size: 6em; 
            margin: 0;
            width: 100%; 
            text-align: center;  
        }
       .login {
            width: 100%; 
            text-align: right; /* 右揃え */
        }
        .login label {
            display: inline-block; /* ラベルをインラインブロックに */
            text-align: right; /* ラベルを右揃え */
            margin-bottom: 5px; /* ラベルと入力の間に余白を追加 */
            margin-right: 10px; /* ラベルと入力フィールドの間に余白を追加 */
        }
        .login input {
            display: inline-block; /* 入力フィールドをインラインブロックに */
            width: calc(100% - 120px); /* 幅を調整 */
            padding: 5px; /* 入力フィールドの内側に余白を追加 */
            margin-bottom: 10px; /* 入力フィールドの下に余白を追加 */
            border: 1px solid white; /* 境界線を追加 */
            background-color: transparent; /* 背景を透明に */
            color: white; /* テキストの色を白に */
            text-align: left; /* テキストを右揃え */
        }
        button {
		    display: block;
		    text-align: center;
		    border: 2px solid white;
		    padding: 10px;
		    margin-bottom: 10px;
		    font-size: 1.5em;
		    color: white;
		    background-color: transparent;
		    width: 100%; /* aタグの幅に合わせる */
		    cursor: pointer; /* 手のマークが出るように */
		    text-decoration: none; /* テキスト装飾を無くす */
		}
        button:hover {
    		background-color: grey; /* hover時の背景色 */
		}
        button {
    		background: none; /* デフォルトのボタンの背景を消す */
    		border: none; /* デフォルトのボーダーを消す */
    		padding: 0; /* デフォルトのパディングを消す */
		}
        .login_btn {
   			position: absolute; /* 絶対位置指定 */
    		bottom: 0; 
    		left: 0; 
			margin-top: 20px;
			padding: 10px;
			border: 2px solid white;
			width: 30%;
		}
		.new_btn {
   			position: absolute; /* 絶対位置指定 */
    		bottom: 0; 
    		right: 0; 

			margin-top: 20px;
			padding: 10px;
			border: 2px solid white;
			width: 30%;
		}
        
    </style>
</head>
<body>
    <script>
        function validateForm() {
            const id = document.querySelector('input[name="id"]').value;
            const pass = document.querySelector('input[name="pass"]').value;
            const errorMessage = document.getElementById('error-message');

            if (!id || !pass) {
                errorMessage.style.display = 'block'; // メッセージを表示
                return false; // フォームの送信を防ぐ
            }

            errorMessage.style.display = 'none'; // メッセージを非表示
            return true; // フォームを送信
        }
    </script>
	<form action="/Dosukoi-Analytics/LoginServlet" method="post">
        <div class="h1">
            <h1>9年後の君へ</h1>
        </div>
        <div class="login">
            <label>ID:</label>
            <input type="text" name="id" class="form-control">
            <br>
            <label>パスワード:</label>
            <input type="password" name="pass" class="form-control">
            <p id="error-message" class="error">※IDまたはパスワードが間違っています。</p>
        </div>
   
        <div class="login_btn">
            <button type="submit" class="btn btn-primary">ログイン</button>
        </div>
        <div class="new_btn">
            <button type="button">新規登録</button>
        </div>
     </form>
</body>
</html>
