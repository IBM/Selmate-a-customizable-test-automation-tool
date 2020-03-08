
package com.ibm.selmate.command;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class SwitchToWindowCommand extends AbstractCommand {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * This operation is used to switch the control from parent to child window or
	 * the reverse one. The window name should be used while changing the control to
	 * child window.
	 */
	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");

		Window mainWindow = selmateContext.getMainWindow();
		int windowCount = mainWindow.getDescendantsCount();
		Window currentWindow = getCurrentWindow(mainWindow);

		long trialCount = selmateContext.getImplicitWaitTime() * 1000 / selmateContext.getPollingInterval();
		logger.info("trialCount" + trialCount);

		boolean found = false;
		for (int i = 0; i < trialCount; i++) {
			Set<String> windowHandles = driver.getWindowHandles();
			logger.info("No of Window handles : " + windowHandles.size());
			if (windowHandles.size() > windowCount) {
				for (String handle : windowHandles) {
					if (!selmateContext.isExistingWindowHandle(handle)) {
						switchToNewWindow(driver, currentWindow, handle);
						found = true;
						break;
					}
				}
			} else {
				sleep(selmateContext);
			}
			if (found) {
				break;
			}
		}
		logger.info("END");

		return true;
	}

	private Window getCurrentWindow(Window mainWindow) {
		Window currentWindow = mainWindow;
		while (currentWindow.getChild() != null) {
			currentWindow = currentWindow.getChild();
		}
		return currentWindow;
	}

	private void sleep(SelmateContext selmateContext) throws SelmateExecutionException {
		try {
			Thread.sleep(selmateContext.getPollingInterval());
		} catch (InterruptedException e) {
			throw new SelmateExecutionException(e);
		}
	}

	/**
	 * This operation switches the control to new window specified by the window
	 * handle.
	 * 
	 * @param driver        : {@link WebDriver}
	 * @param currentWindow : {@link Window}
	 * @param handle        : {@link String}
	 */
	private void switchToNewWindow(WebDriver driver, Window currentWindow, String handle) {
		logger.info("Switching to new window..");
		Window newWindow = new Window();
		newWindow.setHandle(handle);
		newWindow.setParent(currentWindow);
		currentWindow.setChild(newWindow);
		driver.switchTo().window(handle);
	}

	@Override
	public String getName() {
		return CommandTypes.SWITCH_WINDOW.name();
	}

}
