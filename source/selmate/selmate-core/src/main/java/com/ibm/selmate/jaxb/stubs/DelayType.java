//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.30 at 07:53:38 PM IST 
//

package com.ibm.selmate.jaxb.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DelayType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
&lt;simpleType name="DelayType">
  &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
    &lt;enumeration value="IMPLICIT"/>
    &lt;enumeration value="INTERMEDIATE"/>
  &lt;/restriction>
&lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DelayType")
@XmlEnum
public enum DelayType {

	IMPLICIT, INTERMEDIATE;

	public String value() {
		return name();
	}

	public static DelayType fromValue(String v) {
		return valueOf(v);
	}

}
