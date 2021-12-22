
package com.ibm.selmate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;
import com.ibm.selmate.command.Window;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.factory.DefaultWebDriverFactory;
import com.ibm.selmate.util.SelmateConstants;

/**
 * This class is responsible for managing WebDriver required to communicate to
 * the browsers.
 * 
 * @author Avijit Basak
 * 
 */
final class WebDriverManager {

	private static ThreadLocal<WebDriverManager> webDriverManager = new ThreadLocal<>();

	private WebDriver waitEnabledDriver;

	private Logger logger = LogManager.getLogger(WebDriverManager.class);

	private WebDriverManager() {
	}

	public static WebDriverManager getInstance() {

		if (webDriverManager.get() == null) {
			webDriverManager.set(new WebDriverManager());
		}
		return webDriverManager.get();
	}

	/**
	 * This operation creates an instance of {@link WebDriver}.
	 * 
	 * @return {@link WebDriver}
	 * @throws SelmateExecutionException
	 */
	public WebDriver create() throws SelmateExecutionException {
		try {
			logger.info("START");
			WebDriver driver = null;
			String factoryName = null;
			if ((factoryName = System.getProperty(SelmateConstants.WEB_DRIVER_FACTORY_VM_ARG)) != null
					&& !factoryName.trim().equals("")) {
				driver = (WebDriver) Class.forName(factoryName).newInstance();
			} else {
				driver = DefaultWebDriverFactory.getInstance().create();
			}
			this.waitEnabledDriver = new WaitEnabledWebDriver(driver);
			logger.info("END");
			return this.waitEnabledDriver;
		} catch (InstantiationException e) {
			throw new SelmateExecutionException(e);
		} catch (IllegalAccessException e) {
			throw new SelmateExecutionException(e);
		} catch (ClassNotFoundException e) {
			throw new SelmateExecutionException(e);
		}
	}

	/**
	 * This operation closes the web driver for main browser window.
	 */
	public void close() {
		Window window = SelmateContextAdapter.getCurrentContext().getMainWindow();
		if (!window.isClosed()) {
			logger.info("Closing Main Window.");
			this.waitEnabledDriver.close();
		} else {
			logger.info("Main Window already closed.");
		}
	}

