<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="model.Status"%>
<%@ page import="model.monster"%>
<%@ page import="model.dungeon"%>
<%@ page import="model.item"%>
<%
Status status = (Status) session.getAttribute("status");
monster monster = (monster) session.getAttribute("monsterstatus");
dungeon dungeoninformation = (dungeon) session.getAttribute("dungeonInformation");
item item = (item) session.getAttribute("item");

// dungeonid を取得
int dungeonId = dungeoninformation.getDungeonId();

// dungeonid が 5 で割り切れるかを判定
boolean isMultipleOfFive = (dungeonId % 5 == 0);

// BGM の選択
String bgmFile = isMultipleOfFive ? "audio/specialBGM.mp3" : "audio/Umbra.mp3";
// dungeonidが5で割り切れる場合は別のBGM
%>
<%
int maxHp = status.getHp();
int monsterMaxHp = monster.getMonsterHp();

// HP のしきい値（割合に基づく）
int lowThreshold = (int) (maxHp * 0.3); // HP 30% 以下を「低」
int highThreshold = (int) (maxHp * 0.7); // HP 70% 以上を「良好」
int optimumThreshold = (int) (maxHp * 0.9); // HP 90% 以上を「理想的」

int monsterLowThreshold = (int) (monsterMaxHp * 0.3);
int monsterHighThreshold = (int) (monsterMaxHp * 0.7);
int monsterOptimumThreshold = (int) (monsterMaxHp * 0.9);
%>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/animejs/3.2.1/anime.min.js"></script>
</head>
<body>
	<script src="js/dungeon.js"></script>
	<script>
        var statusData = {
            name: "<%=status.getName()%>",
            id: <%=status.getId()%>,
            hp: <%=status.getHp()%>,
            attack: <%=status.getAttack()%>,
            defense: <%=status.getDefense()%>,
            speed: <%=status.getSpeed()%>,
            itemid: <%=status.getItemid()%>,
            dungeonid: <%=status.getDungeonid()%>,
            avaterid: <%=status.getAvatarid()%>
        };

        var monsterData = {
            name: "<%=monster.getMonsterName()%>",
            id: <%=monster.getMonsterId()%>,
            hp: <%=monster.getMonsterHp()%>,
            attack: <%=monster.getMonsterAttack()%>,
            defense: <%=monster.getMonsterDefence()%>,
            speed: <%=monster.getMonsterSpeed()%>,
            skill1: "<%=monster.getSkill1()%>",
            skill2: "<%=monster.getSkill2()%>"
		};

        let maxHp = <%= status.getHp() %>;
        let monsterMaxHp = <%= monster.getMonsterHp() %>;

        let lowThreshold = Math.floor(maxHp * 0.3);   
        let highThreshold = Math.floor(maxHp * 0.7);  
        let optimumThreshold = Math.floor(maxHp * 0.9); 

        let monsterLowThreshold = Math.floor(monsterMaxHp * 0.3);
        let monsterHighThreshold = Math.floor(monsterMaxHp * 0.7);
        let monsterOptimumThreshold = Math.floor(monsterMaxHp * 0.9);
    		
	</script>

	<div class="bg-custom">
		<div class="music-control">
			<button id="playMusicBtn" class="btn btn-info"
				aria-label="音楽を再生または停止">🎵 音楽を停止</button>
		</div>

		<!-- BGMを動的に設定 -->
		<audio id="backgroundMusic" autoplay>
			<source src="<%=bgmFile%>" type="audio/mp3">
		</audio>

		<div class="container text-center">
			<h1 class="level-title"><%=dungeoninformation.getDungeonId()%>階層
			</h1>
			<h2>VS</h2>

			<div class="character-info">
				<div class="character pc" style="margin-right: 50px">
					<div class="character-stats">
						<p>
							HP:
							<%=status.getHp()%></p>
						<p>
							攻撃:
							<%=status.getAttack()%></p>
						<p>
							防御:
							<%=status.getDefense()%></p>
						<p>
							すばやさ:
							<%=status.getSpeed()%></p>
						<p>
							装備品: 「<%=item.getItemName()%>」
						</p>
						<p>
							「<%=item.getDescription()%>」
						</p>
					</div>
					<div class="character-img-box">
						<div class="character-header">
							<p class="character-name"><%=status.getName()%></p>
							<p id="playerHpText" class="hp">
								主人公のHP:<br>
								 <span id="playercurrentHp"><%=status.getHp()%></span>
								/
								<%=maxHp%></p>
							<meter max="<%=maxHp%>" low="<%=lowThreshold%>"
								high="<%=highThreshold%>" optimum="<%=optimumThreshold%>"
								value="<%=status.getHp()%>"> </meter>
						</div>
						<img src="画像/avater<%=status.getAvatarid()%>.jpg" alt="Hero" class="character-img">
					</div>
				</div>

				<div class="character npc" style="margin-left: 50px">
					<div class="character-img-box">
						<div class="character-header">
							<p class="character-name"><%=monster.getMonsterName()%></p>
							<p id="npcHpText" class="hp">
								モンスターのHP:<br>
								<span id="npccurrentHp"><%=monster.getMonsterHp()%></span>
								/
								<%=monsterMaxHp%></p>
							<meter max="<%=monsterMaxHp%>" low="<%=monsterLowThreshold%>"
								high="<%=monsterHighThreshold%>"
								optimum="<%=monsterOptimumThreshold%>"
								value="<%=monster.getMonsterHp()%>"> </meter>
						</div>
						<img src="画像/<%=monster.getMonsterName()%>.jpg" alt="Enemy" class="character-img">
					</div>
					<div class="character-stats">
						<p>
							HP:
							<%=monster.getMonsterHp()%></p>
						<p>
							攻撃:
							<%=monster.getMonsterAttack()%></p>
						<p>
							防御:
							<%=monster.getMonsterDefence()%></p>
						<p>
							すばやさ:
							<%=monster.getMonsterSpeed()%></p>
						<p>
							スキル1:
							<%=monster.getSkill1()%></p>
						<p>
							スキル2:
							<%=monster.getSkill2()%></p>
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
				<form action="/Dosukoi-Analytics/backServlet" method="POST">
					<input type="hidden" name="name" value="<%=status.getName()%>">
					<input type="hidden" name="result" value=0>
					<button type="submit">もどる</button>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>