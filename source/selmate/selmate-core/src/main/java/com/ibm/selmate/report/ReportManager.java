package com.ibm.selmate.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.ProcessingInstruction;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.DateFormatter;
import com.ibm.selmate.util.ImageFileWriter;
import com.ibm.selmate.util.ReportUtil;

/**
 * This class manages Selmate Report generation process.
 * 
 * @author Avijit Basak
 * 
 */
public class ReportManager {

	private static ReportManager instance = null;

	private String reportDirectoryPath;

	private Document document;

	/**
	 * This constructor initializes the report generation process.
	 * 
	 * @throws SelmateExecutionException
	 */
	private ReportManager() throws SelmateExecutionException {
		try {
			// createReportDirectory();
			this.reportDirectoryPath = ReportUtil.createReportDirectory();
			cloneFile(
					this.getClass()
							.getClassLoader()
							.getResourceAsStream(
									"selmate-report.xsl"),
					new FileOutputStream(this.reportDirectoryPath
							+ File.separator + "report.xsl"));
			cloneFile(
					this.getClass().getClassLoader()
							.getResourceAsStream("image/selmate-logo.png"),
					new FileOutputStream(this.reportDirectoryPath
							+ File.separator + "selmate-logo.png"));
		} catch (FileNotFoundException e) {
			throw new SelmateExecutionException(e);
		}
	}

