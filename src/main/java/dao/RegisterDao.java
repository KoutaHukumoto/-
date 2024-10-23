package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDao extends BaseDao {

	public int registerUser(String pass) {
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;

		try {
			// パスワードをSHA256でハッシュ化

			// DB接続
			connect();
			String sql = "INSERT INTO account_table (password) VALUES (?)";
			pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pass); // ハッシュ化されたパスワードを保存

			// SQL文の実行
			int rowsInserted = pstmt.executeUpdate();

	        if (rowsInserted > 0) {
	            generatedKeys = pstmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                // 自動生成されたIDを取得
	                return generatedKeys.getInt(1); // 1は最初のカラムを指す
	            }
	        }
			return 1000000;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} finally {
            try {
                if (generatedKeys != null)
                    generatedKeys.close();
                if (pstmt != null)
                    pstmt.close();
                // DB切断
                disConnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	

	public boolean registercharacter(int id, String name) {
		PreparedStatement pstmt = null;
		try {

			// DB接続
			connect();

			// SQL INSERT文の作成
			String sql = "INSERT INTO character_table (accountid,charactername) VALUES (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);

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