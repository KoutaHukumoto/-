package model;

public class Status {
	private String name;
	private int id;
	private int hp;
	private int attack;
	private int defense;
	private int speed;
	private String item;
	private String itemEffect;

	public Status(String name, int id, int hp, int attack, int defense, int speed, String item, String itemEffect) {
		this.name = name;
		this.id = id;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.item = item;
		this.itemEffect = itemEffect;
	}


	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getHp() {
		return hp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getSpeed() {
		return speed;
	}

	public String getItem() {
		return item;
	}

	public String getItemEffect() {
		return itemEffect;
	}
}
