
package com.ibm.selmate.util;

import java.io.File;

import com.ibm.selmate.SelmateContextAdapter;

public class ReportUtil {

	private static final ThreadLocal<ReportUtil> instance = new ThreadLocal<>();

	private File reportDirectory;

	private ReportUtil() {
		String reportDirectoryPath = "reports" + File.separator
				+ SelmateContextAdapter.getCurrentContext().getScriptName() + File.separator
				+ DateFormatter.format().replace(':', '-');
		File reportDirectory = new File(reportDirectoryPath);
		this.reportDirectory = reportDirectory;
		reportDirectory.mkdirs();
	}

	/**
	 * This operation creates report directory if it does not exist.
	 */
	public String getReportDirectory() {
		return reportDirectory.getAbsolutePath();
	}

	public static final ReportUtil getInstance() {
		if (instance.get() == null) {
			instance.set(new ReportUtil());
		}
		return instance.get();
	}

}
