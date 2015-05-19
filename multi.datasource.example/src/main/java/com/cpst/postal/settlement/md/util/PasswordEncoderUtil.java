package com.cpst.postal.settlement.md.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordEncoderUtil {
	public static void main(String[] args) throws Exception {

		String EncodedPassword = md5Encode("test");
		System.out.println("MD5: " + EncodedPassword);
	}

	public static String md5Encode(String plainPassword) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		// false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true
		// 表示：生成24位的Base64版
		md5.setEncodeHashAsBase64(false);
		String EncodedPassword = md5.encodePassword(plainPassword, null);
		return EncodedPassword;
	}

}
