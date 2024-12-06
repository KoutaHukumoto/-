package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.ItemDao;
import dao.answerDao;
import dao.statusDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Status;
import model.answer;
import model.item;

@WebServlet("/answerServlet")
public class answerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public answerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		int hp = Integer.parseInt(request.getParameter("hp"));
		int attack = Integer.parseInt(request.getParameter("attack"));
		int defense = Integer.parseInt(request.getParameter("defense"));
		int speed = Integer.parseInt(request.getParameter("speed"));
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		int dungeonid = Integer.parseInt(request.getParameter("dungeonid"));

		int size = Integer.parseInt(request.getParameter("size"));
		String s_id = request.getParameter("s_id");
		String d_id = request.getParameter("d_id");

		String change_status = "";

		String change_status_id = "";

		int up_status = 0;

		answerDao answer = new answerDao();

		ArrayList<answer> list = new ArrayList<>();

		int total_answer = 0;

		int itemlist = 5;
		item getitem = null;
		ItemDao itemdao = new ItemDao();
		item acquisition = null;

		for (int i = 0; i < size; i++) {
			String text = request.getParameter("text_" + i);
			String selected_answer = request.getParameter("answer_" + i);
			String model_answer = answer.model_answer(text);

			total_answer = total_answer + answer.getAnswers(text, selected_answer);

			list.add(new answer(text, model_answer, selected_answer));
		}

		int total_answer_status = total_answer;

		if (d_id.equals("中級")) {
			total_answer_status = total_answer * 2;
		} else if (d_id.equals("上級")) {
			total_answer_status = total_answer * 3;
		}
		if (total_answer >= 5) {

			switch (s_id) {
			case "国語":
				change_status = "攻撃";
				change_status_id = "attack";
				up_status = attack + total_answer_status;
				break;
			case "数学":
				change_status = "HP";
				change_status_id = "hp";
				up_status = hp + total_answer_status;
				break;
			case "英語":
				change_status = "防御";
				change_status_id = "defense";
				up_status = defense + total_answer_status;
				break;
			case "理科":
				change_status = "すばやさ";
				change_status_id = "speed";
				up_status = speed + total_answer_status;
				break;
			default:
				change_status = "装備品";
				change_status_id = "item";
				break;
			}

			if (s_id.equals("社会")) {
				if (d_id.equals("中級")) {
					itemlist = 10;
				} else if (d_id.equals("上級")) {
					itemlist = 15;
				}

				acquisition = itemdao.getitemlist(itemid, itemlist);
				getitem = itemdao.getitem(itemid);

			} else {
				
				acquisition = itemdao.getitemlist(itemid, itemlist);
				getitem = itemdao.getitem(itemid);

				statusDao changestatus = new statusDao();
				changestatus.updateStatus(change_status_id, up_status, id);
			}
		} else {
			acquisition = itemdao.getitemlist(itemid, itemlist);
			getitem = itemdao.getitem(itemid);
		}
		
		if(total_answer == 10) {
			
			
		}
			

		System.out.println(getitem.getItemId());

		request.setAttribute("acquisitionitem", acquisition);
		request.setAttribute("item", getitem);
		request.setAttribute("size", size);
		request.setAttribute("s_id", s_id);
		request.setAttribute("d_id", d_id);
		request.setAttribute("total_answer", total_answer);
		request.setAttribute("total_answer_status", total_answer_status);
		request.setAttribute("list", list);
		request.setAttribute("change_status", change_status);
		request.setAttribute("change_status_id", change_status_id);
		request.setAttribute("up_status", up_status);

		System.out.println(hp);

		// Statusオブジェクトを作成
		Status status = new Status(name, id, hp, attack, defense, speed, itemid, dungeonid);

		System.out.println(name);

		request.setAttribute("status", status);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/answer.jsp");
		dispatcher.forward(request, response);
	}

}