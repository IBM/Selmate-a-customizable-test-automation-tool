
package com.ibm.selmate.command;

import java.util.Iterator;

import org.openqa.selenium.WebDriver;

import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.exception.SelmateValidationException;

public interface SelmateScript {

	public String getName();

	public void setName(String name);

	public void addCommand(Command command);

	public Iterator<Command> iterateCommand();

	public void validate() throws SelmateValidationException;

	public Boolean execute(WebDriver driver) throws SelmateExecutionException;

}
