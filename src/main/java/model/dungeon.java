package model;

import java.io.Serializable;

/*
 *JavaBeans 
 * userテーブルのデータを保持するクラス
*/

public class dungeon implements Serializable{
	private int dungeonId;
	
	private int monsterId;
	
	private double reinforcement;
	
	private int bossFlag;

	public dungeon(int dungeonId, int monsterId, double reinforcement, int bossFlag) {
	
		this.dungeonId = dungeonId;
		this.monsterId = monsterId;
		this.reinforcement = reinforcement;
		this.bossFlag = bossFlag;
		
		
	}

	public int getDungeonId() {
		return dungeonId;
	}

	public void setDungeonId(int dugeonId) {
		this.dungeonId = dugeonId;
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
	
	public double getreinforcement() {
		return reinforcement;
		
	}
	
	public void setreinforcement(double reinforcement) {
		this.reinforcement = reinforcement;
	}
	
}