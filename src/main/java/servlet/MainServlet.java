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

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ranking.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // ランキングデータを取得
	    RankingDao rankingDao = new RankingDao();
	    List<character> dataList = new ArrayList<>();
	    
	    try {
	        dataList = rankingDao.getAllData(); // データベースからデータを取得
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // エラーハンドリング: エラーメッセージをリクエストに設定するなど
	        request.setAttribute("errorMessage", "データの取得に失敗しました。");
	    }

	    // リクエスト属性にデータリストを設定
	    request.setAttribute("dataList", dataList);

	    // フォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ranking.jsp");
	    dispatcher.forward(request, response);
	}
}
