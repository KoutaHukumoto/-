package servlet;
 
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import dao.ItemDao;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Status;
import model.item;
import model.loginLogic;
 
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public loginServlet() {
		super();
 
	}
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//フォワード先
		String forward = null;
		//セッション開始
		HttpSession session = request.getSession();
 
		if (session.getAttribute("loginUser") == null) {
			//未ログイン
			forward = "newRegistration.jsp";
		} else {
			//ログイン済み
			forward = "mypage.jsp";
		}
 
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/" + forward);
		dispatcher.forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
 
		String hashdpass = DigestUtils.sha256Hex(pass);
 
		// loginLogicクラスでログイン処理を実行
		loginLogic loginLogic = new loginLogic();
		boolean isLogin = loginLogic.execute(name, hashdpass);
 
		// ログイン結果によるリダイレクト
		if (isLogin) {
			// ログイン成功時のリダイレクト先
			UserDao userdao = new UserDao();
			Status status = userdao.find(name);
			request.setAttribute("status", status);
			ItemDao itemdao = new ItemDao();
			item item = itemdao.getitem(status.getItemid());
			request.setAttribute("item", item);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		} else {
			// ログイン失敗時、エラーメッセージをセットしてログインページに戻す
	        Cookie cookie = new Cookie("loginServlet", "false"); // クッキーに「サーブレット経由」の情報を記録
	        cookie.setMaxAge(5); // クッキーの有効期限（秒）
	        response.addCookie(cookie);
			RequestDispatcher dispatcher = request.getRequestDispatcher("toppage.html");
			dispatcher.forward(request, response);
		}
	}
}
 
 