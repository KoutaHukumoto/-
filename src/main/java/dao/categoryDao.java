package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class categoryDao extends BaseDao {
	public String getcategory(int id, String category) {
		String difficulty = null;
		
		try {
			connect();
			String sql = "SELECT * FROM progress_table"
					+ " WHERE characterid = ?"
					+ " AND category = ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				ps.setString(2, category);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						difficulty = rs.getString("difficulty");
					}
				}
			}
			return difficulty;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return difficulty;

	}
	

}
