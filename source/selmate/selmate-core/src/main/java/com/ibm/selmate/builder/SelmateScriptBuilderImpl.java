
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.ibm.selmate.SelmateScriptImpl;
import com.ibm.selmate.command.AbstractCommand;
import com.ibm.selmate.command.SelmateScript;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.factory.SelmateCommandBuilderFactory;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.Script;

/**
 * This class is responsible for building an instance of {@link SelmateScript}
 * from xml format of Selmate script.
 * 
 * @author Avijit Basak
 * 
 */
public class SelmateScriptBuilderImpl implements SelmateScriptBuilder {

	private Logger logger = LogManager.getLogger(SelmateScriptBuilderImpl.class);

	public SelmateScript build(String xmlScript) throws SelmateExecutionException {

		try {
			logger.info("START");

			SelmateScript script = new SelmateScriptImpl();

			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(xmlScript.getBytes("UTF-8"));
			// unmarshal inputStream
			JAXBContext jaxbContext = JAXBContext.newInstance(Script.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Script executor = (Script) jaxbUnmarshaller.unmarshal(is);
			List<AbstractCommandType> commandList = executor.getCommand();

			logger.info("Iterate all the commands and build the script");

			AbstractCommand command = null;
			SelmateCommandBuilderFactory commandBuilderFactory = SelmateCommandBuilderFactory.getInstance();

			for (AbstractCommandType commandType : commandList) {
				command = (AbstractCommand) commandBuilderFactory.createCommandBuilder(commandType).build(commandType);
				command.setStepDescription(commandType.getStepDescription());
				script.addCommand(command);
			}

			logger.info("END");

			return script;

		} catch (Exception ex) {
			throw new SelmateExecutionException(ex);
		}

	}

}
