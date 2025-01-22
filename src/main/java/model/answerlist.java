package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class answerlist implements Serializable {
    private int characterId;
    private String category;
    private String difficulty;

    // 固定サイズの二次元リストのフィールドを追加
    private List<List<String>> categoryDifficultyList;

    // コンストラクタ
    // コンストラクタ
    public answerlist() {
        this.categoryDifficultyList = new ArrayList<>(5); // 容器のサイズを5に設定
        for (int i = 0; i < 5; i++) {
            this.categoryDifficultyList.add(new ArrayList<>(2)); // 各要素を初期化
        }
        // 初期データを設定
        this.setCategoryDifficultyAt1("国語", "初級");
        this.setCategoryDifficultyAt1("数学", "初級");
        this.setCategoryDifficultyAt1("英語", "初級");
        this.setCategoryDifficultyAt1("理科", "初級");
        this.setCategoryDifficultyAt1("社会", "初級");
    }

    // インデックスを自動で管理するフィールド
    private int currentIndex = 0;

    // 特定の位置にデータをセット（インデックス自動管理版）
    public void setCategoryDifficultyAt1(String category, String difficulty) {
        if (currentIndex < categoryDifficultyList.size()) {
            List<String> row = categoryDifficultyList.get(currentIndex);
            row.clear();
            row.add(category);
            row.add(difficulty);
            currentIndex++;
        } else {
            throw new IndexOutOfBoundsException("設定可能な項目数を超えました");
        }
    }

    // toStringメソッドをオーバーライド
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < categoryDifficultyList.size(); i++) {
            List<String> row = categoryDifficultyList.get(i);
            sb.append("[").append(i).append("]: ").append(row).append("\n");
        }
        return sb.toString();
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

    // categoryDifficultyList のゲッターとセッター
    public List<List<String>> getCategoryDifficultyList() {
        return categoryDifficultyList;
    }

    public void setCategoryDifficultyList(List<List<String>> categoryDifficultyList) {
        if (categoryDifficultyList.size() != 5) {
            throw new IllegalArgumentException("The list must contain exactly 5 elements.");
        }
        this.categoryDifficultyList = categoryDifficultyList;
    }

    // カテゴリと難易度を設定
    public void setCategoryDifficultyAt(int index, String category, String difficulty) {
        List<String> pair = new ArrayList<>(2);
        pair.add(category);
        pair.add(difficulty);
        this.categoryDifficultyList.set(index, pair);
    }

    // カテゴリと難易度を取得
    public List<String> getCategoryDifficultyAt(int index) {
        return this.categoryDifficultyList.get(index);
    }

	public void setSpecificCategoryDifficulty(int i, String string) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
