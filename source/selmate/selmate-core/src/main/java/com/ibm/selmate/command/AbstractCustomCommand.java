
package com.ibm.selmate.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractCustomCommand extends AbstractCommand {

	private List<String> arguments = new ArrayList<String>();

	public void addArgument(String argument) {
		this.arguments.add(argument);
	}

	public void getArgument(int index) {
		this.arguments.get(index);
	}

	public int getArgumentCount() {
		return arguments.size();
	}

	public Iterator<String> iterateArguments() {
		return arguments.iterator();
	}

}
