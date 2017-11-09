package com.yidusoft.utils;


import com.yidusoft.project.system.domain.SecUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
	//private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private static int hashIterations = 2;

	public static void encryptPassword(SecUser secUser) {
		//String salt=randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, secUser.getUserPass(),
				ByteSource.Util.bytes("yidusoft"), hashIterations).toHex();
		//String newPassword = new SimpleHash(algorithmName, secUser.getUserPass(),
		// ByteSource.Util.bytes(secUser.getAccount()), hashIterations).toHex();
		//String newPassword = new SimpleHash(algorithmName, secUser.getUserPass()).toHex();
		secUser.setUserPass(newPassword);

	}
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		SecUser secUser = new SecUser();
		secUser.setAccount("admin");
		secUser.setUserPass("21232f297a57a5a743894a0e4a801fc3");
		passwordHelper.encryptPassword(secUser);
	}

	public static String strToMd5(String str){
		return new SimpleHash(algorithmName, str).toHex();
	}
}
