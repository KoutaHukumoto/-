package servlet;

import java.io.IOException;

import dao.ItemDao;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status;
import model.item;

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemServlet() {
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
		int itemid = Integer.parseInt(request.getParameter("itemId"));

		ItemDao itemdao = new ItemDao();

		itemdao.changeItem(itemid, id);
		
		UserDao userdao = new UserDao();
		
		item itemlist = itemdao.getitem(itemid);
		request.setAttribute("item", itemlist);
		
		
		// Statusオブジェクトを作成
		Status status = userdao.findname(name);

		request.setAttribute("status", status);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dojyo.jsp");
		dispatcher.forward(request, response);
	}

}