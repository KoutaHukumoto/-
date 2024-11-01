package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        
        request.setAttribute("s_id", s_id);
        request.setAttribute("d_id", d_id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/question.jsp");
        dispatcher.forward(request, response);
	}

}
