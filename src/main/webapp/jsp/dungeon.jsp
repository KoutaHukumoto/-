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
<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/3.2.1/anime.min.js"></script>
</head>
<body>
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	    // バトル開始ボタンのイベントリスナー
	    // 「バトル」ボタンを押したら、バトルのメッセージを表示して、ボタンを隠します。
	    document.querySelector('.battle-controls .btn-primary').addEventListener('click', function() {
	        document.getElementById('battleMessage').style.display = 'block';
	        document.querySelector('.battle-controls').style.display = 'none';
	    });

	    // にげるボタンのイベントリスナー
	    // 「にげる」ボタンを押したら、マイページに戻ります。
	    document.querySelectorAll('.battle-option')[3].addEventListener('click', function() {
	        window.location.href = 'mypage.html';
	    });

	    // 攻撃ボタンのイベントリスナー
	    // 「こうげき」ボタンを押したら、攻撃が始まります。
	    document.querySelectorAll('.battle-option')[0].addEventListener('click', function() {
	        battleRound();
	    });
	
	    // 必殺技ボタンのイベントリスナー
	    // 「必殺技」ボタンを押したら、特別な攻撃が始まります。
	    document.querySelectorAll('.battle-option')[2].addEventListener('click', function() {
	        specialAttack();
	    });
	    
		// 音楽ボタンのイベントリスナー
		// 音楽の再生と停止を切り替えます。
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
	// バトルの結果（勝利・敗北）を画面に表示します。
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
	// ボタンがクリックできないようにする関数です。
	function disableAllButtons() {
	    document.querySelectorAll('button, .battle-option').forEach(button => {
	        button.disabled = true;
	        button.style.pointerEvents = 'none';
	    });
	}

	// バトル機能
	// プレイヤーと敵が攻撃し合う機能です。
	function battleRound() {
	    const playerHPElement = document.querySelector('.character.pc .character-header meter');
	    const npcHPElement = document.querySelector('.character.npc .character-header meter');
	    const playerImgElement = document.querySelector('.character.pc .character-img');
	    const npcImgElement = document.querySelector('.character.npc .character-img');
	    const battleLog = document.getElementById('battleLog');
	    let playerHP = parseInt(playerHPElement.getAttribute('value'));
	    let npcHP = parseInt(npcHPElement.getAttribute('value'));
	    const playerAttack = ${attack}; // プレイヤーの攻撃力
	    const npcAttack = 10; // 敵の攻撃力

	    // プレイヤーが攻撃する
	    if (playerHP > 0 && npcHP > 0) {
	        npcHP -= playerAttack;
	        const playerAttackLog = document.createElement('li');
	        battleLog.appendChild(playerAttackLog);
	        displayTextOneByOne(playerAttackLog, `あなたの攻撃！ スライムに 10 のダメージ！`, function() {
	            npcImgElement.classList.add('shake'); // 敵がダメージを受けて揺れる演出
	            setTimeout(function() {
	                npcImgElement.classList.remove('shake');
	                if (npcHP <= 0) {
	                    npcHP = 0;
	                    const winLog = document.createElement('li');
	                    battleLog.appendChild(winLog);
	                    displayTextOneByOne(winLog, 'スライムは倒れた！！！', function() {
	                        npcHPElement.setAttribute('value', npcHP);
	                        displayBattleResult('勝利！！');
	                    });
	                    return;
	                }
	                npcHPElement.setAttribute('value', npcHP);

	                // スライムが攻撃する
	                playerHP -= npcAttack;
	                const npcAttackLog = document.createElement('li');
	                battleLog.appendChild(npcAttackLog);
	                displayTextOneByOne(npcAttackLog, `スライムの攻撃！ あなたに 10 のダメージ！`, function() {
	                    playerImgElement.classList.add('shake'); // プレイヤーがダメージを受けて揺れる演出
	                    setTimeout(function() {
	                        playerImgElement.classList.remove('shake');
	                        if (playerHP <= 0) {
	                            playerHP = 0;
	                            const loseLog = document.createElement('li');
	                            battleLog.appendChild(loseLog);
	                            displayTextOneByOne(loseLog, 'あなたは倒れた。。。', function() {
	                                playerHPElement.setAttribute('value', playerHP);
	                                displayBattleResult('敗北。。。');
	                            });
	                            return;
	                        }
	                        playerHPElement.setAttribute('value', playerHP);
	                    }, 500); // 揺れ終わった後に次の処理に進む
	                });
	            }, 500); // 揺れのアニメーション時間
	        });
	    }
	}

	// 必殺技機能
	// 必殺技を使うと、ビームを敵に向けて発射します。
