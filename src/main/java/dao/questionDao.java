package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class questionDao extends BaseDao {

	public void find(String s_id, String d_id) {
		PreparedStatement pstmt = null;

		try {
			connect();
			String sql = "SELECT * FROM question_table "
					+ "WHERE category = ?, difficulty = ?; ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, s_id);
			pstmt.setString(2, d_id);
			
			int play = pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
