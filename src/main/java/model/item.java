
package model;

import java.io.Serializable;

/*
 *JavaBeans 
 * userテーブルのデータを保持するクラス
*/

public class item implements Serializable{
	private int itemId;
	
	private String itemName;
	
	private String description;
	
	private int effectHp;
	
	private int effectAttack;
	
	private int effectdefense;
	
	private int effectspeed;
	
	public item(int itemId, String itemName, int effectHp, int effectAttack, int effectdefense, int effectspeed) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.effectHp = effectHp;
		this.effectAttack = effectAttack;
		this.effectdefense = effectdefense;
		this.effectspeed = effectspeed;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEffectHp() {
		return effectHp;
	}

	public void setEffectHp(int effectHp) {
		this.effectHp = effectHp;
	}

	public int getEffectAttack() {
		return effectAttack;
	}

	public void setEffectAttack(int effectAttack) {
		this.effectAttack = effectAttack;
	}

	public int getEffectdefense() {
		return effectdefense;
	}

	public void setEffectdefense(int effectdefense) {
		this.effectdefense = effectdefense;
	}

	public int getEffectSpeed() {
		return effectspeed;
	}

	public void setEffectSpeed(int effectSpeed) {
		this.effectspeed = effectSpeed;
	}

}
