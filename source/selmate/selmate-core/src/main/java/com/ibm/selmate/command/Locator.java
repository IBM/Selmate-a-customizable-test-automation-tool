
package com.ibm.selmate.command;

/**
 * Locator of elements.
 */
public class Locator {

	public static final String SEPARATOR = ";";

	private Type type;

	private String value;

	public enum Type {
		ID, XPATH, NAME, CLASS_NAME, TAG_NAME, CSS_SELECTOR, LINKTEXT, PARTIAL_LINKTEXT
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
