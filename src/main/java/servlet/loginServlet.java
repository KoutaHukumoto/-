package servlet;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.loginLogic;


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
			forward = "index.jsp";
		} else {
			//ログイン済み
			forward = "loginResult.jsp";
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/" + forward);
		dispatcher.forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームからのデータを取得
        String pass = request.getParameter("pass");
        String id = request.getParameter("id");
        
      //パスワードをSHA256でハッシュ化
      		String hash = DigestUtils.sha256Hex(pass);

      		//JavaBeansを生成
      		Account account = new Account();

      		//BOを生成
      		loginLogic loginLogic = new loginLogic();

      		//ログイン認証
      		boolean isLogin = loginLogic.execute(account);

      		//フォワード先
      		String forward = null;

      		if (isLogin) {
      			//認証OK

      			//セッション開始
      			HttpSession session = request.getSession();

      			//セッションスコープにユーザー情報を保存
      			session.setAttribute("loginUser", account);

      			//フォワード先を設定
      			forward = "loginResult.jsp";
      		} else {
      			//認証NG

      			//リクエストスコープエラーメッセージ設定
      			request.setAttribute("errorMessage", "入力に誤りがあります");

      			//フォワード先を設定
      			forward = "index.jsp";
      		}
        
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Top_Page.jsp");
        dispatcher.forward(request, response);
    }
}

