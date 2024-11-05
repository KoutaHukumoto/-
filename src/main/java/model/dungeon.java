package model;

import java.io.Serializable;

/*
 *JavaBeans 
 * userテーブルのデータを保持するクラス
*/

public class dungeon implements Serializable{
	private int dugeonId;
	
	private int monsterId;
	
	private double reinforcement;
	
	private int bossFlag;

	public int getDugeonId() {
		return dugeonId;
	}

	public void setDugeonId(int dugeonId) {
		this.dugeonId = dugeonId;
	}

	public int getMonsterId() {
		return monsterId;
	}

	public void setMonsterId(int monsterId) {
		this.monsterId = monsterId;
	}

	public double getReinforcement() {
		return reinforcement;
	}

	public void setReinforcement(double reinforcement) {
		this.reinforcement = reinforcement;
	}

	public int getBossFlag() {
		return bossFlag;
	}

	public void setBossFlag(int bossFlag) {
		this.bossFlag = bossFlag;
	}
}