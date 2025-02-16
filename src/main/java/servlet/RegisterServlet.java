package servlet;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import dao.RegisterDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Status;

/**
 * Servlet implementation class Registration
 */

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public RegisterServlet() {
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
		// フォームからデータを受け取る
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		String hashdpass = DigestUtils.sha256Hex(pass);

		// RegisterDaoを使ってユーザーをDBに登録
		RegisterDao register = new RegisterDao();
		int id = register.registerUser(hashdpass);
		boolean newcharacter = register.registercharacter(id,name);

		Status status = new Status(name, id, pass);
		
		// セッションにStatusオブジェクトを保存
		HttpSession session = request.getSession();
		session.setAttribute("status", status);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Register.jsp");
		dispatcher.forward(request, response);

	}

}
