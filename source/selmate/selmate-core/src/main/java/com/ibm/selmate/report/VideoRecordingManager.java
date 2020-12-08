package com.ibm.selmate.report;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.util.ReportUtil;

import atu.testrecorder.ATUTestRecorder;

public class VideoRecordingManager {

	private static final String REC_DIR_NAME = "RECORDINGS";

	private static VideoRecordingManager instance = new VideoRecordingManager();

	private ATUTestRecorder recorder;

	private Logger logger = Logger.getLogger(VideoRecordingManager.class);

	private VideoRecordingManager() {

	}

	/**
	 * This operation initializes the video recorder.
	 * 
	 * @throws Exception
	 */
	@BeforeTest
	public void setup() throws Exception {
		recorder = new ATUTestRecorder(ReportUtil.createReportDirectory()
				+ File.separator, "RECORDING", false);

		// To start video recording.
		recorder.start();
		logger.info("Recording started...");
	}

	/**
	 * This operation creates the root directory for recording.
	 * 
	 * @return
	 * @throws URISyntaxException
	 * @throws SelmateException
	 */
	private String getRecordingRootDirectory() throws URISyntaxException,
			SelmateException {
		try {
			CodeSource codeSource = VideoRecordingManager.class
					.getProtectionDomain().getCodeSource();
			File rootPath = new File(codeSource.getLocation().toURI().getPath());
			String path = rootPath.getParentFile().getPath();
			String recDirPath = path + File.separator + REC_DIR_NAME;

			File file = new File(recDirPath);
			if (!file.exists()) {
				if (file.mkdirs()) {
					logger.info("Directory is created : " + recDirPath);
				} else {
					logger.fatal("Failed to create directory!. Aborting process.");
					throw new SelmateException(
							"Failed to create directory!. Aborting process.");
				}
			}

			return recDirPath;

		} catch (URISyntaxException e) {
			logger.fatal("Invalid Path", e);
			throw new SelmateException(e);
		}

	}

	/**
	 * This method closes the recorder.
	 * 
	 * @throws Exception
	 */
	@AfterTest
	public void Close() throws Exception {

		// To stop video recording.
		recorder.stop();
		logger.info("Recording stopped...");
	}

	public static final VideoRecordingManager getInstance() {
		return instance;
	}

}
