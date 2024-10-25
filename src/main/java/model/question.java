package model;

import java.io.Serializable;

/*
 *JavaBeans 
 * userテーブルのデータを保持するクラス
*/

public class question implements Serializable{
	private int questionId;
	
	private String category;
	
	private String difficulty;
	
	private String questionText;
	
	private String answer;
	
	private String fakeAnswer1;
	
	private String fakeAnswer2;
	
	private String fakeAnswer3;

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
}