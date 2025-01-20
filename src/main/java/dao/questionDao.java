package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.question;

public class questionDao extends BaseDao {

	public List<question> getQuestions(String s_id, String d_id, int id) {
		List<question> questions = new ArrayList<>();

		try {
			connect();
			String sql = "SELECT * FROM question_table "
					+ "WHERE category = ? AND difficulty = ? ORDER BY RAND() LIMIT ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				// 検索条件を設定
				ps.setString(1, s_id);
				ps.setString(2, d_id);
				ps.setInt(3, id);

				// 検索実行
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						// 検索結果があった場合、Questionオブジェクトを作成し、リストに追加
						question question = new question(
								rs.getString("category"),
								rs.getString("difficulty"),
								rs.getString("question_text"),
								rs.getString("answer"),
								rs.getString("fakeAnswer1"),
								rs.getString("fakeAnswer2"),
								rs.getString("fakeAnswer3"));
						questions.add(question);

					}
				}
			}
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