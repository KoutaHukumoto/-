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
    <script>
        var statusData = {
            name: "<%= status.getName() %>",
            id: <%= status.getId() %>,
            hp: <%= status.getHp() %>,
            attack: <%= status.getAttack() %>,
            defense: <%= status.getDefense() %>,
            speed: <%= status.getSpeed() %>,
            itemid: <%= status.getItemid() %>,
            dungeonid: <%= status.getDungeonid() %>
        };

        var monsterData = {
            name: "<%= monster.getMonsterName() %>",
            id: <%= monster.getMonsterId() %>,
            hp: <%= monster.getMonsterHp() %>,
            attack: <%= monster.getMonsterAttack() %>,
            defense: <%= monster.getMonsterDefence() %>,
            speed: <%= monster.getMonsterSpeed() %>,
            skill1: "<%= monster.getSkill1() %>",
            skill2: "<%= monster.getSkill2() %>"
        };
    </script>

    <div class="bg-custom">
        <div class="music-control">
            <button id="playMusicBtn" class="btn btn-info" aria-label="音楽を再生または停止">🎵 音楽を停止</button>
        </div>

        <!-- BGMを動的に設定 -->
        <audio id="backgroundMusic" autoplay>
            <source src="<%= bgmFile %>" type="audio/mp3">
        </audio>

        <div class="container text-center">
            <h1 class="level-title"><%= dungeoninformation.getDungeonId() %>階層 </h1>
            <h2>VS</h2>

            <div class="character-info">
                <div class="character pc" style="margin-right: 50px">
                    <div class="character-stats">
                        <p>HP: <%= status.getHp() %></p>
                        <p>攻撃: <%= status.getAttack() %></p>
                        <p>防御: <%= status.getDefense() %></p>
                        <p>すばやさ: <%= status.getSpeed() %></p>
                        <p>装備品: 「<%= item.getItemName() %>」</p>
                        <p>「<%= item.getDescription() %>」</p>
                    </div>
                    <div class="character-img-box">
                        <div class="character-header">
                            <p class="character-name"><%= status.getName() %></p>
                            <meter max="<%= status.getHp() %>" low="20" high="80" optimum="90" value="<%= status.getHp() %>"></meter>
                        </div>
                        <img src="画像/avater.jpg" alt="Hero" class="character-img">
                    </div>
                </div>

                <div class="character npc" style="margin-left: 50px">
                    <div class="character-img-box">
                        <div class="character-header">
                            <p class="character-name"><%= monster.getMonsterName() %></p>
                            <meter max="<%= monster.getMonsterHp() %>" low="20" high="80" optimum="90" value="<%= monster.getMonsterHp() %>"></meter>
                        </div>
                        <img src="画像/スライム.png" alt="Enemy" class="character-img">
                    </div>
                    <div class="character-stats">
                        <p>HP: <%= monster.getMonsterHp() %></p>
                        <p>攻撃: <%= monster.getMonsterAttack() %></p>
                        <p>防御: <%= monster.getMonsterDefence() %></p>
                        <p>すばやさ: <%= monster.getMonsterSpeed() %></p>
                        <p>スキル1: <%= monster.getSkill1() %></p>
                        <p>スキル2: <%= monster.getSkill2() %></p>
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
				<input type="hidden" name="id"value="<%=status.getId()%>">
				<input type="hidden" name="hp" value="<%=status.getHp()%>">
				<input type="hidden" name="attack" value="<%=status.getAttack()%>">
				<input type="hidden" name="defense"value="<%=status.getDefense()%>"> 
				<input type="hidden"name="speed" value="<%=status.getSpeed()%>"> 
				<input type="hidden" name="itemid" value=1>
				<input type="hidden" name="dungeonid" value="<%=status.getDungeonid()%>">
				<button type="submit">もどる</button>
			</form>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>