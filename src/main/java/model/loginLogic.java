package model;

import dao.UserDao;

/*
 * ログイン機能
 */
public class loginLogic {

	/*
	 * ログイン認証
	 */

	public boolean execute(String id, String pass) {
		UserDao userDao = new UserDao();

		boolean isLogin = userDao.find(id, pass);

		return isLogin;
	}
}
