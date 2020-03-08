
package com.ibm.selmate.adapter.xls;

import java.util.Iterator;

public interface ScriptCommand {

	public String getName();

	public String getNarration();

	public String getVariableName();

	public String getLocatorType();

	public String getLocatorValue();

	public Iterator<String> iterateInputValues();

	public String getInputValue(int index);

	public String getInputValue();

}
