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

@WebServlet("/rankingServlet")
public class rankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // 初期データを設定
	    Status defaultStatus = new Status("デフォルト名", 0, 100, 10, 10, 10, 1, 1);
	    request.setAttribute("status", defaultStatus);

	    RankingDao rankingDao = new RankingDao();
	    List<character> dataList = new ArrayList<>();
	    try {
	        dataList = rankingDao.getAllData(); // データベースから全データを取得
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "データの取得に失敗しました。");
	    }

	    request.setAttribute("dataList", dataList);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ranking.jsp");
	    dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // ランキングデータを取得
		
		 	String name = request.getParameter("name");
	        int id = Integer.parseInt(request.getParameter("id"));
	        int hp = Integer.parseInt(request.getParameter("hp"));
	        int attack = Integer.parseInt(request.getParameter("attack"));
	        int defense = Integer.parseInt(request.getParameter("defense"));
	        int speed = Integer.parseInt(request.getParameter("speed"));
	        int itemid = Integer.parseInt(request.getParameter("itemid"));
	        int dungeonid = Integer.parseInt(request.getParameter("dungeonid"));
	        

	        // Statusオブジェクトを作成
	        Status status = new Status(name, id, hp, attack, defense, speed, itemid, dungeonid);
	        
	        System.out.println(name);
	        
	        request.setAttribute("status", status);
		
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
