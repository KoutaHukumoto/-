/**
 * 
 */

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

	// 必殺技ボタンのイベントリスナー
	document.querySelectorAll('.battle-option')[2].addEventListener('click', function() {
		specialAttack();
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

// バトル機能
function battleRound() {
	const playerHPElement = document.querySelector('.character.pc .character-header meter');
	const npcHPElement = document.querySelector('.character.npc .character-header meter');
	const playerImgElement = document.querySelector('.character.pc .character-img');
	const npcImgElement = document.querySelector('.character.npc .character-img');
	const battleLog = document.getElementById('battleLog');
	let playerHP = parseInt(playerHPElement.getAttribute('value'));
	let npcHP = parseInt(npcHPElement.getAttribute('value'));
	const playerAttack = 15; // プレイヤーの攻撃力
	const npcAttack = 10; // 敵の攻撃力

	// プレイヤーが攻撃する
	if (playerHP > 0 && npcHP > 0) {
		npcHP -= playerAttack;
		const playerAttackLog = document.createElement('li');
		battleLog.appendChild(playerAttackLog);
		displayTextOneByOne(playerAttackLog, `あなたの攻撃！ スライムに ${playerAttack} のダメージ！`, function() {
			npcImgElement.classList.add('shake');
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
				displayTextOneByOne(npcAttackLog, `スライムの攻撃！ あなたに ${npcAttack} のダメージ！`, function() {
					playerImgElement.classList.add('shake');
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
					}, 500);
				});
			}, 500);
		});
	}
}

// 必殺技機能
function specialAttack() {
    // NPCキャラクターのHPと要素の取得
    const npcHPElement = document.querySelector('.character.npc .character-header meter');
    const npcImgElement = document.querySelector('.character.npc .character-img');
    const battleLog = document.getElementById('battleLog');
    let npcHP = parseInt(npcHPElement.getAttribute('value'));
    const playerAttack = 150;

    // 螺旋丸のアニメーション要素を作成
    var rasengan = document.createElement('div');
    rasengan.id = 'rasengan';
    rasengan.classList.add('rasengan-center');

    // 中央の円のスタイル要素を追加
    const rasenganCenter = document.createElement('div');
    rasenganCenter.classList.add('rasengan-center');
    rasengan.appendChild(rasenganCenter);

    // 螺旋丸の周囲に棒のパーティクルを追加
    const particleClasses = ['particle', 'horizontal', 'diagonal', 'diagonal-opposite'];
    particleClasses.forEach((particleClass) => {
        const particle = document.createElement('div');
        particle.classList.add('particle');
        if (particleClass !== 'particle') {
            particle.classList.add(particleClass);
        }
        rasengan.appendChild(particle);
    });

    document.body.appendChild(rasengan);

    // ヒーロー画像の右端に螺旋丸の位置を設定
    const heroImgElement = document.querySelector('.character.pc .character-img');
    const heroRect = heroImgElement.getBoundingClientRect();
    const heroRight = heroRect.right;
    const heroTop = heroRect.top;

    rasengan.style.left = heroRight + 'px';
    rasengan.style.top = heroTop + (heroRect.height / 2 - 35) + 'px';

    // 螺旋丸のアニメーション
    rasenganAnimation = anime({
        targets: "#rasengan",
        scale: [0, 3], // 小さい状態から元の大きさに拡大
        duration: 20, // 2秒かけて拡大
        easing: "easeOutElastic(1, 0.5)", // 弾むような効果を設定
        complete: function() {
            // 拡大後に螺旋丸を回転させるアニメーション
            rasenganAnimation = anime({
                targets: "#rasengan",
                rotate: 36000,
                duration: 2000, // 2秒で1回転
                easing: "linear", // 一定の速度で回転
                loop: true // 回転を繰り返す
            });
        }
    });

    setTimeout(function() {
        anime({
            targets: "#rasengan",
            translateX: -130, 
            translatey: 100,
            duration: 700, 
        });
    }, 1000);


    setTimeout(function() {
        anime({
            targets: "#rasengan",
            opacity: [1, 0], // 徐々に透明にする
            scale: [1, 0.5], // 徐々に縮小する
            duration: 1000, // 1秒かけて消える
            easing: "easeInOutQuad", // なめらかな消失効果
            complete: function() {
                rasengan.style.display = "none"; // 螺旋丸を非表示にする
                // アニメーションを停止
                if (rasenganAnimation) rasenganAnimation.pause();
            }
        });
    }, 2000);

    setTimeout(function() {

        npcHP -= playerAttack;
        const specialAttackLog = document.createElement('li');
        battleLog.appendChild(specialAttackLog);
        displayTextOneByOne(specialAttackLog, `あなたの必殺技！ スライムに ${playerAttack} のダメージ！`, function() {
            npcImgElement.classList.add('shake');
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
            }, 500);
        });
    }, 2000);
}




// 一文字ずつ表示する関数
function displayTextOneByOne(element, text, callback) {
	let index = 0;
	function typeChar() {
		if (index < text.length) {
			element.textContent += text.charAt(index);
			index++;
			setTimeout(typeChar, 50);
		} else if (callback) {
			callback();
		}
	}
	typeChar();
}

