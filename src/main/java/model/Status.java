package model;

public class Status {
	private String name;
	private int id;
	private int hp;
	private int attack;
	private int defense;
	private int speed;
	private int itemid;
	private String itemEffect;
	private String pass;
	private int dungeonid;
	private int avatarid;

	public int getDungeonid() {
		return dungeonid;
	}

	public void setDungeonid(int dungeonid) {
		this.dungeonid = dungeonid;
	}

	public Status(String name, int id, int hp, int attack, int defense, int speed, int itemid, String itemEffect) {
		this.name = name;
		this.id = id;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.itemid = itemid;
		this.itemEffect = itemEffect;
	}

	public Status(String name, int id, int hp, int attack, int defense, int speed, int itemid, int dungeonid, int avatarid) {
		this.name = name;
		this.id = id;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.itemid = itemid;
		this.dungeonid = dungeonid;
		this.avatarid = avatarid;
	}

	public Status(String name, int id, String pass, int avatarid) {
		this.name = name;
		this.id = id;
		this.pass = pass;
		this.avatarid = avatarid;
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

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
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

	public int getAvatarid() {
		return avatarid;
	}

	public void setAvatarid(int avatarid) {
		this.avatarid = avatarid;
	}

}
