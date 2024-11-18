package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/questionServlet")
public class questionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public questionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int size = Integer.parseInt(request.getParameter("size"));

		List<String> answerList = new ArrayList<String>();
		String answer = new String();

		for (int i = 0; i < size; i++) {
			answer = request.getParameter("answer_" + i);
			answerList.add(answer);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/answer.jsp");
		dispatcher.forward(request, response);
	}

}
