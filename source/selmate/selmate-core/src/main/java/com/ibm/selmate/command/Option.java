
package com.ibm.selmate.command;

public class Option {

	private String value;

	private SelectionType selectionType;

	public static enum SelectionType {
		INDEX, VALUE, VISIBLE_TEXT
	}

	public SelectionType getSelectionType() {
		return selectionType;
	}

	public void setSelectionType(SelectionType selectionType) {
		this.selectionType = selectionType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
