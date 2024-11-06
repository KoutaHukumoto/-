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
		
		String s_id = request.getParameter("s_id");
        String d_id = request.getParameter("d_id");
        
        questionDao question = new questionDao();
        
        List<question> questionlist  = question.getQuestions(s_id, d_id);
        
        request.setAttribute("questionlist", questionlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/question.jsp");
        dispatcher.forward(request, response);
	}

}
