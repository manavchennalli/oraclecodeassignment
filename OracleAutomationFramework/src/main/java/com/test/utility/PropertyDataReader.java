package com.test.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;

/**
 * 
 * @author Manav C K Date: 8 November 2020
 *
 */
public class PropertyDataReader {
	static Properties prop = null;
	static InputStream input = null;
	static String propValue = null;
	static OutputStream output = null;
	static Set<Object> keys = null;

	public static Logger LOG = Logger.getLogger(PropertyDataReader.class.getName());

	/**
	 * Method to get properties value using key
	 * 
	 * @param filename
	 * @param key
	 * @return propValue
	 */
	public static String getProperty(String filename, String key) {
		try {
			prop = new Properties();
			input = PropertyDataReader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				LOG.error("Sorry, Unable to find " + filename);
				return null;
			}
			prop.load(input);
			propValue = prop.getProperty(key);
			input.close();

		} catch (IOException e) {
			LOG.fatal("Exception occured: ", e);
		}
		return propValue;
	}

	@SuppressWarnings("rawtypes")
	public static Set findProperty(String filename) {

		try {
			prop = new Properties();
			input = PropertyDataReader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return keys;
			}
			prop.load(input);
			keys = new TreeSet<Object>(prop.keySet());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return keys;
	}

}
