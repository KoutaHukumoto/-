package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class answerDao extends BaseDao {
	public int getAnswers(String questionText, String answer) {

		int answercount = 0;

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
						answercount = rs.getInt("正答数");
					}
				}
			}
			return answercount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return answercount;

	}

	public String model_answer(String questionText) {
		String model_answer = "";

		try {
			connect();

			String sql = "SELECT answer FROM question_table WHERE question_text = ?";

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, questionText);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						model_answer = rs.getString("answer");
					}
				}
			}
			return model_answer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.disConnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return model_answer;
	}
	
	public boolean clear(int id, String category, String level) {
	    boolean isSuccess = false; // 更新の成否を判定

	    try {
	        connect(); // データベース接続

	        String sql = "INSERT INTO progress_table (characterid, category, difficulty) VALUES (?, ?, ?)";

	        try (PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, id);
	            ps.setString(2, category);
	            ps.setString(3, level);

	            int rowsInserted = ps.executeUpdate(); // INSERT 実行

	            if (rowsInserted > 0) {
	                isSuccess = true; // 挿入が成功した場合
	            }
	        }
	    }catch (Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (con != null && !con.isClosed()) {
	                this.disConnect(); // 接続を閉じる
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return isSuccess;
	}

	
	public boolean updateclear(int id, String category, String level) {
	    boolean isSuccess = false; // 更新の成否を判定

	    try {
	        connect(); // DB接続

	        String sql = "UPDATE progress_table SET difficulty = ? WHERE characterid = ? AND category = ?";
	        
	        try (PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, level);
	            ps.setInt(2, id);
	            ps.setString(3, category);

	            
	            int rowsUpdated = ps.executeUpdate(); // SQLを実行
	            if (rowsUpdated > 0) {
	                isSuccess = true; // 1行以上更新されたら成功
	            }
	        }
	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (con != null && !con.isClosed()) {
	                this.disConnect(); // 接続を閉じる
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return isSuccess;
	}

	
	public String searchclear(int id, String category) {
	    String model_answer = "無";

	    try {
	    	connect();

	        String sql = "SELECT difficulty FROM progress_table WHERE category = ? AND characterid = ?";
	        try (PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, category);
	            ps.setInt(2, id);

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    model_answer = rs.getString("difficulty");
	                    // NULLチェック
	                    if (model_answer == null) {
	                        model_answer = "無";
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
			e.printStackTrace();
		}  finally {
	        try {
	                this.disConnect();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return model_answer;
	}

}