package servlet;

import java.io.IOException;
import java.util.List;

import dao.RegisterDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.character;

@WebServlet("/searchnameServlet")
public class searchnameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public searchnameServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegisterDao register = new RegisterDao();

        // 名前で検索した結果を取得
        List<character> list = register.searchName();



        // セッションスコープにデータを格納
        HttpSession session = request.getSession();
        session.setAttribute("list", list);

        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/newRegistration.jsp");
        dispatcher.forward(request, response);
    }
}
