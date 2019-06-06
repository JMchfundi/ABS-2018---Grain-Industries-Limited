package pjp.akidamjaffar.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

public class GEP_PropertiesFile {
	private static FileInputStream fis;
	@SuppressWarnings("rawtypes")
	private static Vector connectionProperties;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getPropetiesFile() {
		final Properties properties = new Properties();
		try {
			fis = new FileInputStream("jdbcconnection.properties");
		} catch (final FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File jdbcconnection.properties do not exist", "FileNotFoundException",
					JOptionPane.ERROR_MESSAGE);
		}

		try {
			properties.load(fis);
		} catch (final IOException e) {
			JOptionPane.showMessageDialog(null, "Internal error with jdbcconnection.properties", "",
					JOptionPane.ERROR_MESSAGE);
		}

		connectionProperties = new Vector();
		connectionProperties.add(properties.getProperty("db_driver").replace("\"", ""));
		connectionProperties.add(properties.getProperty("db_url").replace("\"", ""));
		connectionProperties.add(properties.getProperty("db_user").replace("\"", ""));
		connectionProperties.add(properties.getProperty("db_password").replace("\"", ""));

		return connectionProperties;
	}
}