function specialAttack() {
    const npcHPElement = document.querySelector('.character.npc .character-header meter');
    const npcImgElement = document.querySelector('.character.npc .character-img');
    const battleLog = document.getElementById('battleLog');
    let npcHP = parseInt(npcHPElement.getAttribute('value'));
    const playerAttack = ${attack} * 10; // 必殺技のダメージは通常攻撃の10倍

    // ビームのアニメーション
    var beam = document.createElement('div');
    beam.id = 'beam';
    document.body.appendChild(beam);

    // ヒーロー画像の右端にビームの位置を設定
    const heroImgElement = document.querySelector('.character.pc .character-img');
    const heroRect = heroImgElement.getBoundingClientRect();
    const heroRight = heroRect.right;
    const heroTop = heroRect.top;

    beam.style.left = heroRight + 'px'; // ヒーロー画像の右端にビームを配置
    beam.style.top = heroTop + (heroRect.height / 2 - 35) + 'px'; // ヒーロー画像の中央に合わせる

    // ビームの溜めアニメーション
    anime({
        targets: '#beam',
        width: '10px',        // 溜め段階では短い状態（エネルギーを蓄える）
        opacity: [0, 1],       // ビームが現れる
        scaleX: [0, 1],        // 横方向に拡大（エネルギーを溜める）
        duration: 1200,        // 溜め時間（1.2秒）
        easing: 'easeInOutQuad',
        complete: function() {
            // ビームの発射アニメーション
            anime({
                targets: '#beam',
                width: ['100px', '100vw'], // ビームが前方に伸びる
                duration: 800,             // 発射のスピード
                easing: 'easeOutExpo',     // 力強く前方に伸びる
                complete: function() {
                    // 左から徐々にフェードアウトして消えるアニメーション
                    anime({
                        targets: '#beam',
                        scaleX: [1, 0],         // 左から縮むように
                        opacity: [1, 0],        // 同時に透明になる
                        duration: 3000,         // 3秒かけて消える
                        easing: 'linear',       // 均一なスピードで消える
                        complete: function() {
                            // 完全に消えた後、元の状態にリセット
                            beam.remove();
                        }
                    });
                }
            });

            // ダメージ処理
            npcHP -= playerAttack;
            const specialAttackLog = document.createElement('li');
            battleLog.appendChild(specialAttackLog);
            displayTextOneByOne(specialAttackLog, `あなたの必死技！ スライムに ${playerAttack} のダメージ！`, function() {
                npcImgElement.classList.add('shake'); // 敵がダメージを受けて揺れる演出
                setTimeout(function() {
                    npcImgElement.classList.remove('shake');
                    if (npcHP <= 0) {
                        npcHP = 0;
                        const winLog = document.createElement('li');
                        battleLog.appendChild(winLog);
                        displayTextOneByOne(winLog, 'スライムは倒れた！！！', function() {
                            npcHPElement.setAttribute('value', npcHP);
                            displayBattleResult('勝利！！');
                        });
                        return;
                    }
                    npcHPElement.setAttribute('value', npcHP);
                }, 500); // 揺れ終わった後に次の処理に進む
            });
        }
    });
}


	// 一文字ずつ表示する関数（1行ずつ表示）
	// メッセージを1文字ずつ順番に表示する関数です。
	function displayTextOneByOne(element, text, callback) {
	    let index = 0;
	    function typeChar() {
	        if (index < text.length) {
	            element.textContent += text.charAt(index);
	            index++;
	            setTimeout(typeChar, 50); // 表示速度 (50ms 間隔)
	        } else if (callback) {
	            callback(); // 行の表示が終わったら次の処理を呼び出す
	        }
	    }
	    typeChar();
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
				<div class="character pc" style = "margin-right: 150px">
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

				<div class="character npc" style = "margin-left: 150px">
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