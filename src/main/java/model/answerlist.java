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
    public answerlist() {
        this.categoryDifficultyList = new ArrayList<>(5); // 容器のサイズを5に設定
        for (int i = 0; i < 5; i++) {
            this.categoryDifficultyList.add(new ArrayList<>(2)); // 各要素を初期化
        }
        // 初期データを設定
        this.setCategoryDifficultyAt(0, "国語", "");
        this.setCategoryDifficultyAt(1, "数学", "");
        this.setCategoryDifficultyAt(2, "英語", "");
        this.setCategoryDifficultyAt(3, "理科", "");
        this.setCategoryDifficultyAt(4, "社会", "");
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
}
