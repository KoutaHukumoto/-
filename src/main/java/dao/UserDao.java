package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDao extends BaseDao {

	/*
	 * ユーザーとHash値で検索し検索結果あるか否かをチェックする
	 */

	public boolean find(String id, String pass) {

		boolean isLogin = false;
		int idInt =Integer.parseInt(id);
		try {
			// パスワードをハッシュ化 (必要に応じて変更)
			String hashedPass = DigestUtils.sha256Hex(pass);

			// DB接続
			this.connect();

			// SQL文
			String sql = "SELECT id FROM account_table WHERE id = ? AND password = ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				// 検索条件を設定
				ps.setInt(1, idInt);
				ps.setString(2, hashedPass); // ハッシュ化されたパスワードを使う

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
	 *  IDを取り出す
	 */

	public int findID(String pass) {
		int accountId = -1; // IDが見つからなかった場合の値を設定

		try {
			// パスワードをSHA256でハッシュ化
			String hashedPass = DigestUtils.sha256Hex(pass);

			// DB接続
			this.connect();

			String sql = "SELECT id FROM account_table WHERE password = ?";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, hashedPass);

				// 検索処理の実行
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						accountId = rs.getInt("id");
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

		return accountId; // IDを返す。見つからなければ-1を返す。
	}

}
