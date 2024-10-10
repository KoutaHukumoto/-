<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ダンジョン</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/battle.css">
</head>
<body>
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	    // バトル開始ボタンのイベントリスナー
	    document.querySelector('.battle-controls .btn-primary').addEventListener('click', function() {
	        document.getElementById('battleMessage').style.display = 'block';
	        document.querySelector('.battle-controls').style.display = 'none';
	    });

	    // にげるボタンのイベントリスナー
	    document.querySelectorAll('.battle-option')[3].addEventListener('click', function() {
	        window.location.href = 'mypage.html';
	    });

	    // 攻撃ボタンのイベントリスナー
	    document.querySelectorAll('.battle-option')[0].addEventListener('click', function() {
	        battleRound();
	    });
	    
		
	    // 音楽ボタンのイベントリスナー
		document.getElementById('playMusicBtn').addEventListener('click', function() {
			const music = document.getElementById('backgroundMusic');
			if (music.paused) {
				music.play();
				this.textContent = "🎵 音楽を停止";
			} else {
				music.pause();
				this.textContent = "🎵 音楽を再生";
			}
		});
	});

	// バトル結果表示
function displayBattleResult(message) {
    const resultOverlay = document.createElement('div');
    resultOverlay.classList.add('battle-result-overlay');
    
    resultOverlay.innerHTML = 
        '<div class="result-message">' + message + '</div>' +
        '<div class="result-buttons">' +
        '<a href="mypage.html" class="btn btn-primary btn-lg result-back-button" role="button">マイページに戻る</a>' +
        '<button class="btn btn-primary btn-lg" onclick="restartBattle()">再戦する</button>' +
        '</div>';
    
    document.body.appendChild(resultOverlay);
    disableAllButtons();
}


	// すべてのボタンを無効にする
	function disableAllButtons() {
	    document.querySelectorAll('button, .battle-option').forEach(button => {
	        button.disabled = true;
	        button.style.pointerEvents = 'none';
	    });
	}

	function restartBattle() {
	    // バトル状態のリセット処理
	    document.querySelector('.battle-result-overlay').remove();
	    document.getElementById('battleMessage').style.display = 'none';
	    document.querySelector('.battle-controls').style.display = 'block';

	    // プレイヤーと敵のHPのリセット
	    const playerHPElement = document.querySelector('.character.pc .character-header meter');
	    const npcHPElement = document.querySelector('.character.npc .character-header meter');
	    playerHPElement.setAttribute('value', 100);
	    npcHPElement.setAttribute('value', 100);

	    // バトルログのクリア
	    const battleLog = document.getElementById('battleLog');
	    battleLog.innerHTML = '';

	    // ボタンの有効化
	    document.querySelectorAll('button, .battle-option').forEach(button => {
	        button.disabled = false;
	        button.style.pointerEvents = 'auto';
	    });
	}

	// バトル機能
	function battleRound() {
	    const playerHPElement = document.querySelector('.character.pc .character-header meter');
	    const npcHPElement = document.querySelector('.character.npc .character-header meter');
	    const battleLog = document.getElementById('battleLog');
	    let playerHP = parseInt(playerHPElement.getAttribute('value'));
	    let npcHP = parseInt(npcHPElement.getAttribute('value'));
	    const playerAttack = ${attack};
	    const npcAttack = 10;

	    if (playerHP > 0 && npcHP > 0) {
	        npcHP -= playerAttack;
	        const playerAttackLog = document.createElement('li');
	        playerAttackLog.textContent = `あなたの攻撃！ スライムに 10 のダメージ！`;
	        battleLog.appendChild(playerAttackLog);

	        if (npcHP <= 0) {
	            npcHP = 0;
	            const winLog = document.createElement('li');
	            winLog.textContent = 'スライムは倒れた！！！';
	            battleLog.appendChild(winLog);
	            npcHPElement.setAttribute('value', npcHP);
	            displayBattleResult('勝利！！');
	            return;
	        }

	        npcHPElement.setAttribute('value', npcHP);

	        playerHP -= npcAttack;
	        const npcAttackLog = document.createElement('li');
	        npcAttackLog.textContent = `スライムの攻撃！ あなたに 10 のダメージ！`;
	        battleLog.appendChild(npcAttackLog);

	        if (playerHP <= 0) {
	            playerHP = 0;
	            const loseLog = document.createElement('li');
	            loseLog.textContent = 'あなたは倒れた。。。';
	            battleLog.appendChild(loseLog);
	            playerHPElement.setAttribute('value', playerHP);
	            displayBattleResult('敗北。。。');
	            return;
	        }

	        playerHPElement.setAttribute('value', playerHP);
	    }
	}

	</script>
	<div class="bg-custom">

		<div class="music-control">
			<button id="playMusicBtn" class="btn btn-info"
				aria-label="音楽を再生または停止">🎵 音楽を停止</button>
		</div>

		<audio id="backgroundMusic" autoplay>
			<source src="audio/Umbra.mp3" type="audio/mp3">
		</audio>

		<div class="container text-center">
			<h1 class="level-title">1階層</h1>
			<h2>VS</h2>

			<div class="character-info">
				<div class="character pc">
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
							<meter max="100" low="20" high="80" optimum="90"  value="${hp}"></meter>
						</div>
						<img src="画像/avater.jpg" alt="Hero" class="character-img">
					</div>
				</div>

				<div class="character npc">
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

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>