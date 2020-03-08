
package com.ibm.selmate.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class SelmatePwdHandler {

	private static final String keyString = "9e798ea3-67c1-47ae-ac44-9ef6b18796e8";

	public static String encrypt(byte[] content) {
		String encodedEncryptedContent = null;
		try {
			encodedEncryptedContent = encrypt(content, Arrays.copyOf(keyString.getBytes("UTF-8"), 16));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedEncryptedContent;
	}

	public static String encrypt(byte[] content, byte[] key) {
		String encodedEncryptedContent = null;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] encryptedContent = cipher.doFinal(content);
			encodedEncryptedContent = DatatypeConverter.printBase64Binary(encryptedContent);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encodedEncryptedContent;
	}

	public static String decrypt(String encodedContent) {
		return decrypt(encodedContent, Arrays.copyOf(keyString.getBytes(), 16));
	}

	public static String decrypt(String encodedContent, byte[] key) {
		String content = null;
		try {
			byte[] encryptedContent = DatatypeConverter.parseBase64Binary(encodedContent);
			Cipher cipher = Cipher.getInstance("AES");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			content = new String(cipher.doFinal(encryptedContent), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return content;
	}

}
