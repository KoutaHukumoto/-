package model;

import java.io.Serializable;

/*
*JavaBeans
* userテーブルのデータを保持するクラス
*/

public class character implements Serializable {
	private int characterId;

	private int accountId;

	private String characterName;

	private int userAttack;

	private int userDefense;

	private int userSpeed;

	private int itemId;

	private int dungeonId;

	private int rank;

	public character() {

	}

	public character(String characterName) {
		this.characterName = characterName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getUserAttack() {
		return userAttack;
	}

	public void setUserAttack(int userAttack) {
		this.userAttack = userAttack;
	}

	public int getUserDefense() {
		return userDefense;
	}

	public void setUserDefense(int userDefence) {
		this.userDefense = userDefence;
	}

	public int getUserSpeed() {
		return userSpeed;
	}

	public void setUserSpeed(int userSpeed) {
		this.userSpeed = userSpeed;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getDungeonId() {
		return dungeonId;
	}

	public void setDungeonId(int dungeonId) {
		this.dungeonId = dungeonId;
	}
    @Override
    public String toString() {
        return this.characterName;
    }

}
