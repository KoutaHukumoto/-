package servlet;

import java.io.IOException;
import java.util.List;

import dao.questionDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Status;
import model.question;

@WebServlet("/dojyoServlet")
public class dojyoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public dojyoServlet() {
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
        int hp = Integer.parseInt(request.getParameter("hp"));
        int attack = Integer.parseInt(request.getParameter("attack"));
        int defense = Integer.parseInt(request.getParameter("defense"));
        int speed = Integer.parseInt(request.getParameter("speed"));
        String item = request.getParameter("item");
        String itemEffect = request.getParameter("itemEffect");
        
        System.out.println(name);
        System.out.println(id);

        // Statusオブジェクトを作成
        Status status = new Status(name, id, hp, attack, defense, speed, item, itemEffect);

        // セッションにStatusオブジェクトを保存
        HttpSession session = request.getSession();
        session.setAttribute("status", status);

		String s_id = request.getParameter("s_id");
		String d_id = request.getParameter("d_id");

		int id1 = 10;
		System.out.println(s_id);
		System.out.println(d_id);

		questionDao question = new questionDao();

		List<question> questionlist = question.getQuestions(s_id, d_id, id1);

		question category = new question(s_id, d_id);

		request.setAttribute("category", category);
		request.setAttribute("questionlist", questionlist);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/question.jsp");
		dispatcher.forward(request, response);
	}

}