	/**
	 * This operation clones one file from {@link InputStream} to
	 * {@link OutputStream}.
	 * 
	 * @param is
	 * @param os
	 * @throws SelmateExecutionException
	 */
	private void cloneFile(InputStream is, OutputStream os)
			throws SelmateExecutionException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(os);
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = bis.read(buffer)) > -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			throw new SelmateExecutionException(e);
		} catch (IOException e) {
			throw new SelmateExecutionException(e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method initializes the report generation process.
	 * 
	 * @param {@link SelmateContext}
	 * 
	 */
	public void init(SelmateContext selmateContext) {
		document = new Document();
		ProcessingInstruction processingInstruction = new ProcessingInstruction(
				"xml-stylesheet");
		Map<String, String> data = new HashMap<String, String>();
		data.put("type", "text/xsl");
		data.put("href", "report.xsl");
		processingInstruction.setData(data);
		document.addContent(processingInstruction);
		Element rootElement = new Element("selmate-report");
		Attribute nameAttribute = new Attribute("name",
				selmateContext.getScriptName());
		Attribute executionTimeAttribute = new Attribute("exec-time",
				DateFormatter.format(new Date()));
		rootElement.setAttribute(nameAttribute);
		rootElement.setAttribute(executionTimeAttribute);
		document.setRootElement(rootElement);
	}

	/**
	 * This operation logs a command execution status into generated report.
	 * 
	 * @param stepNo
	 * @param commandName
	 * @param stepDescription
	 * @param status
	 * @throws SelmateExecutionException
	 */
	public void log(int stepNo, String commandName, String stepDescription,
			String status) throws SelmateExecutionException {
		document.getRootElement().addContent(
				createCommandReportElement(stepNo, commandName,
						stepDescription, status));
	}

	/**
	 * This operation logs a command execution status into generated report.
	 * 
	 * @param stepNo
	 * @param commandName
	 * @param stepDescription
	 * @param status
	 * @param errorDescription
	 * @throws SelmateExecutionException
	 */
	public void log(int stepNo, String commandName, String stepDescription,
			String status, String errorDescription)
			throws SelmateExecutionException {
		document.getRootElement().addContent(
				createCommandReportElement(stepNo, commandName,
						stepDescription, status, errorDescription));
	}

	/**
	 * This operation logs a command execution status into generated report.
	 * 
	 * @param stepNo
	 * @param commandName
	 * @param stepDescription
	 * @param status
	 * @param imageData
	 * @throws SelmateExecutionException
	 */
	public void log(int stepNo, String commandName, String stepDescription,
			String status, byte[] imageData) throws SelmateExecutionException {

		log(stepNo, commandName, stepDescription, status, imageData, null);
	}

	/**
	 * This operation logs a command execution status into generated report.
	 * 
	 * @param stepNo
	 * @param commandName
	 * @param stepDescription
	 * @param status
	 * @param imageData
	 * @param errorDescription
	 * @throws SelmateExecutionException
	 */
	public void log(int stepNo, String commandName, String stepDescription,
			String status, byte[] imageData, String errorDescription)
			throws SelmateExecutionException {
		Element commandReportElement = createCommandReportElement(stepNo,
				commandName, stepDescription, status, errorDescription);
		Element imageSrcElement = createImageElement(stepNo, imageData);
		commandReportElement.addContent(imageSrcElement);
		document.getRootElement().addContent(commandReportElement);
	}

	/**
	 * This operation creates an Image element.
	 * 
	 * @param stepNo
	 * @param imageData
	 * @return
	 * @throws SelmateExecutionException
	 */
	private Element createImageElement(int stepNo, byte[] imageData)
			throws SelmateExecutionException {
		try {
			String fileName = "Img" + stepNo + ".png";
			ImageFileWriter.writeImage(imageData, new FileOutputStream(
					this.reportDirectoryPath + File.separator + fileName));
			Element imageSrcElement = new Element("image-src");
			imageSrcElement.setText(fileName);
			return imageSrcElement;
		} catch (FileNotFoundException e) {
			throw new SelmateExecutionException(e);
		}
	}

	/**
	 * This operation creates a report element for a command.
	 * 
	 * @param stepNo
	 * @param commandName
	 * @param stepDescription
	 * @param status
	 * @param errorDescription
	 * @return
	 */
	private Element createCommandReportElement(int stepNo, String commandName,
			String stepDescription, String status) {
		Element commandReportElement = new Element("command-report");
		Element stepElement = new Element("step");
		stepElement.setText(Integer.toString(stepNo));
		commandReportElement.addContent(stepElement);
		Element commandNameElement = new Element("command-name");
		commandNameElement.setText(commandName);
		commandReportElement.addContent(commandNameElement);
		Element stepDescElement = new Element("step-description");
		stepDescElement.setText(stepDescription);
		commandReportElement.addContent(stepDescElement);
		Element statusElement = new Element("status");
		statusElement.setText(status);
		commandReportElement.addContent(statusElement);

		return commandReportElement;
	}

	/**
	 * This operation creates a report element for a command.
	 * 
	 * @param stepNo
	 * @param commandName
	 * @param stepDescription
	 * @param status
	 * @param errorDescription
	 * @return
	 */
	private Element createCommandReportElement(int stepNo, String commandName,
			String stepDescription, String status, String errorDescription) {
		Element commandReportElement = createCommandReportElement(stepNo,
				commandName, stepDescription, status);
		if (errorDescription != null) {
			Element errorDescElement = new Element("description");
			errorDescElement.setText(errorDescription);
			commandReportElement.addContent(errorDescElement);
		}

		return commandReportElement;
	}

	/**
	 * This operation generates the output report fie and writes to the
	 * designated directory.
	 * 
	 * @throws SelmateExecutionException
	 */
	public void finish() throws SelmateExecutionException {
		try {
			XMLOutputter xmlOutputter = new XMLOutputter();
			xmlOutputter.setFormat(Format.getPrettyFormat());
			xmlOutputter.output(document, new FileWriter(
					this.reportDirectoryPath + "/report.xml"));
		} catch (IOException e) {
			throw new SelmateExecutionException(e);
		}
	}

	public static ReportManager getInstance() throws SelmateExecutionException {
		if (instance == null) {
			synchronized (ReportManager.class) {
				if (instance == null) {
					instance = new ReportManager();
				}
			}
		}
		return instance;
	}

}
