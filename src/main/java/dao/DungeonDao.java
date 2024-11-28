package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dungeon;


public class DungeonDao extends BaseDao {

	public dungeon getdungeon(int dungeonid) {
		dungeon Dungeon = null;

	        try {
	            // DB接続
	            this.connect();

	            String sql = "SELECT *"
	            		+ "FROM dungeon_table WHERE dungeonid = ?";
	            
	            //上記だと一意のユーザのデータが持ってこれない
	            //ログインしているユーザのdungeonidで絞りこみたい
	            
	            
	           
	            try (PreparedStatement ps = con.prepareStatement(sql)) {
	                // 検索条件を設定
	                ps.setInt(1, dungeonid);

	                // 検索実行
	                try (ResultSet rs = ps.executeQuery()) {
	                    if (rs.next()) {
	                        // 検索結果があった場合
	                    	Dungeon = new dungeon(
	                            rs.getInt("dungeonid"),     
	                            rs.getInt("monsterId"),
	                            rs.getDouble("reinforcement"),
	                            rs.getInt("bossFlag")
	                        );
	                    }
	                }
	            }
	            return Dungeon;  
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                this.disConnect();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return Dungeon;
	    }

	
	
}