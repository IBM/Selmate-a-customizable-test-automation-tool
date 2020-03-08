
package com.ibm.selmate.util;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import com.ibm.selmate.exception.SelmateExecutionException;

public class ImageFileWriter {

	/**
	 * This operation generates an image file.
	 * 
	 */
	public static void writeImage(byte[] imageData, OutputStream os) throws SelmateExecutionException {
		BufferedOutputStream writer = null;
		try {
			writer = new BufferedOutputStream(os);
			writer.write(imageData);
			writer.flush();
		} catch (FileNotFoundException e) {
			throw new SelmateExecutionException(e);
		} catch (IOException e) {
			throw new SelmateExecutionException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new SelmateExecutionException(e);
				}
			}
		}

	}

}
