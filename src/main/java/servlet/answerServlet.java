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

		String change_status;

		int up_status = 0;

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

		switch (s_id) {
		case "国語":
			change_status = "攻撃";
			up_status = attack + total_answer;
			break;
		case "数学":
			change_status = "HP";
			up_status = hp + total_answer;
			break;
		case "英語":
			change_status = "防御";
			up_status = defense + total_answer;
			break;
		case "理科":
			change_status = "すばやさ";
			up_status = speed + total_answer;
			break;
		default:
			change_status = "装備品";
			break;
		}

		request.setAttribute("size", size);
		request.setAttribute("s_id", s_id);
		request.setAttribute("d_id", d_id);
		request.setAttribute("total_answer", total_answer);
		request.setAttribute("list", list);
		request.setAttribute("change_status", change_status);
		request.setAttribute("up_status", up_status);

		// Statusオブジェクトを作成
		Status status = new Status(name, id, hp, attack, defense, speed, item, itemEffect);

		request.setAttribute("status", status);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/answer.jsp");
		dispatcher.forward(request, response);
	}

}