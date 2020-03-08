
package com.ibm.selmate.client;

import java.io.UnsupportedEncodingException;

import com.ibm.selmate.util.SelmatePwdHandler;

public class SelmatePasswordEncryptor {

	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				System.out.println("Need the password to encrypt");
				System.exit(1);
			}
			String password = args[0];
			String encryptedPassword = SelmatePwdHandler.encrypt(password.getBytes("UTF-8"));
			System.out.println("Encrypted password:");
			System.out.println(encryptedPassword);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
