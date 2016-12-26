package com.daoliuhe.sell.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * 加密工具
 * 
 */
public class EncryptUtil {

	public static void main(String[] args) {
		String encode = encode("DHMID@dahuatech2002");
		System.out.println(encode);
		System.out.println(decode("cDtSpGWEyFPCnLClmFmaUw=="));
	}
	
	static BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	
	static {
		textEncryptor.setPassword("dts");
	}
	
	/**
	 * 对文本加密
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		return textEncryptor.encrypt(text);
	}
	
	/**
	 * 对文本解密
	 * @param text
	 * @return
	 */
	public static String decode(String text) {
		return textEncryptor.decrypt(text);
	}

}
