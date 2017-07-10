package org.ferrari.utils.password;

import org.ferrari.exception.UtilsException;
import org.ferrari.utils.EncryptMd5;

/**
 * 设置密码级别:弱中强, default:强
 * 
 * @author kevin
 * 
 */
public class LevelEntry {
	private String level = Level.LEVEL_ADVANCE;//默认级别：强
	private int length = 8;// 密码默认8位
	private int maxLength = 16;// 密码默认最大16位
	public LevelEntry() {

	}
	public LevelEntry(String level) {
		boolean bflag = Level.LEVEL_LOW.equals(level)
				|| Level.LEVEL_MIDDLE.equals(level)
				|| Level.LEVEL_ADVANCE.equals(level);
		if (level != null && bflag)
			this.level = level;
	}

	public boolean decide(String pwd, int length) {
		if (this.length > length)
			length = this.length;
		if (pwd == null || pwd.length() < length || pwd.length() > maxLength){
			return false;
		}
		return level.equals(pattern(pwd));
	}

	public String pattern(String pwd) {
		if (pwd == null || "".equals(pwd))
			throw new UtilsException("password is null");
		return level(pwd);
	}

	public String level(String pwd) {
		if (pwd.matches(Level.INT_PATTERN))
			return Level.LEVEL_LOW;
		else if (pwd.matches(Level.LETTER_PATTERN))
			return Level.LEVEL_MIDDLE;
		else
			return Level.LEVEL_ADVANCE;
	}
	
	public String random(){
		int n = 19;
		int[] intArr = new int[n];
		int j = 0;
		int number = 100000000;
		while (true) {
			boolean flag = true;
			int a = (int) (Math.random() * number);
			for (int i = 0; i < intArr.length; i++) {
				if (intArr[i] == a) {
					flag = false;
				}
			}
			if (flag && String.valueOf(a).length() == 8 && a > 10000000 && a < 99999999) {
				intArr[j] = a;
				j++;
			}
			if (j > n - 1) {
				break;
			}
		}
		return String.valueOf(intArr[n - 1]);
	}
	/**
	 * MD5加密
	 * @param code 明文
	 * @param length 定义的长度
	 * @return 密文
	 */
	public String securityCode(String code, int length) {
		if (code == null)
			return null;
		boolean f = this.decide(code, length);
		if (f)
			return this.codeMd5(code);
		return null;
	}

	public String codeMd5(String dis) {
		if (dis == null || "".equals(dis))
			return null;
		return new EncryptMd5().getMD5ofStr(dis);
	}
	public static void main(String[] args) {
		LevelEntry lev = new LevelEntry();
		System.out.println(lev.decide("123343222", 10));
		System.out.println(lev.random());
	}
}
