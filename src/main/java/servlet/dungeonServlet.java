package servlet;

import java.io.IOException;

import dao.monsterDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Status;
import model.monster;

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
        String item = request.getParameter("item");
        String itemEffect = request.getParameter("itemEffect");
        

        // Statusオブジェクトを作成
        Status status = new Status(name, id, hp, attack, defense, speed, item, itemEffect);

        // セッションにStatusオブジェクトを保存
        HttpSession session = request.getSession();
        session.setAttribute("status", status);

        int monsterId = 1;
        int bossId = 0;

        monsterDao monster = new monsterDao();
        monster monsterstatus = monster.getMonster(monsterId,bossId);
        session.setAttribute("monsterstatus",monsterstatus);

        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dungeon.jsp");
        dispatcher.forward(request, response);
    }
}
