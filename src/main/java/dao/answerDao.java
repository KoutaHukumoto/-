package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.answer;

public class answerDao extends BaseDao {
	public List<answer> getAnswers(String questionText, String answer) {
		List<answer> answers = new ArrayList<>();

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
						// 検索結果があった場合、Questionオブジェクトを作成し、リストに追加

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
		return answers;

	}
}
