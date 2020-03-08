
package com.ibm.selmate.builder;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public interface SelmateCommandBuilder {

	public Command build(AbstractCommandType abstractCommandType) throws SelmateException;

}
