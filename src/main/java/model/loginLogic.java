package model;

import dao.UserDao;

/*
 * ログイン機能
 */
public class loginLogic {

	/*
	 * ログイン認証
	 */

	public boolean execute(String name, String pass) {
		UserDao userDao = new UserDao();

		boolean isLogin = userDao.find(name, pass);

		return isLogin;
	}
}
