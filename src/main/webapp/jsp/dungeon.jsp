<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ダンジョン</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/battle.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/3.2.1/anime.min.js"></script>
</head>
<body>
	<script src="js/dungeon.js"></script>
	<div class="bg-custom">
		<div class="music-control">
			<button id="playMusicBtn" class="btn btn-info" aria-label="音楽を再生または停止">🎵 音楽を停止</button>
		</div>

		<audio id="backgroundMusic" autoplay>
			<source src="audio/Umbra.mp3" type="audio/mp3">
		</audio>

		<div class="container text-center">
			<h1 class="level-title">1階層</h1>
			<h2>VS</h2>

			<div class="character-info">
				<div class="character pc" style="margin-right: 50px">
					<div class="character-stats">
						<p>HP: ${hp}</p>
						<p>攻撃: ${attack}</p>
						<p>防御: ${defense}</p>
						<p>すばやさ: ${speed}</p>
						<p>装備品: 「${item}」</p>
						<p>「${itemEffect}」</p>
					</div>
					<div class="character-img-box">
						<div class="character-header">
							<p class="character-name">${name}</p>
							<meter max="100" low="20" high="80" optimum="90" value="${hp}"></meter>
						</div>
						<img src="画像/avater.jpg" alt="Hero" class="character-img">
					</div>
				</div>

				<div class="character npc" style="margin-left: 50px">
					<div class="character-img-box">
						<div class="character-header">
							<p class="character-name">スライム</p>
							<meter max="100" low="20" high="80" optimum="90" value="100"></meter>
						</div>
						<img src="画像/スライム.png" alt="Enemy" class="character-img">
					</div>
					<div class="character-stats">
						<p>HP: 100</p>
						<p>攻撃: 10</p>
						<p>防御: 10</p>
						<p>すばやさ: 10</p>
						<p>装備品: 「経験値」</p>
						<p>「倒すと経験値を落とす」</p>
					</div>
				</div>
			</div>

			<div class="battle-message" id="battleMessage" style="display: none;">
				<div class="battle-options">
					<div class="battle-option">こうげき</div>
					<div class="battle-option">ぼうぎょ</div>
					<div class="battle-option">必殺技</div>
					<div class="battle-option">にげる</div>
				</div>
				<div class="battle-log">
					<ul id="battleLog">
					</ul>
				</div>
			</div>

			<div class="battle-controls">
				<button class="btn btn-primary btn-lg">バトル</button>
				<a href="mypage.html" class="btn btn-secondary btn-lg" role="button">もどる</a>
			</div>
		</div>
	</div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>