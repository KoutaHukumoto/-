package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.item;

public class ItemDao extends BaseDao{

	
	public item getitem(int itemid) {
       item item = null;

        try {
            // DB接続
            this.connect();

            String sql = "SELECT * FROM item_table WHERE itemid = ?";
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // 検索条件を設定
                ps.setInt(1, itemid);

                // 検索実行
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // 検索結果があった場合
                        item = new item(
                            rs.getInt("itemid"),     
                            rs.getString("itemname"),
                            rs.getInt("effecthp"),
                            rs.getInt("effectattack"),
                            rs.getInt("effectdefence"),
                            rs.getInt("effectspeed")
                        );
                    }
                }
            }
            return item;  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.disConnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }
}
	