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
 
			String sql = "SELECT charactername, dungeonid , \r\n"

					+ "RANK() OVER (ORDER BY dungeonid DESC) AS 順位\r\n"

					+ "FROM character_table;";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
 
			while (rs.next()) {

				character data = new character();

				data.setRank(rs.getInt("順位"));

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
 
	public List<character> searchData(String characterName) throws SQLException {

		List<character> dataList = new ArrayList<>();
 
		System.out.println(1);
 
		try {

			int searchrank = 0;

			this.connect();
 
			// 名前を部分一致で検索するクエリ

			String sql = "WITH ranked_people AS ("

					+ "SELECT"

					+ " charactername,"

					+ " dungeonid,"

					+ " RANK() OVER (ORDER BY dungeonid DESC) AS rank"

					+ " FROM character_table"

					+ " )"

					+ " SELECT "

					+ "charactername,"

					+ " dungeonid,"

					+ "rank"

					+ " FROM ranked_people"

					+ " WHERE charactername LIKE ?;";
 
			System.out.println(2);
 
			searchrank++;

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, "%" + characterName + "%"); // 名前を部分一致で検索

			ResultSet rs = ps.executeQuery();
 
			System.out.println(searchrank);
 
			while (rs.next()) {

				character data = new character();

				data.setRank(rs.getInt("rank"));

				data.setCharacterName(rs.getString("charactername")); // カラム名を修正

				data.setDungeonId(rs.getInt("dungeonid"));
 
				System.out.println(3);
 
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

 