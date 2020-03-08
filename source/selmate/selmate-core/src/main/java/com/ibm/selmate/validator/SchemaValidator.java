package com.ibm.selmate.validator;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.ibm.selmate.exception.SelmateValidationException;

public class SchemaValidator {

	private static final String xsdPath = "selmate.xsd";

	private Validator validator;

	private static SchemaValidator instance;

	private SchemaValidator() throws SelmateValidationException {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory
					.newSchema(new StreamSource(this.getClass().getClassLoader().getResourceAsStream(xsdPath)));
			this.validator = schema.newValidator();
		} catch (SAXException e) {
			throw new SelmateValidationException(e);
		}
	}

	public boolean validate(String script) throws SelmateValidationException {
		try {
			this.validator.validate(new StreamSource(new ByteArrayInputStream(script.getBytes("UTF-8"))));
			return true;
		} catch (IOException e) {
			throw new SelmateValidationException("Error in Schema validation.", e);
		} catch (SAXException e) {
			throw new SelmateValidationException("Error in Schema validation.", e);
		}
	}

	public static SchemaValidator getInstance() throws SelmateValidationException {
		if (instance == null) {
			synchronized (SchemaValidator.class) {
				if (instance == null) {
					instance = new SchemaValidator();
				}
			}
		}
		return instance;
	}

}