package model;

import dao.UserDao;

/*
 * ログイン機能
 */
public class loginLogic {
	
	/*
	 * ログイン認証
	 */
	
	public boolean execute(Account account) {
		UserDao userDao = new UserDao();
		
		boolean isLogin = userDao.findByIdAndPassword(account);
		
		return isLogin;
	}
}
