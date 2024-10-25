package model;

import java.io.Serializable;

/*
 *JavaBeans 
 * userテーブルのデータを保持するクラス
*/

public class character implements Serializable{
	private int characterId;
	
	private int accountId;
	
	private String characterName;
	
	private int userAttack;
	
	private int userDefence;
	
	private int userSpeed;
	
	private int itemId;
	
	private int dungeonId;

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

	public int getUserDefence() {
		return userDefence;
	}

	public void setUserDefence(int userDefence) {
		this.userDefence = userDefence;
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
}