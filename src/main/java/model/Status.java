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
	private String pass;
	

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
	
	public Status(String name, int id, int hp, int attack, int defense, int speed, String item) {
		this.name = name;
		this.id = id;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.item = item;
	}

	public Status(String name,int id,String pass) {
		this.name = name;
		this.id = id;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemEffect() {
		return itemEffect;
	}

	public void setItemEffect(String itemEffect) {
		this.itemEffect = itemEffect;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}

