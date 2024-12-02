package servlet;

import java.io.IOException;

import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status; // Status クラスがある前提

@WebServlet("/backServlet")
public class backServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 必要なパラメータをリクエストから取得
		int id = Integer.parseInt(request.getParameter("id"));

		// Statusオブジェクトを作成
		UserDao userdao = new UserDao();
		Status status = userdao.find(id);
		
		
		request.setAttribute("status", status);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

}
