package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class dungeonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public dungeonServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームからのデータを取得
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String hp = request.getParameter("hp");
        String attack = request.getParameter("attack");
        String defense = request.getParameter("defense");
        String speed = request.getParameter("speed");
        String item = request.getParameter("item");
        String itemEffect = request.getParameter("itemEffect");
        

        request.setAttribute("name", name);
        request.setAttribute("id", id);
        request.setAttribute("hp", hp);
        request.setAttribute("attack", attack);
        request.setAttribute("defense", defense);
        request.setAttribute("speed", speed);
        request.setAttribute("item", item);
        request.setAttribute("itemEffect", itemEffect);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dungeon.jsp");
        dispatcher.forward(request, response);
    }
}
