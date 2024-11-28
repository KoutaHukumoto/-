
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
	})
	
	 // 防御ボタンのイベントリスナー
	 // 「ぼうぎょ」ボタンを押したら、ぼうぎょが始まります。
    document.querySelectorAll('.battle-option')[1].addEventListener('click', function() {
	    	defenseRound();
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

	// プレイヤーとNPCのHPをstatusDataから取得
	//プレイヤーとNPCのHPを取得
	let playerHP = statusData.hp;  // ここでstatusDataを使用
	let npcHP = parseInt(npcHPElement.getAttribute('value'));

	//プレイヤーとNPCのattackを取得
	const playerAttack = statusData.attack; // プレイヤーの攻撃力をstatusDataから取得
	const npcAttack = monsterData.attack; // 敵の攻撃力は固定値

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
				console.log(monsterData);

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
	// 防御機能
	function defenseRound() {
    const playerHPElement = document.querySelector('.character.pc .character-header meter');
    const npcHPElement = document.querySelector('.character.npc .character-header meter');
    const playerImgElement = document.querySelector('.character.pc .character-img');
    const battleLog = document.getElementById('battleLog');
    let playerHP = parseInt(playerHPElement.getAttribute('value'));
    let npcHP = parseInt(npcHPElement.getAttribute('value'));
    const playerDefense = statusData.defense; // プレイヤーの防御力
    const npcAttack = 20; // 敵の攻撃力

    // プレイヤーが防御を固めたログを表示
    const defenseLog = document.createElement('li');
    battleLog.appendChild(defenseLog);
    displayTextOneByOne(defenseLog, 'あなたは防御を固めた！！', function() {
		
		
        // 相手が攻撃する
        const damage = npcAttack - playerDefense > 0 ? npcAttack - playerDefense : 0; // 防御効果を適用
        playerHP -= damage;
        const npcAttackLog = document.createElement('li');
        battleLog.appendChild(npcAttackLog);
        displayTextOneByOne(npcAttackLog, `スライムの攻撃！ あなたに${npcAttack} のダメージ！`, function() {
            playerImgElement.classList.add('shake'); // プレイヤーがダメージを受けて揺れる演出
            setTimeout(function() {
                playerImgElement.classList.remove('shake');
                playerHPElement.setAttribute('value', playerHP);
                if (playerHP <= 0) {
                    playerHP = 0;
                    const loseLog = document.createElement('li');
                    battleLog.appendChild(loseLog);
                    displayTextOneByOne(loseLog, 'あなたは倒れた。。。', function() {
                        displayBattleResult('敗北。。。');
                    });
                }
            }, 500); // 揺れ終わった後に次の処理に進む
        });
    });
}

// 必殺技機能
function specialAttack() {
	const npcHPElement = document.querySelector('.character.npc .character-header meter');
	const npcImgElement = document.querySelector('.character.npc .character-img');
	const battleLog = document.getElementById('battleLog');
	let npcHP = parseInt(npcHPElement.getAttribute('value'));
	const playerAttack = 150; // 必殺技の攻撃力は固定値

	// 螺旋丸のアニメーション要素を作成
	var rasengan = document.createElement('div');
	rasengan.id = 'rasengan';
	rasengan.classList.add('rasengan-center');

	const rasenganCenter = document.createElement('div');
	rasenganCenter.classList.add('rasengan-center');
	rasengan.appendChild(rasenganCenter);

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

	const heroImgElement = document.querySelector('.character.pc .character-img');
	const heroRect = heroImgElement.getBoundingClientRect();
	const heroRight = heroRect.right;
	const heroTop = heroRect.top;

	rasengan.style.left = heroRight + 'px';
	rasengan.style.top = heroTop + (heroRect.height / 2 - 35) + 'px';

	rasenganAnimation = anime({
		targets: "#rasengan",
		scale: [0, 3],
		duration: 20,
		easing: "easeOutElastic(1, 0.5)",
		complete: function() {
			rasenganAnimation = anime({
				targets: "#rasengan",
				rotate: 36000,
				duration: 2000,
				easing: "linear",
				loop: true
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
			opacity: [1, 0],
			scale: [1, 0.5],
			duration: 1000,
			easing: "easeInOutQuad",
			complete: function() {
				rasengan.style.display = "none";
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