package model;

import java.io.Serializable;

/*
 *JavaBeans 
 * userテーブルのデータを保持するクラス
*/

public class Account implements Serializable{
	private int accountId;
	
	private String password;
	
	private String hash;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}