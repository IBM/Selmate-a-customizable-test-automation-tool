
package com.ibm.selmate.factory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.DriverDetector;
import com.ibm.selmate.util.SelmateConstants;

/**
 * This class represents the default {@link WebDriverFactory} used by Selmate.
 * 
 * @author Avijit Basak
 * 
 */
public class DefaultWebDriverFactory implements WebDriverFactory {

	private static final WebDriverFactory instance = new DefaultWebDriverFactory();

	private Logger logger = Logger.getLogger(DefaultWebDriverFactory.class);

	private DefaultWebDriverFactory() {

	}

	/**
	 * This operation creates an instance of {@link WebDriver} as per the specified
	 * argument.
	 */
	@Override
	public WebDriver create() throws SelmateExecutionException {

		logger.info("START");

		WebDriver driver = null;
		DesiredCapabilities capabilities = null;
		if (DriverDetector.isChromeDriver()) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		} else if (DriverDetector.isFirefoxDriver() || DriverDetector.isFirefoxGekoDriver()) {
			driver = new FirefoxDriver();
		} else if (DriverDetector.isIEDriver()) {
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			driver = new InternetExplorerDriver(capabilities);
		} else {
			throw new SelmateExecutionException(
					"Driver not supported. Should be either of " + SelmateConstants.CHROME_DRIVER + " or "
							+ SelmateConstants.FIREFOX_DRIVER + " or " + SelmateConstants.INTERNET_EXPLORER_DRIVER);
		}

		logger.info("END");

		return driver;
	}

	public static WebDriverFactory getInstance() {
		return instance;
	}

}
