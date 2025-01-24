package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        Cookie cookie = new Cookie("loginServlet", "true"); // クッキーに「サーブレット経由」の情報を記録
        cookie.setMaxAge(60); // クッキーの有効期限（秒）
        response.addCookie(cookie);

        HttpSession session = request.getSession(false);

        if (session != null) {
            // セッションを無効化してログアウト処理を行う
            session.invalidate();
        }
        
        response.sendRedirect("toppage.html");
    }
}
