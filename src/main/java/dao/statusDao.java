package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class statusDao extends BaseDao {
	public int updateStatus(String change_status_id, int up_status, int id) {
		int rowsUpdated = 0;
		try {
			connect();
			String sql = "UPDATE character_table SET " + change_status_id + " = ? WHERE characterid = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, up_status);
			ps.setInt(2, id);

			rowsUpdated = ps.executeUpdate();

			return rowsUpdated;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsUpdated;
	}
}
