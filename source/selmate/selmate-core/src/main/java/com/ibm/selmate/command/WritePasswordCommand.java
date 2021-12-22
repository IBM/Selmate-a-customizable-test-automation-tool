
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;
import com.ibm.selmate.util.SelmatePwdHandler;

public class WritePasswordCommand extends AbstractInputCommand {

	private String content;

	private Logger logger = LogManager.getLogger(WriteCommand.class);

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		super.validate(errorHandler, selmateContext);
		validateNullorBlank(errorHandler, "PASSWORD", this.content, selmateContext);
		logger.info("END");
	}

	@Override
	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		WebElement webElement = findElement(driver);
		String decryptedContent = SelmatePwdHandler.decrypt(this.content);
		webElement.clear();
		webElement.sendKeys(decryptedContent);
		logger.info("END");
		return true;
	}

	@Override
	public String getName() {
		return CommandTypes.WRITE_PASSWORD.name();
	}

}
