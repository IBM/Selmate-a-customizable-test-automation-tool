package com.ibm.selmate.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageUtil {

	private Map<String, String> messages = new HashMap<String, String>();

	private static MessageUtil instance = new MessageUtil();

	private MessageUtil() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
		String key = null;
		Enumeration<String> keys = resourceBundle.getKeys();
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			messages.put(key, resourceBundle.getString(key));
		}
	}

	public String getMessage(String key) {
		return messages.get(key);
	}

	public static MessageUtil getInstance() {
		return instance;
	}

}