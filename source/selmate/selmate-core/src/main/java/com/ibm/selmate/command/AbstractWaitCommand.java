
package com.ibm.selmate.command;

import org.apache.log4j.Logger;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.util.CommandValidationErrorHandler;

public abstract class AbstractWaitCommand extends AbstractCommand {

	private long timeInterval;

	private Unit unit;

	private Logger logger = Logger.getLogger(AbstractWaitCommand.class);

	public static enum Unit {

		S, M, H;

		public static Unit valueOfStr(String unitStr) {
			if (unitStr.equals("s")) {
				return S;
			} else if (unitStr.equals("m")) {
				return M;
			} else if (unitStr.equals("h")) {
				return H;
			}
			throw new IllegalArgumentException("Invalid value of Time Unit : " + unitStr);
		}
	}

	public long getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(long timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		validatePositiveInteger(errorHandler, "Time Interval", this.timeInterval, selmateContext);
		logger.info("END");
	}

}
