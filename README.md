# どすこいアナリティクス
チーム開発

GitHubの簡単な操作説明

https://yuta-iwase.github.io/doc/pdf/github_tutorial.pdf

インポートで使用するパスワードの作成方法

https://yuta-iwase.github.io/doc/pdf/github_tutorial.pdf

インポートで使用するパスワードの作成方法

https://qiita.com/shiro01/items/e886aa1e4beb404f9038

アニメーションに用いているanime.jsについての説明

https://uxbear.me/tech-animejs-2/

GitHubの用語説明（ここで分からない所があったら福本に聞いてください）

https://liginc.co.jp/246190

function(関数)についての説明

https://developer.mozilla.org/ja/docs/Web/JavaScript/Guide/Functions


<script>
	// クッキーを確認する関数
	function checkIfFromServlet() {
		// クッキー文字列を取得
		const cookies = document.cookie;

		// "fromServlet=true"が含まれているか確認
		if (cookies.includes("loginServlet=true")) {

			errorMessage.style.display = 'block'; // メッセージを表示
			return false; // フォームの送信を防ぐ
			// クッキーを削除
			document.cookie = "fromServlet=; max-age=0";
		}
	}

	// ページ読み込み時にチェックを実行
	window.onload = checkIfFromServlet;
</script>
