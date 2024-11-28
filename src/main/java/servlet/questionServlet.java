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
		
		String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        int hp = Integer.parseInt(request.getParameter("hp"));
        int attack = Integer.parseInt(request.getParameter("attack"));
        int defense = Integer.parseInt(request.getParameter("defense"));
        int speed = Integer.parseInt(request.getParameter("speed"));
        int itemid = Integer.parseInt(request.getParameter("itemid"));
        int dungeonid = Integer.parseInt(request.getParameter("dungeonid"));
        
        System.out.println(name);
        System.out.println(id);

        // Statusオブジェクトを作成
        Status status = new Status(name, id, hp, attack, defense, speed, itemid, dungeonid);

        // セッションにStatusオブジェクトを保存


		
		
	    RankingDao rankingDao = new RankingDao();
	    List<character> questionlist = new ArrayList<>();

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
