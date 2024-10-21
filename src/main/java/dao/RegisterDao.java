package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

public class RegisterDao extends BaseDao {

	public boolean registerUser(String pass) {
		PreparedStatement pstmt = null;

		try {
			// パスワードをSHA256でハッシュ化
			String hashedPass = DigestUtils.sha256Hex(pass);

			// DB接続
			connect();

			// SQL INSERT文の作成
			String sql = "INSERT INTO account_table (password) VALUES (?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hashedPass); // ハッシュ化されたパスワードを保存

			// SQL文の実行
			int rowsInserted = pstmt.executeUpdate();

			// 挿入が成功したかどうかを返す
			return rowsInserted > 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				// DB切断
				disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean registercharacter(String name) {
		PreparedStatement pstmt = null;
		try {


			// DB接続
			connect();

			// SQL INSERT文の作成
			String sql = "INSERT INTO character_table (name) VALUES (?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			

			// SQL文の実行
			int rowsInserted = pstmt.executeUpdate();

			// 挿入が成功したかどうかを返す
			return rowsInserted > 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				// DB切断
				disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}