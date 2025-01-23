package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class answerlist implements Serializable {
	private int characterId;
	private String category;
	private String difficulty;

	// 固定サイズの二次元リストのフィールドを追加
	private List<List<String>> categoryDifficultyList;

	// コンストラクタ
	public answerlist() {
		this.categoryDifficultyList = new ArrayList<>(
				Arrays.asList(
						Arrays.asList("国語", "初級"),
						Arrays.asList("数学", "初級"),
						Arrays.asList("英語", "初級"),
						Arrays.asList("理科", "初級"),
						Arrays.asList("社会", "初級")));
	}

	// Getter
	public List<List<String>> getcategoryDifficultyList() {
		return categoryDifficultyList;
	}

	// Setter
	public void setItems(List<List<String>> categoryDifficultyList) {
		this.categoryDifficultyList = categoryDifficultyList;
	}

	// 科目のレベルを更新
	public void updateLevel(String category, String difficulty) {
		for (List<String> item : this.categoryDifficultyList) {
			if (item.get(0).equals(category)) {
				item.set(1, difficulty);
				break;
			}
		}
	}

	// 特定の科目の難易度を返す
	public String getDifficultyBySubject(String subject) {
		for (List<String> list : categoryDifficultyList) {
			if (list.get(0).equals(subject)) {
				return list.get((1));
			}
		}
		return null; // 見つからなかった場合はnullを返す
	}

	public List<String> getcategorylist() {
	    List<String> categories = new ArrayList<>();
	    for (List<String> item : categoryDifficultyList) {
	        // 各行の最初の要素（科目）を取得してリストに追加
	        categories.add(item.get(0));
	    }
	    return categories;
	}

	// characterId のゲッターとセッター
	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	// category のゲッターとセッター
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// difficulty のゲッターとセッター
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

}
