package com.ssm.test;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class MD5 {

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
	
	@Test
	public void md5_1(){
		String hashAlgorithmName = "md5";
        String credentials = "123";
        int hashIterations = 1;
        ByteSource credentialsSalt = ByteSource.Util.bytes("abc");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj);
	}
	
	@Test
	public void md5_2() {
		String hashAlgorithmName = "md5";
		String credentials = "123";
		int hashIterations = 1;
		//ByteSource credentialsSalt = ByteSource.Util.bytes("md");
		Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
		System.out.println(obj);
	}
	
}
