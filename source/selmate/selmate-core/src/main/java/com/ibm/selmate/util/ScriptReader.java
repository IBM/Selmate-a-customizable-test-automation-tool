
package com.ibm.selmate.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.ibm.selmate.exception.SelmateScriptParserException;

public class ScriptReader {

	public static String read(InputStream is) throws SelmateScriptParserException {
		try {
			return read(new InputStreamReader(is, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new SelmateScriptParserException(e);
		}
	}

	public static String read(Reader reader) throws SelmateScriptParserException {
		StringBuilder script = new StringBuilder();
		BufferedReader buffReader = new BufferedReader(reader);
		String line = null;
		try {
			while ((line = buffReader.readLine()) != null) {
				script.append(line);
			}
			return script.toString();
		} catch (IOException e) {
			throw new SelmateScriptParserException(e);
		}
	}
}
