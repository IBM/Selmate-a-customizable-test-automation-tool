
package com.ibm.selmate.factory;

import org.openqa.selenium.WebDriver;

import com.ibm.selmate.exception.SelmateExecutionException;

/**
 * This interface represents the WebDriver factory used in Selmate.
 * 
 * @author Avijit Basak
 * 
 */
public interface WebDriverFactory {

	/**
	 * This operation creates an instance of {@link WebDriver}.
	 * 
	 * @return {@link WebDriver}
	 * @throws SelmateExecutionException
	 */
	public WebDriver create() throws SelmateExecutionException;

}
