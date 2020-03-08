
package com.ibm.selmate.util;

import java.io.File;

import com.ibm.selmate.SelmateContextAdapter;

public class ReportUtil {

	/**
	 * This operation creates report directory if it does not exist.
	 */
	public static String createReportDirectory() {
		String reportDirectoryPath = "reports" + File.separator
				+ SelmateContextAdapter.getCurrentContext().getScriptName() + File.separator
				+ DateFormatter.format().replace(':', '-');
		File reportDirectory = new File(reportDirectoryPath);
		reportDirectory.mkdirs();
		return reportDirectoryPath;
	}

}
