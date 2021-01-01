
package com.ibm.selmate.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ibm.selmate.exception.SelmateException;

public class ExecCommandParserUtil {

	public static File getScriptFile(String[] args) throws SelmateException {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--file")) {
				if (args.length >= i + 1) {
					File inputFile = new File(args[i + 1]);
					if (inputFile.exists()) {
						return inputFile;
					} else {
						throw new SelmateException("Invalid File name.");
					}
				}
			}
		}
		throw new SelmateException("File Not Found");
	}

	public static boolean isBulkExecution(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--bulk")) {
				return true;
			}
		}
		return false;
	}

	public static List<File> getScriptFiles(String[] args) throws SelmateException {
		List<File> files = new ArrayList<>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--dir")) {
				if (args.length >= i + 1) {
					String dirPath = args[i + 1];
					File inputDir = new File(dirPath);
					if (inputDir.exists()) {
						File[] fileList = inputDir.listFiles();
						if (fileList.length == 0) {
							throw new SelmateException("No file exist in the directory.");
						}
						for (File file : fileList) {
							files.add(file);
						}
					} else {
						throw new SelmateException("Invalid Directory name.");
					}
				}
			}
		}
		return files;
	}

	public static List<String> getOptions(String[] args) {
		List<String> options = new ArrayList<String>();
		boolean isOptionFound = false;
		for (int i = 0; i < args.length; i++) {
			if (!isOptionFound && args[i].equals("--options")) {
				isOptionFound = true;
			} else if (args[i].startsWith("-")) {
				break;
			} else if (isOptionFound) {
				options.add(args[i]);
			}
		}
		return options;
	}

}
