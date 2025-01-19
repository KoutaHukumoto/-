package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.monster;

public class monsterDao extends BaseDao {

	public monster getMonster(int monsterid, int bossid) {
		monster monster = null;

		try {
			// DB接続
			this.connect();

			String sql = "SELECT * FROM monster_table WHERE monsterid = ? AND bossid = ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				// 検索条件を設定
				ps.setInt(1, monsterid);
				ps.setInt(2, bossid);

				// 検索実行
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						// 検索結果があった場合
						monster = new monster(
								rs.getInt("monsterid"),
								rs.getString("monstername"),
								rs.getInt("attack"),
								rs.getInt("hp"),
								rs.getInt("defence"),
								rs.getInt("speed"),
								rs.getString("skill1"),
								rs.getString("skill2"));
					}
				}
			}
			return monster;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return monster;
	}
}