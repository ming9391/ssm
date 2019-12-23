package com.ssm.test;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class MD5 {

	/**
	 * 1、用户名和密码混合加盐加密
	 * 2、加密1次
	 */
	@Test
	public void md5() {
		String algorithmName = "md5";  
		String username = "abc";  
		String password = "123";  
		String salt1 = username;  
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();  
		int hashIterations = 1;  
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);  
		String encodedPassword = hash.toHex();
		System.out.println(encodedPassword);
	}
	
	/**
	 * 1、使用固定字符"abc"进行加盐
	 * 2、加密1次
	 */
	@Test
	public void md5_1(){
		String hashAlgorithmName = "md5";
        String credentials = "123";
        int hashIterations = 1;
        ByteSource salt = ByteSource.Util.bytes("abc");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(obj);
	}
	
	/**
	 * 1、普通md5加密
	 * 2、加密1次
	 */
	@Test
	public void md5_2() {
		String hashAlgorithmName = "md5";
		String password = "123456";
		int hashIterations = 1;
		Object obj = new SimpleHash(hashAlgorithmName, password, null, hashIterations);
		System.out.println(obj);
	}
	
}
