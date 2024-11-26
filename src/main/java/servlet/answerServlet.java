package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.answerDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status;
import model.answer;

@WebServlet("/answerServlet")
public class answerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public answerServlet() {
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
        


		int size = Integer.parseInt(request.getParameter("size"));
		String s_id = request.getParameter("s_id");
		String d_id = request.getParameter("d_id");

		answerDao answer = new answerDao();

		ArrayList<answer> list = new ArrayList<>();

		int total_answer = 0;

		for (int i = 0; i < size; i++) {
			String text = request.getParameter("text_" + i);
			String selected_answer = request.getParameter("answer_" + i);
			String model_answer = answer.model_answer(text);

			total_answer = total_answer + answer.getAnswers(text, selected_answer);

			list.add(new answer(text, model_answer, selected_answer));
		}

		request.setAttribute("size", size);
		request.setAttribute("s_id", s_id);
		request.setAttribute("d_id", d_id);
		request.setAttribute("total_answer", total_answer);
		request.setAttribute("list", list);
		
		System.out.println(s_id);
		
		
		//以下能力上昇判定機能
		if (s_id.equals("国語")) {
		    hp = hp + 10000;
		}

		
		
		System.out.println(hp);
		
		
		 // Statusオブジェクトを作成
        Status status = new Status(name, id, hp, attack, defense, speed, item, itemEffect);
        
        System.out.println(name);
        
        
        
        request.setAttribute("status", status);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/answer.jsp");
		dispatcher.forward(request, response);
	}

}