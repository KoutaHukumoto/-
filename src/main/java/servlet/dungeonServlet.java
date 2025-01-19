package servlet;
 
import java.io.IOException;

import dao.DungeonDao;
import dao.ItemDao;
import dao.monsterDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Status;
import model.dungeon;
import model.item;
import model.monster;
 

@WebServlet("/dungeonServlet")
public class dungeonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public dungeonServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームからのデータを取得
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        int hp = Integer.parseInt(request.getParameter("hp"));
        int attack = Integer.parseInt(request.getParameter("attack"));
        int defense = Integer.parseInt(request.getParameter("defense"));
        int speed = Integer.parseInt(request.getParameter("speed"));
        int itemid = Integer.parseInt(request.getParameter("itemid"));
        int dungeonid = Integer.parseInt(request.getParameter("dungeonid"));
       
        
        //アイテムによるステータス上昇の計算処理
        ItemDao effectitem = new ItemDao();
        item item = effectitem.getitem(itemid);
        
        hp = hp + item.getEffectHp();
        attack = attack + item.getEffectAttack();
        defense = defense +item.getEffectdefence();
        speed = speed + item.getEffectSpeed();
       
 
        // Statusオブジェクトを作成
        Status status = new Status(name, id, hp, attack, defense, speed, itemid, dungeonid);
 
        // セッションにStatusオブジェクトを保存
        HttpSession session = request.getSession();
        session.setAttribute("status", status);
        session.setAttribute("item", item);
       
        
        //ダンジョン情報を保存
        
        DungeonDao dungeonInfo = new DungeonDao();
        dungeon dungeonInformation = dungeonInfo.getdungeon(dungeonid);

        session.setAttribute("dungeonInformation", dungeonInformation);
        
        //ダンジョン情報からモンスターの決定とモンスターのステータスを取得
   
        
        int monsterId = dungeonInformation.getMonsterId();
        int bossId = 0;
        
    
        
        monsterDao monster = new monsterDao();
        monster monsterstatus = monster.getMonster(monsterId,bossId);
        session.setAttribute("monsterstatus",monsterstatus);
        
 
        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dungeon.jsp");
        dispatcher.forward(request, response);
    }
}