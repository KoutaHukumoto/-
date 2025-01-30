package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RankingDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status;
import model.character;

@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GETリクエスト時の処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ranking.jsp");
		dispatcher.forward(request, response);
	}

	// POSTリクエスト時の処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		int hp = Integer.parseInt(request.getParameter("hp"));
		int attack = Integer.parseInt(request.getParameter("attack"));
		int defense = Integer.parseInt(request.getParameter("defense"));
		int speed = Integer.parseInt(request.getParameter("speed"));
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		int dungeonid = Integer.parseInt(request.getParameter("dungeonid"));
		int avaterid = Integer.parseInt(request.getParameter("avaterid"));

		// Statusオブジェクトを作成
		Status status = new Status(name, id, hp, attack, defense, speed, itemid, dungeonid,avaterid);

		// Status オブジェクトをリクエスト属性にセット
		request.setAttribute("status", status);

		// フォームから送信されたキャラクター名を取得
		String characterName = request.getParameter("characterName");

		// ランキングデータを取得するためにRankingDaoを使用
		RankingDao rankingDao = new RankingDao();
		List<character> dataList = new ArrayList<>();

		try {
			// キャラクター名が空でない場合は検索を実行、空の場合は全件取得
			if (characterName != null && !characterName.trim().isEmpty()) {
				dataList = rankingDao.searchData(characterName); // 部分一致検索
			} else {
				dataList = rankingDao.getAllData(); // 全件取得
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// エラーメッセージをリクエストに設定
			request.setAttribute("errorMessage", "データの取得に失敗しました。");
		}

		// リクエスト属性にデータリストを設定
		request.setAttribute("dataList", dataList);

		// エラーメッセージがあればJSPに渡す
		String errorMessage = (String) request.getAttribute("errorMessage");

		// JSPにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ranking.jsp");
		dispatcher.forward(request, response);
	}
}
