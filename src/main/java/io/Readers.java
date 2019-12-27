package io;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Readers {
	private final static Logger LOGGER = LoggerFactory.getLogger(Readers.class);
	
	public void strReader(String s) {
		StringReader sr = new StringReader(s);
		StringWriter sw = new StringWriter();
		try {
			int mark;
			char[] charArr = new char[1024];
			while ((mark = sr.read(charArr)) != -1) {
				sw.write(charArr);
			}
			
			System.err.println(sw.toString());
		} catch (IOException ex) {
			LOGGER.error(ExceptionUtils.getStackTrace(ex));
		}
	}
	
	public static void main(String[] args) {
		Readers readers = new Readers();
		readers.strReader("aka");
	}
}
