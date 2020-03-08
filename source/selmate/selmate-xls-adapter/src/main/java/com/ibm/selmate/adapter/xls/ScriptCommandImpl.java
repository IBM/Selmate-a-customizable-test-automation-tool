
package com.ibm.selmate.adapter.xls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScriptCommandImpl implements ScriptCommand {

	private String name;

	private String narration;

	private String variableName;

	private String locatorType;

	private String locatorValue;

	private List<String> inputValues = new ArrayList<String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(String locatorType) {
		this.locatorType = locatorType;
	}

	public String getLocatorValue() {
		return locatorValue;
	}

	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}

	public Iterator<String> iterateInputValues() {
		return inputValues.iterator();
	}

	public void addInputValue(String inputValue) {
		this.inputValues.add(inputValue);
	}

	@Override
	public String getInputValue(int index) {
		if (this.inputValues.size() <= index) {
			return null;
		}
		return this.inputValues.get(index);
	}

	@Override
	public String getInputValue() {
		return getInputValue(0);
	}

}
