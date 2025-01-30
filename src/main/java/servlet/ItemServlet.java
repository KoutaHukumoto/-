package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ItemDao;
import dao.RankingDao;
import dao.UserDao;
import dao.categoryDao;
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

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		int itemid = Integer.parseInt(request.getParameter("itemId"));

		UserDao userdao = new UserDao();
		Status status = userdao.findname(name);

		ItemDao itemdao = new ItemDao();
		item item = itemdao.getitem(status.getItemid());
		request.setAttribute("item", item);

		// answerlist のインスタンスを生成
		answerlist answers = new answerlist();

		categoryDao difficulty = new categoryDao();
		RankingDao rankingDao = new RankingDao();
		List<character> questionlist = new ArrayList<>();

		List<String> categorylist = answers.getcategorylist();

		for (int i = 0; i < 5; i++) {
			String category = difficulty.getcategory(status.getId(), categorylist.get(i));
			if (category != null) {
				answers.updateLevel(categorylist.get(i), category);
			}
		}
		try {
			questionlist = rankingDao.getAllData(); // 質問データを取得
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "データの取得に失敗しました。");
		}

		System.out.println(answers.getcategorylist());

		request.setAttribute("answers", answers);

		request.setAttribute("status", status);

		// サンプルのカテゴリデータを設定
		character category = new character();
		request.setAttribute("category", category);

		// リクエスト属性に設定
		request.setAttribute("questionlist", questionlist);


		itemdao.changeItem(itemid, id);

		item itemlist = itemdao.getitem(itemid);
		
		request.setAttribute("item", itemlist);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dojyo.jsp");
		dispatcher.forward(request, response);
	}

}