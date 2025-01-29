
document.addEventListener('DOMContentLoaded', function() {
	// バトル開始ボタンのイベントリスナー
	document.querySelector('.battle-controls .btn-primary').addEventListener('click', function() {
		document.getElementById('battleMessage').style.display = 'block';
		document.querySelector('.battle-controls').style.display = 'none';
	});

	// にげるボタンのイベントリスナー
	document.querySelectorAll('.battle-option')[3].addEventListener('click', function() {
		// formを作成
		var form = document.createElement('form');
		form.method = 'POST';
		form.action = '/Dosukoi-Analytics/backServlet';

		// nameデータを追加
		var nameInput = document.createElement('input');
		nameInput.type = 'hidden';
		nameInput.name = 'name';
		nameInput.value = statusData.name;
		form.appendChild(nameInput);

		// resultデータを追加
		var resultInput = document.createElement('input');
		resultInput.type = 'hidden';
		resultInput.name = 'result';
		resultInput.value = 0;
		form.appendChild(resultInput);

		// formを送信
		document.body.appendChild(form);
		form.submit();
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
function displayBattleResult(message, isVictory) {
	const resultOverlay = document.createElement('div');
	resultOverlay.classList.add('battle-result-overlay');

	// 共通のhidden inputを作成する関数
	function createHiddenInput(name, value) {
		const input = document.createElement('input');
		input.type = 'hidden';
		input.name = name;
		input.value = value;
		return input;
	}

	// マイページに戻るフォーム
	const backForm = document.createElement('form');
	backForm.method = 'POST';
	backForm.action = '/Dosukoi-Analytics/backServlet';

	backForm.appendChild(createHiddenInput('name', statusData.name));
	backForm.appendChild(createHiddenInput('result', isVictory ? 1 : 0));

	const backButton = document.createElement('button');
	backButton.type = 'submit';
	backButton.classList.add('btn', 'btn-primary', 'btn-lg', 'result-back-button');
	backButton.textContent = 'マイページに戻る';
	backForm.appendChild(backButton);

	// 再戦ボタン
	const rematchForm = document.createElement('form');
	rematchForm.method = 'POST';
	rematchForm.action = '/Dosukoi-Analytics/rematchServlet';

	rematchForm.appendChild(createHiddenInput('name', statusData.name));
	rematchForm.appendChild(createHiddenInput('result', isVictory ? 1 : 0));

	const rematchButton = document.createElement('button');
	rematchButton.type = 'submit';
	rematchButton.classList.add('btn', 'btn-primary', 'btn-lg');
	rematchButton.textContent = '再戦する';
	rematchForm.appendChild(rematchButton);

	// 結果オーバーレイの内容を作成
	const resultMessage = document.createElement('div');
	resultMessage.classList.add('result-message');
	resultMessage.textContent = message;

	const resultButtons = document.createElement('div');
	resultButtons.classList.add('result-buttons');
	resultButtons.appendChild(backForm);
	resultButtons.appendChild(rematchForm);

	resultOverlay.appendChild(resultMessage);
	resultOverlay.appendChild(resultButtons);

	document.body.appendChild(resultOverlay);
}




// バトル機能
function battleRound() {
	disableAllButtons(); // ボタンを無効化

	const playerHPElement = document.querySelector('.character.pc .character-header meter');
	const npcHPElement = document.querySelector('.character.npc .character-header meter');
	const playerImgElement = document.querySelector('.character.pc .character-img');
	const npcImgElement = document.querySelector('.character.npc .character-img');
	const battleLog = document.getElementById('battleLog');

	let playerHP = statusData.hp;  // プレイヤーのHP
	let npcHP = monsterData.hp;   // NPCのHP

	const playerAttack = statusData.attack; // プレイヤーの攻撃力
	const npcAttack = monsterData.attack;   // NPCの攻撃力
	const playerSpeed = statusData.speed;   // プレイヤーの素早さ
	const npcSpeed = monsterData.speed;     // NPCの素早さ

	// 素早さ判定
	if (playerSpeed >= npcSpeed) {
		// プレイヤーが先に攻撃
		if (npcHP > 0) {
			npcHP = Math.max(0, npcHP - playerAttack);
			const playerAttackLog = document.createElement('li');
			battleLog.appendChild(playerAttackLog);
			displayTextOneByOne(playerAttackLog, `あなたの攻撃！ スライムに ${playerAttack} のダメージ！`, function() {
				npcImgElement.classList.add('shake');
				setTimeout(function() {
					npcImgElement.classList.remove('shake');
					if (npcHP <= 0) {
						const winLog = document.createElement('li');
						battleLog.appendChild(winLog);
						displayTextOneByOne(winLog, 'スライムは倒れた！！！', function() {
							npcHPElement.setAttribute('value', npcHP);
							displayBattleResult('勝利！！', true);
						});
						return;
					}
					npcHPElement.setAttribute('value', npcHP);
					// NPCの反撃
					npcAttackTurn();
				}, 500);
			});
		}
	} else {
		// NPCが先に攻撃
		npcAttackTurn();
	}

	function npcAttackTurn() {
		if (playerHP > 0) {
			playerHP = Math.max(0, playerHP - npcAttack);
			const npcAttackLog = document.createElement('li');
			battleLog.appendChild(npcAttackLog);
			displayTextOneByOne(npcAttackLog, `スライムの攻撃！ あなたに ${npcAttack} のダメージ！`, function() {
				playerImgElement.classList.add('shake');
				setTimeout(function() {
					playerImgElement.classList.remove('shake');
					if (playerHP <= 0) {
						const loseLog = document.createElement('li');
						battleLog.appendChild(loseLog);
						displayTextOneByOne(loseLog, 'あなたは倒れた。。。', function() {
							playerHPElement.setAttribute('value', playerHP);
							displayBattleResult('敗北。。。', false);
						});
						return;
					}
					playerHPElement.setAttribute('value', playerHP);
					// プレイヤーの攻撃ターン
					if (playerSpeed < npcSpeed) {
						playerAttackTurn();
					}
				}, 500);
			});
		}
	}

	function playerAttackTurn() {
		if (npcHP > 0) {
			npcHP = Math.max(0, npcHP - playerAttack);
			const playerAttackLog = document.createElement('li');
			battleLog.appendChild(playerAttackLog);
			displayTextOneByOne(playerAttackLog, `あなたの攻撃！ スライムに ${playerAttack} のダメージ！`, function() {
				npcImgElement.classList.add('shake');
				setTimeout(function() {
					npcImgElement.classList.remove('shake');
					if (npcHP <= 0) {
						const winLog = document.createElement('li');
						battleLog.appendChild(winLog);
						displayTextOneByOne(winLog, 'スライムは倒れた！！！', function() {
							npcHPElement.setAttribute('value', npcHP);
							displayBattleResult('勝利！！', true);
						});
						return;
					}
					npcHPElement.setAttribute('value', npcHP);
					enableAllButtons(); // ボタンを再有効化
				}, 500);
			});
		} else {
			enableAllButtons(); // ボタンを再有効化
		}
	}
}



// 防御機能
function defenseRound() {
	disableAllButtons(); // ボタンを無効化

	const playerHPElement = document.querySelector('.character.pc .character-header meter');
	const playerImgElement = document.querySelector('.character.pc .character-img');
	const battleLog = document.getElementById('battleLog');

	let playerHP = statusData.hp;  // プレイヤーのHP
	const npcAttack = monsterData.attack;   // NPCの攻撃力
	const playerDefense = statusData.defense; // プレイヤーの防御力

	// 防御ログを表示
	const defenseLog = document.createElement('li');
	battleLog.appendChild(defenseLog);
	displayTextOneByOne(defenseLog, 'あなたは防御を固めた！', function() {
		// ダメージ軽減計算
		const damage = Math.max(1, npcAttack - playerDefense);
		playerHP = Math.max(0, playerHP - damage);

		// 防御成功時にカウンター発動の確率
		const counterChance = 0.3; // 30%の確率でカウンター攻撃

		if (Math.random() < counterChance) {
			const counterLog = document.createElement('li');
			battleLog.appendChild(counterLog);
			displayTextOneByOne(counterLog, 'カウンター攻撃が発動！ スライムに大ダメージ！', function() {
				const counterDamage = Math.floor(statusData.attack * 1.5); // 通常攻撃の1.5倍ダメージ
				const npcHPElement = document.querySelector('.character.npc .character-header meter');
				let npcHP = monsterData.hp;
				npcHP = Math.max(0, npcHP - counterDamage);
				npcHPElement.setAttribute('value', npcHP);
				if (npcHP <= 0) {
					const winLog = document.createElement('li');
					battleLog.appendChild(winLog);
					displayTextOneByOne(winLog, 'スライムは倒れた！！！', function() {
						displayBattleResult('勝利！！', true);
					});
				}
				enableAllButtons(); // ボタンを再有効化
			});
		} else {
			// カウンターが発動しない場合
			const damageLog = document.createElement('li');
			battleLog.appendChild(damageLog);
			displayTextOneByOne(damageLog, `スライムの攻撃！ あなたに ${damage} のダメージ！`, function() {
				playerImgElement.classList.add('shake');
				setTimeout(function() {
					playerImgElement.classList.remove('shake');
					playerHPElement.setAttribute('value', playerHP);
					if (playerHP <= 0) {
						const loseLog = document.createElement('li');
						battleLog.appendChild(loseLog);
						displayTextOneByOne(loseLog, 'あなたは倒れた。。。', function() {
							displayBattleResult('敗北。。。', false);
						});
					} else {
						// 防御成功時の回復量を設定
						const healAmount = Math.floor(statusData.hp * 0.1); // 最大HPの10%を回復
						playerHP = Math.min(statusData.hp, playerHP + healAmount);
						enableAllButtons(); // ボタンを再有効化
					}
				}, 500);
			});
		}
	});
}

// 必殺技ボタンの制限
let specialAttackUsed = false;


// 必殺技機能
// 必殺技: 強力な炎
function specialAttack() {
	if (specialAttackUsed) {
		const battleLog = document.getElementById('battleLog');
		const exhaustedLog = document.createElement('li');
		battleLog.appendChild(exhaustedLog);
		displayTextOneByOne(exhaustedLog, `${statusData.name}は疲れて必殺技を出せない....`, function() {
			enableAllButtons(); // ボタンを再有効化
		});
		return;
	}

	specialAttackUsed = true; // 必殺技を使用済みに設定
	disableAllButtons(); // ボタンを無効化

	const npcHPElement = document.querySelector('.character.npc .character-header meter');
	const npcImgElement = document.querySelector('.character.npc .character-img');
	const battleLog = document.getElementById('battleLog');
	let npcHP = monsterData.hp;
	const playerAttack = 150; // 必殺技の攻撃力は固定値

	// 必殺技エフェクトの作成
	const flameEffect = document.createElement('div');
	flameEffect.classList.add('flame-effect');
	document.body.appendChild(flameEffect);

	// アニメーション開始位置
	const startPosition = document.querySelector('.character.pc .character-img').getBoundingClientRect();
	const targetPosition = npcImgElement.getBoundingClientRect();

	// アニメーション実行
	anime({
		targets: flameEffect,
		left: [startPosition.left, targetPosition.left + targetPosition.width / 2],
		top: [startPosition.top, targetPosition.top + targetPosition.height / 2],
		scale: [0, 2],
		opacity: [1, 0.5],
		duration: 1000,
		easing: 'easeInOutQuad',
		complete: function() {
			// 爆発エフェクトを生成
			const explosionEffect = document.createElement('div');
			explosionEffect.classList.add('explosion-effect');
			explosionEffect.style.left = `${targetPosition.left}px`;
			explosionEffect.style.top = `${targetPosition.top}px`;
			document.body.appendChild(explosionEffect);

			anime({
				targets: explosionEffect,
				scale: [0, 5],
				opacity: [1, 0],
				duration: 800,
				easing: 'easeOutExpo',
				complete: function() {
					explosionEffect.remove();
					flameEffect.remove();

					// ダメージ処理
					npcHP = Math.max(0, npcHP - playerAttack);
					const specialAttackLog = document.createElement('li');
					battleLog.appendChild(specialAttackLog);
					displayTextOneByOne(specialAttackLog, `あなたの必殺技！ スライムに ${playerAttack} のダメージ！`, function() {
						npcImgElement.classList.add('shake');
						setTimeout(function() {
							npcImgElement.classList.remove('shake');
							if (npcHP <= 0) {
								const winLog = document.createElement('li');
								battleLog.appendChild(winLog);
								displayTextOneByOne(winLog, 'スライムは倒れた！！！', function() {
									npcHPElement.setAttribute('value', npcHP);
									displayBattleResult('勝利！！', true);
								});
								return;
							}
							npcHPElement.setAttribute('value', npcHP);
							enableAllButtons(); // ボタンを再有効化
						}, 500);
					});
				}
			});
		}
	});
}

// すべてのボタンを無効化する関数
function disableAllButtons() {
	document.querySelectorAll('button, .battle-option').forEach(button => {
		button.disabled = true;
		button.style.pointerEvents = 'none';
	});
}

// すべてのボタンを有効化する関数
function enableAllButtons() {
	document.querySelectorAll('button, .battle-option').forEach(button => {
		button.disabled = false;
		button.style.pointerEvents = '';
	});
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