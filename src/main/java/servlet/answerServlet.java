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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/answer.jsp");
		dispatcher.forward(request, response);
	}

}
