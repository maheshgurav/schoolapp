package com.samarthsoft.prabandhak.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationLoader {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationLoader.class);

	private static Properties configurationProperties = null;

	public static void loadConfiguration(String fileName) {

		if (configurationProperties == null) {
			try {
				File configFile = getPropertiesFile(fileName);

				if (configFile.exists()) {
					InputStream is = new FileInputStream(configFile);
					configurationProperties = new Properties();
					configurationProperties.load(is);
					decryptProperties(configurationProperties);
				} else {
					LOG.warn(fileName
							+ " file not found in user.home directory");
				}
			} catch (Exception e) {
				configurationProperties = null;
				LOG.error("", e);
			}
		}
	}

	private static File getPropertiesFile(final String fileName) {
		try {
			LOG.debug("Getting properties file: " + fileName);
			String home = System.getProperty("user.home");
			LOG.debug("User home is : " + home);
			File propsFile = new File(home, fileName);

			return propsFile;
		} catch (Exception e) {
			LOG.error("", e);
			return null;
		}
	}

	private static void decryptProperties(final Properties props) {
		try {
			for (Entry<Object, Object> o : configurationProperties.entrySet()) {
				System.setProperty(o.getKey().toString(), o.getValue()
						.toString());
			}
		} catch (Exception e) {
			LOG.error("", e);
		}
	}

}
