package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.question;

public class answerDao extends BaseDao {
	public List<question> getAnswers(String s_id, String d_id, int id) {
		List<question> questions = new ArrayList<>();

		try {
			connect();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return questions;
	}
}
