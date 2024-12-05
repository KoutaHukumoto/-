package servlet;

import java.io.IOException;

import dao.ItemDao;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status; // Status クラスがある前提
import model.item;

@WebServlet("/backServlet")
public class backServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 必要なパラメータをリクエストから取得
		String name = request.getParameter("name");
		

		// Statusオブジェクトを作成
		UserDao userdao = new UserDao();
		Status status = userdao.findname(name);
		
		ItemDao itemdao = new ItemDao();
		item item = itemdao.getitem(status.getItemid());
		request.setAttribute("item", item);
		
		
		request.setAttribute("status", status);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

}