	/**
	 * This class represents an instance of {@link WebDriver} which works based
	 * on fluent wait and implements {@link WebDriver}, {@link TakesScreenshot},
	 * {@link HasInputDevices} and {@link JavascriptExecutor} interfaces. It
	 * encapsulates a {@link WebDriver}. The lookup for an {@link WebElement}
	 * should wait until a predefined time with periodic polling. The default
	 * wait time is 2 minutes and polling interval is 500 ms. Implementation for
	 * all other operations are delegated to the encapsulated {@link WebDriver}.
	 * 
	 * @author Avijit Basak
	 * 
	 */
	private class WaitEnabledWebDriver
			implements WebDriver, JavascriptExecutor, FindsById, FindsByClassName, FindsByLinkText, FindsByName,
			FindsByCssSelector, FindsByTagName, FindsByXPath, HasInputDevices, HasCapabilities, TakesScreenshot {

		private WebDriver driver;

		private WaitEnabledWebDriver(WebDriver driver) {
			this.driver = driver;
		}

		@Override
		public void close() {

			this.driver.close();
		}

		/**
		 * This operation returns an instance of {@link WebElement} based on the
		 * argument passed as {@link By}. The operation should wait for the
		 * element till it is visible with periodic polling.
		 */
		@Override
		public WebElement findElement(final By by) {
			logger.info("Finding Element by " + by.toString());
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(SelmateContextImpl.getCurrentContext().getImplicitWaitTime(), TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			return wait.until(new Function<WebDriver, WebElement>() {

				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
		}

		/**
		 * This operation returns a {@link List} of {@link WebElement} based on
		 * the argument passed as {@link By}. The operation should wait for the
		 * elements to be visible with periodic polling.
		 */
		@Override
		public List<WebElement> findElements(final By by) {
			logger.info("Finding Elements by " + by.toString());
			SelmateContextImpl selmateContext = SelmateContextImpl.getCurrentContext();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(selmateContext.getImplicitWaitTime(), TimeUnit.SECONDS)
					.pollingEvery(selmateContext.getPollingInterval(), TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class);
			return wait.until(new Function<WebDriver, List<WebElement>>() {

				@Override
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(by);
				}
			});
		}

		@Override
		public void get(String url) {
			this.driver.get(url);
		}

		@Override
		public String getCurrentUrl() {
			return this.driver.getCurrentUrl();
		}

		@Override
		public String getPageSource() {
			return this.driver.getPageSource();
		}

		@Override
		public String getTitle() {
			return this.driver.getTitle();
		}

		@Override
		public String getWindowHandle() {
			return this.driver.getWindowHandle();
		}

		@Override
		public Set<String> getWindowHandles() {
			return this.driver.getWindowHandles();
		}

		@Override
		public Options manage() {
			return this.driver.manage();
		}

		@Override
		public Navigation navigate() {
			return this.driver.navigate();
		}

		@Override
		public void quit() {
			this.driver.quit();
		}

		@Override
		public TargetLocator switchTo() {
			return this.driver.switchTo();
		}

		@Override
		public <X> X getScreenshotAs(OutputType<X> paramOutputType) throws WebDriverException {
			logger.info("START");
			if (TakesScreenshot.class.isAssignableFrom(driver.getClass())) {
				return ((TakesScreenshot) driver).getScreenshotAs(paramOutputType);
			}
			logger.info("END");
			throw new WebDriverException("Operation not Supported");
		}

		@Deprecated
		@Override
		public Keyboard getKeyboard() {
			return ((HasInputDevices) driver).getKeyboard();
		}

		@Deprecated
		@Override
		public Mouse getMouse() {
			return ((HasInputDevices) driver).getMouse();
		}

		@Override
		public Object executeScript(String paramString, Object... paramVarArgs) {
			return ((JavascriptExecutor) driver).executeScript(paramString, paramVarArgs);
		}

		@Override
		public Object executeAsyncScript(String paramString, Object... paramVarArgs) {
			return ((JavascriptExecutor) driver).executeAsyncScript(paramString, paramVarArgs);
		}

		@Override
		public Capabilities getCapabilities() {
			return ((HasCapabilities) driver).getCapabilities();
		}

		@Deprecated
		@Override
		public WebElement findElementByXPath(String paramString) {
			return ((FindsByXPath) driver).findElementByXPath(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsByXPath(String paramString) {
			return ((FindsByXPath) driver).findElementsByXPath(paramString);
		}

		@Deprecated
		@Override
		public WebElement findElementByTagName(String paramString) {
			return ((FindsByTagName) driver).findElementByTagName(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsByTagName(String paramString) {
			return ((FindsByTagName) driver).findElementsByTagName(paramString);
		}

		@Deprecated
		@Override
		public WebElement findElementByCssSelector(String paramString) {
			return ((FindsByCssSelector) driver).findElementByCssSelector(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsByCssSelector(String paramString) {
			return ((FindsByCssSelector) driver).findElementsByCssSelector(paramString);
		}

		@Deprecated
		@Override
		public WebElement findElementByName(String paramString) {
			return ((FindsByName) driver).findElementByName(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsByName(String paramString) {
			return ((FindsByName) driver).findElementsByName(paramString);
		}

		@Deprecated
		@Override
		public WebElement findElementByLinkText(String paramString) {
			return ((FindsByLinkText) driver).findElementByLinkText(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsByLinkText(String paramString) {
			return ((FindsByLinkText) driver).findElementsByLinkText(paramString);
		}

		@Deprecated
		@Override
		public WebElement findElementByPartialLinkText(String paramString) {
			return ((FindsByLinkText) driver).findElementByPartialLinkText(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsByPartialLinkText(String paramString) {
			return ((FindsByLinkText) driver).findElementsByPartialLinkText(paramString);
		}

		@Deprecated
		@Override
		public WebElement findElementByClassName(String paramString) {
			return ((FindsByClassName) driver).findElementByClassName(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsByClassName(String paramString) {
			return ((FindsByClassName) driver).findElementsByClassName(paramString);
		}

		@Deprecated
		@Override
		public WebElement findElementById(String paramString) {
			return ((FindsById) driver).findElementById(paramString);
		}

		@Deprecated
		@Override
		public List<WebElement> findElementsById(String paramString) {
			return ((FindsById) driver).findElementsById(paramString);
		}

	}

}
