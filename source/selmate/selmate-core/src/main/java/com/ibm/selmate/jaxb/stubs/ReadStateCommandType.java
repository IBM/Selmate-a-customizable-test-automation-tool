//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.30 at 07:53:38 PM IST 
//

package com.ibm.selmate.jaxb.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * This Complex Type represents a command to read status of an Html Element.
 * 
 * 
 * <p>
 * Java class for ReadStateCommandType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
&lt;complexType name="ReadStateCommandType">
  &lt;complexContent>
    &lt;extension base="{http://www.ibm.com/selmate}AbstractReadCommandType">
      &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
    &lt;/extension>
  &lt;/complexContent>
&lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReadStateCommandType")
public class ReadStateCommandType extends AbstractReadCommandType {

	@XmlAttribute(name = "state")
	@XmlSchemaType(name = "anySimpleType")
	protected String state;

	/**
	 * Gets the value of the state property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setState(String value) {
		this.state = value;
	}

}
