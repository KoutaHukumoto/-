package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * JavaBeans 
 * questionテーブルのデータを保持するクラス
*/

public class question implements Serializable {
    private int questionId;
    private String category;
    private String difficulty;
    private String questionText;
    private String answer;
    private String fakeAnswer1;
    private String fakeAnswer2;
    private String fakeAnswer3;

    // コンストラクタ
    public question(String category, String difficulty, String questionText, String answer, String fakeAnswer1,
            String fakeAnswer2, String fakeAnswer3) {
        this.category = category;
        this.difficulty = difficulty;
        this.questionText = questionText;
        this.answer = answer;
        this.fakeAnswer1 = fakeAnswer1;
        this.fakeAnswer2 = fakeAnswer2;
        this.fakeAnswer3 = fakeAnswer3;
    }

    public question(String category, String difficulty) {
        this.category = category;
        this.difficulty = difficulty;
    }

    // ゲッターとセッター
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFakeAnswer1() {
        return fakeAnswer1;
    }

    public void setFakeAnswer1(String fakeAnswer1) {
        this.fakeAnswer1 = fakeAnswer1;
    }

    public String getFakeAnswer2() {
        return fakeAnswer2;
    }

    public void setFakeAnswer2(String fakeAnswer2) {
        this.fakeAnswer2 = fakeAnswer2;
    }

    public String getFakeAnswer3() {
        return fakeAnswer3;
    }

    public void setFakeAnswer3(String fakeAnswer3) {
        this.fakeAnswer3 = fakeAnswer3;
    }

    /**
     * 選択肢をランダム化して取得するメソッド
     * 
     * @return ランダム化された選択肢のリスト
     */
    public List<String> getShuffledChoices() {
        List<String> choices = new ArrayList<>();
        choices.add(answer);
        choices.add(fakeAnswer1);
        choices.add(fakeAnswer2);
        choices.add(fakeAnswer3);
        Collections.shuffle(choices); // 選択肢をランダムに並べ替え
        return choices;
    }
}
