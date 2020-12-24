package com.ibm.selmate.util;

import java.util.regex.Pattern;

public class SelmateConstants {

	// driver name
	public static final String FIREFOX_DRIVER = "FIREFOX";

	public static final String FIREFOX_GEKO_DRIVER = "FIREFOX_GEKO";

	public static final String CHROME_DRIVER = "CHROME";

	public static final String INTERNET_EXPLORER_DRIVER = "INTERNET EXPLORER";

	// property name
	public static final String CHROME_DRIVER_PROPERTY_KEY = "webdriver.chrome.driver";

	public static final String IE_DRIVER_PROPERTY_KEY = "webdriver.ie.driver";

	public static final String FIREFOX_DRIVER_PROPERTY_KEY = "webdriver.firefox.bin";

	public static final String FIREFOX_GECKO_DRIVER_PROPERTY_KEY = "webdriver.gecko.driver";

	public static final String VALIDATION_ERROR_MSG = "STEP %s, Field Name: %s, Error Message: %s.";

	public static final String NULL_OR_BLANK_ERROR_MSG = "Value NULL or BLANK.";

	public static final String INVALID_VARIABLE_NAME_ERROR_MSG = "Invalid Variable Name. Acceptable pattern : ";

	public static final String NEGATIVE_INTEGER_ERROR_MSG = "Value cannot be negative.";

	public static final String URL_ERROR_MSG = "Invalid URL.";

	public static final String OPTION_ERROR_MSG = "Need to have atleast one option";

	public static final String DRAG_DROP_VARIABLE_NAME = "DRAG_DROP_VARIABLE_NAME";

	public static final String WEB_DRIVER_FACTORY_VM_ARG = "selmate.webdriver.factory";

	public static final String VARIABLE_NAME_PATTERN_STR = "[a-zA-Z_0-9]+";

	public static final String VARIABLE_NAME_REF_PATTERN_STR = "(\\$\\{[a-zA-Z_0-9]+\\})|(\\$[a-zA-Z_0-9]+)";

	public static final Pattern VARIABLE_NAME_PATTERN = Pattern.compile(VARIABLE_NAME_PATTERN_STR);

	public static Pattern VARIABLE_NAME_REF_PATTERN = Pattern.compile(VARIABLE_NAME_REF_PATTERN_STR);

	public static final String HEADLESS = "headless";

}
