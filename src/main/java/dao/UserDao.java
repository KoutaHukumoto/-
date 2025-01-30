package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Status;

public class UserDao extends BaseDao {

	/*
	 * ユーザーとHash値で検索し検索結果あるか否かをチェックする
	 */

	public boolean find(String name, String pass) {
		boolean isLogin = false;

		try {

			// DB接続
			this.connect();

			// SQL文
			String sql = "SELECT charactername FROM account_table WHERE charactername = ? AND password = ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				// 検索条件を設定
				ps.setString(1, name);
				ps.setString(2, pass);

				// 検索実行
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						// 検索結果があった場合
						isLogin = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// DB切断
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isLogin;
	}

	/*
	 *  データをすべて取り出す
	 */

	public Status find(String name) {

		Status status = null;

		try {
			// DB接続
			this.connect();

			String sql = "SELECT * FROM character_table WHERE charactername = ?";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, name);

				// 検索処理の実行
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						// データが見つかった場合、Characterオブジェクトにセットして返す
						status = new Status(
								rs.getString("charactername"),
								rs.getInt("characterid"),
								rs.getInt("hp"),
								rs.getInt("attack"),
								rs.getInt("defense"),
								rs.getInt("speed"),
								rs.getInt("itemid"),
								rs.getInt("dungeonid"),
								rs.getInt("avaterid"));
					}
				}
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public Status findname(String name) {

		Status status = null;

		try {
			// DB接続
			this.connect();

			String sql = "SELECT * FROM character_table WHERE charactername = ?";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, name);

				// 検索処理の実行
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						// データが見つかった場合、Characterオブジェクトにセットして返す
						status = new Status(
								rs.getString("charactername"),
								rs.getInt("characterid"),
								rs.getInt("hp"),
								rs.getInt("attack"),
								rs.getInt("defense"),
								rs.getInt("speed"),
								rs.getInt("itemid"),
								rs.getInt("dungeonid"),
								rs.getInt("avaterid"));
					}
				}
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public boolean updateDungeon(String name, int dungeon) {
	    boolean isUpdated = false;

	    try {
	        // DB接続
	        this.connect();

	        // ダンジョンIDを更新するSQL文
	        String updateSql = "UPDATE character_table SET dungeonid = ? WHERE charactername = ?";
	        try (PreparedStatement ps = con.prepareStatement(updateSql)) {
	            ps.setInt(1, dungeon);
	            ps.setString(2, name);

	            // 更新処理の実行
	            int rowsUpdated = ps.executeUpdate();
	            if (rowsUpdated > 0) {
	                isUpdated = true; // 更新が成功した場合は true を設定
	            } else {
	                System.out.println("指定されたキャラクターが見つかりませんでした。更新は行われません。");
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println("エラーが発生しました: " + e.getMessage());
	    } finally {
	        try {
	            this.disConnect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return isUpdated;
	}

}