package servlet;

import java.io.IOException;

import dao.RegisterDao;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        // フォームからデータを受け取る
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // RegisterDaoを使ってユーザーをDBに登録
        RegisterDao register = new RegisterDao();
        boolean Registered = register.registerUser(pass);
        boolean newcharacter = register.registerUser(name);
        
        UserDao user = new UserDao();
        int id = user.findID(pass);
        
        request.setAttribute("name", name);
        request.setAttribute("id", id);
        request.setAttribute("pass", pass);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Register.jsp");
        dispatcher.forward(request, response);
        
	}

}
