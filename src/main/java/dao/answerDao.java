package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class answerDao extends BaseDao {
	public int getAnswers(String questionText, String answer) {

		int answercount = 0;

		try {
			connect();
			String sql = "SELECT COUNT(*) AS 正答数 FROM question_table"
					+ " WHERE question_text = ?"
					+ " AND answer = ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, questionText);
				ps.setString(2, answer);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						answercount = rs.getInt("正答数");
					}
				}
			}
			return answercount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return answercount;

	}

	public String model_answer(String questionText) {
		String model_answer = "";

		try {
			connect();

			String sql = "SELECT answer FROM question_table WHERE question_text = ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, questionText);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						model_answer = rs.getString("answer");
					}
				}
			}
			return model_answer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return model_answer;
	}
}
