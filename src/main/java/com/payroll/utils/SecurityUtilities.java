package com.payroll.utils;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class SecurityUtilities {

	private static final String ALGORITHM = "AES";
	// constant used to encrypt or decrypt the text to provide security
	private static final String KEY_ENCRYPT_DESCRYPT = "PAYROLLPTGATEWAY";
	private static final byte[] keyValue = KEY_ENCRYPT_DESCRYPT.getBytes();
	
	public static String encrypt(String valueToEnc) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encValue);
		return encryptedValue;
	}
	
	public static String decrypt(String encryptedValue) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue, 0, decValue.length,"UTF-8");
		return decryptedValue;
	}
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}
	public static void main(String[] args) throws Exception {
		System.out.println("Enc :"+encrypt("neeraj"));	
	System.out.println("Enc :"+decrypt(encrypt("neeraj")));	
	}
}