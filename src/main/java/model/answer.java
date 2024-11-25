package model;

import java.io.Serializable;

public class answer implements Serializable {
	private int questionId;

	private String category;

	private String difficulty;

	private String questionText;

	private String answer;

	private String selected_answer;

	public answer(String questionText, String answer) {
		this.questionText = questionText;
		this.answer = answer;
	}

	public answer(String questionText, String answer, String selected_answer) {
		this.questionText = questionText;
		this.answer = answer;
		this.selected_answer = selected_answer;
	}

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

	public String getSelected_answer() {
		return selected_answer;
	}

	public void setSelected_answer(String selected_answer) {
		this.selected_answer = selected_answer;
	}
}