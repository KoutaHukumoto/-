package servlet;

import java.io.IOException;

import dao.DungeonDao;
import dao.ItemDao;
import dao.UserDao;
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

/**
 * Servlet implementation class rematchServlet
 */
@WebServlet("/rematchServlet")
public class rematchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public rematchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int result = Integer.parseInt(request.getParameter("result"));
		UserDao userdao = new UserDao();
		Status statues = userdao.findname(name);

		int id = statues.getId();
		int hp = statues.getHp();
		int attack = statues.getAttack();
		int defense = statues.getDefense();
		int speed = statues.getSpeed();
		int itemid = statues.getItemid();
		int dungeonid = statues.getDungeonid();
		int avaterid = statues.getAvatarid();
		if(dungeonid < 5) {
		if (result != 0) {
			dungeonid = result + dungeonid;
			boolean isUpdated = userdao.updateDungeon(name, dungeonid);
		}
		}

		//アイテムによるステータス上昇の計算処理
		ItemDao effectitem = new ItemDao();
		item item = effectitem.getitem(itemid);

		hp = hp + item.getEffectHp();
		attack = attack + item.getEffectAttack();
		defense = defense + item.getEffectdefence();
		speed = speed + item.getEffectSpeed();

		// Statusオブジェクトを作成
		Status status = new Status(name, id, hp, attack, defense, speed, itemid, dungeonid,avaterid);

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

		monsterDao monster = new monsterDao();
		monster monsterstatus = monster.getMonster(monsterId);
		session.setAttribute("monsterstatus", monsterstatus);
		// JSPにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dungeon.jsp");
		dispatcher.forward(request, response);

	}

}
