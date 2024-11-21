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
import model.character;

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
	    RankingDao rankingDao = new RankingDao();
	    List<character> questionlist = new ArrayList<>();

	    try {
	        questionlist = rankingDao.getAllData(); // 質問データを取得
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "データの取得に失敗しました。");
	    }

	    // サンプルのカテゴリデータを設定
	    character category = new character();
	    request.setAttribute("category", category);

	    // リクエスト属性に設定
	    request.setAttribute("questionlist", questionlist);

	    // フォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/dojyo.html");
	    dispatcher.forward(request, response);
	}

}
