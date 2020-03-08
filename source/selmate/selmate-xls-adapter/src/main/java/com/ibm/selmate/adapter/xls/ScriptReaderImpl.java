
package com.ibm.selmate.adapter.xls;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.selmate.adapter.xls.exception.SelmateXLSAdapterException;
import com.ibm.selmate.adapter.xls.util.SelmateConstants;

public class ScriptReaderImpl implements ScriptReader {
	static final Logger logger = Logger.getLogger("reportsLogger");

	public List<ScriptCommand> read(InputStream inputStream) throws SelmateXLSAdapterException {

		try {

			logger.info("Start of read method inside ScriptReaderImpl");

			boolean isBeginFound = false;
			List<ScriptCommand> scriptCommands = new ArrayList<ScriptCommand>();

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			String commandName = null;
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// get the command name from the first column of each row
				if (null != row.getCell(0))
					commandName = row.getCell(0).getStringCellValue();

				if (commandName == null || commandName.trim().equals("")) {
					continue;
				}

				// start execution from BEGIN command and stop execution till
				// END command
				if (!isBeginFound) {
					if (commandName.equalsIgnoreCase(SelmateConstants.BEGIN_COMMAND)) {
						isBeginFound = true;
					} else {
						continue;
					}
				}
				if (commandName.equalsIgnoreCase(SelmateConstants.END_COMMAND)) {
					break;
				}

				ScriptCommandImpl scriptCommand = new ScriptCommandImpl();
				scriptCommand.setName(commandName);

				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// Check the cell type and format accordingly
					switch (cell.getColumnIndex()) {

					case 0:

						break;

					case 1:

						break;

					case 2:

						scriptCommand.setNarration(getCellStringValue(cell));
						break;

					case 3:
						scriptCommand.setVariableName(getCellStringValue(cell));
						break;

					case 4:
						scriptCommand.setLocatorType(getCellStringValue(cell));
						break;

					case 5:
						scriptCommand.setLocatorValue(getCellStringValue(cell));
						break;

					default:
						scriptCommand.addInputValue(getCellStringValue(cell));
						break;
					} // end of switch case

				} // end of while (cellIterator.hasNext()) loop

				scriptCommands.add(scriptCommand);

			}

			// close input stream
			inputStream.close();
			logger.info("End of read method inside ScriptReaderImpl");
			return scriptCommands;

		} catch (Exception ex) {
			throw new SelmateXLSAdapterException(ex);
		}

	}

	public String getCellStringValue(Cell cell) {

		if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
			return "";
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
			return String.valueOf(cell.getErrorCellValue());
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
			return String.valueOf(cell.getCellFormula());
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}
		return null;

	}

}
