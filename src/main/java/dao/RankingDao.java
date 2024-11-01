package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.character;

public class RankingDao extends BaseDao {

	public List<character> getAllData() throws SQLException {
	    List<character> dataList = new ArrayList<>();
	    try {
	        this.connect();
	        
	        String sql = "SELECT charactername, dungeonid FROM character_table ORDER BY dungeonid DESC;";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            character data = new character();
	            data.setCharacterName(rs.getString("charactername")); // カラム名を修正
	            data.setDungeonId(rs.getInt("dungeonid"));

	            dataList.add(data);
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

	    return dataList;
	}

	
	


}
