
package com.ibm.selmate.util;

public class DriverDetector {

	public static boolean isIEDriver() {
		String value = null;
		return (value = System.getProperty(SelmateConstants.IE_DRIVER_PROPERTY_KEY)) != null
				&& !value.trim().equals("");
	}

	public static boolean isChromeDriver() {
		String value = null;
		return (value = System.getProperty(SelmateConstants.CHROME_DRIVER_PROPERTY_KEY)) != null
				&& !value.trim().equals("");
	}

	public static boolean isFirefoxDriver() {
		String value = null;
		return (value = System.getProperty(SelmateConstants.FIREFOX_DRIVER_PROPERTY_KEY)) != null
				&& !value.trim().equals("");
	}

	public static boolean isFirefoxGekoDriver() {
		String value = null;
		return (value = System.getProperty(SelmateConstants.FIREFOX_GECKO_DRIVER_PROPERTY_KEY)) != null
				&& !value.trim().equals("");
	}

}
