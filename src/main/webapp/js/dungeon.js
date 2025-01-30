
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
	let playercurrentHpText = document.getElementById("playercurrentHp");
	let npccurrentHpText = document.getElementById("npccurrentHp");


	let playerHP = parseInt(playerHPElement.value, 10);  // プレイヤーのHP
	let npcHP = parseInt(npcHPElement.value, 10);   // NPCのHP
	let npcname = monsterData.name;
	let playername = statusData.name;

	console.log(playerHPElement, npcHPElement, playerHP, npcHP)

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
			displayTextOneByOne(playerAttackLog, `${playername}の攻撃！ ${npcname}に ${playerAttack} のダメージ！`, function() {
				npcImgElement.classList.add('shake');
				setTimeout(function() {
					npcImgElement.classList.remove('shake');
					if (npcHP <= 0) {
						const winLog = document.createElement('li');
						battleLog.appendChild(winLog);
						displayTextOneByOne(winLog, `${npcname}は倒れた！！！`, function() {
							npcHPElement.setAttribute('value', npcHP);
							displayBattleResult('勝利！！', true);
						});
						return;
					}
					npcHPElement.setAttribute('value', npcHP);
					npccurrentHpText.innerText = npcHP;
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
		let npcskill = monsterData.skill1
		if (playerHP > 0) {
			playerHP = Math.max(0, playerHP - npcAttack);
			const npcAttackLog = document.createElement('li');
			battleLog.appendChild(npcAttackLog);
			displayTextOneByOne(npcAttackLog, `${npcname}の ${npcskill}！ ${playername}に ${npcAttack} のダメージ！`, function() {
				playerImgElement.classList.add('shake');
				enableAllButtons(); // ボタンを再有効化
				setTimeout(function() {
					playerImgElement.classList.remove('shake');
					if (playerHP <= 0) {
						const loseLog = document.createElement('li');
						battleLog.appendChild(loseLog);
						displayTextOneByOne(loseLog, `${playername}は倒れた。。。`, function() {
							playerHPElement.setAttribute('value', playerHP);
							displayBattleResult('敗北。。。', false);
						});
						return;
					}
					playerHPElement.setAttribute('value', playerHP);
					playercurrentHpText.innerText = playerHP;
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
			displayTextOneByOne(playerAttackLog, `${playername}の攻撃！ ${npcname}に ${playerAttack} のダメージ！`, function() {
				npcImgElement.classList.add('shake');
				setTimeout(function() {
					npcImgElement.classList.remove('shake');
					if (npcHP <= 0) {
						const winLog = document.createElement('li');
						battleLog.appendChild(winLog);
						displayTextOneByOne(winLog, `${npcname}は倒れた！！！`, function() {
							npcHPElement.setAttribute('value', npcHP);
							displayBattleResult('勝利！！', true);
						});
						return;
					}
					npcHPElement.setAttribute('value', npcHP);
					npccurrentHpText.innerText = npcHP;
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
	const npcHPElement = document.querySelector('.character.npc .character-header meter');
	const playerImgElement = document.querySelector('.character.pc .character-img');
	const npcImgElement = document.querySelector('.character.npc .character-img');
	const battleLog = document.getElementById('battleLog');
	let playercurrentHpText = document.getElementById("playercurrentHp");
	let npccurrentHpText = document.getElementById("npccurrentHp");

	let playerHP = parseInt(playerHPElement.value, 10);  // プレイヤーのHP
	let npcHP = parseInt(npcHPElement.value, 10);   // NPCのHP
	const npcAttack = monsterData.attack;   // NPCの攻撃力
	const playerDefense = statusData.defense; // プレイヤーの防御力
	let npcskill = monsterData.skill2;
	let npcname = monsterData.name;
	let playername = statusData.name;

	// 防御ログを表示
	const defenseLog = document.createElement('li');
	battleLog.appendChild(defenseLog);
	displayTextOneByOne(defenseLog, `${playername}は防御を固めた！`, function() {
		// ダメージ軽減計算
		const damage = Math.max(1, npcAttack - playerDefense);
		playerHP = Math.max(0, playerHP - damage);

		// 防御成功時にカウンター発動の確率
		const counterChance = 0.3; // 30%の確率でカウンター攻撃

		if (Math.random() < counterChance) {
			const counterLog = document.createElement('li');
			battleLog.appendChild(counterLog);
			const counterDamage = Math.floor(statusData.attack * 1.5); // 通常攻撃の1.5倍ダメージ
			displayTextOneByOne(counterLog, `カウンター攻撃が発動！ ${npcname} に${counterDamage}ダメージ！`, function() {

				npcHP = Math.max(0, npcHP - counterDamage);
				npcHPElement.setAttribute('value', npcHP);
				npccurrentHpText.innerText = npcHP;
				if (npcHP <= 0) {
					enableAllButtons(); // ボタンを再有効化
					const winLog = document.createElement('li');
					battleLog.appendChild(winLog);
					displayTextOneByOne(winLog, `${npcname}は倒れた！！！`, function() {
						displayBattleResult('勝利！！', true);
					});
				}
				enableAllButtons(); // ボタンを再有効化
			});
		} else {
			// カウンターが発動しない場合
			const damageLog = document.createElement('li');
			battleLog.appendChild(damageLog);
			displayTextOneByOne(damageLog, `${npcname}の${npcskill} ！ ${playername}に ${damage} のダメージ！`, function() {
				playerImgElement.classList.add('shake');
				enableAllButtons(); // ボタンを再有効化
				setTimeout(function() {
					playerImgElement.classList.remove('shake');
					playerHPElement.setAttribute('value', playerHP);
					playercurrentHpText.innerText = playerHP;
					if (playerHP <= 0) {
						const loseLog = document.createElement('li');
						battleLog.appendChild(loseLog);
						displayTextOneByOne(loseLog, `${playername}は倒れた。。。`, function() {
							displayBattleResult('敗北。。。', false);
						});
					}
				}, 500);
			});
		}
	});
}

// 必殺技ボタンの制限
let specialAttackUsed = false;

function video(callback) {
	// 親のdiv要素を作成（中央配置用）
	const videoContainer = document.createElement('div');
	videoContainer.style.display = 'flex';
	videoContainer.style.justifyContent = 'center';
	videoContainer.style.alignItems = 'center';
	videoContainer.style.height = '100vh';
	videoContainer.style.width = '100vw';
	videoContainer.style.position = 'fixed'; // 画面固定
	videoContainer.style.top = '0';
	videoContainer.style.left = '0';
	videoContainer.style.backgroundColor = 'rgba(0, 0, 0, 0.8)'; // 半透明の黒背景
	videoContainer.style.zIndex = '9999'; // 最前面に表示する

	// <video> 要素を作成
	const video = document.createElement('video');
	video.src = '画像/momo.mp4';
	video.controls = false;  // コントロールなし（任意）
	video.autoplay = true;
	video.width = 640;
	video.height = 360;

	// 動画の再生が終わったら削除 & コールバック実行
	video.addEventListener('ended', () => {
		console.log('動画の再生が終了しました');
		videoContainer.remove(); // 親ごと削除
		if (callback) callback(); // コールバックを実行（specialAttack の続きへ）
	});

	// 指定の親要素（.container.text-center）を取得
	const targetContainer = document.querySelector('.container.text-center');
	if (targetContainer) {
		targetContainer.appendChild(videoContainer); // 指定した場所に追加
	} else {
		document.body.appendChild(videoContainer); // もし存在しない場合はbodyに追加
	}

	// 動画を親コンテナに追加
	videoContainer.appendChild(video);
}



//必殺技機能
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

	// 動画を再生後に必殺技処理を実行
	if (statusData.avaterid == 5) {
		video(() => {
			executeSpecialAttack(); // 動画終了後に続きの処理
		});
	} else {
		executeSpecialAttack(); // 動画が不要ならそのまま実行
	}
}

// 必殺技の続きの処理（動画終了後に呼び出される）
function executeSpecialAttack() {
	specialAttackUsed = true; // 必殺技を使用済みに設定
	disableAllButtons(); // ボタンを無効化

	const playerHPElement = document.querySelector('.character.pc .character-header meter');
	const npcHPElement = document.querySelector('.character.npc .character-header meter');
	const playerImgElement = document.querySelector('.character.pc .character-img');
	const npcImgElement = document.querySelector('.character.npc .character-img');
	const battleLog = document.getElementById('battleLog');
	let playercurrentHpText = document.getElementById("playercurrentHp");
	let npccurrentHpText = document.getElementById("npccurrentHp");

	let playerHP = parseInt(playerHPElement.value, 10);  // プレイヤーのHP
	let npcHP = parseInt(npcHPElement.value, 10);   // NPCのHP
	const playerAttack = statusData.attack * 10; // 必殺技の攻撃力は10倍
	let npcname = monsterData.name;

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
					displayTextOneByOne(specialAttackLog, `${statusData.name}の必殺技！ ${npcname}に ${playerAttack} のダメージ！`, function() {
						npcImgElement.classList.add('shake');
						setTimeout(function() {
							npcImgElement.classList.remove('shake');
							if (npcHP <= 0) {
								const winLog = document.createElement('li');
								battleLog.appendChild(winLog);
								displayTextOneByOne(winLog, `${npcname}は倒れた！！！`, function() {
									npcHPElement.setAttribute('value', npcHP);
									npccurrentHpText.innerText = npcHP;
									displayBattleResult('勝利！！', true);
								});
								return;
							}
							npcHPElement.setAttribute('value', npcHP);
							console.log(npcHP);
							npccurrentHpText.innerText = npcHP;
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

// バトルログを自動スクロール
function scrollBattleLogToBottom() {
	const battleLog = document.getElementById('battleLog');
	battleLog.scrollTop = battleLog.scrollHeight;
}

// 一文字ずつ表示する関数（改良版）
function displayTextOneByOne(element, text, callback) {
	let index = 0;

	function typeChar() {
		if (index < text.length) {
			element.innerHTML += text.charAt(index); // innerHTMLなら <br> も解釈可能
			scrollBattleLogToBottom(); // 途中でもスクロール
			index++;

			// 句読点（「、。」）なら少し遅延
			const delay = (text.charAt(index - 1) === '、' || text.charAt(index - 1) === '。') ? 150 : 50;

			setTimeout(typeChar, delay);
		} else {
			if (callback) {
				callback();
			}
		}
	}

	typeChar();
}
