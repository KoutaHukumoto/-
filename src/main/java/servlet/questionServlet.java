package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ItemDao;
import dao.RankingDao;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status;
import model.answerlist;
import model.character;
import model.item;

@WebServlet("/questionServlet")
public class questionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/dojyo.html");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォームからのデータを取得
		String name = request.getParameter("name");

		UserDao userdao = new UserDao();
		Status status = userdao.findname(name);

		ItemDao itemdao = new ItemDao();
		item item = itemdao.getitem(status.getItemid());
		request.setAttribute("item", item);

		RankingDao rankingDao = new RankingDao();
		List<character> questionlist = new ArrayList<>();

		List<answerlist> answerlist = new ArrayList<>();

		answerlist answers = new answerlist();
		answerlist.addAll(answerlist);
		
        for (answerlist answer : answerlist) {
            System.out.println("Character ID: " + answers.getCharacterId());
            for (int i = 0; i < 5; i++) {
                List<String> pair = answer.getCategoryDifficultyAt(i);
                System.out.println(pair.get(0) + ": " + pair.get(1));
            }
        }
		
		try {
			questionlist = rankingDao.getAllData(); // 質問データを取得
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "データの取得に失敗しました。");
		}

		request.setAttribute("status", status);

		// サンプルのカテゴリデータを設定
		character category = new character();
		request.setAttribute("category", category);

		// リクエスト属性に設定
		request.setAttribute("questionlist", questionlist);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dojyo.jsp");
		dispatcher.forward(request, response);
	}

}
